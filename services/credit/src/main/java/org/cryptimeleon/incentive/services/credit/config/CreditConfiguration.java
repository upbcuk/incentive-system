package org.cryptimeleon.incentive.services.credit.config;

import org.cryptimeleon.incentive.services.credit.BasketClientHelper;
import org.cryptimeleon.incentive.services.credit.interfaces.CreditInterface;
import org.cryptimeleon.incentive.services.credit.mock.CryptoCreditMock;
import org.cryptimeleon.incentive.services.credit.interfaces.BasketClientInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Set up Beans such that mocks are used until actual implementation is provided
 */
@Configuration
public class CreditConfiguration {

    @Value("${basket-service.url}")
    private String basketUrl;

    @Value("${basket-service.redeem-secret}")
    private String redeemSecret;

    @Bean
    @ConditionalOnMissingBean
    BasketClientInterface basketClientInterface() {
        return new BasketClientHelper(basketUrl, redeemSecret);
    }

    @Bean
    @ConditionalOnMissingBean
    CreditInterface creditInterface() {
        return new CryptoCreditMock();
    }
}