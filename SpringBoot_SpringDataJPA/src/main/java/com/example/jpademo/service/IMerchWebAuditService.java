package com.example.jpademo.service;

import com.example.jpademo.entity.MerchWebAudit;
import org.springframework.data.domain.Page;

/**
 * @author wangxu
 * 商户网站审核记录
 */
public interface IMerchWebAuditService {
    /**
     * 保存
     * @param merchWebAudit 商户网站审核对象
     * @return
     */
    void save(MerchWebAudit merchWebAudit);

    /**
     * 获取修改页面初始化数据
     * @param id  商户网站审核记录id
     * @return
     */
    MerchWebAudit findByid(Long id);

    /**
     * 更新
     * @param merchWebAudit
     */
    void update(MerchWebAudit merchWebAudit);

    /**
     * 根据ID 删除记录
     * @param id
     */
    void delete(Long id);

    /**
     * 分页查询
     * @param page
     * @param size
     * @param merchWebAudit
     * @return
     */
    Page<MerchWebAudit> pageQuery(Integer page, Integer size, MerchWebAudit merchWebAudit);

}
