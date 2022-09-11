package com.github.dhermanson.github.users.rest.v1.services;

import com.github.dhermanson.github.users.infra.github.GithubClient;
import com.github.dhermanson.github.users.rest.v1.resources.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

  @Autowired
  private GithubClient githubClient;

  @Override
  @Cacheable("users")
  public Optional<User> getUser(String username) {
    return githubClient.getUser(username)
        .map(githubUser -> {

          return new User(
              githubUser.login(),
              githubUser.name(),
              githubUser.avatarUrl(),
              githubUser.location(),
              githubUser.email(),
              githubUser.htmlUrl(),
              githubUser.createdAt(),
              List.of()
          );
        });

  }
}
