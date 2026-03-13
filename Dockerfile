FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/secure_employee_system-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar","--spring.profiles.active=docker"]