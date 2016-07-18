import org.json.JSONObject;
import utils.FileUtils;

/**
 * Created by anyang on 2016/7/18.
 */
public class DefaultDiscount implements IDiscount {
    public JSONObject putDiscount() {
        return FileUtils.fileParse(CashRegisterDiscount.PROJECTPATH + "\\data\\discount.txt", "discount");
    }
}
