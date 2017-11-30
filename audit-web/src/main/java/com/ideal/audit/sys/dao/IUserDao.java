package com.ideal.audit.sys.dao;

import com.ideal.audit.common.dao.jpa.JpaRepository;
import com.ideal.audit.sys.entity.SysUser;
import com.ideal.audit.sys.entity.SysUserRoleRelation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
* @Description:用户Dao接口
* @author JRed bravecatking@gmail.com 
 */
@Repository
public interface IUserDao extends JpaRepository<SysUser,Long> {

	/**
	 * 
	* @Title: getByUsername 
	* @Description: 根据用户工号获取用户实体
	* @author JRed bravecatking@gmail.com   
	* @param @return
	* @return SysUser
	* @throws
	 */
	@Query(value = "from SysUser t where t.userName = ?1")
	SysUser getByUsername(String username);
	/**
	 * 
	* @Title: getUserRoleRelationByUserId 
	* @Description: 根据用户ID获取用户与角色关系的集合
	* @author JRed bravecatking@gmail.com   
	* @param @param userId
	* @param @return
	* @return List<SysUserRoleRelation>
	* @throws
	 */
	@Query(value = "from SysUserRoleRelation t where t.user.id=?1")
	List<SysUserRoleRelation> getUserRoleRelationByUserId(Long userId);
	/**
	 * 
	* @Title: delUserRoleRelationByUserId 
	* @Description: 根据用户
	* @author JRed bravecatking@gmail.com   
	* @param @param userId
	* @return void
	* @throws
	 */
	@Query(value="delete from SysUserRoleRelation t where t.user.id=?1")
	@Modifying
	void delUserRoleRelationByUserId(Long userId);


}
