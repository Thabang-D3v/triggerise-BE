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

    /**
     *
     * @param code the code of the product being purchased
     * @throws ItemNotFoundException when the code doesn't match any products
     */
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

    /**
     * calculates the total price of all the items being purchased
     * taking into account the 2-for-1 and the 30% discount on the different items
     * @return the value price
     */
    public BigDecimal total(){//calculate the total price of the checkout
        double totalPrice=0;
               totalPrice+=mugDiscount();
               totalPrice+=bulkPrice(tShirts);
               totalPrice+=bulkPrice(keys);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        totalPrice=Double.parseDouble(df.format(totalPrice).replace(',','.'));//round of to two decimals
       return BigDecimal.valueOf(totalPrice);

    }

    /**
     *
     * @param code the code of the item being purchased
     * @return this instance of the class where the calculation is taking place
     * @throws ItemNotFoundException when/if the code doesn't match any products
     */

    public CheckOut scan(String code)throws ItemNotFoundException{//scan the products
            addItem(code);
            return this;
    }

    /**
     * method for calculating the bulk price of the items
     * @param items items to be scanned
     * @return the price of the products being purchased
     */

    private double bulkPrice(List<Product>items){//calculate the bulk price @ 30% discount
        if (items.isEmpty())
            return 0;
        return items.size()>=3?items.size()*items.get(0).getPrice()*0.7://price if discount is available
                items.size()*items.get(0).getPrice();//price if discount is not applicable
    }

    /**
     * method for calculating the 2 for 1 discount for mugs
     * @return the total price of the mugs
     */
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
