import model.Product;

import java.util.HashMap;

/**
 * Created by anyang on 2016/7/19.
 */
public interface CalculatorStrategy {

    public double calculate(HashMap<Product, Integer> ProductListWithNumber);
}
