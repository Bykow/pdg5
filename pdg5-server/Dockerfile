FROM java:openjdk-8-jre-alpine

COPY target/pdg5-server.jar .
COPY src/main/resources .

ENTRYPOINT ["/usr/bin/java", "-jar", "pdg5-server.jar"]
