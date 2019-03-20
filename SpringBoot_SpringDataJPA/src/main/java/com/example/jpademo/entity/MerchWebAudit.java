package com.example.jpademo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 商户网站审核记录
 */
@Data
@DynamicUpdate //跟新数据时只会跟新发生改变的值
@Entity
@Table(name = "TSL_MERCHWEBAUDITRECORD")
public class MerchWebAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MerchWebAuditSeq")   Oracle方式
//    @SequenceGenerator(name="MerchWebAuditSeq",initialValue = 1,allocationSize = 1,sequenceName = "SEQ_MERCHWEBAUDITRECORD")
    private Long id;
    /**
     * 商户客户号
     */
    private String merchCusNo;
    /**
     * 商户名称
     */
    private String merchName;
    /**
     * 提交日期
     */
    private Date submitDate;
    /**用于Dto*/
    @Transient
    private Date startDate;
    /**用于Dto*/
    @Transient
    private Date endDate;

    /**
     * 所属代理商
     */
    private String ofAgent;
    /**
     * 网址
     */
    private String url;

    /**
     * 网址是否正常打开
     */
    private String urlCanOpen;
    /**
     * 商品能否正常下单
     */
    private String goodsCanOrder;
    /**
     * 商户基本情况
     */
    private String merchBaseCase;
    /**
     * 商品详情
     */
    private String goodsDetail;
    /**
     * ICP信息
     */
    private String ICPInfo;
    /**
     * 热线服务或投诉方式
     */
    private String HotLineServe;
    /**
     *  网站经营范围是否符合
     */
    private String manageScope;
    /**
     * 备注
     */
    private String remark;
    /**
     * 下载文件地址
     */
    private String uploadFile;
}
