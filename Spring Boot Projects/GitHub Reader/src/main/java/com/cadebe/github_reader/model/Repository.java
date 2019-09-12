package com.cadebe.github_reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Repository {

    String repoName;
    String urlLink;
    String description;
    String language;

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
