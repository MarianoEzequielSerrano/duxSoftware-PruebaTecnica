FROM openjdk:17
EXPOSE 8080
ADD target/challenge-0.0.1-SNAPSHOT.jar dux-challenge-app.jar
ENTRYPOINT ["java", "-jar", "dux-challenge-app.jar"]