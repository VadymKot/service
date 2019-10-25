FROM openjdk:8-jdk-alpine
COPY target/containers-demo.jar containers-demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "containers-demo.jar"]