FROM maven:3.5.2-jdk-8-alpine AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk:8-jdk-alpine

#maintainer 
MAINTAINER gyan_cusat07@yahoo.co.in
#expose port 8080
EXPOSE 8080

#default command
CMD java -jar /data/dealerlistings-0.1.0.jar

#copy dealerplisting to docker image from builder image

COPY --from=maven_build /tmp/target/dealerlistings-0.1.0.jar /data/dealerlistings-0.1.0.jar
