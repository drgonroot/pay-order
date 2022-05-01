package order.model;

import pay.model.PurchaseProduct;

/**
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public class CreateOrderResult {

    private final String orderId;
    private final PurchaseProduct purchaseProduct;

    public CreateOrderResult(String orderId, PurchaseProduct purchaseProduct) {
        this.orderId = orderId;
        this.purchaseProduct = purchaseProduct;
    }

    public String getOrderId() {
        return orderId;
    }

    public PurchaseProduct getPurchaseProduct() {
        return purchaseProduct;
    }
}
