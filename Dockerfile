FROM openjdk:11
ARG NEXUS_URL=http://192.168.43.214:8081/repository/maven-releases/
ARG JAR_FILE=stationSki-1.1.0.jar
ADD ${NEXUS_URL}${JAR_FILE} stationski-1.1.0.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/stationSki-1.1.0.jar"]


