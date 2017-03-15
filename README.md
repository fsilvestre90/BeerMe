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
1. Create DB with `java -jar target/BeerMe-1.0-SNAPSHOT.jar db migrate config.yml`
1. Start application with `java -jar target/BeerMe-1.0-SNAPSHOT.jar server config.yml`
1. To check that the application is running enter url `http://localhost:9000`


What I would change

1) I would add a generator for test objects so changes would be propagated ONCE. I changed the model to include breweries and needed to modify the object creation in a few locations
2) More unit tests!
3) More in-depth unit tests!
4) If I had more time I would add features to read/write to database