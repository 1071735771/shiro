package com.ideal.audit.sys.dao;

import com.ideal.audit.common.dao.jpa.JpaRepository;
import com.ideal.audit.sys.entity.SysRole;
import com.ideal.audit.sys.entity.SysRoleMenuRelation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色dao层
 * Created by snow on 2016/6/20.
 */
@Repository
public interface IRoleDao extends JpaRepository<SysRole,Long> {

    /**
     *
     * @Title: getRoleMenuRelationsByRoleId
     * @Description: 根据角色ID获取角色与权限菜单的映射关系集合
     * @author JRed bravecatking@gmail.com
     * @param @param roleId
     * @param @return
     * @return List<SysRoleMenuRelation>
     * @throws
     */
    @Query("from SysRoleMenuRelation t where t.role.id = ?1")
    List<SysRoleMenuRelation> getRoleMenuRelationsByRoleId(Long roleId);

    /**
     *
     * @Title: delTRoleMenuRelationByRoleId
     * @Description: 根据角色ID删除角色与权限菜单的映射关系
     * @author JRed bravecatking@gmail.com
     * @param @param roleId
     * @return void
     * @throws
     */
    @Query("delete from SysRoleMenuRelation t where t.role.id = ?1")
    @Modifying
    void delTRoleMenuRelationByRoleId(Long roleId);



}
