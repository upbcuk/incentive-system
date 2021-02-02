package de.upb.crypto.incentive.services.credit;

import de.upb.crypto.incentive.protocoldefinition.creditearn.CreditResponse;
import de.upb.crypto.incentive.protocoldefinition.creditearn.EarnRequest;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
public class CreditController {

    private CreditService creditService;  // Automatically injects an instance of the service

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        log.debug("Called test function");
        return new ResponseEntity<>("Its working", HttpStatus.OK);
    }

    @GetMapping("/credit")
    @ApiOperation(value = "Credit protocol", notes = "Earn to a token.", response = CreditResponse.class)
    public CreditResponse credit(@Validated EarnRequest request) throws IncentiveException {
        return creditService.handleEarnRequest(request);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IncentiveException.class)
    public String handleIncentiveException(IncentiveException ex) {
        return "An incentive exception occurred!";
    }
}