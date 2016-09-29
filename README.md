# Storager Backend Proto
Storager server web application prototype

## Build
```
$ git clone https://github.com/onlythenaive/storager-backend-proto.git
$ cd storager-backend-proto
$ gradle clean build
```

## Deployment
To start the application on 8080 port:
```
$ java -jar build/libs/storager-server.jar --port=8080
```
You can also run the application without creating a JAR:
```
$ gradle bootRun -Dport=8080
```

### Parameters
Application can be configured with several parameters:
* "port": HTTP port which is used for application deployment, default is "8080"
* "profile": deployment profile ["production", "dev.local"], default is "production"
