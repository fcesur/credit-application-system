FROM openjdk:17

WORKDIR /CreditApp

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]



#FROM openjdk:17 AS build
#
#COPY target/CreditApplicationSystem-0.0.1-SNAPSHOT.jar CreditApplicationSystem.jar
#
#ENTRYPOINT ["java","-jar","CreditApplicationSystem.jar"]