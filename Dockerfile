FROM openjdk:11
ARG NEXUS_URL=http://192.168.43.214:8081/#browse/browse:maven-releases:com%2Fexample
ARG JAR_FILE=stationSki-1.1.0.jar
ADD ${NEXUS_URL} stationski-1.1.0.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/stationSki-1.1.0.jar"]
RUN sed -i 's/deb.debian.org/mirrors.ubuntu.com/g' /etc/apt/sources.list
