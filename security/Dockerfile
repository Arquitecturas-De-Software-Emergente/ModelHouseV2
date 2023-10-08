FROM amazoncorretto:17.0.8
VOLUME /tmp
EXPOSE 8080
ENV HOST='mysql:3306'
ENV NAME_DATABASE=modelhouse
ENV USER_DATABASE=root
ENV PASS_DATABASE='modelhouse##'
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]