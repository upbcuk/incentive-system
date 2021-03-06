package org.cryptimeleon.incentive.services.credit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class BasketException extends IncentiveException {
    private String errorMessage;
    private HttpStatus httpStatus;
}
