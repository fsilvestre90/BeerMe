package com.filipesilvestre.beerme.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipesilvestre.beerme.db.BeerDAO;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.DAOTestRule;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by filipesilvestre on 3/15/17.
 */
public class BeerTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    @Rule
    public DAOTestRule daoTestRule = DAOTestRule.newBuilder()
            .addEntityClass(Beer.class)
            .build();
    private BeerDAO beerDAO;

    // make static to access this from any class
    public static Beer getBeer() {
        return new Beer()
                .setId(10)
                .setBeerName("Test Beer")
                .setBeerStyle("Style")
                .setIbu(5)
                .setBeerAlcPercent(5.0);
    }

    @Before
    public void setUp() {
        beerDAO = new BeerDAO(daoTestRule.getSessionFactory());
    }

    @Test
    public void createPerson() {
        String name = "Allagash";
        String style = "Good";
        int ibu = 5;
        double alcPercent = 5.0;

        final Beer beer = daoTestRule.inTransaction(() -> beerDAO.create(new Beer(name, style, ibu, alcPercent)));
        assertThat(beer.getId()).isGreaterThan(0);
        assertThat(beer.getBeerName()).isEqualTo(name);
        assertThat(beer.getBeerStyle()).isEqualTo(style);
        assertThat(beer.getIbu()).isEqualTo(ibu);
        assertThat(beer.getAlcPercent()).isEqualTo(alcPercent);
        assertThat(beerDAO.findById(beer.getId())).isEqualTo(Optional.of(beer));
    }

    @Test
    public void findAll() {
        String name = "Allagash";
        String style = "Good";
        int ibu = 5;
        double alcPercent = 5.0;

        daoTestRule.inTransaction(() -> {
            beerDAO.create(new Beer(name, style, ibu, alcPercent));
        });

        final List<Beer> beers = beerDAO.findAll();
        assertThat(beers).extracting("beerName").containsOnly(name);
        assertThat(beers).extracting("beerStyle").containsOnly(style);
    }

    @Test(expected = ConstraintViolationException.class)
    public void handlesNullBeerName() {
        daoTestRule.inTransaction(() -> beerDAO.create(new Beer(null, "The null", -5, 3212312)));
    }

    @Test
    public void serializesToJson() throws Exception {
        final Beer beer = getBeer();
        assertEquals(fixture("fixtures/beer.json"), MAPPER.writeValueAsString(beer));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Beer beer = getBeer();
        assertEquals(beer, MAPPER.readValue(fixture("fixtures/beer.json"), Beer.class));
    }
}