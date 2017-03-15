package com.filipesilvestre.beerme.resources;

import com.filipesilvestre.beerme.core.Beer;
import com.filipesilvestre.beerme.core.BeerTest;
import com.filipesilvestre.beerme.core.Brewery;
import com.filipesilvestre.beerme.core.BreweryTest;
import com.filipesilvestre.beerme.db.BreweryDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by filipesilvestre on 3/15/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class BreweryResourceTest {
    private static final BreweryDAO breweryDAO = mock(BreweryDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new BreweryService(breweryDAO))
            .build();

    @Test
    public void getAll() throws Exception {
        List<Brewery> breweries = new ArrayList<>();
        breweries.add(BreweryTest.getBrewery());
        breweries.add(BreweryTest.getBrewery());
        when(breweryDAO.findAll()).thenReturn(breweries);

        List<Brewery> result = resources.client().target("/brewery/all").request().get(new GenericType<List<Brewery>>() {
        });

        assertEquals(2, result.size());
        assertEquals("Test Brewery", result.get(0).getBreweryName());
    }

    @Test
    public void getOne() throws Exception {
        when(breweryDAO.findById(1)).thenReturn(
                java.util.Optional.ofNullable(BreweryTest.getBrewery())
        );

        Brewery brewery = resources.client().target("/brewery/1").request().get(Brewery.class);

        assertEquals(10, brewery.getId());
        assertEquals("Test Brewery", brewery.getBreweryName());
    }

    @Test()
    public void createBrewery() throws Exception {
        Brewery newBrewery = BreweryTest.getBrewery();
        when(breweryDAO.create(any(Brewery.class))).thenReturn(newBrewery);

        Brewery brewery = resources.client().target("/brewery/save")
                .request().post(Entity.json(newBrewery), Brewery.class);

        assertEquals(newBrewery.getBreweryName(), brewery.getBreweryName());
        verify(breweryDAO, times(1)).create(any(Brewery.class));
    }


    @Test()
    public void addBeerToBrewery() throws Exception {
        Response response = resources.client().target("/brewery/")
                .path("addBeer/{id}")
                .resolveTemplate("id", 1)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(BeerTest.getBeer(), MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

}
