package com.ideal.audit.sys.dao;

import com.ideal.audit.common.dao.jpa.JpaRepository;
import com.ideal.audit.sys.entity.SysRoleMenuRelation;
import org.springframework.stereotype.Repository;

/**
 * ISysRoleMenuRelationDao 系统角色和菜单关系表
 * JRed(breavecatking@gmail.com)
 * 2016/6/23 18:33
 **/
@Repository
public interface ISysRoleMenuRelationDao extends JpaRepository<SysRoleMenuRelation,Long> {
}
