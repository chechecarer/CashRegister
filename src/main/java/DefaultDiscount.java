import utils.FileUtils;

import java.util.HashMap;

/**
 * Created by anyang on 2016/7/18.
 */
public class DefaultDiscount implements DiscountStrategy {

    public static final double DISCOUNT = 0.95;

    public HashMap<String, String> getDiscount() {
        return FileUtils.fileParse(CashRegisterDiscount.PROJECTPATH + "\\data\\discount.txt", "discount");
    }
}
