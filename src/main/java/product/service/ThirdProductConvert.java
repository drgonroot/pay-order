package product.service;

import pay.model.Payment;
import product.model.ProductType;

/**
 * 第三方商品ID转换器
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public interface ThirdProductConvert {

    String encode(ThirdProduct thirdProduct);

    ThirdProduct decode(String thirdProductId);

    class ThirdProduct {
        private final Payment payment;
        private final ProductType productType;
        private final String productId;

        public ThirdProduct(Payment payment, ProductType productType, String productId) {
            this.payment = payment;
            this.productType = productType;
            this.productId = productId;
        }

        public Payment getPayment() {
            return payment;
        }

        public ProductType getProductType() {
            return productType;
        }

        public String getProductId() {
            return productId;
        }
    }
}
