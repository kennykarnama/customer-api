FROM maven:3.8.6-openjdk-18-slim

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

ENTRYPOINT ["java", "-jar","target/customer-api-docker.jar"]

EXPOSE 8080
