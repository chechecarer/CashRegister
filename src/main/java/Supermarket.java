import org.json.JSONObject;
import utils.FileUtils;

/**
 * Created by anyang on 2016/7/18.
 */
public class Supermarket {

    public JSONObject supermarketGoods;
    private volatile static Supermarket supermarketInstance = null;

    private Supermarket() {
        supermarketGoods = initGoods();
    }

    private JSONObject initGoods(){
        return FileUtils.fileParse(CashRegisterDiscount.PROJECTPATH + "\\data\\supermarket.txt", "supermarket");
    }

    public static Supermarket getInstance(){
        if(supermarketInstance == null){
            synchronized(Supermarket.class) {
                if(supermarketInstance == null){
                    supermarketInstance = new Supermarket();
                }
            }
        }
        return supermarketInstance;
    }
}
