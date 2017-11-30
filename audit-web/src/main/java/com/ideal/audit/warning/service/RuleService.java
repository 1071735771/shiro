package com.ideal.audit.warning.service;

import com.ideal.audit.common.dao.jpa.DynamicSpecifications;
import com.ideal.audit.common.dao.jpa.SearchFilter;
import com.ideal.audit.sys.util.UserUtils;
import com.ideal.audit.warning.dao.IRuleDao;
import com.ideal.audit.warning.entity.Rule;
import com.ideal.audit.warning.form.search.SendSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by chu on 2017/9/19.
 */
@Service
@Transactional
public class RuleService {

    @Resource
    private IRuleDao ruleDao;

    /**
     * 保存
     * @param rule
     */

    public void saveRule(Rule rule) {
        ruleDao.save(rule);
    }


    /**
     * 查询
     * @param searchForm
     * @return
     */
    public Page<Rule> getPageBySearchForm(SendSearchForm searchForm) {
        searchForm.setEQ_userName(UserUtils.getCurrentUser().getUserName());
        return ruleDao.findPageBySearchForm(searchForm);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public Rule getById(Long id) {
        return ruleDao.findOne(id);
    }

    public void delByIds(String[] ids) {
        if(ids!=null&&ids.length>0){
            for(String id:ids){
                ruleDao.delete(Long.parseLong(id));
            }
        }
    }

    /**
     * 查询待发送列表
     * @return
     */
    public List<Rule> getReadySendInfo() {
        String userName=UserUtils.getCurrentUser().getUserName();
        return ruleDao.getReadySendInfo(userName);
    }

    public Page<Rule> searchRulePage(Map<String, Object> searchParams, Pageable pager) {
        Page<Rule> page = null;
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Rule> specs = DynamicSpecifications.bySearchFilter(filters.values());
        page = ruleDao.findAll(specs,pager);
        return page;
    }
}
