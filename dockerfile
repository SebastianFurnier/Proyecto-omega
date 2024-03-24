FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/Proyecto-omega-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ARG CRED=./credentials.txt
COPY ${CRED} credentials.txt
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080