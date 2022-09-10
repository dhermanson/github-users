package com.github.dhermanson.github.users.rest.v1.services;

import com.github.dhermanson.github.users.rest.v1.resources.User;
import java.util.Optional;

public interface UserService {
  Optional<User> getUser(String username);
}
