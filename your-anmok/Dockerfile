#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=build/libs/your-anmok-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]

FROM openjdk:17-jdk-alpine AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

FROM openjdk:17-jdk-alpine
COPY --from=builder build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "/app.jar"]
