FROM maven:3.8.7-openjdk-18

COPY target/jira-1.0.jar ./jira-1.0.jar
COPY resources ./resources

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","/jira-1.0.jar"]


#docker build -t project-final .
#docker run --name jirarush -p 8080:8080 -t project-final
