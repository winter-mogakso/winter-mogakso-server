FROM openjdk:19-oracle
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${SERVER_MODE}", "-jar", "/app.jar"]