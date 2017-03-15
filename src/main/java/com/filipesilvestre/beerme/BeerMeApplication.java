package com.filipesilvestre.beerme;

import com.filipesilvestre.beerme.health.BeerMeCheck;
import com.filipesilvestre.beerme.resources.BeerService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class BeerMeApplication extends Application<BeerMeConfiguration> {

    public static void main(String[] args) throws Exception {
        new BeerMeApplication().run(args);
    }

    @Override
    public void run(BeerMeConfiguration config, Environment environment) throws Exception {
        final BeerService beerService = new BeerService();
        environment.jersey().register(beerService);

        final BeerMeCheck healthCheck = new BeerMeCheck(config.getVersion());
        environment.healthChecks().register("beer-health", healthCheck);
        environment.jersey().register(healthCheck);
    }
}
