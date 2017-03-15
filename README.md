# BeerMe

# Introduction

This project is for the Urban Airship interview project.

# Overview

I used the [Dropwizard](http://www.dropwizard.io/1.0.6/docs/) library because

1) I did not want to reinvent the wheel

2) it's features contain many libraries UA uses based on the job description

How to start the BeerMe application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/BeerMe-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
