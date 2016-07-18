package utils;

import model.Product;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by anyang on 2016/7/18.
 */
public class FileUtils {

    public static JSONObject fileParse(String filePath, String filetype) {

        JSONObject jsonObject = null;
        File file = new File(filePath);
        BufferedReader bufferedReader = null;

        if(file.exists() && file.isFile()) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
                if(filetype.equals("shoppingCart")) {
                    return shoppingCartFileParse(bufferedReader);
                }else if(filetype.equals("discount")) {
                    return discountFileParse(bufferedReader);
                }else if(filetype.equals("supermarket")) {
                    return supermarketFileParse(bufferedReader);
                }
            } catch(Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                CloseUtils.close(bufferedReader);
            }
        }
        return jsonObject;
    }

    private static JSONObject shoppingCartFileParse(BufferedReader bufferedReader) throws IOException {
        String lineData = "";
        JSONObject shoppingCartProducts = new JSONObject();

        while((lineData = bufferedReader.readLine()) != null) {
            if(lineData.contains("-")) {
                String[] dataArray = lineData.split("-");
                shoppingCartProducts.put(dataArray[0], Integer.parseInt(dataArray[1]));
            }else {
                if(shoppingCartProducts.has(lineData)) {
                    int number = shoppingCartProducts.getInt(lineData) + 1;
                    shoppingCartProducts.put(lineData, number);
                }else {
                    shoppingCartProducts.put(lineData, 1);
                }
            }
        }
        return shoppingCartProducts;
    }

    private static JSONObject discountFileParse(BufferedReader bufferedReader) throws IOException {
        String lineData = "";
        JSONObject discountInfo = new JSONObject();

        while((lineData = bufferedReader.readLine()) != null) {
            String[] dataArray = lineData.split(",");
            if (dataArray.length > 2) {
                discountInfo.put(dataArray[0], "2/1");
            }else {
                discountInfo.put(dataArray[0], dataArray[1]);
            }
        }
        return discountInfo;
    }

    private static JSONObject supermarketFileParse(BufferedReader bufferedReader) throws IOException {
        String lineData = "";
        JSONObject supermarketProducts = new JSONObject();

        while((lineData = bufferedReader.readLine()) != null) {
            String[] dataArray = lineData.split(",");
            // 存储商品信息
            supermarketProducts.put(dataArray[0], new Product(dataArray[0], dataArray[1], dataArray[2], dataArray[3], dataArray[4],
                    Double.parseDouble(dataArray[5])));
        }
        return supermarketProducts;
    }

}
