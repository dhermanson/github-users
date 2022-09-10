package com.github.dhermanson.github.users.rest.v1.services;

import com.github.dhermanson.github.users.rest.v1.resources.User;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
  @Override
  public Optional<User> getUser(String username) {
    throw new RuntimeException("not implemented");
  }
}
