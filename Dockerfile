FROM eclipse-temurin:17-alpine
RUN mkdir /opt/app
COPY target/parkingspace-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/parkingspace-0.0.1-SNAPSHOT.jar"]