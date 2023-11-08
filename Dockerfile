FROM openjdk:17-alpine

COPY target/service-management-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java","-jar","/app.jar"]