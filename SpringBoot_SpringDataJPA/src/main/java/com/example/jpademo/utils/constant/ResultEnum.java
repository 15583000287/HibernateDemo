package com.example.jpademo.utils.constant;

/**
 * 返回信息ENnum
 * @author yanghongquan
 * @email 842592135@qq.com
 * @date 2018/10/31 15:15
 */
public enum ResultEnum {
    /** 返回参数属性名 */
    DATA("retData","返回参数属性值"),
    /** 返回前端处理异常信息 */
    ERROR("error","未知异常,请联系管理员"),
    /** 分页查询返回属性名 */
    PAGE("page","分页查询返回属性名"),
    /** 分页查询当页数量 */
    LIMIT("limit","当页数量限制"),
    /** 处理成功返回值 */
    SUCCESS("1","success"),
    /** 处理失败返回值 */
    FAIL("0","fail"),
    /** 返回错误代码  0交易失败 1交易成功 2 部分失败（含义待添加,暂不用） */
    CODE("retCode","返回错误代码"),
    /** 微服务名称 Micro service */
    SERVICE_NAME("serName","微服务名称"),
    /** 在交易错误情况下 具体错误信息 */
    MSG("retMsg","错误信息"),
    /** 自己的系统请修改此处 */
    MY_SERVICE_NAME("ORDER","自己的服务名");

    /** 属性值 */
    public final String code;
    /** 属性备注 */
    public final String desc;

    ResultEnum(String mValue,String mDesc){
        code = mValue;
        desc = mDesc;
    }

}
