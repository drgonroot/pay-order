package payment.apple.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public class AppleReceiptInfo {

    @JsonProperty("cancellation_date")
    private String cancellationDate;

    @JsonProperty("cancellation_date_ms")
    private long cancellationDateMs;

    @JsonProperty("cancellation_date_pst")
    private String cancellationDatePst;

    @JsonProperty("cancellation_reason")
    private String cancellationReason;  // 1, 0

    @JsonProperty("expires_date")
    private String expiresDate;

    @JsonProperty("expires_date_ms")
    private long expiresDateMs;

    @JsonProperty("expires_date_pst")
    private String expiresDatePst;

    @JsonProperty("in_app_ownership_type")
    public String inAppOwnershipType;

    @JsonProperty("is_in_intro_offer_period")
    private boolean isInIntroOfferPeriod;

    @JsonProperty("is_trial_period")
    private boolean isTrialPeriod;

    @JsonProperty("original_purchase_date")
    private String originalPurchaseDate;

    @JsonProperty("original_purchase_date_ms")
    private long originalPurchaseDateMs;

    @JsonProperty("original_purchase_date_pst")
    private String originalPurchaseDatePst;

    @JsonProperty("original_transaction_id")
    private String originalTransactionId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("promotional_offer_id")
    private String promotionalOfferId;

    @JsonProperty("purchase_date")
    private String purchaseDate;

    @JsonProperty("purchase_date_ms")
    private long purchaseDateMs;

    @JsonProperty("purchase_date_pst")
    private String purchaseDatePst;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("web_order_line_item_id")
    private String webOrderLineItemId;

    public String getCancellationDate() {
        return cancellationDate;
    }

    public long getCancellationDateMs() {
        return cancellationDateMs;
    }

    public String getCancellationDatePst() {
        return cancellationDatePst;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public String getExpiresDate() {
        return expiresDate;
    }

    public long getExpiresDateMs() {
        return expiresDateMs;
    }

    public String getExpiresDatePst() {
        return expiresDatePst;
    }

    public String getInAppOwnershipType() {
        return inAppOwnershipType;
    }

    public boolean isInIntroOfferPeriod() {
        return isInIntroOfferPeriod;
    }

    public boolean isTrialPeriod() {
        return isTrialPeriod;
    }

    public String getOriginalPurchaseDate() {
        return originalPurchaseDate;
    }

    public long getOriginalPurchaseDateMs() {
        return originalPurchaseDateMs;
    }

    public String getOriginalPurchaseDatePst() {
        return originalPurchaseDatePst;
    }

    public String getOriginalTransactionId() {
        return originalTransactionId;
    }

    public String getProductId() {
        return productId;
    }

    public String getPromotionalOfferId() {
        return promotionalOfferId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public long getPurchaseDateMs() {
        return purchaseDateMs;
    }

    public String getPurchaseDatePst() {
        return purchaseDatePst;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getWebOrderLineItemId() {
        return webOrderLineItemId;
    }
}
