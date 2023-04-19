#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER eazybytes.com

# Add the application's jar to the container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/accounts-0.0.1-SNAPSHOT.jar"]


#mvn clean install
#docker images
#docker build . -t eazybytes/accounts
#docker images
#docker run -p 8080:8080 eazybytes/accounts
#docker ps
#docker push docker.io/eazybytes/accounts...
#docker-compose up
#docker run -d -p 9411:9411 openzipkin/zipkin