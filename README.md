# Github Users and Repositories
The purpose of this project is to provide a REST API on top of the Github REST API which merges users with their repositories.

## Dependencies

### Required
In order to run this project locally, you must have these dependencies:

- [Docker](https://www.docker.com/) or something equivalent like [Rancher Desktop](https://rancherdesktop.io/)
- [Java 17 JDK](https://adoptium.net/)

### Optional
Here are some optional dependencies

- [curl](https://curl.se/)

  curl is a tool for issuing HTTP requests. There are, of course, other options which you can use instead. This README describes all examples using curl.
  
  
## Building a docker image
We are using the spring boot gradle plugin's [OCI Image Packing capability](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#build-image).

#### Create a docker image
To build a docker image from this application, run:

```sh
./gradlew bootBuildImage
```

If you wish to override the version, you can do so with a flag:
```sh
./gradlew -Pversion=1.0.3 bootBuildImage
```

## Running Locally using [Docker Compose](https://docs.docker.com/compose/)

After building a docker image via the steps described above, you are now able to run the application, along with its dependencies in a local docker-compose environment with the following command:

```sh
docker-compose up
```

These are the ports which must be available by default:

- `8080` - main spring boot app tomcat web server port
- `8081` - spring boot actuator tomcat web server port


## Interacting with application

### Health Check
```sh
curl localhost:8081/actuator/health
```

### Get User
```sh
curl localhost:8080/api/v1/users/octocat
```
