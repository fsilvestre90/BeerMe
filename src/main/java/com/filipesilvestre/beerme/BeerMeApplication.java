package com.filipesilvestre.beerme;

import com.filipesilvestre.beerme.core.Beer;
import com.filipesilvestre.beerme.db.BeerDAO;
import com.filipesilvestre.beerme.resources.BeerService;
import com.filipesilvestre.beerme.resources.BreweryService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BeerMeApplication extends Application<BeerMeConfiguration> {
    public static void main(String[] args) throws Exception {
        new BeerMeApplication().run(args);
    }

    private final HibernateBundle<BeerMeConfiguration> hibernateBundle =
            new HibernateBundle<BeerMeConfiguration>(Beer.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(BeerMeConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public void initialize(Bootstrap<BeerMeConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<BeerMeConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(BeerMeConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(BeerMeConfiguration config, Environment environment) throws Exception {
        final BeerDAO beerDAO = new BeerDAO(hibernateBundle.getSessionFactory());

        final BeerService beerService = new BeerService(beerDAO);
//        final BreweryService breweryService = new BreweryService();

        environment.jersey().register(beerService);
//        environment.jersey().register(breweryService);
    }
}
