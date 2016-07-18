package utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by anyang on 2016/7/18.
 */
public class CloseUtils {

    public static void close(Closeable closeable) {
        if(null != closeable){
            try {
                closeable.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
