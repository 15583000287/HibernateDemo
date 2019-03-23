package com.example.jpademo.service.impl;

import com.example.jpademo.entity.MerchWebAudit;
import com.example.jpademo.exception.MobaoException;
import com.example.jpademo.repository.MerchWebAuditRepository;
import com.example.jpademo.service.IMerchWebAuditService;

import com.example.jpademo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("merchWebAuditService")
public class MerchWebAuditServiceImpl implements IMerchWebAuditService {
    @Autowired
    private MerchWebAuditRepository merchWebAuditRepository;

    @Override
    public void save(MerchWebAudit record) {
        //设置提交时间
        record.setSubmitDate(new Date());
        MerchWebAudit result = merchWebAuditRepository.save(record);
        if(result == null){
            throw new MobaoException("保存失败！",2);
        }
    }

    @Override
    public MerchWebAudit findByid(Long id) {
        MerchWebAudit result = merchWebAuditRepository.getOne(id);
        if(result == null){
            throw new MobaoException("页面初始化失败！",2);
        }
        return result;
    }

    @Override
    public void update(MerchWebAudit merchWebAudit) {
        try{
            MerchWebAudit result = merchWebAuditRepository.getOne(merchWebAudit.getId());
            result.setMerchCusNo(merchWebAudit.getMerchCusNo());
            result.setMerchName(merchWebAudit.getMerchName());
            result.setOfAgent(merchWebAudit.getOfAgent());
            result.setUrl(merchWebAudit.getUrl());
            result.setUrlCanOpen(merchWebAudit.getUrlCanOpen());
            result.setMerchBaseCase(merchWebAudit.getMerchBaseCase());
            result.setGoodsDetail(merchWebAudit.getGoodsDetail());
            result.setICPInfo(merchWebAudit.getICPInfo());
            result.setHotLineServe(merchWebAudit.getHotLineServe());
            result.setManageScope(merchWebAudit.getManageScope());
            if (merchWebAudit.getUploadFile()!=null){
                result.setUploadFile(merchWebAudit.getUploadFile());
            }
            result.setRemark(merchWebAudit.getRemark());
            //更新提交日期
            result.setSubmitDate(new Date());
            merchWebAuditRepository.saveAndFlush(result);
        }catch(Exception e){
            throw new MobaoException("跟新失败！",2);
        }
    }

    @Override
    public void delete(Long id) {
        try{
            merchWebAuditRepository.deleteById(id);
        }catch (Exception e){
            throw new MobaoException("删除失败！",2);
        }
    }

    @Override
    public Page<MerchWebAudit> pageQuery(Integer page, Integer size, MerchWebAudit record) {
        //由于SpringDataJPA是从page是从0开始的，所以将page-1
        page--;
        //默认order by submitDate desc
        if(StringUtil.isBlank(record.getOrderBy())){
            String[] arr = {"submitDate"};
            record.setOrderBy(arr);
        }
        Boolean flag = StringUtil.isBlank(record.getOrder()) || "desc".equalsIgnoreCase(record.getOrder());
        Pageable pageable = new PageRequest(page,size,flag ? Sort.Direction.DESC : Sort.Direction.ASC,record.getOrderBy());
        Page<MerchWebAudit> pageInfo = null;
        try{
            pageInfo = merchWebAuditRepository.findAll((Specification) (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> list = new ArrayList<>();
                if(!StringUtil.isBlank(record.getMerchCusNo())){
                    list.add(criteriaBuilder.equal(root.get("merchCusNo"),record.getMerchCusNo()));
                }
                if(!StringUtil.isBlank(record.getMerchName())){
                    list.add(criteriaBuilder.equal(root.get("merchName"),record.getMerchName()));
                }
                if(!StringUtil.isBlank(record.getStartDate())){
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("submitDate"),record.getStartDate()));
                }
                if(!StringUtil.isBlank(record.getEndDate())){
                    list.add(criteriaBuilder.lessThanOrEqualTo(root.get("submitDate"),record.getEndDate()));
                }
                /*由于criteriaBuilder and方法只支持可变参数，将集合转换成数组*/
                Predicate[] p = new Predicate[list.size()];

                return criteriaBuilder.and(list.toArray(p));
            },pageable);
        }catch(Exception e){
            throw new MobaoException("查询失败",2);
        }
        return pageInfo;
    }
}
