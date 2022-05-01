package product.model;

import pay.model.Payment;

/**
 * 商品种类
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public interface ProductType {

    /**
     * 获取产品类型
     * */
    ProductType getProductType();

    /**
     * 产品名称
     * name 需要为英文名称
     * @see product.service.ThirdProductConvert;
     * */
    String getName();
}
