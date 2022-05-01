package product.service;

import pay.model.PurchaseProduct;
import pay.model.RefundProduct;

/**
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public interface OrderDetailConvert<PP extends PurchaseProduct, OD extends RefundProduct> {

    String encode(PP purchaseProduct);

    OD decode(String orderDetailJson);
}
