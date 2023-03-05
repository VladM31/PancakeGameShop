FROM openjdk:17-alpine
COPY target/BotPhoneNumberProdject-0.0.2-SNAPSHOT.jar bot.jar
ADD src/main/resources src/main/resources
ENTRYPOINT ["java","-jar","/bot.jar","--spring.profiles.active=docker"]