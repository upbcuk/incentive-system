package org.cryptimeleon.incentive.services.basket.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Model class representing a basket.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @ApiModelProperty(value = "${basketModel.basketID}")
    private UUID basketID;
    @ApiModelProperty(value = "${basketModel.items}")
    private Map<UUID, Integer> items;
    @ApiModelProperty(value = "${basketModel.paid}")
    private boolean paid;
    @ApiModelProperty(value = "${basketModel.redeemed}")
    private boolean redeemed;
    @ApiModelProperty(value = "${basketModel.redeemRequest}")
    private String redeemRequest;
    // value must be set manually for serialization
    @ApiModelProperty(value = "${basketModel.value}")
    private long value;

    public Basket(UUID id) {
        basketID = id;
        items = new HashMap<>();
        paid = false;
        redeemed = false;
        redeemRequest = "";
    }
}
