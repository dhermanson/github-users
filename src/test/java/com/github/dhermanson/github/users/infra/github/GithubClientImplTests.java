package com.github.dhermanson.github.users.infra.github;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withRawStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.net.URI;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.client.MockRestServiceServer;

@RestClientTest(GithubClientImpl.class)
public class GithubClientImplTests {

  @Autowired
  private MockRestServiceServer server;

  @Autowired
  private GithubClientImpl client;

  @Test
  public void shouldReturnUserWhenFound() {


    // arrange
    var responseBody = """
        {
          "login": "octocat",
          "id": 1,
          "node_id": "MDQ6VXNlcjE=",
          "avatar_url": "https://github.com/images/error/octocat_happy.gif",
          "gravatar_id": "",
          "url": "https://api.github.com/users/octocat",
          "html_url": "https://github.com/octocat",
          "followers_url": "https://api.github.com/users/octocat/followers",
          "following_url": "https://api.github.com/users/octocat/following{/other_user}",
          "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
          "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
          "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
          "organizations_url": "https://api.github.com/users/octocat/orgs",
          "repos_url": "https://api.github.com/users/octocat/repos",
          "events_url": "https://api.github.com/users/octocat/events{/privacy}",
          "received_events_url": "https://api.github.com/users/octocat/received_events",
          "type": "User",
          "site_admin": false,
          "name": "monalisa octocat",
          "company": "GitHub",
          "blog": "https://github.com/blog",
          "location": "San Francisco",
          "email": "octocat@github.com",
          "hireable": false,
          "bio": "There once was...",
          "twitter_username": "monatheoctocat",
          "public_repos": 2,
          "public_gists": 1,
          "followers": 20,
          "following": 0,
          "created_at": "2008-01-14T04:33:35Z",
          "updated_at": "2008-01-14T04:33:35Z"
        }
        """;

    server.expect(requestTo("/users/octocat"))
        .andRespond(withSuccess(responseBody, GithubClientImpl.responseMediaType));

    // act
    var optionalUser = client.getUser("octocat");

    // assert
    assertThat(optionalUser.isPresent()).isTrue();

    var user = optionalUser.get();
    assertThat(user.login()).isEqualTo("octocat");
    assertThat(user.name()).isEqualTo("monalisa octocat");
    assertThat(user.avatarUrl()).isEqualTo(URI.create("https://github.com/images/error/octocat_happy.gif"));
    assertThat(user.location()).isEqualTo("San Francisco");
    assertThat(user.email()).isEqualTo("octocat@github.com");
    assertThat(user.htmlUrl()).isEqualTo(URI.create("https://github.com/octocat"));
    assertThat(user.createdAt()).isEqualTo(Instant.parse("2008-01-14T04:33:35Z"));
  }

  @Test
  public void shouldReturnEmptyValueWhenNoUserFound() {
    // arrange
    server.expect(requestTo("/users/octocat"))
        .andRespond(withRawStatus(404));

    // act
    var optionalUser = client.getUser("octocat");

    // assert
    assertThat(optionalUser.isEmpty()).isTrue();
  }

}
