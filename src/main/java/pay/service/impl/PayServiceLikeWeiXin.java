package pay.service.impl;

import exception.PayErrorException;
import order.model.CreateOrderResult;
import order.model.OrderStatus;
import order.service.OrderService;
import pay.model.*;
import pay.service.PayService;
import product.model.ProductType;

/**
 * 支付服务 类似微信支付结构 下单 -> 回调通知支付成功
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public abstract class PayServiceLikeWeiXin<Result> implements PayService<PayServiceLikeWeiXin.WeiXinPayParams, Result> {

    private final OrderService orderService;
    public PayServiceLikeWeiXin(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public WeiXinPayParams checkAndBuildPayParams(CreateOrderParams payRequestParams) throws PayErrorException {
        final String orderUserId = payRequestParams.getOrderUserId();
        if (orderUserId == null || orderUserId.isEmpty()) {
            // FIXME
            throw new PayErrorException();
        }

        final Payment payment = payRequestParams.getPayment();
        if (payment == null) {
            // FIXME
            throw new PayErrorException();
        }
        final ProductType productType = payRequestParams.getProductType();
        if (productType == null) {
            // FIXME
            throw new PayErrorException();
        }

        final String productId = payRequestParams.getProductId();
        if (productId == null || productId.isEmpty()) {
            // FIXME
            throw new PayErrorException();
        }

        return new WeiXinPayParams(orderUserId, payment, productId, productType, payRequestParams.isSubscribe());
    }

    @Override
    public Result completePayOnOrder(WeiXinPayParams payParams) throws PayErrorException {
        final Payment payment = payParams.getPayment();
        final String orderUserId = payParams.getOrderUserId();
        final ProductType productType = payParams.getProductType();
        final String productId = payParams.getProductId();
        final boolean subscribe = payParams.isSubscribe();
        final CreateOrderResult createOrderResult = orderService.createOrder(orderUserId, productType, payment, productId, subscribe);
        return buildPayInfo(createOrderResult);
    }

    public abstract Result buildPayInfo(CreateOrderResult createOrderResult) throws PayErrorException;

    @Override
    public OrderStatus checkAndGetOrderStatus(CheckOrderParams checkOrderParams) throws PayErrorException {
        final String orderId = checkOrderParams.getOrderId();
        if (orderId == null || orderId.isEmpty()) {
            throw new PayErrorException();
        }
        return orderService.getStatusById(orderId);
    }

    public static class WeiXinPayParams implements PayParams {
        private final String orderUserId;
        private final Payment payment;
        private final String productId;
        private final ProductType productType;
        private final boolean subscribe;

        public WeiXinPayParams(String orderUserId, Payment payment, String productId, ProductType productType, boolean subscribe) {
            this.orderUserId = orderUserId;
            this.payment = payment;
            this.productId = productId;
            this.productType = productType;
            this.subscribe = subscribe;
        }

        @Override
        public String getOrderUserId() {
            return this.orderUserId;
        }

        @Override
        public Payment getPayment() {
            return this.payment;
        }

        public String getProductId() {
            return productId;
        }

        public ProductType getProductType() {
            return productType;
        }

        public boolean isSubscribe() {
            return subscribe;
        }
    }
}
