package com.cadebe.github_reader.service;

import com.cadebe.github_reader.model.Repository;
import com.cadebe.github_reader.model.User;
import org.eclipse.egit.github.core.client.GitHubClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Service {

    List<org.eclipse.egit.github.core.Repository> getAllRepositories(String token) throws IOException;

    User getUser(GitHubClient client) throws IOException;

    int countAllRepositories(GitHubClient client) throws IOException;

    List<Repository> buildRepositoryList(GitHubClient client) throws IOException;

    Map<String, Integer> getAllLanguages(GitHubClient client) throws IOException;

    Map<String, Double> getLanguageFrequencies(GitHubClient client) throws IOException;
}
