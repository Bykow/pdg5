FROM java:openjdk-8-jre-alpine

COPY target/pdg5-server.jar .

ENTRYPOINT ["/usr/bin/java", "-jar", "pdg5-server.jar"]
