import cashregister.CashRegister;

/**
 * Created by anyang on 2016/7/18.
 */
public class CashRegisterMain {

    public static final String PROJECTPATH = System.getProperty("user.dir");

    public static void main(String[] args) {
        CashRegister cashRegister = new CashRegister();
        cashRegister.printReceipt();
    }

}
