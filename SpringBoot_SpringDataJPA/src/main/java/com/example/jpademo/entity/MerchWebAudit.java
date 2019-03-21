package com.example.jpademo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 商户网站审核记录
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchCusNo() {
        return merchCusNo;
    }

    public void setMerchCusNo(String merchCusNo) {
        this.merchCusNo = merchCusNo;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOfAgent() {
        return ofAgent;
    }

    public void setOfAgent(String ofAgent) {
        this.ofAgent = ofAgent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlCanOpen() {
        return urlCanOpen;
    }

    public void setUrlCanOpen(String urlCanOpen) {
        this.urlCanOpen = urlCanOpen;
    }

    public String getGoodsCanOrder() {
        return goodsCanOrder;
    }

    public void setGoodsCanOrder(String goodsCanOrder) {
        this.goodsCanOrder = goodsCanOrder;
    }

    public String getMerchBaseCase() {
        return merchBaseCase;
    }

    public void setMerchBaseCase(String merchBaseCase) {
        this.merchBaseCase = merchBaseCase;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public String getICPInfo() {
        return ICPInfo;
    }

    public void setICPInfo(String ICPInfo) {
        this.ICPInfo = ICPInfo;
    }

    public String getHotLineServe() {
        return HotLineServe;
    }

    public void setHotLineServe(String hotLineServe) {
        HotLineServe = hotLineServe;
    }

    public String getManageScope() {
        return manageScope;
    }

    public void setManageScope(String manageScope) {
        this.manageScope = manageScope;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }
}
