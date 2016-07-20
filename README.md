****************************************************
说明文档
****************************************************

Master：

## 文件说明

1. data文件夹中的三个文件分别代表:
		discount.txt:打折信息文件，模拟打折活动，文件格式为商品条码，折扣方式（95折/买二赠一），eg:ITEM000001,95，其中95表示九五折;
		             ITEM000002,2/1,其中2/1表示买二赠一;ITEM000005,95,2/1表示同时享受两种折扣，店员设置当“95折”和“买二赠一”发生冲突的时候，
		             也就是一款商品既符合享受“买二赠一”优惠的条件，又符合享受“95折”优惠的条件时，只享受“买二赠一”优惠。
		shoppingCart.txt:购物车文件，模拟用户购物商品，文件格式为ITEM000001或ITEM000003-2，即输入列表分为两种模式：（1）扫描仪一次扫一个条码，扫多次；（2）扫描仪只扫一次条码，然后手动输入数量。
		supermarket.txt:超市商品列表文件，模拟超市，文件格式为条形码（伪），名称，数量单位，类别，子类别和单价，即ITEM000000,可口可乐,瓶,食品,碳酸饮料,3.00。

2. 项目包说明：
        包src\main\java\calculator下存放CalculatorStrategy接口及其三种具体实现（原价，95折，买二赠一）。
        包src\main\java\cashregister下存放收银机实现类和超市类，收银机类接收购物车输入并打印输出购物小票；超市类初始化超市商品信息。
        包src\main\java\discount下存放DiscountStrategy接口及其默认折扣实现（目前只涉及95折与买二赠一，如需扩展请参考下文用法说明部分）。
		包src\main\java\model下存放商品实体类Product。
		包src\main\java\printer下存放购物小票格式化输出类。
		包src\main\java\utils下存放工具类CloseUtils和FileUtils，分别用于关闭可关闭对象(eg:BufferedReader)与三种文件类型（shoppingCart,discount,supermarket）解析。
		包src\main\java下存放项目执行入口程序CashRegisterMain.java。

## 用法说明

设置data文件夹下discount，shoppingCart和supermarket三个文件，分别模拟打折活动，用户购物车和超市，然后执行即可在控制台得到输出小票信息。若店员需要设置优惠活动，
则可以通过在CashRegisterMain类的main方法内加入下面的代码即可，其中fileParse方法的参数filePath为折扣信息文件路径，"discount"表示将文件以折扣文件类型进行解析。
		cashRegister.setDiscount(new DiscountStrategy() {

			public HashMap<String, String> getDiscount() {
                return FileUtils.fileParse(filePath, "discount");
            }
		});