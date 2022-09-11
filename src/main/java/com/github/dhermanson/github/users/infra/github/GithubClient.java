package com.github.dhermanson.github.users.infra.github;

import java.util.Optional;

public interface GithubClient {
  Optional<User> getUser(String username);
}
