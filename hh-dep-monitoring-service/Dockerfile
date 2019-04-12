FROM openjdk:11.0.3-slim
MAINTAINER AAAAKNgroup

COPY /target/hh-dep-monitoring-service.jar /backend/
RUN apt update
RUN apt install -y wait-for-it

CMD ["java", "-jar", "-DsettingsDir=/backend/configs", "-Xms256m", "-Xmx512m", "/backend/hh-dep-monitoring-service.jar"]

EXPOSE 8080
