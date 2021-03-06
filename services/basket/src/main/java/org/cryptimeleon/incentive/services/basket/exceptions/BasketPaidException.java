package org.cryptimeleon.incentive.services.basket.exceptions;

public class BasketPaidException extends BasketServiceException {

    public BasketPaidException() {
        super("Basket's contents cannot be altered since it is already paid or redeemed!");
    }
}
