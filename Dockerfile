FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /src

COPY . .

RUN mvn clean package

FROM tomcat:10.0

COPY --from=build /src/target/*.war /usr/local/tomcat/webapps/pvz.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
