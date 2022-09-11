package com.github.dhermanson.github.users.rest.v1.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import java.io.Serializable;
import java.net.URI;
import java.time.Instant;
import java.util.List;

public record User(
    String userName,
    String displayName,
    URI avatar,
    String geoLocation,
    String email,
    URI url,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    Instant createdAt,
    List<UserRepo> repos) implements Serializable {
}
