package com.cadebe.github_reader.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GitHubRepository {

    String repoName;
    String urlLink;
    String description;
    String language;

    public GitHubRepository(String repoName, String urlLink, String description, String language) {
        this.repoName = repoName;
        this.urlLink = urlLink;
        this.description = description;
        this.language = language;
    }
}
