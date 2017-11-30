package com.ideal.audit.warning.service;

import com.ideal.audit.warning.dao.LogWarningDao;
import com.ideal.audit.warning.entity.log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ybb on 2017/10/11.
 */
@Service
@Transactional
public class LogWarningService {

    @Resource
    private LogWarningDao logWarningDao;

    /**
     * 告警列表
     * @return
     */
   /* public List<LogWarning> getInstance() {
        System.out.println("Service------------------------------");
        return logWarningDao.getLogWarningInfo();
    }*/
    public Page<log> getInfo(log log, Pageable pager){
        StringBuffer dataSql =new StringBuffer("");
        StringBuffer countSql=new StringBuffer("");
        dataSql.append("select id as id,\n" +
                "action_module as actionModule,\n" +
                "action_type as actionType,\n" +
                "user_id as userId,\n" +
                "action_user as actionUser,\n" +
                "action_time as actionTime,\n" +
                "action_info as actionInfo,\n" +
                "open_url as openUrl,\n" +
                "action_result as actionResult,\n" +
                "login_ip as loginIp,\n" +
                "action_date as actionDate from log_warning_infor order by id desc limit " + pager.getOffset() + "," + pager.getPageSize());
        countSql.append("select count(id) from log_warning_infor");
        return logWarningDao.getInfo(log,pager,dataSql.toString(),countSql.toString());
    }
}
