package com.ideal.audit.sys.util;

import com.ideal.audit.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * 
* @Description: 用户工具类
* @author JRed bravecatking@gmail.com 
* @date 2015年11月8日 下午2:41:04
 */
public class UserUtils {
	
	/**
	 * 
	* @Title: getCurrentUser 
	* @Description: 获取当前登录用户
	* @author JRed bravecatking@gmail.com   
	* @param @return
	* @return SysUser
	* @throws
	 */
	public static SysUser getCurrentUser(){
		org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
	    SysUser currentUser = (SysUser)subject.getPrincipal();
	    return currentUser;
	}

}
