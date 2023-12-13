FROM openjdk:11-jdk-slim

EXPOSE 8081
WORKDIR /app

COPY build/libs/server.jar app.jar
COPY ./docker-entrypoint.sh docker-entrypoint.sh

HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD curl --fail --silent http://localhost:8081/actuator/health | jq --exit-status -n 'inputs | if has(\"status\") then .status==\"UP\" else false end' > /dev/null || exit 1

ENTRYPOINT ./docker-entrypoint.sh