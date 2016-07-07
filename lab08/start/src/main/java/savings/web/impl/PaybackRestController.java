package savings.web.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import savings.model.PaybackConfirmation;
import savings.model.Purchase;
import savings.service.PaybackBookKeeper;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

// TODO #1 mark this class as a controller component mapped to '/api/payback' url
@Controller
@RequestMapping("/api/payback")
public class PaybackRestController {

    private final PaybackBookKeeper paybackBookKeeper;

    @Autowired
    public PaybackRestController(PaybackBookKeeper paybackBookKeeper) {
        this.paybackBookKeeper = paybackBookKeeper;
    }

    // TODO #3 make this method respond only to POST request and consume and produce `application/json`
    // TODO #4 make this method return HTTP 201 Created response status
    // TODO #5 make this method serialize the returned value into response body
    // TODO #6 make this method deserialize request body into 'purchase' parameter
    @RequestMapping(method = POST/*, consumes = "application/json", produces = "application/json"*/)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PaybackConfirmation create(@RequestBody Purchase purchase) {

        return paybackBookKeeper.registerPaybackFor(purchase);
    }
}
