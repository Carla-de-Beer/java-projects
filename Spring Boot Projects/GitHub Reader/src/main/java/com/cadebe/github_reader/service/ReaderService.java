package com.cadebe.github_reader.service;

import com.cadebe.github_reader.model.GitHubRepository;
import com.cadebe.github_reader.model.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Service
@Slf4j
public class ReaderService {

    private static final String GITHUB_PREFIX = "https://api.github.com/users/";
    private static final String GITHUB_SUFFIX = "/repos";

    public JsonArray getJsonArray(String name) {
        String nameModified = name.replace(" ", "-");
        String sURL = GITHUB_PREFIX + nameModified + GITHUB_SUFFIX;
        JsonElement root = null;
        try {
            // Connect to the URL using Java's native library
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            // Convert to a JSON object to print data with gson
            JsonParser jp = new JsonParser();
            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            log.error("Could not link to GitHub account '{}'.", e.getMessage());
            throw new RuntimeException();
        }
        assert root != null;
        return root.getAsJsonArray();
    }

    public List<GitHubRepository> getAllRepositories(JsonArray jArray) {
        List<GitHubRepository> repositories = new ArrayList<>();

        for (int i = 0; i < jArray.size(); ++i) {
            JsonElement element = jArray.get(i);

            String name = "";
            if (!element.getAsJsonObject().get("name").toString().equals("null")) {
                name = element.getAsJsonObject().get("name").getAsString();
            }

            String htmlUrl = "";
            if (!element.getAsJsonObject().get("html_url").toString().equals("null")) {
                htmlUrl = element.getAsJsonObject().get("html_url").getAsString();
            }

            String description = "";
            if (!element.getAsJsonObject().get("description").toString().equals("null")) {
                description = element.getAsJsonObject().get("description").getAsString();
            }

            String language = "";
            if (!element.getAsJsonObject().get("language").toString().equals("null")) {
                language = element.getAsJsonObject().get("language").getAsString();
            }

            GitHubRepository repo = GitHubRepository.builder()
                    .repoName(name)
                    .urlLink(htmlUrl)
                    .description(description)
                    .language(language)
                    .build();

            repositories.add(repo);
        }
        return repositories;
    }

    public User getUser(JsonElement element) {
        JsonElement owner = element.getAsJsonObject().get("owner");
        String userName = "";
        String urlUser = "";
        String avatarUrl = "";

        if (!owner.toString().equals("null") && !owner.getAsJsonObject().get("login").toString().equals("null")) {
            userName = owner.getAsJsonObject().get("login").getAsString();
            if (!owner.getAsJsonObject().getAsJsonObject().get("html_url").toString().equals("null")) {
                urlUser = owner.getAsJsonObject().get("html_url").getAsString();
            }

            if (!owner.getAsJsonObject().getAsJsonObject().get("avatar_url").toString().equals("null")) {
                avatarUrl = owner.getAsJsonObject().get("avatar_url").getAsString();
            }
        }

        return User.builder()
                .userName(userName)
                .url(urlUser)
                .avatarUrl(avatarUrl)
                .build();
    }

    public int countAllRepositories(List<GitHubRepository> repositories) {
        return repositories.size();
    }

    public Map<String, Integer> getAllLanguages(List<GitHubRepository> repositories) {
        List<String> languages = new ArrayList<>();
        for (GitHubRepository repository : repositories) {
            languages.add(repository.getLanguage());
        }

        Map<String, Integer> map = new HashMap<>();
        for (String i : languages) {
            Integer j = map.get(i);
            map.put(i, (j == null) ? 1 : j + 1);
        }
        return map;
    }

    public Map<String, Double> getLanguageFrequencies(Map<String, Integer> langMap, int langCount) {
        Map<String, Double> resultMap = new TreeMap<>();
        for (String i : langMap.keySet()) {
            double a = ((double) langMap.get(i) / langCount) * 100;
            a = Math.round(a * 1000.0) / 1000.0;
            resultMap.put(i, a);
        }
        return resultMap;
    }
}

