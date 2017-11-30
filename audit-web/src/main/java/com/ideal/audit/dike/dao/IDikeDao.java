package com.ideal.audit.dike.dao;

import com.ideal.audit.dike.dto.*;
import org.jruby.RubyIO$s$0$2$sysopen;
import org.jruby.RubyProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by chu on 2017/9/30.
 */
@Repository
public class IDikeDao {

    @PersistenceContext
    EntityManager entityManager = null;

    @PersistenceContext
    private EntityManager em;

    /**
     * @Author:  ybb
     * @Description :  获取场景一日志类型
     * @Date:   2017/11/21  17:54
     */
    public Page<Sence_one> searchOveride(Sence_one so,Pageable pager,String dataSql,String countSql){
        Query dataQuery = em.createNativeQuery(dataSql);
        Query countQuery = em.createNativeQuery(countSql);
        Number totalSize = (Number)countQuery.getResultList().get(0);
        List<Sence_one> data = dataQuery.getResultList();

        Page<Sence_one> page = new PageImpl<Sence_one>(data, pager, totalSize.longValue());
        return page;
    }
      /**
       * @Author:  ybb
       * @Description : 获取场景二日志类型
       * @Date:   2017/11/22  14:36
       */
      public Page<Sence_two> searchTwo(Sence_two so, Pageable pager, String dataSql, String countSql){
          Query dataQuery = em.createNativeQuery(dataSql);
          Query countQuery = em.createNativeQuery(countSql);
          Number totalSize = (Number)countQuery.getResultList().get(0);
          List<Sence_two> data = dataQuery.getResultList();
          Page<Sence_two> page = new PageImpl<Sence_two>(data, pager, totalSize.longValue());
          return page;
      }
    /**
     * @Author:  ybb
     * @Description :获取场景三日志类型
     * @Date:   2017/11/22  14:37
     */
    public Page<Sence_three> searchThree(Sence_three so, Pageable pager, String dataSql, String countSql){
        Query dataQuery = em.createNativeQuery(dataSql);
        Query countQuery = em.createNativeQuery(countSql);
        Number totalSize = (Number)countQuery.getResultList().get(0);
        List<Sence_three> data = dataQuery.getResultList();
        Page<Sence_three> page = new PageImpl<Sence_three>(data, pager, totalSize.longValue());

        return page;
    }

    /**
     * @Author:  ybb
     * @Description : 场景四
     * @Date:   2017/11/27  16:10
     */
    public Page<Sence_four> searchFour(Sence_four so, Pageable pager, String dataSql, String countSql){
        Query dataQuery = em.createNativeQuery(dataSql);
        Query countQuery = em.createNativeQuery(countSql);
        Number totalSize = (Number)countQuery.getResultList().get(0);
        List<Sence_four> data = dataQuery.getResultList();
        Page<Sence_four> page = new PageImpl<Sence_four>(data, pager, totalSize.longValue());

        return page;
    }

    /**
     * @Author:  ybb
     * @Description : 场景五
     * @Date:   2017/11/27  16:10
     */
    public Page<Sence_five> searchFive(Sence_five so, Pageable pager, String dataSql, String countSql){
        Query dataQuery = em.createNativeQuery(dataSql);
        Query countQuery = em.createNativeQuery(countSql);
        Number totalSize = (Number)countQuery.getResultList().get(0);
        List<Sence_five> data = dataQuery.getResultList();
        Page<Sence_five> page = new PageImpl<Sence_five>(data, pager, totalSize.longValue());

        return page;
    }

    /**
     * @Author:  ybb
     * @Description :获取场景六日志类型
     * @Date:   2017/11/22  14:37
     */
    public Page<Sence_six> searchSix(Sence_six so, Pageable pager, String dataSql, String countSql){
        Query dataQuery = em.createNativeQuery(dataSql);
        Query countQuery = em.createNativeQuery(countSql);
        Number totalSize = (Number)countQuery.getResultList().get(0);
        List<Sence_six> data = dataQuery.getResultList();
        Page<Sence_six> page = new PageImpl<Sence_six>(data, pager, totalSize.longValue());
        return page;
    }

    /**
     * @Author:  ybb
     * @Description : 场景七
     * @Date:   2017/11/27  16:10
     */
    public Page<Sence_seven> searchSeven(Sence_seven so, Pageable pager, String dataSql, String countSql){
        Query dataQuery = em.createNativeQuery(dataSql);
        Query countQuery = em.createNativeQuery(countSql);
        Number totalSize = (Number)countQuery.getResultList().get(0);
        List<Sence_seven> data = dataQuery.getResultList();
        Page<Sence_seven> page = new PageImpl<Sence_seven>(data, pager, totalSize.longValue());

        return page;
    }

    /**
     * @Author:  ybb
     * @Description : web文本采集规范
     * @Date:   2017/11/28  14:24
     */
    public Page<TextCollection> getTextCollection(TextCollection so, Pageable pager, String dataSql, String countSql){
        Query dataQuery = em.createNativeQuery(dataSql);
        Query countQuery = em.createNativeQuery(countSql);
        Number totalSize = (Number)countQuery.getResultList().get(0);
        List<TextCollection> data = dataQuery.getResultList();
        Page<TextCollection> page = new PageImpl<TextCollection>(data, pager, totalSize.longValue());

        return page;
    }
    @SuppressWarnings("unchecked")
    public Page<DikeDto> search(DikeDto dikeDto, Pageable pager, String dataSql, String countSql) {
        Query dataQuery = em.createNativeQuery(dataSql);
        Query countQuery = em.createNativeQuery(countSql);
        Number length=null;
        try {
             length = (Number)countQuery.getResultList().get(0);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("哦卧槽");
        }

        long totalSize = length.longValue();
        List<DikeDto> data = dataQuery.getResultList();
        Page<DikeDto> page = new PageImpl<DikeDto>(data, pager, totalSize);
        return page;
    }

