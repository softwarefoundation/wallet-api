FROM gradle:6.8.3-jdk8

ENV APP_HOME=/opt/app/

WORKDIR $APP_HOME

COPY . .

RUN gradle build -x test

RUN cp -R /opt/app/build/libs/* $APP_HOME/wallet-api.jar

RUN gradle clean

EXPOSE $PORT

CMD ["java", "-jar", "-Dspring.profiles.active=${PROFILES_ACTIVE}" , "wallet-api.jar"]
