FROM eclipse-temurin:17-jre-alpine
VOLUME /temp
COPY target/*.jar app.jar
CMD ["java","-jar","app.jar"]
EXPOSE 8080
