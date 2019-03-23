package com.example.jpademo.utils.constant;

/**
 * @author yanghongquan
 * @email 842592135@qq.com
 * @date 2018/11/30 9:28
 */
public class ValidateParam {
    /** orderDto一般支付网银下单时非空必要參數 */
    public final static String[] orderCBRule = new String[]{
            "merchAccNo","merchTradeNo","Currency","tradeAmt","merchUrl","bankCode",
            "BankCardType","payEnvType","paytype","product","payEnvType","tradeType"
    };

}
