# Harbor
[harbor](https://github.com/vmware/harbor) is the enterprise-class registry server for docker distribution.

# harbor-java-client
Harbor Java Client uses Spring boot and okhttpclient to expose Harbor REST API via HarborJavaClient.

# Usage
~~~
/** Constructor
 * public HarborClient(String baseUrl) // default verifySSL is false 
 * public HarborClient(String baseUrl, boolean verifySSL) 
 **/
 
//1) Creat harbor client
HarborClient harborClient = new HarborClient(baseUrl);

//2) login
harborClient.login(username, password);

//3) Call other API

//n) log out
harborClient.logout();

~~~

# Implemented Harbor API 

## Basic
* Login: post /login
* Logout: get  /log_out
* Search: get /search
* Get Logs: get /logs
* Get Statistics: get /statistics

## Project
* Get Projects: get /projects
* head /projects
* post /projects
* put /projects/{project_id}/publicity
* post /projects/{project_id}/logs/filter
* get /projects/{project_id}/members/
* post /projects/{project_id}/members/
* delete /projects/{project_id}/members/{user_id}
* get /projects/{project_id}/members/{user_id}
* put /projects/{project_id}/members/{user_id}

## Users
* post /users
* delete /users/{user_id}

## Repositories API
* get /repositories
* get /repositories/tags
* get /repositories/manifests
* get /repositories/top
* delete /repositories


# Not implemented Harbor API

* get /users
* put /users/{user_id}
* put /users/{user_id}/password
* put /users/{user_id}/sysadmin

# Author
Please concat Grissom(@grissomsh) and Misha(misha913loki) if you have any issue or comment.