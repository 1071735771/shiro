package com.ideal.audit.warning.service;

import com.ideal.audit.sys.util.UserUtils;
import com.ideal.audit.warning.dao.ISendDao;
import com.ideal.audit.warning.entity.Send;
import com.ideal.audit.warning.form.search.SendSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chu on 2017/9/19.
 */
@Service
@Transactional
public class SendService {

    @Resource
    private ISendDao sendDao;

    /**
     * 保存
     * @param send
     */
    public void saveUser(Send send) {
        send.setUserName(UserUtils.getCurrentUser().getUserName());
        send.setSendWay("2");
        send.setSendState("0");
        sendDao.save(send);
    }

    /**
     * 查询
     * @param searchForm
     * @return
     */
    public Page<Send> getPageBySearchForm(SendSearchForm searchForm) {
        searchForm.setEQ_userName(UserUtils.getCurrentUser().getUserName());
        return sendDao.findPageBySearchForm(searchForm);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public Send getById(Long id) {
        return sendDao.findOne(id);
    }

    public void delByIds(String[] ids) {
        if(ids!=null&&ids.length>0){
            for(String id:ids){
                sendDao.delete(Long.parseLong(id));
            }
        }
    }

    /**
     * 查询待发送列表
     * @return
     */
    public List<Send> getReadySendInfo() {
        String userName= UserUtils.getCurrentUser().getUserName();
        return sendDao.getReadySendInfo(userName);
    }
}
