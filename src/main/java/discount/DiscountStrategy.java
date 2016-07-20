package discount;

import java.util.HashMap;

/**
 * Created by anyang on 2016/7/18.
 */
public interface DiscountStrategy {
    public HashMap<String, String> getDiscount();
}
