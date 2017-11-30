package com.ideal.audit.warning.dao;

import com.ideal.audit.common.dao.jpa.JpaRepository;
import com.ideal.audit.warning.entity.Rule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chu on 2017/9/19.
 */

@Repository
public interface IRuleDao extends JpaRepository<Rule,Long> {

    @Query(value = "select * from send s where  s.user_name=?1 and s.send_state='0' ", nativeQuery = true)
    public List<Rule> getReadySendInfo(String userName) ;
}
