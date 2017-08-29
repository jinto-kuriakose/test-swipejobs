# test-swipejobs
Simple matching engine that presents jobs with appropriate workers
# Getting Started
Make sure that you have Java and Maven installed on your computer.
run mvn clean install
# API Docs
### GET /jobmatcher/{workerId}
This REST API will return a set of matching jobs for the given worker based on his or her Location preference, Driving Licence Requirement and Skill Set
# Testing
mvn test

