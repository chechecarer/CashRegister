import model.Product;
import org.json.JSONObject;
import utils.FileUtils;

import java.util.HashMap;

/**
 * Created by anyang on 2016/7/18.
 */
public class CashRegister {

    private IDiscount discount = new DefaultDiscount();
    private ReceiptPrinter receiptPrinter = new ReceiptPrinter();
    private HashMap<Product, Integer> buyTwoGetOneProductListWithNumber;
    private HashMap<Product, Integer> originalPriceProductListWithNumber;
    private HashMap<Product, Integer> discountProductListWithNumber;

    public void setDiscount(IDiscount discount) {
        this.discount = discount;
    }

    public JSONObject getShoppingCartProduct(String filePath) {
        return FileUtils.fileParse(filePath, "shoppingCart");
    }

    public double calculateTotalPrice() {
        double total = 0.0;

        return total;
    }

    public double calculateTotalSavePrice() {
        double total = 0.0;

        return total;
}

    public String printReceipt() {
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append(receiptPrinter.printTitle());
        receiptBuilder.append(receiptPrinter.printMultipleProductWithOriginalPrice(originalPriceProductListWithNumber));
        receiptBuilder.append(receiptPrinter.printMultipleProductWithDiscount(discountProductListWithNumber));
        receiptBuilder.append(receiptPrinter.printBuyTwoGetOneProduct(buyTwoGetOneProductListWithNumber));
        receiptBuilder.append(receiptPrinter.printSumPrice(this.calculateTotalPrice()));
        receiptBuilder.append(receiptPrinter.printSavePrice(this.calculateTotalSavePrice()));
        return receiptBuilder.toString();
    }

}
