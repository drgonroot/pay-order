package order.service;

import exception.PayErrorException;
import order.model.CreateOrderResult;
import order.model.OrderStatus;
import pay.model.Payment;
import product.model.ProductType;
import product.service.ProductService;

/**
 * Created by useheart on 2022/4/30
 * @author useheart
 */
public interface OrderService {

    @SuppressWarnings("rawtypes")
    void registerProductService(ProductService productService);

    OrderStatus getStatusById(String orderId);

    CreateOrderResult createOrder(String orderUserId, ProductType productType, Payment payment, String productId, boolean subscribe) throws PayErrorException;
}
