package cashregister;

import model.Product;
import utils.FileUtils;

import java.util.HashMap;

/**
 * Created by anyang on 2016/7/18.
 */
public class Supermarket {

    public HashMap<String, Product> supermarketProducts;
    private volatile static Supermarket supermarketInstance = null;

    private Supermarket() {
        supermarketProducts = getProducts();
    }

    private HashMap<String, Product> getProducts(){
        return FileUtils.fileParse(CashRegister.PROJECTPATH + "\\data\\supermarket.txt", "supermarket");
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
