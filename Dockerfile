FROM openjdk:17
EXPOSE 5500
COPY target/CourseworkMoneyTransferService-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]