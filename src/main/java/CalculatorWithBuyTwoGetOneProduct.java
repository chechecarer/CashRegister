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
            price += buyTwoGetOneProductItem.getPrice() * buyTwoGetOneProductListWithNumber.get(buyTwoGetOneProductItem);
        }
        return price;
    }
}
