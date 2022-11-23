package com.triggerise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckOutController {

    private CheckOut checkOut;

    @PostMapping("/checkout")
    public ResponseEntity<CheckOutResults> checkout(@RequestBody CheckOutDTO checkOutDTO) throws Exception {
            checkOut=new CheckOut("bulk");
            for (String code : checkOutDTO.getCodes()) {
                checkOut.scan(code);
            }
            CheckOutResults checkOutResults= CheckOutResults.builder()
                    .totalDue(checkOut.total())
                    .totalItems(checkOutDTO.getCodes().size())
                    .build();

        return ResponseEntity.ok(checkOutResults);

    }


}
