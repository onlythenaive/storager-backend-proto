# Storager Backend Proto
Storager server web application prototype

## Build
```
$ git clone https://github.com/onlythenaive/storager-backend-proto.git
$ cd storager-backend-proto
$ gradle clean build
```

## Deployment
To deploy application at a dedicated server (like Tomcat or Glassfish) use the built artifact:
```
$ cp build/libs/storager-server.war {destination_folder}
```
In this case the application will be started with the default profile: "oracle.deployed".

However, it is also possible to start the application without a dedicated server:
```
$ java -jar build/libs/storager-server.war --profile=oracle.standalone --databaseUrl={connection_url}
```

To start the application with custom profiles set:
```
$ java -jar build/libs/storager-server.war --port=8080 --profile=hsql,bootstrap
```
In this case application will be started on 8080 port with "hsql" and "bootstrap" profiles.

You can also run the application without creating a WAR file:
```
$ gradle bootRun -Dport=8080 -Dprofile=hsql,bootstrap
```

### Parameters
Application can be configured with several parameters:
* "**port**": HTTP port which is used for application deployment, default is "8080"
* "**profile**": deployment profile ["oracle.deployed", "oracle.standalone", "hsql", "bootstrap"], default is "oracle.deployed"

## Frontend Development

To make frontend development more fluent one can use backend mock instead of the real server. Firstly, it requires to install NodeJS dependencies:
```
$ cd frontend
$ npm install
```

After it "mocked" application can be started at the default port (8090):
```
$ npm start
```

You can also specify a custom port, for example:
```
$ npm start -- --port=5000
```
