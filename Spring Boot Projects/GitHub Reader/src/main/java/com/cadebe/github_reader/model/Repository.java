package com.cadebe.github_reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Repository {

    private String repoName;
    private String urlLink;
    private String description;
    private String language;

    public Repository(@JsonProperty("repoName") String repoName,
                      @JsonProperty("urlLink") String urlLink,
                      @JsonProperty("description") String description,
                      @JsonProperty("language") String language) {
        this.repoName = repoName;
        this.urlLink = urlLink;
        this.description = description;
        this.language = language;
    }
}
