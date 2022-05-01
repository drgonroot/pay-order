package pay.model;

import product.model.ProductType;

/**
 * 购买商品基础类
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public interface PurchaseProduct {

    // 购买信息
    Payment getPayment();
    ProductType getProductType();
    int getPayPrice();
    CurrencyType getCurrencyType();
    String getPurchaseDesc();
}
