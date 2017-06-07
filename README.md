# BeerMe

# Introduction

This project is to get familiar with dropwizard. Testing this out for possible analytics project.

# Overview

I used the [Dropwizard](http://www.dropwizard.io/1.0.6/docs/) library to complete this excercise.

How to start the BeerMe application
---

1. Run `mvn clean install` to build your application
1. Create DB with `java -jar target/BeerMe-1.0-SNAPSHOT.jar db migrate config.yml`

1. If you want to skip those, just type `./clean` to kickstart a script which does that for you

1. Start application with `java -jar target/BeerMe-1.0-SNAPSHOT.jar server config.yml`
1. To check that the application is running enter url `http://localhost:9000`

# Endpoints
    GET     /beer/all                    # Get all beers
    GET     /beer/get/{id}               # Get a beer with ID
    DELETE  /beer/remove/{id}            # Remove a beer with ID
    POST    /beer/save                   # Add a new beer
    GET     /beer/style?q={query}        # Filter beer based on a style
    PUT     /brewery/addBeer/{id}        # Add a beer to the brewery
    GET     /brewery/all                 # Get all breweries
    POST    /brewery/save                # Add a new brewery
    GET     /brewery/{id}                # Get a brewery on ID
