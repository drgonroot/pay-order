package product.service;

import exception.PayErrorException;
import pay.model.Payment;
import pay.model.PurchaseProduct;
import pay.model.RefundProduct;
import product.model.ProductType;

import java.util.Map;

/**
 * 构建产品下发服务
 * PP 购买商品
 * OD 订单详情
 * Created by useheart on 2022/4/30
 * @author useheart
 */
public interface ProductService<PP extends PurchaseProduct, OD extends RefundProduct> extends OrderDetailConvert<PP, OD> {

    boolean support(ProductType productType);

    PP getProductInfo(Payment payment, String productId, boolean subscribe);

    void checkValidationOnOrder(PP purchaseProduct) throws PayErrorException;

    /**
     * 寻找查找真正商品接收者
     * */
    String lookReceiverUserId(String orderUserId, PP purchaseProduct);

    void addProductInfoOnOrder(PP purchaseProduct, Map<String, Object> orderInfoMap);

    void sendProduct(String orderId, OD orderDetail) throws PayErrorException;

    void refundProduct(String orderId, OD orderDetail) throws PayErrorException;
}
