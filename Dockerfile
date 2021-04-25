FROM gradle:6.8.3-jdk8

ENV APP_HOME=/opt/app/

WORKDIR $APP_HOME

COPY build/libs/wallet-api-0.0.1-SNAPSHOT.jar $APP_HOME/wallet-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "wallet-api.jar"]
