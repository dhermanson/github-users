package com.github.dhermanson.github.users.rest.v1.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.dhermanson.github.users.rest.v1.resources.User;
import com.github.dhermanson.github.users.rest.v1.resources.UserRepo;
import com.github.dhermanson.github.users.rest.v1.services.UserService;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTests {

  @MockBean
  UserService userService;

  @Autowired
  MockMvc mockMvc;

  @Test
  public void returnUserWhenFound() throws Exception {

    var repo0 = new UserRepo("repo-1", URI.create("https://google.com"));
    var repo1 = new UserRepo("repo-2", URI.create("https://yahoo.com"));
    var user = new User("derick",
        "display name",
        URI.create("https://example.com/avatar"),
        "Fargo",
        "test@example.com",
        URI.create("https://example.com/url"),
        Instant.parse("2022-09-10T17:50:19.933Z"),
        List.of(repo0, repo1)
    );

    Mockito.when(userService.getUser(user.userName()))
        .thenReturn(Optional.of(user));

    mockMvc.perform(get("/api/v1/users/%s".formatted(user.userName())))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.user_name", is(user.userName())))
        .andExpect(jsonPath("$.display_name", is(user.displayName())))
        .andExpect(jsonPath("$.avatar", is(user.avatar().toString())))
        .andExpect(jsonPath("$.geo_location", is(user.geoLocation())))
        .andExpect(jsonPath("$.email", is(user.email())))
        .andExpect(jsonPath("$.url", is(user.url().toString())))
        .andExpect(jsonPath("$.created_at", is("2022-09-10 17:50:19")))
        .andExpect(jsonPath("$.repos", hasSize(2)))
        .andExpect(jsonPath("$.repos[0].name", is(repo0.name())))
        .andExpect(jsonPath("$.repos[0].url", is(repo0.url().toString())))
        .andExpect(jsonPath("$.repos[1].name", is(repo1.name())))
        .andExpect(jsonPath("$.repos[1].url", is(repo1.url().toString())))
    ;

  }

  @Test
  public void return404WhenNotFound() throws Exception {
    var username = "baduser";

    Mockito.when(userService.getUser(username))
        .thenReturn(Optional.empty());

    mockMvc.perform(get("/api/v1/users/%s".formatted(username)))
        .andExpect(status().isNotFound());
  }

}
