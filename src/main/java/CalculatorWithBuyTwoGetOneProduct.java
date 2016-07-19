import model.Product;

import java.util.HashMap;

/**
 * Created by anyang on 2016/7/19.
 */
public class CalculatorWithBuyTwoGetOneProduct implements CalculatorStrategy{

    @Override
    public double calculate(HashMap<Product, Integer> buyTwoGetOneProductListWithNumber) {
        double price = 0.0;
        for (Product buyTwoGetOneProductItem : buyTwoGetOneProductListWithNumber.keySet()) {
            // price += buyTwoGetOneProductItem.getPrice() * buyTwoGetOneProductListWithNumber.get(buyTwoGetOneProductItem);
            int productTotalNumber = buyTwoGetOneProductListWithNumber.get(buyTwoGetOneProductItem);
            int product3GroupNumber = productNumber / 3;
            int productOtherNumber = productNumber % 3;
            price += buyTwoGetOneProductItem.getPrice() *(2 * (product3Number) + productOtherNumber) ;
        }
        return price;
    }
}
