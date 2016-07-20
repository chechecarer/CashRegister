package calculator;

import model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by carrie on 2016/7/20.
 */
public class CalculatorWithOriginalPriceProductTest {
    CalculatorWithOriginalPriceProduct calculatorWithOriginalPriceProduct;
    @Before
    public void setUp() throws Exception {
        calculatorWithOriginalPriceProduct = new CalculatorWithOriginalPriceProduct();
    }

    @Test
    public void testCalculatorOriginalPriceWithSingleProduct() throws Exception {
        HashMap<Product, Integer> originalProductListWithNumber = new HashMap<Product, Integer>();
        originalProductListWithNumber.put(createProduct(1.0), 1);

        double price = calculatorWithOriginalPriceProduct.calculate(originalProductListWithNumber);

        assertEquals(1.0, price, 0.01);
    }

    @Test
    public void testCalculatorOriginalPriceWithMultipleProduct() throws Exception {
        HashMap<Product, Integer> originalProductListWithNumber = new HashMap<Product, Integer>();
        originalProductListWithNumber.put(createProduct(1.0), 1);
        originalProductListWithNumber.put(createProduct(5.0), 2);
        originalProductListWithNumber.put(createProduct(10.0), 5);

        double price = calculatorWithOriginalPriceProduct.calculate(originalProductListWithNumber);

        assertEquals(61.0, price, 0.01);
    }

    private Product createProduct(double price) {
        Product product = mock(Product.class);
        when(product.getPrice()).thenReturn(price);
        return product;
    }
}
