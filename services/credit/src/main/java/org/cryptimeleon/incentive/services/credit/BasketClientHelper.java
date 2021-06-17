package org.cryptimeleon.incentive.services.credit;

import org.cryptimeleon.incentive.client.BasketClient;
import org.cryptimeleon.incentive.client.dto.BasketDto;
import org.cryptimeleon.incentive.client.dto.PostRedeemBasketDto;
import org.cryptimeleon.incentive.services.credit.interfaces.BasketClientInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/*
 * Mock for use until basket server is merged into develop
 */
public class BasketClientHelper implements BasketClientInterface {

    Logger logger = LoggerFactory.getLogger(BasketClientHelper.class);

    private String basketUrl;
    private String redeemSecret;
    private BasketClient basketClient;

    public BasketClientHelper(String basketUrl, String redeemSecret) {
        this.redeemSecret = redeemSecret;
        this.basketUrl = basketUrl;
        this.basketClient = new BasketClient(basketUrl);
    }

    @Override
    public BasketDto getBasket(UUID basketId) {
        return basketClient.getBasket(basketId)
                .block();
    }

    @Override
    public void redeem(UUID basketId, String redeemRequestText, long value) {
        var redeemRequest = new PostRedeemBasketDto(basketId, redeemRequestText, value);
        basketClient.redeemBasket(redeemRequest, redeemSecret)
                .block();
    }
}