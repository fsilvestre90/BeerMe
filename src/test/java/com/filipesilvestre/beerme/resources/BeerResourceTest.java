package com.filipesilvestre.beerme.resources;

import com.filipesilvestre.beerme.core.Beer;
import com.filipesilvestre.beerme.core.BeerTest;
import com.filipesilvestre.beerme.db.BeerDAO;
import io.dropwizard.testing.junit.ResourceTestRule;

import javax.ws.rs.core.Response;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link BeerResourceTest}.
 */
@RunWith(MockitoJUnitRunner.class)
public class BeerResourceTest {
    private static final BeerDAO beerDAO = mock(BeerDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new BeerService(beerDAO))
            .build();

    @Test
    public void getAll() throws Exception {
        List<Beer> beers = new ArrayList<>();
        beers.add(BeerTest.getBeer());
        beers.add(BeerTest.getBeer());
        when(beerDAO.findAll()).thenReturn(beers);

        List<Beer> result = resources.client().target("/beer/all").request().get(new GenericType<List<Beer>>() {
        });

        assertEquals(2, result.size());
        assertEquals("Test Beer", result.get(0).getBeerName());
    }

    @Test
    public void getOne() throws Exception {
        when(beerDAO.findById(1)).thenReturn(
                java.util.Optional.ofNullable(BeerTest.getBeer())
        );

        Beer beer = resources.client().target("/beer/get/1").request().get(Beer.class);

        assertEquals(10, beer.getId());
        assertEquals("Test Beer", beer.getBeerName());
    }

    @Test
    public void getByNonExistantBeerStyle() throws Exception {
        List<Beer> beer = resources.client().target("/beer/style")
                .queryParam("q", "Whitbier")
                .request()
                .get(new GenericType<List<Beer>>() {});

        assertEquals(0, beer.size());
    }

    @Test()
    public void add() throws Exception {
        Beer newBeer = BeerTest.getBeer();
        when(beerDAO.create(any(Beer.class))).thenReturn(newBeer);

        Beer beer = resources.client().target("/beer/save")
                .request().post(Entity.json(newBeer), Beer.class);

        assertEquals(newBeer.getBeerName(), beer.getBeerName());
        verify(beerDAO, times(1)).create(any(Beer.class));
    }

    @Test()
    public void delete() {
        resources.target("/beer/1").request().delete();
        Response response = resources.client().target("/beer/get/1").request().get(Response.class);
        assertThat(response.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
    }
}