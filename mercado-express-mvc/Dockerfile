# ===================================================================
# Multi-Stage Dockerfile - Mercado Express MVC (Spring Boot 3.3 / Java 21)
# ===================================================================

# 1. Stage de Build com Maven e OpenJDK 21
FROM maven:3.9.8-eclipse-temurin-21-alpine AS builder
WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# 2. Stage de Execução com Alpine JRE e Usuário Não-Root
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Criação de usuário e grupo não-root para segurança corporativa
RUN addgroup -g 10001 -S appgroup && \
    adduser -u 10001 -S appuser -G appgroup

# Cópia do artefato compilado
COPY --from=builder --chown=appuser:appgroup /build/target/mercado-express-mvc-*.jar app.jar

USER appuser:appgroup

ENV PORT=8090
EXPOSE 8090

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
