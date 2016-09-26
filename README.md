# Storager Backend Proto
Storager server web application prototype

## Build
```
$ git clone https://github.com/onlythenaive/storager-backend-proto.git
$ cd storager-backend-proto
$ gradle clean build
```

## Deployment
To start the application at the default (8080) port:
```
$ java -jar build/libs/storager-server.jar
```
You can also run the application without creating a JAR:
```
$ gradle bootRun
```
