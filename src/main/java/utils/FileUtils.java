package utils;

import model.Product;

import java.io.*;
import java.util.HashMap;

/**
 * Created by anyang on 2016/7/18.
 */
public class FileUtils {

    public static HashMap fileParse(String filePath, String filetype) {
        HashMap hashMap = null;
        File file = new File(filePath);
        BufferedReader bufferedReader = null;

        if(file.exists() && file.isFile()) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
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
        return hashMap;
    }

    private static HashMap<String, Integer> shoppingCartFileParse(BufferedReader bufferedReader) throws IOException {
        String lineData = "";
        HashMap<String, Integer> shoppingCartProducts = new HashMap<String, Integer>();

        while((lineData = bufferedReader.readLine()) != null) {
            if(lineData.contains("-")) {
                String[] dataArray = lineData.split("-");
                shoppingCartProducts.put(dataArray[0], Integer.parseInt(dataArray[1]));
            }else {
                if(shoppingCartProducts.containsKey(lineData)) {
                    int number = shoppingCartProducts.get(lineData) + 1;
                    shoppingCartProducts.put(lineData, number);
                }else {
                    shoppingCartProducts.put(lineData, 1);
                }
            }
        }
        return shoppingCartProducts;
    }

    private static HashMap<String, String> discountFileParse(BufferedReader bufferedReader) throws IOException {
        String lineData = "";
        HashMap<String, String> discountInfo = new HashMap<String, String>();

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

    private static HashMap<String, Product> supermarketFileParse(BufferedReader bufferedReader) throws IOException {
        String lineData = "";
        HashMap<String, Product> supermarketProducts = new HashMap<String, Product>();

        while((lineData = bufferedReader.readLine()) != null) {
            String[] dataArray = lineData.split(",");
            // 存储商品信息
            supermarketProducts.put(dataArray[0], new Product(dataArray[0], dataArray[1], dataArray[2], dataArray[3], dataArray[4],
                    Double.parseDouble(dataArray[5])));
        }
        return supermarketProducts;
    }

}
