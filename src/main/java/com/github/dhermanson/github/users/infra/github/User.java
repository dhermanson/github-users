package com.github.dhermanson.github.users.infra.github;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.net.URI;
import java.time.Instant;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record User(

    String login,

    String name,

    @JsonProperty("avatar_url") // snake-case strategy does not seem to be working
    URI avatarUrl,

    String location,

    String email,

    @JsonProperty("html_url") // snake-case strategy does not seem to be working
    URI htmlUrl,

    @JsonProperty("created_at") // snake-case strategy does not seem to be working
    Instant createdAt
) {
}
