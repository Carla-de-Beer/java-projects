package com.cadebe.github_reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class User {

    private String userName;
    private String url;

    public User(@JsonProperty("userName") String userName,
                @JsonProperty("webLink") String url) {
        this.userName = userName;
        this.url = url;
    }
}
