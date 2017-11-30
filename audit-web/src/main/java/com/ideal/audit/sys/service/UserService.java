package com.ideal.audit.sys.service;

import com.ideal.audit.common.security.CredentialsDigest;
import com.ideal.audit.common.security.Digests;
import com.ideal.audit.common.util.BeanUtils;
import com.ideal.audit.common.util.EndecodeUtils;
import com.ideal.audit.sys.dao.IUserDao;
import com.ideal.audit.sys.entity.SysMenu;
import com.ideal.audit.sys.entity.SysRoleMenuRelation;
import com.ideal.audit.sys.entity.SysUser;
import com.ideal.audit.sys.entity.SysUserRoleRelation;
import com.ideal.audit.sys.form.edit.UserEditForm;
import com.ideal.audit.sys.form.search.UserSearchForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;


@Service
@Transactional
public class UserService{
	@Resource
	private IUserDao userDao;
	
	@Autowired
	private CredentialsDigest credentialsDigest;

	public SysUser getById(Long id) {
		return userDao.findOne(id);
	}

	public SysUser getByUsername(String username) {
		return userDao.getByUsername(username);
	}

	public void saveUser(SysUser user) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		if(user.getId()!=null){ //保存修改
			user = userDao.findOne(user.getId());
			BeanUtils.merge(user,user);
			user.setModifyDate(now);
		}else{
			user.setModifyDate(now);
			user.setCreateDate(now);
		}
		if(StringUtils.isNotEmpty(user.getPassword())){
			encryptPassword(user);
		}
		userDao.save(user);
	}

	//加密密码
	private void encryptPassword(SysUser user){
		byte[] saltBytes = Digests.generateSalt(8);
		String salt = EndecodeUtils.encodeHex(saltBytes);
		user.setConfoundCode(salt);
		String oriPwd = user.getPassword();
		user.setPassword(credentialsDigest.digest(oriPwd, saltBytes));
	}

	public void delUserByIds(String[] ids) {
		if(ids!=null&&ids.length>0){
			for(String id:ids){
				SysUser user = userDao.findOne(Long.valueOf(id));
				user.setStatus(2);
				userDao.save(user);
			}
		}		
	}

	public List<SysUserRoleRelation> getUserRoleRelationByUserId(Long userId) {
		return userDao.getUserRoleRelationByUserId(userId);
	}

	public List<SysMenu> getUserSysmenusByUserId(Long userId) {
		List<SysMenu> menus = null;
		//获取当前用户的后台角色
		List<SysUserRoleRelation> urrl = userDao.getUserRoleRelationByUserId(userId);
		if(urrl!=null&&urrl.size()>0){
			SysUserRoleRelation ur = null;
			SysRoleMenuRelation rm = null;
			//使用set集合来去重
			Set<SysMenu> sets = new HashSet<SysMenu>();
			for(int i=0;i<urrl.size();i++){
				ur = urrl.get(i);
				//获取权限点
				Set<SysRoleMenuRelation> rmrl = ur.getRole().getRoleMenus();
				Iterator<SysRoleMenuRelation> its = rmrl.iterator();
				while(its.hasNext()){
					SysRoleMenuRelation rr = its.next();
					sets.add(rr.getMenu());
				}
			}
			menus = new ArrayList<SysMenu>(sets);
		}
		return menus;
	}

	/**
	 * 根据条件分页查询用户数据
	 * @param searchForm
	 * @return Page<SysUser>
	 */
	public Page<SysUser> getPageBySearchForm(UserSearchForm searchForm) {
		return userDao.findPageBySearchForm(searchForm);
	}


}
