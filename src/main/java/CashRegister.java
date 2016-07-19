import model.Product;
import utils.FileUtils;

import java.util.HashMap;

/**
 * Created by anyang on 2016/7/18.
 */
public class CashRegister {

    private IDiscount discount;
    private ReceiptPrinter receiptPrinter;

    private HashMap<String, Integer> shoppingCartProductListWithNumber;
    private HashMap<String, Product> supermarketProductList;
    private HashMap<String, String> discountProductListWithDiscount;

    private HashMap<Product, Integer> buyTwoGetOneProductListWithNumber;
    private HashMap<Product, Integer> originalPriceProductListWithNumber;
    private HashMap<Product, Integer> discountProductListWithNumber;

    public CashRegister() {
        discount = new DefaultDiscount();
        receiptPrinter = new ReceiptPrinter();

        shoppingCartProductListWithNumber = this.getShoppingCartProduct(CashRegisterDiscount.PROJECTPATH
                + "\\data\\shoppingCart.txt");
        supermarketProductList = Supermarket.getInstance().supermarketProducts;
        discountProductListWithDiscount = discount.getDiscount();

        buyTwoGetOneProductListWithNumber = new HashMap<Product, Integer>();
        originalPriceProductListWithNumber = new HashMap<Product, Integer>();
        discountProductListWithNumber = new HashMap<Product, Integer>();
    }

    public void setDiscount(IDiscount discount) {
        this.discount = discount;
    }

    private HashMap<String, Integer> getShoppingCartProduct(String filePath) {
        return FileUtils.fileParse(filePath, "shoppingCart");
    }

    private void isDiscountProduct() {
        for (String shoppingCartProductItem : shoppingCartProductListWithNumber.keySet()) {
            if (discountProductListWithDiscount.containsKey(shoppingCartProductItem)) {
                classifyDiscountProduct(shoppingCartProductItem);
            }else {
                originalPriceProductListWithNumber.put(getProductByBarcode(shoppingCartProductItem),
                        shoppingCartProductListWithNumber.get(shoppingCartProductItem));
            }
        }
    }

    private void classifyDiscountProduct(String barcode) {
        String discount = discountProductListWithDiscount.get(barcode);
        if (discount.equals("95")) {
            discountProductListWithNumber.put(getProductByBarcode(barcode),
                    shoppingCartProductListWithNumber.get(barcode));
        }else if (discount.equals("2/1")) {
            buyTwoGetOneProductListWithNumber.put(getProductByBarcode(barcode),
                    shoppingCartProductListWithNumber.get(barcode));
        }
    }

    private Product getProductByBarcode(String barcode) {
        return supermarketProductList.get(barcode);
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        totalPrice = calculatePriceWithOriginalPriceProducts() + calculatePriceWithDiscountProducts()
                + calculatePriceWithBuyTwoGetOneProducts();
        return totalPrice;
    }

    private double calculatePriceWithOriginalPriceProducts() {
        double price = 0.0;
        for (Product originalPriceProductItem : originalPriceProductListWithNumber.keySet()) {
            price += originalPriceProductItem.getPrice() * originalPriceProductListWithNumber.get(originalPriceProductItem);
        }
        return price;
    }

    private double calculatePriceWithDiscountProducts() {
        double price = 0.0;
        for (Product discountProductItem : discountProductListWithNumber.keySet()) {
            price += discountProductItem.getPrice() * discountProductListWithNumber.get(discountProductItem)
                    * DefaultDiscount.DISCOUNT;
        }
        return price;
    }

    // 待实现
    private double calculatePriceWithBuyTwoGetOneProducts() {
        double price = 0.0;
        for (Product buyTwoGetOneProductItem : buyTwoGetOneProductListWithNumber.keySet()) {
            price += buyTwoGetOneProductItem.getPrice() * buyTwoGetOneProductListWithNumber.get(buyTwoGetOneProductItem);
        }
        return price;
    }

    private double calculateTotalSavePrice() {
        double totalSavePrice = 0.0;
        totalSavePrice = calculateSavePriceWithDiscountProducts() + calculateSavePriceWithBuyTwoGetOneProducts();
        return totalSavePrice;
}

    private double calculateSavePriceWithDiscountProducts() {
        double price = 0.0;
        for (Product discountProductItem : discountProductListWithNumber.keySet()) {
            price += discountProductItem.getPrice() * discountProductListWithNumber.get(discountProductItem)
                    * (1 - DefaultDiscount.DISCOUNT);
        }
        return price;
    }

    // 待实现
    private double calculateSavePriceWithBuyTwoGetOneProducts() {
        double price = 0.0;
        for (Product buyTwoGetOneProductItem : buyTwoGetOneProductListWithNumber.keySet()) {
            price += buyTwoGetOneProductItem.getPrice() * buyTwoGetOneProductListWithNumber.get(buyTwoGetOneProductItem);
        }
        return price;
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
