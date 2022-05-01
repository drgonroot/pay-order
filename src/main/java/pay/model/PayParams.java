package pay.model;

/**
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public interface PayParams {

    String getOrderUserId(); // 获取下单用户Id

    Payment getPayment(); // 获取支付方式
}
