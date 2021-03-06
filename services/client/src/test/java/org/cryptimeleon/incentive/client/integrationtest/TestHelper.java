package org.cryptimeleon.incentive.client.integrationtest;

import org.cryptimeleon.incentive.client.BasketClient;
import org.cryptimeleon.incentive.client.dto.BasketDto;

import java.util.UUID;


/**
 * Some utility workflows that can be re-used.
 */
public class TestHelper {
    /**
     * Create a basket and return basket object.
     *
     * @param basketUrl url of basket service
     * @return basket object that was added to basket service
     */
    static BasketDto createBasketWithItems(String basketUrl) {
        var basketClient = new BasketClient(basketUrl);

        UUID basketId = basketClient.createBasket().block();
        var items = basketClient.getItems().block();
        var firstTestItem = items[0];
        var secondTestItem = items[1];

        basketClient.putItemToBasket(basketId, firstTestItem.getId(), 3).block();
        basketClient.putItemToBasket(basketId, secondTestItem.getId(), 1).block();

        return basketClient.getBasket(basketId).block();
    }
}
