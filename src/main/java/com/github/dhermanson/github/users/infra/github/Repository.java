package com.github.dhermanson.github.users.infra.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.net.URI;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Repository(
    String name,

    @JsonProperty("html_url") // TODO: snake-case strategy does not seem to be working
    URI htmlUrl
) {
}
