package com.ideal.audit.sys.dao;

import com.ideal.audit.common.dao.jpa.JpaRepository;
import com.ideal.audit.sys.entity.SysUserRoleRelation;
import org.springframework.stereotype.Repository;

/**
 * 用户与角色关系表dao层
 * Created by snow on 2016/6/20.
 */
@Repository
public interface IUserRoleRelationDao extends JpaRepository<SysUserRoleRelation,Long> {

}