    @SuppressWarnings("unchecked")
    public Page<DikeDto> searchOveride(DikeDto dikeDto, Pageable pager, List<String> dataSql) {
        List<DikeDto> resultList = new ArrayList<DikeDto>();
        Query dataQuery;
        for (int i=0;i<dataSql.size();i++){
            dataQuery = em.createNativeQuery(dataSql.get(i));
            List<DikeDto> data = dataQuery.getResultList();
            for (int j=0;j<data.size();j++){
                resultList.add(data.get(j));
               // System.out.println(data.get(j).getAction_user()+">>>>>>>>>>>>>>>>>>>>>>");
            }
        }
        Page<DikeDto> page = new PageImpl<DikeDto>(resultList, pager, resultList.size());
        return page;
    }

    @SuppressWarnings("unchecked")
    public List<DikeDto> getData(String dataSql) {
        List<DikeDto> dikeDtoList = new ArrayList<DikeDto>();
        Query dataQuery = em.createNativeQuery(dataSql);
        List<Object> dtList =dataQuery.getResultList();
        for (Object obj : dtList) {
            DikeDto d=new DikeDto();
            Object[] objs = (Object[]) obj;
            String action_module = (String) objs[2];
            String action_type = (String) objs[3];
            String action_user = (String) objs[5];
            String action_info=(String) objs[7];
            Timestamp action_time=(Timestamp) objs[6];
            String action_result=(String) objs[9];
            String login_ip=(String) objs[10];
            Date action_date=(Date) objs[11];
            d.setAction_info(action_info);
            d.setAction_date(action_date);
            d.setAction_module(action_module);
            d.setAction_result(action_result);
            d.setAction_time(action_time);
            d.setAction_type(action_type);
            d.setAction_user(action_user);
            d.setLogin_ip(login_ip);
            dikeDtoList.add(d);
        }
        System.out.println(dikeDtoList.toString());
        return dikeDtoList;

    }

    public Long checkSameTime(String dataSql) {
        Query dataQuery =em.createNativeQuery(dataSql);
        Number length = (Number) dataQuery.getResultList().get(0);
        long dtst = length.longValue();
        return dtst;
    }

    public long checkIp(String dataSql) {
        Query dataQuery =em.createNativeQuery(dataSql);
        Number length = (Number) dataQuery.getResultList().get(0);
        long checkIp=length.longValue();
        return checkIp;
    }

    public String getInterval_time(String s) {
        Query dataQuery =em.createNativeQuery(s);
        List<String> interval_times=dataQuery.getResultList();
        String interval_time=interval_times.get(0);
        return interval_time;
    }

    public Timestamp getTime(String  s) {
        Query dataQuery =em.createNativeQuery(s);
        List<Timestamp> as=dataQuery.getResultList();
        Timestamp a=as.get(0);
        return a;
    }

    public int getError_try_number(String dataSql) {
        Query dataQuery =em.createNativeQuery(dataSql);
        List<Integer> error_try_numbers=dataQuery.getResultList();
        int error_try_number=error_try_numbers.get(0);
        return error_try_number;
    }

    public long checkdeflog(String s) {
        Query dataQuery =em.createNativeQuery(s);
        Number length = (Number) dataQuery.getResultList().get(0);
        long deflog=length.longValue();
        return deflog;
    }

    public int getCommon_number(String s) {
        Query dataQuery =em.createNativeQuery(s);
        List<Integer> common_numbers=dataQuery.getResultList();
        int common_number=common_numbers.get(0);
        return common_number;
    }

    public long checkLogtime(String s) {
        Query dataQuery =em.createNativeQuery(s);
        Number length = (Number) dataQuery.getResultList().get(0);
        long logtimes=length.longValue();
        return logtimes;
    }

    public List<DikeDto> getMlData(String s) {
        List<DikeDto> dikeDtoList = new ArrayList<DikeDto>();
        Query dataQuery = em.createNativeQuery(s);
        List<Object> dtList =dataQuery.getResultList();
        for (Object obj : dtList) {
            DikeDto d=new DikeDto();
            Object[] objs = (Object[]) obj;
            String action_module = (String) objs[1];
            String action_type = (String) objs[2];
            String action_user = (String) objs[4];
            String action_info=(String) objs[6];
            Timestamp action_time=(Timestamp) objs[5];
            String action_result=(String) objs[8];
            String login_ip=(String) objs[9];
            Date action_date=(Date) objs[10];
            d.setAction_info(action_info);
            d.setAction_date(action_date);
            d.setAction_module(action_module);
            d.setAction_result(action_result);
            d.setAction_time(action_time);
            d.setAction_type(action_type);
            d.setAction_user(action_user);
            d.setLogin_ip(login_ip);
            dikeDtoList.add(d);
        }
        return dikeDtoList;
    }

    public long getWarning(String s) {
        Query dataQuery =em.createNativeQuery(s);
        Number length = (Number) dataQuery.getResultList().get(0);
        long deflog=length.longValue();
        return deflog;
    }

    public String getSend_email(String s) {
        Query dataQuery =em.createNativeQuery(s);
        List<String> interval_times=dataQuery.getResultList();
        String interval_time=interval_times.get(0);
        return interval_time;
    }
}
