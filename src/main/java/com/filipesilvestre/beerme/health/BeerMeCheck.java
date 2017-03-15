package com.filipesilvestre.beerme.health;

import com.codahale.metrics.health.HealthCheck;

public class BeerMeCheck extends HealthCheck {
    private final String version;

    public BeerMeCheck(String version) {
        this.version = version;
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy("OK");
    }
}