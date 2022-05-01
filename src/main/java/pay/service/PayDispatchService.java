package pay.service;

import exception.PayErrorException;
import order.model.OrderStatus;
import pay.model.CheckOrderParams;
import pay.model.CreateOrderParams;
import pay.model.Payment;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 支付调度
 * Created by useheart on 2022/4/30
 * @author useheart
 */
public interface PayDispatchService {

    /**
     * 注册支付服务
     * */
    void register(PayService payService);

    /**
     * 创建支付订单
     * 返回支付信息
     * */
    Object createPayOrder(CreateOrderParams payOrderParams) throws PayErrorException;

    /**
     * 创建订单上补充支付信息
     * */
    void addPayInfoOnCreateOrder(Payment payment, Map<String, Object> orderInfoMap);

    /**
     * 检查订单状态
     * */
    OrderStatus checkOrderStatus(CheckOrderParams checkOrderParams) throws PayErrorException;

    /**
     * HttpServletRequest 引入 servlet-api jar
     * FIXME 思考是否有必要通过其他方式来解决这个问题
     * */
    void processPayNotify(Payment payment, HttpServletRequest request) throws PayErrorException, IOException;
}
