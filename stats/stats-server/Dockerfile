FROM amazoncorretto:21
WORKDIR /app
COPY target/*.jar stats-server.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "stats-server.jar"]