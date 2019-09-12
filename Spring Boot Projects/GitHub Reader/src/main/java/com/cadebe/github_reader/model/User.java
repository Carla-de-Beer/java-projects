package com.cadebe.github_reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    String userName;
    String url;

    public User(@JsonProperty("userName") String userName,
                @JsonProperty("webLink") String url) {
        this.userName = userName;
        this.url = url;
    }
}
