FROM openjdk:8-jdk-alpine

COPY . /tmp

RUN cd /tmp && ./mvnw install -DskipTests

RUN mkdir -p /app

RUN cp /tmp/target/*.jar /app/dockerize-spring-boot.jar

RUN rm -rf /tmp/*

WORKDIR /app

RUN sh -c 'touch dockerize-spring-boot.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","dockerize-spring-boot.jar"]