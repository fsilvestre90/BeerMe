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

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link BeerResourceTest}.
 */
@RunWith(MockitoJUnitRunner.class)
public class BeerResourceTest {
    @ClassRule
    public static final ResourceTestRule RULE = ResourceTestRule.builder()
            .addResource(new BeerService())
            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
            .build();
    private static final BeerService BEER_SERVICE = mock(BeerService.class);
    private Beer beer;

    @Before
    public void setup() {
        beer = new Beer(1, "Pliny the Elder", "American Double", 50, 8.00);
    }

    @After
    public void tearDown() {
        reset(BEER_SERVICE);
    }

    @Test
    public void getBeerSuccess() {
        int goodId = 1;
        when(BEER_SERVICE.getBeerById(goodId)).thenReturn(beer);

        Beer found = RULE.target(String.format("/beer/get/%d", goodId)).request().get(Beer.class);

        assertThat(found.getId()).isEqualTo(beer.getId());
    }

    @Test
    public void getBeerNotFound() {
        int badId = 5;
        final Response response = RULE.target(String.format("/beer/get/%d", badId)).request().get();

        assertThat(response.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void getAllBeers() {
        final List<Beer> beers = BEER_SERVICE.getBeers();

        when(BEER_SERVICE.getBeers()).thenReturn(beers);

        final List<Beer> response = RULE.target("/beer/all")
                .request().get(new GenericType<List<Beer>>() {
                });

        assertThat(response).containsAll(beers);
    }
}