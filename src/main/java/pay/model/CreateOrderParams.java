package pay.model;

import product.model.ProductType;

/**
 * 支付订单参数
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public class CreateOrderParams {

    // 基础信息
    private final String orderUserId; // 下单用户
    private final Payment payment; // 支付方式

    // 微信/支付宝支付方式 需要参数
    private final String productId; // 商品ID
    private final ProductType productType; // 商品种类

    // google/支付宝/微信 需要参数
    private final boolean subscribe; // 是否订阅

    // google 需要参数
    private final String purchaseToken;  // 支付标识

    // apple // 需要参数
    private final String receiptData;  // 支付凭证

    // google/apple 需要参数
    private final String thirdProductId; // 第三方商品ID, com.vip.oneMonth
    private final boolean recover; // 是否恢复购买


    private CreateOrderParams(String orderUserId, Payment payment, String productId, ProductType productType, boolean subscribe, String purchaseToken, String receiptData, String thirdProductId, boolean recover) {
        this.orderUserId = orderUserId;
        this.payment = payment;
        this.productId = productId;
        this.productType = productType;
        this.subscribe = subscribe;
        this.purchaseToken = purchaseToken;
        this.thirdProductId = thirdProductId;
        this.receiptData = receiptData;
        this.recover = recover;
    }

    public String getOrderUserId() {
        return orderUserId;
    }

    public Payment getPayment() {
        return payment;
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

    public String getPurchaseToken() {
        return purchaseToken;
    }

    public String getReceiptData() {
        return receiptData;
    }

    public String getThirdProductId() {
        return thirdProductId;
    }

    public boolean isRecover() {
        return recover;
    }

    public static class Builder {
        private String orderUserId;
        private Payment payment;
        private String productId;
        private ProductType productType;
        private boolean subscribe;
        private String purchaseToken;
        private String thirdProductId;
        private String receiptData;
        private boolean recover;

        public Builder() {
        }

        public Builder addPayUserId(String orderUserId) {
            this.orderUserId = orderUserId;
            return this;
        }

        public Builder addPayment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Builder addProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder addProductType(ProductType productType) {
            this.productType = productType;
            return this;
        }

        public Builder addSubscribe(boolean subscribe) {
            this.subscribe = subscribe;
            return this;
        }

        public Builder addPurchaseToken(String purchaseToken) {
            this.purchaseToken = purchaseToken;
            return this;
        }

        public Builder addThirdProductId(String thirdProductId) {
            this.thirdProductId = thirdProductId;
            return this;
        }

        public Builder addReceiptData(String receiptData) {
            this.receiptData = receiptData;
            return this;
        }

        public Builder addRecover(boolean recover) {
            this.recover = recover;
            return this;
        }

        public CreateOrderParams build() {
            return new CreateOrderParams(this.orderUserId, this.payment, this.productId, this.productType, this.subscribe, this.purchaseToken, this.receiptData, this.thirdProductId, this.recover);
        }
    }


    @Override
    public String toString() {
        return "PayOrderParams{" +
                "orderUserId=" + orderUserId +
                ", payment=" + payment +
                ", productId='" + productId + '\'' +
                ", productType=" + productType +
                ", subscribe=" + subscribe +
                ", purchaseToken='" + purchaseToken + '\'' +
                ", thirdProductId='" + thirdProductId + '\'' +
                ", receiptData='" + receiptData + '\'' +
                ", recover=" + recover +
                '}';
    }
}
