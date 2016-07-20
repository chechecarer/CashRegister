package printer;

import discount.DefaultDiscount;
import model.Product;

import java.util.HashMap;

/**
 * Created by anyang on 2016/7/18.
 */
public class ReceiptPrinter {

    public String printTitle() {
        return "***<没钱赚商店>购物清单***\n";
    }

    private String printEachProductWithOriginalPrice(Product product, int number) {
        return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)\n",
                product.getName(),
                number,product.getUnit(),
                product.getPrice(),
                product.getPrice() * number);
    }

    private String printEachProductWithDiscount(Product product, int number) {
        double originalPrice = product.getPrice() * number;
        double discountedPrice = originalPrice * DefaultDiscount.DISCOUNT;
        return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元), 节省：%1.2f(元)\n",
                product.getName(),
                number,product.getUnit(),
                product.getPrice(),
                discountedPrice,
                originalPrice - discountedPrice);
    }

    private String printEachProductWithBuyTwoGetOne(Product product, int number) {
        int productGroupNumberWithThreeItems = number / 3 ;
        int productOtherNumber = number % 3;
        double price = product.getPrice() * (2 * productGroupNumberWithThreeItems + productOtherNumber);
        return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)\n",
                product.getName(),
                number,product.getUnit(),
                product.getPrice(),
                price);
    }

    public String printBuyTwoGetOneProduct(HashMap<Product, Integer> buyTwoGetOneProductListWithNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("******************************\n");
        stringBuilder.append("买二赠一商品：\n");
        for(Product product : buyTwoGetOneProductListWithNumber.keySet()) {
            stringBuilder.append( String.format("名称：%s，数量：%d%s\n", product.getName(),
                    buyTwoGetOneProductListWithNumber.get(product), product.getUnit()));
        }
        return stringBuilder.toString();
    }

    public String printMultipleProductWithOriginalPrice(HashMap<Product, Integer> originalPriceProductListWithNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Product originalPriceProduct : originalPriceProductListWithNumber.keySet()) {
            stringBuilder.append(printEachProductWithOriginalPrice(originalPriceProduct, originalPriceProductListWithNumber
                    .get(originalPriceProduct)));
        }
        return stringBuilder.toString();
    }

    public String printMultipleProductWithBuyTwoGetOne(HashMap<Product, Integer> buyTwoGetOneProductListWithNumber) {
        StringBuilder stringBuilder = new StringBuilder();

        for(Product buyTwoGetOnePriceProduct : buyTwoGetOneProductListWithNumber.keySet()) {
            stringBuilder.append(printEachProductWithBuyTwoGetOne(buyTwoGetOnePriceProduct, buyTwoGetOneProductListWithNumber
                    .get(buyTwoGetOnePriceProduct)));
        }
        return stringBuilder.toString();
    }

    public String printMultipleProductWithDiscount(HashMap<Product, Integer> discountProductListWithNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Product discountProduct : discountProductListWithNumber.keySet()) {
            stringBuilder.append(printEachProductWithDiscount(discountProduct, discountProductListWithNumber
                    .get(discountProduct)));
        }
        return stringBuilder.toString();
    }

    public String printSavePrice(double totalPrice) {
        return String.format("节省：%1.2f(元)\n", totalPrice);
    }

    public String printSumPrice(double totalPrice) {
        return String.format("总计：%1.2f(元)\n", totalPrice);
    }
}
