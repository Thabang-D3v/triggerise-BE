package com.triggerise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @PostMapping//endpoint for checking out items
    public ResponseEntity<CheckOutResponse>checkoutItems(@RequestBody CheckoutDTO dto) {
        CheckOut checkOut=new CheckOut();
        for (String code : dto.getCodes()) {
            checkOut.scan(code);
        }
        return ResponseEntity.ok(CheckOutResponse.builder().totalItems(dto.getCodes()
                .size()).total(checkOut.total()).build());

    }
}
