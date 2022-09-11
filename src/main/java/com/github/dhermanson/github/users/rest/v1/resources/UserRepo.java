package com.github.dhermanson.github.users.rest.v1.resources;

import java.io.Serializable;
import java.net.URI;

public record UserRepo(
    String name,
    URI url
) implements Serializable {
}
