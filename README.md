# Storager Backend Proto
Storager server web application prototype

## Build
```
$ git clone https://github.com/onlythenaive/storager-backend-proto.git
$ cd storager-backend-proto
$ gradle clean build
```

## Deployment
To start the application on 8080 port with "hsql,bootstrap" profile:
```
$ java -jar build/libs/storager-server.war --port=8080 --profile=hsql,bootstrap
```
You can also run the application without creating a WAR file:
```
$ gradle bootRun -Dport=8080 -Dprofile=hsql,bootstrap
```

### Parameters
Application can be configured with several parameters:
* "**port**": HTTP port which is used for application deployment, default is "8080"
* "**profile**": deployment profile ["oracle.deployed", "oracle.standalone", "hsql", "bootstrap"], default is "oracle.deployed"
