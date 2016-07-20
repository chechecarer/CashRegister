package calculator;

import model.Product;

import java.util.HashMap;

/**
 * Created by anyang on 2016/7/19.
 */
public class CalculatorWithBuyTwoGetOneProduct implements CalculatorStrategy{

    public double calculate(HashMap<Product, Integer> buyTwoGetOneProductListWithNumber) {
        double price = 0.0;
        for (Product buyTwoGetOneProductItem : buyTwoGetOneProductListWithNumber.keySet()) {
            int productNumber = buyTwoGetOneProductListWithNumber.get(buyTwoGetOneProductItem);
            int productGroupNumberWithThreeItems = productNumber / 3 ;
            int productOtherNumber = productNumber % 3;
            price += buyTwoGetOneProductItem.getPrice() * (2 * productGroupNumberWithThreeItems + productOtherNumber);
        }
        return price;
    }
}
