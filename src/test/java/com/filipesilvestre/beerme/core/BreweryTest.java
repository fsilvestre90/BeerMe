package com.filipesilvestre.beerme.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipesilvestre.beerme.db.BreweryDAO;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.DAOTestRule;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by filipesilvestre on 3/15/17.
 */
public class BreweryTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    @Rule
    public DAOTestRule daoTestRule = DAOTestRule.newBuilder()
            .addEntityClass(Brewery.class)
            .addEntityClass(Beer.class)
            .build();
    private BreweryDAO BreweryDAO;

    // make static to access this from any class
    public static Brewery getBrewery() {
        return new Brewery()
                .setId(10)
                .setBeerList(new ArrayList<>())
                .setBreweryName("Test Brewery");
    }

    @Before
    public void setUp() {
        BreweryDAO = new BreweryDAO(daoTestRule.getSessionFactory());
    }

    @Test
    public void createBrewery() {
        String name = "Moonlight Corner";

        final Brewery Brewery = daoTestRule.inTransaction(() -> BreweryDAO.create(new Brewery(name)));
        assertThat(Brewery.getId()).isGreaterThan(0);
        assertThat(Brewery.getBreweryName()).isEqualTo(name);
        assertThat(BreweryDAO.findById(Brewery.getId())).isEqualTo(Optional.of(Brewery));
    }

    @Test
    public void findAll() {
        String name = "Best Brews Cruise";

        daoTestRule.inTransaction(() -> {
            BreweryDAO.create(new Brewery(name));
        });

        final List<Brewery> Brewerys = BreweryDAO.findAll();
        assertThat(Brewerys).extracting("breweryName").containsOnly(name);
    }

    @Test(expected = ConstraintViolationException.class)
    public void handlesNullBreweryName() {
        daoTestRule.inTransaction(() -> BreweryDAO.create(new Brewery(null)));
    }

    @Test
    public void serializesToJson() throws Exception {
        final Brewery brewery = getBrewery();
        assertEquals(fixture("fixtures/brewery.json"), MAPPER.writeValueAsString(brewery));
    }



    @Test
    public void deserializesFromJSON() throws Exception {
//        final Brewery brewery = getBrewery();
//        assertEquals(brewery, MAPPER.readValue(fixture("fixtures/brewery.json"), Brewery.class));
    }
}
