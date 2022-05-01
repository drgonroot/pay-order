package order.service.impl;

import exception.PayErrorException;
import order.model.CreateOrderResult;
import order.service.OrderService;
import pay.model.Payment;
import pay.model.PurchaseProduct;
import pay.model.RefundProduct;
import pay.service.PayDispatchService;
import product.model.ProductType;
import product.service.ProductService;

import java.util.*;

/**
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public abstract class AbstractOrderService implements OrderService {

    private final PayDispatchService payDispatchService;

    public AbstractOrderService(PayDispatchService payDispatchService) {
        this.payDispatchService = payDispatchService;
    }

    @SuppressWarnings("rawtypes")
    private List<ProductService> productServiceList;
    private final Object lock = new Object();

    @Override
    @SuppressWarnings("rawtypes")
    public void registerProductService(ProductService productService) {
        if (productServiceList == null) {
            synchronized (this.lock) {
                if (productServiceList == null) {
                    this.productServiceList = new ArrayList<>();
                }
            }
        }
        synchronized (this.lock) {
            this.productServiceList.add(productService);
        }
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public CreateOrderResult createOrder(String orderUserId, ProductType productType, Payment payment, String productId, boolean subscribe) throws PayErrorException {
        final Optional<ProductService> productServiceOptional = productServiceList.stream().filter(e -> e.support(productType)).findFirst();
        if (!productServiceOptional.isPresent()) {
            // FIXME
            throw new PayErrorException();
        }
        /**
         * 1. 生成商品
         * 2. 校验商品
         * 3. 寻找用户
         * 4. 添加商品信息
         * 5. 添加支付信息
         * 6. 创建订单实体
         * */
        final ProductService productService = productServiceOptional.get();
        final PurchaseProduct purchaseProduct = productService.getProductInfo(payment, productId, subscribe);
        productService.checkValidationOnOrder(purchaseProduct);
        final String receiverUserId = productService.lookReceiverUserId(orderUserId, purchaseProduct);

        Map<String, Object> orderInfoMap = new HashMap<>();
        productService.addProductInfoOnOrder(purchaseProduct, orderInfoMap);
        payDispatchService.addPayInfoOnCreateOrder(payment, orderInfoMap);
        String orderDetailJson = productService.encode(purchaseProduct);
        final String orderId = innerCreateOrder(receiverUserId, purchaseProduct, orderDetailJson, orderInfoMap);
        return new CreateOrderResult(orderId, purchaseProduct);
    }

    protected abstract String innerCreateOrder(String receiverUserId, PurchaseProduct purchaseProduct, String orderDetailJson, Map<String, Object> orderInfoMap);

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected <OD extends RefundProduct> void sendProduct(String orderId, ProductType productType, String orderDetailJson) throws PayErrorException {
        final Optional<ProductService> productServiceOptional = productServiceList.stream().filter(e -> e.support(productType)).findFirst();
        if (!productServiceOptional.isPresent()) {
            // FIXME 商品服务丢失
            throw new PayErrorException();
        }
        final ProductService productService = productServiceOptional.get();
        final OD orderDetail = (OD)(productService.decode(orderDetailJson));
        productService.sendProduct(orderId, orderDetail);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected <OD extends RefundProduct> void refundProduct(String orderId, ProductType productType, String orderDetailJson) throws PayErrorException {
        final Optional<ProductService> productServiceOptional = productServiceList.stream().filter(e -> e.support(productType)).findFirst();
        if (!productServiceOptional.isPresent()) {
            // FIXME 商品服务丢失
            throw new PayErrorException();
        }

        final ProductService productService = productServiceOptional.get();
        final OD orderDetail = (OD)productService.decode(orderDetailJson);
        productService.refundProduct(orderId, orderDetail);
    }
}
