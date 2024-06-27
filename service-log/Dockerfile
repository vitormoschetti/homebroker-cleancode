FROM openjdk:22-jdk-slim

WORKDIR /app

COPY ./build/libs/service-log.jar /app/service-log.jar

EXPOSE 8080

CMD ["java", "-jar", "service-log.jar"]
