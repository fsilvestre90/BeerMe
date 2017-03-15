package com.filipesilvestre.beerme.resources;

import com.filipesilvestre.beerme.core.Beer;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link BeerResourceTest}.
 */
public class BeerResourceTest {
//    @ClassRule
//    public static final ResourceTestRule RULE = ResourceTestRule.builder()
//            .addResource(new BeerService())
//            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
//            .build();
//    private static final BeerService BEER_SERVICE = mock(BeerService.class);
//    private Beer beer;
//
//    @Before
//    public void setup() {
//        beer = new Beer(1, "Pliny the Elder", "American Double", 50, 8.00, 1);
//    }
//
//    @After
//    public void tearDown() {
//        reset(BEER_SERVICE);
//    }
//
//    @Test
//    public void getBeerSuccess() {
//        int goodId = 1;
//        when(BEER_SERVICE.getBeerById(goodId)).thenReturn(beer);
//
//        Beer found = RULE.target(String.format("/beer/get/%d", goodId)).request().get(Beer.class);
//
//        assertThat(found.getId()).isEqualTo(beer.getId());
//    }
//
//    @Test
//    public void getBeerNotFound() {
//        int badId = 100;
//        final Response response = RULE.target(String.format("/beer/get/%d", badId)).request().get();
//
//        assertThat(response.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
//    }
//
//    @Test
//    public void getAllBeers() {
//        final List<Beer> beers = BEER_SERVICE.getBeers();
//
//        when(BEER_SERVICE.getBeers()).thenReturn(beers);
//
//        final List<Beer> response = RULE.target("/beer/all")
//                .request().get(new GenericType<List<Beer>>() {
//                });
//
//        assertThat(response).containsAll(beers);
//    }
//
//    @Test
//    public void getStyleNotFound() {
//        String query = "IPA";
//        final List<Beer> response = RULE.target("/beer/style").queryParam("q", query)
//                .request().get(new GenericType<List<Beer>>() {
//                });
//
//        assertThat(response).hasSize(0);
//    }
//
//    @Test
//    public void getStyleFound() {
//        String query = "whitbier";
//
//        final List<Beer> response = RULE.target("/beer/style").queryParam("q", query)
//                .request().get(new GenericType<List<Beer>>() {
//                });
//
//        assertThat(response).hasSize(2);
//    }
//
//    @Test
//    public void addBeer() {
//        Beer newBeer = new Beer(5, "Test Beer", "Sour", 5, 9.0, 1);
//
//        when(BEER_SERVICE.addBeer(newBeer)).thenReturn("Added Beer with id=5");
//
//        Response response = RULE.target("/beer/save").request().post(Entity.json(newBeer), Response.class);
//
//        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
//    }
//
//    @Test
//    public void updateBeer() {
//        Beer newBeer = new Beer(1, "Updated Beer", "Sour", 5, 150.0,1);
//
//        when(BEER_SERVICE.addBeer(newBeer)).thenReturn("Updated Beer with id=5");
//
//        Response response = RULE.target("/beer/save").request().post(Entity.json(newBeer), Response.class);
//        Beer updatedBeer = RULE.target("/beer/get/1").request().get(Beer.class);
//
//        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
//        assertThat(updatedBeer.getBeerName()).isEqualTo("Updated Beer");
//        assertThat(updatedBeer.getAlcPercent()).isEqualTo(150.0);
//    }
}