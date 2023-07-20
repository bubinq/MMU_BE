FROM eclipse-temurin:17

LABEL mentainer = "alexanderparpulansky@gmail.com"

WORKDIR /app

COPY target/docconnect-0.0.1-SNAPSHOT.jar /app/docconnect-docker.jar

ENTRYPOINT ["java", "-jar", "docconnect-docker.jar"]
