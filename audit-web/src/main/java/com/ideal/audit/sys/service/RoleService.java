package com.ideal.audit.sys.service;


import com.ideal.audit.common.dao.jpa.DynamicSpecifications;
import com.ideal.audit.common.dao.jpa.SearchFilter;
import com.ideal.audit.sys.dao.IMenuDao;
import com.ideal.audit.sys.dao.IRoleDao;
import com.ideal.audit.sys.dao.ISysRoleMenuRelationDao;
import com.ideal.audit.sys.entity.SysMenu;
import com.ideal.audit.sys.entity.SysRole;
import com.ideal.audit.sys.entity.SysRoleMenuRelation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * RoleServiceImpl
 * JRed(breavecatking@gmail.com)
 * 2016/6/23 15:42
 **/
@Transactional
@Service
public class RoleService  {
    @Resource
    private IRoleDao roleDao;
    @Resource
    private IMenuDao menuDao;
    @Resource
    private ISysRoleMenuRelationDao roleMenuDao;


    /**
     * 根据条件分页查询菜单数据
     * @param params
     * @param pageable
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SysRole> findRolePageByParams(Map<String, Object> params, Pageable pageable, String sortName, String sortValue) {
        Page<SysRole> rolePage = null;
        if(sortName!=null&&sortValue!=null){
            Integer pageNumber = pageable.getPageNumber();
            Integer pageSize = pageable.getPageSize();
            if(sortValue.equals("asc")) {
                pageable = new PageRequest(pageNumber,pageSize, Sort.Direction.ASC, sortName);
            }else {
                pageable = new PageRequest(pageNumber,pageSize,Sort.Direction.DESC, sortName);
            }
        }
        if(params == null){
            rolePage = roleDao.findAll(pageable);
        }else{
            Map<String,SearchFilter>  filters = SearchFilter.parse(params);
            Specification<SysRole> spec = DynamicSpecifications.bySearchFilter(filters.values());
            rolePage = roleDao.findAll(spec, pageable);
        }
        return rolePage;
    }

    /**
     * 根据id查询一个菜单信息
     *
     * @param id
     * @return
     */
    public SysRole findOneRole(Long id) {
        return roleDao.findOne(id);
    }

    /**
     * 保存一条菜单信息
     * @param role
     * @return
     */
    public SysRole saveOrUpdateRole(SysRole role,String permIds) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(role.getId()!=0){ //保存修改
            SysRole old = roleDao.findOne(role.getId());
            role.setModifyDate(now);
            role.setCreateDate(old.getCreateDate());
            if(StringUtils.isBlank(permIds)){
                roleDao.delTRoleMenuRelationByRoleId(role.getId());
            }
        }else{
            role.setModifyDate(now);
            role.setCreateDate(now);
        }
        //权限集合部分
        if(StringUtils.isNotBlank(permIds)){
            String[] ids = permIds.split(",");
            if(ids!=null&&ids.length>0){
                //先删除角色与权限菜单的映射关系
                roleDao.delTRoleMenuRelationByRoleId(role.getId());
                SysRoleMenuRelation rml = null;
                for(String id:ids){
                    if("0".equals(id))continue;
                    SysMenu menu = menuDao.findOne(Long.parseLong(id));
                    rml = new SysRoleMenuRelation();
                    rml.setRole(role);
                    rml.setMenu(menu);
                    roleMenuDao.save(rml);
                }
            }
        }
        return roleDao.save(role);
    }

    /**
     * 删除角色数据
     * @param roleId
     */

    public void deleteRole(Long roleId) {
       roleDao.delete(roleId);
    }

    /**
     * @param roleId
     * @return List<SysRoleMenuRelation>
     * @throws
     * @Title: getRoleMenuRelationsByRoleId
     * @Description: 根据角色ID获取角色与权限菜单的映射关系集合
     * @author JRed bravecatking@gmail.com
     */

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SysRoleMenuRelation> getRoleMenuRelationsByRoleId(Long roleId) {
        return roleDao.getRoleMenuRelationsByRoleId(roleId);
    }

    /**
     * 批量删除角色
     * @param ids
     */

    public void delRoleByIds(String[] ids) {
        if(ids!=null){
            for(String id:ids){
                SysRole role = roleDao.findOne(Long.parseLong(id));
                role.setIsShow(0);
                roleDao.save(role);
            }
        }
    }

    /**
     * 根据条件获取角色
     * @param params
     * @return
     */

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SysRole> getRolesByParams( Map<String,Object>  params) {
        List<SysRole> roleList = null;
        Map<String,SearchFilter>  filters = SearchFilter.parse(params);
        Specification<SysRole> spec = DynamicSpecifications.bySearchFilter(filters.values());
        roleList = roleDao.findAll(spec);
        return roleList;
    }
}
