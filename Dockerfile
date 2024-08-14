FROM openjdk:17-alpine
VOLUME /tmp
COPY /build/libs/demo-0.0.1-SNAPSHOT.jar health.jar
ENTRYPOINT ["java","-jar","health.jar"]
EXPOSE 8080