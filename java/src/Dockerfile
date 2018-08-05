FROM openjdk:8-jre-alpine
WORKDIR ./java/src
ADD ./java/src/build/libs/backend-stateless-0.0.1-SNAPSHOT.jar /usr/src/app/backend-stateless-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend-stateless-0.0.1-SNAPSHOT.jar"]