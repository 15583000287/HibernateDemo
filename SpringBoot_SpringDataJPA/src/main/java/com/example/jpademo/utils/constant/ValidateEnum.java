package com.example.jpademo.utils.constant;

/**
 * 验证相关ENUM
 * @author yanghongquan
 * @email 842592135@qq.com
 * @date 2018/11/29 9:22
 */
public enum ValidateEnum {
    /** orderDto相关参数校验错误码及日志内容 */
    ORD11001("merchAccNo","ORD11001","参数商户号[merchAccNo]为空!"),
    ORD11002("merchTradeNo","ORD11002","商户订单号[merchTradeNo]为空!"),
    ORD11003("Currency","ORD11003","参数支付币种[Currency]为空!"),
    ORD11004("tradeAmt","ORD11004","参数支付金额[tradeAmt]为空!"),
    ORD11005("merchUrl","ORD11005","参数后台通知URL[merchUrl]为空!"),
    ORD11006("bankCode","ORD11006","参数支付银行代码[bankCode]为空!"),
    ORD11007("BankCardType","ORD11007","参数支付银行卡类型[BankCardType]为空!"),
    ORD11008("payEnvType","ORD11008","参数支付环境[payEnvType]为空!"),
    ORD11009("paytype","ORD11009","参数支付方式[paytype]为空!"),
    ORD11010("product","ORD11010","参数产品类型[product]为空!"),
    ORD11011("payEnvType","ORD11011","参数支付环境[payEnvType]为空!"),
    ORD11012("tradeType","ORD11012","参数交易类型[tradeType]为空!")
    ;
    /** 字段名 */
    public String columnName;
    /** 错误码 */
    public String code;
    /** 错误码内容 */
    public String msg;
    ValidateEnum(String mcolumnName,String mcode,String mmsg){
        columnName = mcolumnName;
        code = mcode;
        msg = mmsg;
    }

    /**
     * 根据column名获取当前异常
     * @param column
     * @return
     */
    public static ValidateEnum getByColumn(String column){
        for(ValidateEnum validateEnum: ValidateEnum.values()){
            if(validateEnum.columnName.equals(column)){
                return validateEnum;
            }
        }
        return null;
    }
}
