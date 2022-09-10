# Github Users and Repositories
The purpose of this project is to provide a REST API on top of the Github REST API which merges users with their repositories.

## Dependencies

### Required
In order to run this project locally, you must have these dependencies:

- [Docker](https://www.docker.com/) or something equivalent like [Rancher Desktop](https://rancherdesktop.io/)

### Development
Additionally, if you plan to do any development work, you must also have these dependencies:

- [Java 17 JDK](https://adoptium.net/)

### Optional
Here are some optional dependencies

- [curl](https://curl.se/)

  curl is a tool for issuing HTTP requests. There are, of course, other options which you can use instead. This README describes all examples using curl.
  

## Running Locally
To run this application locally, run the following command

```sh
docker-compose up --build
```
