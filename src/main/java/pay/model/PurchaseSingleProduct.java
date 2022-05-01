package pay.model;

import com.google.api.client.json.GenericJson;
import payment.apple.model.AppleReceiptInfo;
import product.model.Product;
import product.model.ProductType;

/**
 * 购买单个商品
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public class PurchaseSingleProduct<SP extends Product> implements PurchaseProduct {

    private final Payment payment;
    private final ProductType productType;
    private final int payPrice;
    private final CurrencyType currencyType;
    private final boolean subscribe;
    private final SP product;
    private AppleReceiptInfo appleReceiptInfo;
    private GenericJson genericJson;

    public PurchaseSingleProduct(Payment payType, ProductType productType, int payPrice, CurrencyType currencyType, boolean subscribe, SP product) {
        this.payment = payType;
        this.productType = productType;
        this.payPrice = payPrice;
        this.currencyType = currencyType;
        this.subscribe = subscribe;
        this.product = product;
    }

    @Override
    public Payment getPayment() {
        return payment;
    }

    @Override
    public ProductType getProductType() {
        return productType;
    }

    @Override
    public int getPayPrice() {
        return payPrice;
    }

    @Override
    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    @Override
    public String getPurchaseDesc() {
        final Product product = this.getProduct();
        return product.getProductType() + ":" + product.getName();
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public SP getProduct() {
        return product;
    }

    public void setAppleReceiptInfo(AppleReceiptInfo appleReceiptInfo) {
        this.appleReceiptInfo = appleReceiptInfo;
    }

    public void setGenericJson(GenericJson genericJson) {
        this.genericJson = genericJson;
    }

    public AppleReceiptInfo getAppleReceiptInfo() {
        return appleReceiptInfo;
    }

    public GenericJson getGenericJson() {
        return genericJson;
    }
}
