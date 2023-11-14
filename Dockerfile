FROM openjdk:11
WORKDIR /app
EXPOSE 9091
ADD http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/gestion-station-ski-1.0.jar /app/
ENTRYPOINT ["java", "-jar","gestion-station-ski-1.0.jar"]