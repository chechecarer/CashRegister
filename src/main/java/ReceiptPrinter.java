import model.Product;

/**
 * Created by anyang on 2016/7/18.
 */
public class ReceiptPrinter {

    public String printTitle() {
        return "***<没钱赚商店>购物清单***\n";
    }

    public String printEachProductItem(Product product, int number) {
        return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)",
                product.getName(),
                number,product.getUnit(),
                product.getPrice(),
                product.getPrice() * number);
    }
}
