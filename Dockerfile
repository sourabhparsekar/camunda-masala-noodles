FROM openjdk:8-alpine

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

COPY target/camunda-masala-noodles-1.0.0-SNAPSHOT.jar camunda-masala-noodles-1.0.0-SNAPSHOT.jar

EXPOSE 10101

ENTRYPOINT ["java","-jar","camunda-masala-noodles-1.0.0-SNAPSHOT.jar"]
