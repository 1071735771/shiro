package com.ideal.audit.sys.dao;

import com.ideal.audit.common.dao.jpa.JpaRepository;
import com.ideal.audit.sys.entity.SystemEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *系统字典信息dao层
 */
@Repository
public interface ISystemEnumDao  extends JpaRepository<SystemEnum,Integer> {
    /**
     * 根据group获取数据
     * */
    @Query(value = "select * from system_enum se where  se.groups=?1 ", nativeQuery = true)
    public List<SystemEnum> findSystemEnumByGroup(Long group);

}
