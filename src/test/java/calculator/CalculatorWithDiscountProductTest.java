package calculator;

import model.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import  static org.mockito.Mockito.*;

import java.util.HashMap;

/**
 * Created by carrie on 2016/7/20.
 */
public class CalculatorWithDiscountProductTest {
    CalculatorWithDiscountProduct calculatorWithDiscountProduct;
    @Before
    public void setUp() throws Exception {
        calculatorWithDiscountProduct = new CalculatorWithDiscountProduct();
    }

    @Test
    public void testCalculatorDiscountPriceWithSingleProduct() throws Exception {
        HashMap<Product, Integer> discountProductListWithNumber = new HashMap<Product, Integer>();
        discountProductListWithNumber.put(createProduct(10.0), 1);

        double price = calculatorWithDiscountProduct.calculate(discountProductListWithNumber);

        assertEquals(9.5, price, 0.01);
    }

    @Test
    public void testCalculatorDiscountPriceWithMultipleProduct() throws Exception {
        HashMap<Product, Integer> discountProductListWithNumber = new HashMap<Product, Integer>();
        discountProductListWithNumber.put(createProduct(1.0), 1);
        discountProductListWithNumber.put(createProduct(5.0), 3);
        discountProductListWithNumber.put(createProduct(10.0),5);

        double price = calculatorWithDiscountProduct.calculate(discountProductListWithNumber);

        assertEquals(62.7, price, 0.01);

    }

    private Product createProduct(double price) {
        Product product = mock(Product.class);
        when(product.getPrice()).thenReturn(price);
        return product;
    }


}
