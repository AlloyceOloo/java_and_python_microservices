# wallet-service/Dockerfile
# Stage 1: build
FROM gradle:8-jdk17 AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
# build the single-module project (adjust if your project is multi-module)
RUN gradle clean build --no-daemon -x test

# Stage 2: runtime (use jammy for compatibility on Ubuntu 22)
FROM eclipse-temurin:17-jdk-jammy AS runtime
WORKDIR /app
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar
# helper script to wait for services (included in project)
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
EXPOSE 8080
ENTRYPOINT ["/wait-for.sh", "postgres:5432", "redpanda:9092", "mock-fineract:7000", "--", "java", "-jar", "/app/app.jar"]
