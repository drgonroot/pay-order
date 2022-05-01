package utils;

/**
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public class PayHelper {

    /**
     * 分->元
     * 100 -> 1.00
     * 102 -> 1.02
     * 120 -> 1.20
     * @param value int(分)
     * @return float(元)
     */
    public static String fenToYuanStr(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("参数不可小于0");
        }
        int remainder = value % 100;
        return (value / 100) + "." + (remainder < 10 ? "0" + remainder : remainder);
    }


    /**
     * 分->元 简短型
     * 末尾为0时，删除末尾的0
     * 精密度更细 用于显示
     * 100 -> 1
     * 102 -> 1.02
     * 120 -> 1.2
     */
    public static String fenToYuanByShort(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("参数不可小于0");
        }
        int hundredRemainder = value % 100;
        if (hundredRemainder == 0) {
            return value / 100 + "";
        }

        final int tensRemainder = hundredRemainder % 10;
        if (tensRemainder == 0) {
            return value / 100 + "." + hundredRemainder / 10;
        }

        return value / 100 + "." + hundredRemainder;
    }

    /**
     * 元转分
     */
    public static int yuanToFen(int yuan) {
        return 100 * yuan;
    }
}
