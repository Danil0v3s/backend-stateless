FROM openjdk:8-jdk-alpine
WORKDIR /usr/src/app
ADD ./java/src/build/libs/backend*.jar /usr/src/app/backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]