FROM amazoncorretto:17
WORKDIR /app
COPY build/libs/userms.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]







