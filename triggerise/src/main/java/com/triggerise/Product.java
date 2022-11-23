package com.triggerise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private String code;
    private String name;
    private double price;

    public boolean isMug(){
        return code.equalsIgnoreCase("MUG");
    }



}
