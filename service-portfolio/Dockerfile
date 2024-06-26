FROM openjdk:22-jdk-slim

WORKDIR /app

COPY ./build/libs/portfolio.jar /app/portfolio.jar

EXPOSE 8080

CMD ["java", "-jar", "portfolio.jar"]
