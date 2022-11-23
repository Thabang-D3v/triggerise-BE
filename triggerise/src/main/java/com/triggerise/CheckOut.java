package com.triggerise;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class CheckOut {
    private final List<Product>mugs=new ArrayList<>();
    private final List<Product>tShirts=new ArrayList<>();
    private final List<Product>keys=new ArrayList<>();
    private String pricingRules;

    public CheckOut (String pricingRules){
        this.pricingRules=pricingRules;
    }
    private  void addItem(String code) throws ItemNotFoundException{
        switch (code){
            case "MUG":{
                mugs.add( Product.builder()
                        .code(code)
                        .name("Triggerise Mug")
                        .price(4).build());
                break;

            }
            case "TSHIRT":{
                tShirts.add(Product.builder()
                        .code(code)
                        .name("Triggerise Tshirt")
                        .price(21).build());
                break;

            }
            case "USBKEY":{
                keys.add(Product.builder()
                        .code(code)
                        .name("Triggerise USB Key")
                        .price(10).build());
                break;

            }
            default: throw new ItemNotFoundException("Product with code not found: "+code);

        }

    }
    public BigDecimal total(){
        double totalPrice=0;
               totalPrice+=mugDiscount();
               totalPrice+=bulkPrice(tShirts);
               totalPrice+=bulkPrice(keys);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        totalPrice=Double.parseDouble(df.format(totalPrice).replace(',','.'));
       return BigDecimal.valueOf(totalPrice);

    }

    public CheckOut scan(String code)throws ItemNotFoundException{
            addItem(code);
            return this;
    }

    private double bulkPrice(List<Product>items){
        if (items.isEmpty())
            return 0;
        return items.size()>=3?items.size()*items.get(0).getPrice()*0.7://price if discount is available
                items.size()*items.get(0).getPrice();//price if discount is not applicable
    }

    private double noDiscount(){//get the no discount amount
        double totalMugs=mugs.isEmpty()?0:mugs.size()*mugs.get(0).getPrice();
        double totalShirts=tShirts.isEmpty()?0:tShirts.size()*tShirts.get(0).getPrice();
        double totalKeys=keys.isEmpty()?0:keys.size()*keys.get(0).getPrice();
        return totalMugs+totalKeys+totalShirts;

    }

    private double mugDiscount(){
        if (!mugs.isEmpty()){
            int size= mugs.size();
            if (size%2==0){
               return mugs.get(0).getPrice()*(size/2);
            }else
                return mugs.get(0).getPrice()*(size/2)+mugs.get(0).getPrice();

        }
        return 0;
    }



}
