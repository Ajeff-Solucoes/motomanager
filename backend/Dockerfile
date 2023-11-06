FROM adoptopenjdk/openjdk11:alpine
VOLUME /tmp
COPY target/motomanager.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]