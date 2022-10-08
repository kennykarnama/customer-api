FROM maven:3.8.6-openjdk-18-slim

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

ADD target/customer-api-docker.jar customer-api-docker.jar

ENTRYPOINT ["java", "-jar","customer-api-docker.jar"]

EXPOSE 8080
