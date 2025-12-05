# wallet-service/Dockerfile (replace entire file)
# Stage 1: build with Gradle
FROM gradle:8-jdk17 AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
# use wrapper if present, otherwise run gradle
RUN gradle clean build --no-daemon -x test

# Stage 2: runtime image
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
# copy jar from builder
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]