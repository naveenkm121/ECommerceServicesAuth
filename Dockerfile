FROM eclipse-temurin:17-jre-alpine
COPY --from=builder target/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
