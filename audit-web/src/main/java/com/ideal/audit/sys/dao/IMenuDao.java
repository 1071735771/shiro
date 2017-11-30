package com.ideal.audit.sys.dao;

import com.ideal.audit.common.dao.jpa.JpaRepository;
import com.ideal.audit.sys.entity.SysMenu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统菜单dao
 * Created by snow on 2016/6/21.
 */
@Repository
public interface IMenuDao extends JpaRepository<SysMenu,Long> {

    /**
     * 查询一级菜单
     * @return
     */
    @Query(value = "from SysMenu t where t.isShow=0 and t.isDelete ='0' and t.parentId = 0 order by sortNumber")
    List<SysMenu> findTopMenuList();

    /**
     * 级联删除菜单及其子菜单
     * 只是标识位删除
     * @param menuId
     */
    @Query(value = "update SysMenu set isDelete = '1' where id=?1 or parentId = ?2 ")
    @Modifying
    void deleteCascade(Long menuId, Long parentId);

}
