package pay.service.impl;

import exception.PayErrorException;
import order.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pay.model.CheckOrderParams;
import pay.model.CreateOrderParams;
import pay.model.PayParams;
import pay.model.Payment;
import pay.service.PayDispatchService;
import pay.service.PayService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public class PayDispatchServiceImpl implements PayDispatchService {
    private final Logger logger = LoggerFactory.getLogger(PayDispatchServiceImpl.class);

    @SuppressWarnings("rawtypes")
    private List<PayService> payServiceList;
    private final Object lock = new Object();

    @Override
    @SuppressWarnings("rawtypes")
    public void register(PayService payService) {
        if (payServiceList == null) {
            synchronized (this.lock) {
                if (payServiceList == null) {
                    this.payServiceList = new ArrayList<>();
                }
            }
        }
        synchronized (this.lock) {
            this.payServiceList.add(payService);
        }
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object createPayOrder(CreateOrderParams payOrderParams) throws PayErrorException {
        final Payment payment = payOrderParams.getPayment();
        final Optional<PayService> payServiceOptional = payServiceList.stream().filter(e -> e.support(payment)).findFirst();
        if (!payServiceOptional.isPresent()) {
            logger.error("pay service lose，payment:{}", payment);
            // FIXME 后面调整返回异常
            throw new PayErrorException();
        }
        final PayService payService = payServiceOptional.get();
        final PayParams payParams = payService.checkAndBuildPayParams(payOrderParams);
        return payService.completePayOnOrder(payParams);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void addPayInfoOnCreateOrder(Payment payment, Map<String, Object> orderInfoMap) {
        final Optional<PayService> payServiceOptional = payServiceList.stream().filter(e -> e.support(payment)).findFirst();
        payServiceOptional.ifPresent(payService -> {
            payService.addPayInfoOnCreateOrder(orderInfoMap);
        });
    }

    @Override
    @SuppressWarnings("rawtypes")
    public OrderStatus checkOrderStatus(CheckOrderParams checkOrderParams) throws PayErrorException {
        final Payment payment = checkOrderParams.getPayment();
        final Optional<PayService> payServiceOptional = payServiceList.stream().filter(e -> e.support(payment)).findFirst();
        if (!payServiceOptional.isPresent()) {
            logger.error("pay service lose，payment:{}", payment);
            // FIXME 后面调整返回异常
            throw new PayErrorException();
        }
        final PayService payService = payServiceOptional.get();
        return payService.checkAndGetOrderStatus(checkOrderParams);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void processPayNotify(Payment payment, HttpServletRequest request) throws PayErrorException, IOException {
        final Optional<PayService> payServiceOptional = payServiceList.stream().filter(e -> e.support(payment)).findFirst();
        if (!payServiceOptional.isPresent()) {
            // FIXME 后面调整返回异常
            logger.error("pay service lose，payment:{}, requestURI:{}", payment, request.getRequestURI());
            throw new PayErrorException();
        }
        final PayService payService = payServiceOptional.get();
        payService.processPayNotify(request);
    }
}
