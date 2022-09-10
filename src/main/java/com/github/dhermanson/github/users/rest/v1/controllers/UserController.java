package com.github.dhermanson.github.users.rest.v1.controllers;

import com.github.dhermanson.github.users.rest.v1.resources.User;
import com.github.dhermanson.github.users.rest.v1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/{username}")
  public ResponseEntity<User> getUser(@PathVariable("username") String username) {
    return userService.getUser(username)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
