FROM amazoncorretto:11-alpine-jdk

VOLUME /shippingrate

COPY target/middleware-0.0.1-SNAPSHOT.jar middleware.jar

EXPOSE 8096

ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-jar", "/middleware.jar"]