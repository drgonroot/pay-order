package pay.model;

/**
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public class CheckOrderParams {

    private final Payment payment;
    // 微信/支付宝
    private final String orderId;
    // google
    private final String purchaseToken;
    // apple
    private final String receiptData;

    private CheckOrderParams(Payment payment, String orderId, String purchaseToken, String receiptData) {
        this.payment = payment;
        this.orderId = orderId;
        this.purchaseToken = purchaseToken;
        this.receiptData = receiptData;
    }

    public Payment getPayment() {
        return payment;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPurchaseToken() {
        return purchaseToken;
    }

    public String getReceiptData() {
        return receiptData;
    }

    public static class Builder {
        private Payment payment;
        private String orderId;
        private String purchaseToken;
        private String receiptData;

        public Builder() {
        }

        public Builder addPayment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Builder addOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder addPurchaseToken(String purchaseToken) {
            this.purchaseToken = purchaseToken;
            return this;
        }

        public Builder addReceiptData(String receiptData) {
            this.receiptData = receiptData;
            return this;
        }

        public CheckOrderParams build() {
            return new CheckOrderParams(this.payment, this.orderId, this.purchaseToken, this.receiptData);
        }
    }
}
