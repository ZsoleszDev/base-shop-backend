FROM openjdk:17

COPY target/baseshop-2.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app.jar"]
