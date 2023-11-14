FROM openjdk:11
WORKDIR /app

# Specify the absolute link to the JAR file
ADD http://localhost:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/gestion-station-ski-1.0.jar /app/

ENTRYPOINT ["java", "-jar","gestion-station-ski-1.0.jar"]
EXPOSE 9091
