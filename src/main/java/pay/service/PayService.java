package pay.service;

import exception.PayErrorException;
import order.model.OrderStatus;
import pay.model.CheckOrderParams;
import pay.model.CreateOrderParams;
import pay.model.PayParams;
import pay.model.Payment;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by useheart on 2022/4/30
 * @author useheart
 */
public interface PayService<Params extends PayParams, Result> {

    boolean support(Payment payment);

    /**
     * 检查创建订单参数
     * 返回支付参数
     */
    Params checkAndBuildPayParams(CreateOrderParams createOrderParams) throws PayErrorException;


    default void addPayInfoOnCreateOrder(Map<String, Object> orderInfoMap) {
    }

    /**
     * 完成支付事情
     */
    Result completePayOnOrder(Params payParams) throws PayErrorException;

    /**
     * 检查支付结果
     */
    OrderStatus checkAndGetOrderStatus(CheckOrderParams checkOrderParams) throws PayErrorException;


    default void processPayNotify(HttpServletRequest request) throws PayErrorException, IOException {

    }
}
