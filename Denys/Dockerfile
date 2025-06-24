FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


FROM tomcat:9.0-jdk17

# @TODO Replace Oleksandr-JR-Example.war with your app name
COPY --from=build /app/target/Oleksandr-JR-Example.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
