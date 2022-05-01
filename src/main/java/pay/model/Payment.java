package pay.model;

/**
 * 支付方式
 * Created by useheart on 2022/4/30
 * @author useheart
 */
public interface Payment {

    /**
     * 获取支付方式
     * */
    Payment getPayment();

    /**
     * 支付方式名称
     * 需要为英文名称
     * @see product.service.ThirdProductConvert
     * */
    String getName();
}
