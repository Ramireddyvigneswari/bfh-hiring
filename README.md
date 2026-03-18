# Backend Hiring Task - Spring Boot

This project was developed as part of a backend hiring challenge. It demonstrates API integration, authentication handling, and advanced SQL query processing using Spring Boot.

## Tech Stack
- Java
- Spring Boot
- REST API (RestTemplate)
- SQL

## Features
- Sends POST request to external API with user details
- Generates webhook URL and access token
- Constructs advanced SQL query using JOIN, GROUP BY, and RANK()
- Submits solution securely using Authorization header
- Automates the entire process using CommandLineRunner

##  Description
The application integrates with an external API to generate a webhook and access token. It then processes a complex SQL query to identify the highest-paid employee in each department and submits the result back to the server.

##  Key Concepts Used
- REST API integration
- HTTP request/response handling
- JSON processing
- Authorization headers
- SQL window functions (RANK)

##  How It Works
1. Sends POST request to generate webhook and access token
2. Receives webhook URL and token from API
3. Builds SQL query to find highest salary employees per department
4. Submits query to webhook using Authorization header
5. Receives response from server
   
##  How to Run
1. Clone the repository
2. Open in Eclipse / STS
3. Run as Spring Boot Application



## 👩‍💻 Author
Ramireddy Vigneswari   

##  Author
Ramireddy Vigneswari
