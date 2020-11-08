FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY pom.xml /desafio/
COPY /desafio-repository /desafio/desafio-repository
COPY /desafio-business /desafio/desafio-business
COPY /desafio-service /desafio/desafio-service
WORKDIR /desafio/
RUN mvn -f /desafio/pom.xml clean package install


FROM openjdk:8-jdk-alpine
COPY --from=MAVEN_BUILD /desafio/desafio-service/target/desafio-service-0.0.1-SNAPSHOT.jar /desafio/
WORKDIR /desafio

ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/desafio/desafio-service-0.0.1-SNAPSHOT.jar"]
