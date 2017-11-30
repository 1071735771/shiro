package com.ideal.audit.sys.service;

import com.ideal.audit.common.dao.jpa.DynamicSpecifications;
import com.ideal.audit.common.dao.jpa.SearchFilter;
import com.ideal.audit.common.util.BeanUtils;
import com.ideal.audit.sys.dao.IMenuDao;
import com.ideal.audit.sys.entity.SysMenu;
import com.ideal.audit.sys.util.UserUtils;
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
 * MenuServiceImpl
 * JRed(breavecatking@gmail.com)
 * 2016/6/21 13:03
 **/
@Transactional
@Service
public class MenuService{

    @Resource
    private IMenuDao menuDao;

    /**
     * 根据条件查询所有菜单
     *
     * @param params
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SysMenu> findAllMenuByParams(Map<String, Object> params) {
        List<SysMenu> menuList = null;
        Map<String, SearchFilter> filters = SearchFilter.parse(params);
        Specification<SysMenu> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Sort sort = new Sort("sortNumber");
        menuList = menuDao.findAll(spec, sort);
        return menuList;
    }

    /**
     * 根据id查询一个菜单信息
     *
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SysMenu findOneSysMenu(Long id) {
        return menuDao.findOne(id);
    }

    /**
     * 保存一条菜单信息
     *
     * @param menu@return
     */
    public SysMenu saveOrUpdateSysmenu(SysMenu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0l);
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(menu.getId()==0){//新增
            menu.setCreateDate(now);
            menu.setCreateUser(UserUtils.getCurrentUser().getId());
            menu.setIsDelete("0");
            menu.setIsShow("0");
        }else{//修改
            SysMenu oldMenu = findOneSysMenu(menu.getId());
            menu.setCreateUser(UserUtils.getCurrentUser().getId());
            menu.setCreateDate(now);
            menu.setModifyDate(now);
            menu.setIsDelete("0");
            menu.setModifyUser(UserUtils.getCurrentUser().getId());
        }
        return menuDao.save(menu);
    }

    /**
     * 级联删除菜单及其子菜单
     * 只是标识位删除
     *
     * @param menuId
     * @param parentId
     */
    public void deleteCascade(Long menuId, Long parentId) {
        menuDao.deleteCascade(menuId, parentId);
    }


}
