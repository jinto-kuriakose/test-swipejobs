# Technical Assignment for Swipejobs
Simple matching engine that presents jobs with appropriate workers
# Getting Started
Make sure that you have Java and Maven installed on your computer.
run below command from project directory. 
> mvn clean install
> java -jar target\job-matcher-1.0-SNAPSHOT.jar

# API Docs
### GET /jobmatcher/{workerId}
This REST API will return a set of matching jobs for the given worker based on his or her Location preference, Driving Licence Requirement and Skill Set
# Testing
mvn test

