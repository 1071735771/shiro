package com.ideal.audit.warning.dao;

import com.ideal.audit.common.dao.jpa.JpaRepository;
import com.ideal.audit.dike.dto.DikeDto;
import com.ideal.audit.warning.entity.Send;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by chu on 2017/9/19.
 */

@Repository
public interface ISendDao extends JpaRepository<Send,Long> {

    @Query(value = "select * from send s where  s.user_name=?1 and s.send_state='0' ", nativeQuery = true)
    public List<Send> getReadySendInfo(String userName) ;

    @Query(value = "INSERT INTO log_warning_infor (\n" +
            "\taction_module, action_type, action_user, action_time, action_info, action_result, login_ip, action_date\n" +
            ")\n" +
            "VALUES\n" +
            "\t(?1,?2,?3,?4,?5,?6,?7,?8)\n",nativeQuery = true)
    @Modifying@Transactional
    void insertLog(String action_module, String action_type, String action_user, Timestamp action_time, String action_info, String action_result, String login_ip, Date action_date);
}
