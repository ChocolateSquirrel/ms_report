FROM openjdk:8
EXPOSE 8080
COPY target/ms_report-2.6.0.jar report.jar
ENTRYPOINT ["java", "-jar", "/report.jar"]

