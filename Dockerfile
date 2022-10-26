FROM eclipse-temurin:11-jdk
MAINTAINER jossemar.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} customer-microservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/customer-microservice-0.0.1-SNAPSHOT.jar"]