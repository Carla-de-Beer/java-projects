package com.cadebe.github_reader.service;

import com.cadebe.github_reader.model.User;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import java.io.IOError;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class ReaderService implements com.cadebe.github_reader.service.Service {

    @Override
    public List<Repository> getAllRepositories(String token) throws IOException {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(token);
        RepositoryService service = new RepositoryService(client);
        List<Repository> repositories = service.getRepositories();
        System.out.println(repositories.get(0).getGitUrl());
        return repositories;
    }

    @Override
    public User getUser(GitHubClient client) throws IOException {
        List<Repository> list = getRepositoryList(client);
        String userName = "";
        String url = "";
        try {
            userName = list.get(0).getOwner().getLogin();
            url = list.get(0).getOwner().getHtmlUrl();
        } catch (IOError error) {
            log.error("Could not read repository list to obtain username and URL data.");
        }
        return new User(userName, url);
    }

    @Override
    public int countAllRepositories(GitHubClient client) throws IOException {
        return getRepositoryList(client).size();
    }

    @Override
    public List<com.cadebe.github_reader.model.Repository> buildRepositoryList(GitHubClient client) throws IOException {
        List<Repository> repositories = getRepositoryList(client);
        List<com.cadebe.github_reader.model.Repository> list = new ArrayList<>();

        for (int i = 0; i < repositories.size(); ++i) {
            // log.info("Repo {}, name {}.", i, repositories.get(i).getGitUrl());
            list.add(new com.cadebe.github_reader.model.Repository(
                    repositories.get(i).getName(),
                    repositories.get(i).getUrl(),
                    repositories.get(i).getDescription(),
                    repositories.get(i).getLanguage()
            ));
        }
        return list;
    }

    @Override
    public Map<String, Double> getLanguageFrequencies(GitHubClient client) throws IOException {
        int langCount = getTotalLanguageCount(client);
        Map<String, Integer> langMap = getAllLanguages(client);
        Map<String, Double> resultMap = new TreeMap<>();

        for (String i : langMap.keySet()) {
            double a = ((double) langMap.get(i) / langCount) * 100;
            a = Math.round(a * 1000.0) / 1000.0;
            resultMap.put(i, a);
        }
        return resultMap;
    }

    @Override
    public Map<String, Integer> getAllLanguages(GitHubClient client) throws IOException {
        List<Repository> repositories = getRepositoryList(client);
        List<String> languages = new ArrayList<>();

        for (Repository repository : repositories) {
            languages.add(repository.getLanguage());
        }

        Map<String, Integer> map = new HashMap<>();
        for (String i : languages) {
            Integer j = map.get(i);
            map.put(i, (j == null) ? 1 : j + 1);
        }
        return map;
    }

    private List<Repository> getRepositoryList(GitHubClient client) throws IOException {
        RepositoryService service = new RepositoryService(client);
        return service.getRepositories();
    }

    private int getTotalLanguageCount(GitHubClient client) throws IOException {
        Map<String, Integer> langMap = getAllLanguages(client);
        int count = 0;

        Iterator iter = langMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry pair = (Map.Entry) iter.next();
            count += ((int) pair.getValue());
            iter.remove();
        }
        return count;
    }
}

