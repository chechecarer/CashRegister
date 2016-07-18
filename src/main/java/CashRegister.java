import org.json.JSONObject;
import utils.FileUtils;

/**
 * Created by anyang on 2016/7/18.
 */
public class CashRegister {

    private IDiscount discount = new DefaultDiscount();
    private ReceiptPrinter receiptPrinter = new ReceiptPrinter();

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

    public String printReceipt() {
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append(receiptPrinter.printTitle());

        receiptString += receiptPrinter.printMultipleItemsInItemSection(null);
        receiptString += receiptPrinter.getReceiptSum(this.getTotalPrice());
        return receiptString;
    }

}
