package pay.service.impl;

import exception.PayErrorException;
import pay.model.PayParams;
import pay.service.PayService;

/**
 * 支付服务 类似微信支付结构 客户端上传凭证下单
 * Created by useheart on 2022/5/1
 * @author useheart
 */
public abstract class PayServiceLikeApple<Params extends PayParams, Result> implements PayService<Params, Result> {

    public abstract Result createReceiptOrder(Params payParams) throws PayErrorException;

    @Override
    public Result completePayOnOrder(Params payParams) throws PayErrorException {
        return this.createReceiptOrder(payParams);
    }
}
