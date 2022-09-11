package com.github.dhermanson.github.users.infra.github;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubClientImpl implements GithubClient {

  private static final String rootUri = "https://api.github.com"; // TODO: don't hardcode this
  private static final String githubResponseMediaTypeRoot = "application";
  private static final String githubResponseContentMediaSubtype = "vnd.github+json";
  public static final MediaType responseMediaType = new MediaType(githubResponseMediaTypeRoot, githubResponseContentMediaSubtype);

  private RestTemplate rest;

  @Autowired
  public GithubClientImpl(
      RestTemplateBuilder restTemplateBuilder
  ) {
    rest = restTemplateBuilder
        .rootUri(rootUri)
        .defaultHeader("accept", responseMediaType.toString())
        .build();
  }

  @Override
  public Optional<User> getUser(String username) {
    try {
      var response = rest.getForEntity("/users/%s".formatted(username), User.class);
      return Optional.ofNullable(response.getBody());
    } catch (HttpClientErrorException.NotFound notFound) {
      return Optional.empty();
    }
  }

  @Override
  public List<Repository> getUserRepositories(String username) {
    try {
      var response = rest.getForEntity("/users/%s/repos".formatted(username), Repository[].class);
      var body = response.getBody();
      if (body == null) {
        return Collections.emptyList();
      }
      return Arrays.stream(body).toList();
    } catch (HttpClientErrorException.NotFound notFound) {
      return Collections.emptyList();
    }
  }
}
