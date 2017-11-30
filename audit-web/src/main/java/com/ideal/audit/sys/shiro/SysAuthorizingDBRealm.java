package com.ideal.audit.sys.shiro;

import com.ideal.audit.common.security.CredentialsDigest;
import com.ideal.audit.common.security.CredentialsMatcherAdapter;
import com.ideal.audit.common.util.EndecodeUtils;
import com.ideal.audit.sys.service.UserService;
import com.ideal.audit.sys.entity.SysRoleMenuRelation;
import com.ideal.audit.sys.entity.SysUser;
import com.ideal.audit.sys.entity.SysUserRoleRelation;
import com.ideal.audit.sys.shiro.session.SessionDAO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * shiro 认证回调realm
 */

@Component("shiroDbRealm")
public class SysAuthorizingDBRealm extends AuthorizingRealm implements InitializingBean{
    
	private Logger log = LoggerFactory.getLogger(SysAuthorizingDBRealm.class);


	@Resource
	protected UserService userService;

	@Resource
	private CredentialsDigest credentialsDigest;

	@Resource
	private SessionDAO sessionDao;
	
	


	@Override
	public void afterPropertiesSet() throws Exception {
		CredentialsMatcher matcher = new CredentialsMatcherAdapter(
				credentialsDigest);
		setCredentialsMatcher(matcher);
	}

	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		if (user != null) {
			//超级管理员
			if (user.getId()==1) {
				auth.addRole("superadmin");
				auth.addStringPermission("*");
			}else{
				//获取当前用户的后台角色
				List<SysUserRoleRelation> urrl = userService.getUserRoleRelationByUserId(user.getId());
				if(urrl!=null&&urrl.size()>0){
					SysUserRoleRelation ur = null;
					for(int i=0;i<urrl.size();i++){
						ur = urrl.get(i);
						auth.addRole(ur.getRole().getRoleName());
						//获取权限点
						Set<SysRoleMenuRelation> rmrl = ur.getRole().getRoleMenus();
						Iterator<SysRoleMenuRelation> its = rmrl.iterator();
						while(its.hasNext()){
							SysRoleMenuRelation rr = its.next();
							if(StringUtils.isNotBlank(rr.getMenu().getPermission())){
								auth.addStringPermission(rr.getMenu().getPermission());
							}
							
						}
					}
				}
				
			}
		}
		return auth;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		LoginToken authurtoken = (LoginToken)token;
		SysUser user = userService.getByUsername(authurtoken.getUsername());
		// 前后台登录共用，非管理员也可登录。
		if (user != null ) {
			byte[] salt =  EndecodeUtils.decodeHex(user.getConfoundCode());
			return new SimpleAuthenticationInfo(user, user.getPassword(),
					ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}

}
