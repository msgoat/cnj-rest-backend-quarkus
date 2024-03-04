# cnj-rest-backend-quarkus

Cloud native Java backend based on Quarkus using JAX-RS, JSON-B and [MP Rest Client](https://github.com/eclipse/microprofile-rest-client) to expose and consume REST endpoints.

The application is packaged as a multi-architecture docker image which supports the following platforms:
* linux/amd64
* linux/arm64/v8

## Synopsis

Please check [Maven POM](pom.xml) for details on how-to integrate `MicroProfile REST Client`
into your application.

A Microprofile REST client looks like this (see [GrantedPermissionsClient](src/main/java/group/msg/at/cloud/cloudtrain/adapter/rest/grantedpermissions/GrantedPermissionsClient.java) for details:

```java 
@RegisterRestClient(configKey = "cloudtrain.services.grantedpermissions")
@Path("api/v1/grantedPermissions")
public interface GrantedPermissionsClient {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<GrantedPermission> getGrantedPermissionsByCurrentUser();
}
```

It's basically a Java interface annotated with `@RegisterRestClient` exposing all REST endpoints as methods.
All mapping configuration is done using the same well-known annotations like `@Path`, `@GET`, `@Consumes`, `@Produces` etc.
you used on your JAX-RS resource classes.

The attribute `configKey` specifies the properties namespace for HTTP connection parameters like REST endpoint base URL,
connection timeout, response timeout which can be configured using
the `MicroProfile Config` feature:

```properties
cloudtrain.services.grantedpermissions/mp-rest/url=http://downstream:8080
cloudtrain.services.grantedpermissions/mp-rest/connectTimeout=5000
cloudtrain.services.grantedpermissions/mp-rest/readTimeout=5000
```

In order to invoke the REST client you simply inject it into your calling class like this
(see [UserPermissionVerifier](src/main/java/group/msg/at/cloud/cloudtrain/core/control/UserPermissionVerifier.java))

```java
@Dependent
public class UserPermissionVerifier {

    @Inject
    @RestClient
    GrantedPermissionsClient client;

    /* [..] */
}
```

## Status

![Build status](https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiWnpqaWFrZHdnTnc2eWVwOWdIanR0TC9JZFpUKzdjQ1RxblJ3MHBJdkoveWw3bTJLdW1oT2J0emtacmlsQWg1ViswQkpoUG9Ib1I0QmFiUE9LbUZBOHBvPSIsIml2UGFyYW1ldGVyU3BlYyI6IjF0WHdzV3QxRU5CVHNveUciLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=main)

## Release Information

A changelog can be found in [changelog.md](changelog.md).

## Docker Pull Command

`docker pull docker.cloudtrain.aws.msgoat.eu/cloudtrain/cnj-rest-backend-quarkus`

## Helm Pull Command

`helm pull oci://docker.cloudtrain.aws.msgoat.eu/cloudtrain-charts/cnj-rest-backend-quarkus`

## HOW-TO build this application locally

If all prerequisites are met, just run the following Maven command in the project folder:

```shell 
mvn clean verify -P pre-commit-stage
```

Build results: a Docker image containing the showcase application.

## HOW-TO start and stop this showcase locally

In order to run the whole showcase locally, just run the following docker commands in the project folder:

```shell 
docker compose up -d
docker compose logs -f 
```

Press `Ctlr+c` to stop tailing the container logs and run the following docker command to stop the showcase:

```shell 
docker compose down
```

## HOW-TO demo this showcase

The showcase application will be accessible:
* locally via `http://localhost:38080`
* remotely via `https://train2023-dev.k8s.cloudtrain.aws.msgoat.eu/cloudtrain/cnj-rest-backend-quarkus` (if the training cluster is up and running)
