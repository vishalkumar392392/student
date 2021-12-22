FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/student-service-0.0.1-SNAPSHOT.jar student-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh", "-c", "java -jar /student-service-0.0.1-SNAPSHOT.jar"]