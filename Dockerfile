FROM openjdk:17

COPY target/baseshop-1.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app.jar"]