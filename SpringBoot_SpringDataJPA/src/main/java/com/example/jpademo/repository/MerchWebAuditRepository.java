package com.example.jpademo.repository;

import com.example.jpademo.entity.MerchWebAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MerchWebAuditRepository extends JpaRepository<MerchWebAudit,Long>, JpaSpecificationExecutor<MerchWebAudit> {

}
