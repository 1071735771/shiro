
package com.ideal.audit.dike.service;

import com.ideal.audit.dike.dao.IDikeDao;
import com.ideal.audit.dike.dto.*;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chu on 2017/9/30.
 */

@Service
@Transactional
public class DikeService {

    @Resource
    private IDikeDao IDikeDao;

    /**
     * @Author:  ybb
     * @Description : 场景一
     * @Date:   2017/11/21  17:57
     */
    public Page<Sence_one> getResourceAsOne(Sence_one so,Pageable pager,String type,String userName,String startTime,String endTime){
        StringBuffer dataSql = new StringBuffer("");
        StringBuffer countSql = new StringBuffer("");
        if (null != type && !"".equals(type)) {
            dataSql.append("select userName ,opTime, cmd, count from senceone where type ="+"'"+type+"'");
            countSql.append("select count(1) from senceone where type ="+"'"+type+"'");
        }else {
            dataSql.append("select userName ,opTime, cmd, count from senceone_copy where 1=1");
            countSql.append("select count(1) from senceone_copy where 1=1");
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("userName",userName);
        dataSql = JointStr(dataSql,map);
        countSql = JointStr(countSql,map);
        dataSql = JointTime(dataSql,"optime",startTime,endTime);
        countSql = JointTime(countSql,"optime",startTime,endTime);

        dataSql.append("  limit " + pager.getOffset() + "," + pager.getPageSize());
        System.out.println(dataSql.toString()+">>>>>>");
        System.out.println(countSql.toString()+">>>>>>>>");
        Page<Sence_one> page =  IDikeDao.searchOveride(so,pager,dataSql.toString(),countSql.toString());
        return page;
    }

    /**
     * @Author:  ybb
     * @Description :场景二
     * @Date:   2017/11/22  15:19
     */
    public Page<Sence_two> getResourceAsTwo(Sence_two so, Pageable pager, String portalsUser,String Hname ,String startTime, String endTime) {
        StringBuffer dataSql = new StringBuffer("select portalsUser ,Hname,opTime, db,tb,cmd from sencetwo where 1=1 ");
        StringBuffer countSql = new StringBuffer("select count(1) from sencetwo where 1=1 ");
        Map<String,String> map = new HashMap<String, String>();
        map.put("portalsUser",portalsUser);
        map.put("Hname",Hname);
        dataSql = JointStr(dataSql, map);
        dataSql = JointTime(dataSql, "opTime", startTime, endTime);
        countSql = JointStr(countSql, map);
        countSql = JointTime(countSql, "opTime", startTime, endTime);
        dataSql.append("  limit " + pager.getOffset() + "," + pager.getPageSize());
        System.out.println("<<<"+dataSql+">>");
        System.out.println("<<<"+countSql+">>");
        Page<Sence_two> page = IDikeDao.searchTwo(so, pager, dataSql.toString(), countSql.toString());
        return page;
    }

    /**
     * @Author:  ybb
     * @Description : 场景三
     * @Date:   2017/11/22  15:35
     */
    public Page<Sence_three> getResourceAsThree(Sence_three so, Pageable pager, String webusername, String noticeId , String startTime, String endTime) {
        StringBuffer dataSql = new StringBuffer("select webusername,noticeId,host,noticeDescription,time,noticeType from sencethree where 1=1 ");
        StringBuffer countSql = new StringBuffer("select count(1) from sencethree where 1=1 ");
        Map<String,String> map = new HashMap<String, String>();
        map.put("webusername",webusername);
        map.put("noticeId",noticeId);
        dataSql = JointStr(dataSql, map);
        dataSql = JointTime(dataSql, "time", startTime, endTime);
        countSql = JointStr(countSql, map);
        countSql = JointTime(countSql, "time", startTime, endTime);
        dataSql.append("  limit " + pager.getOffset() + "," + pager.getPageSize());
        Page<Sence_three> page = IDikeDao.searchThree(so, pager, dataSql.toString(), countSql.toString());

        return page;
    }

    /**
     * @Author:  ybb
     * @Description : 场景四
     * @Date:   2017/11/27  16:35
     */
    public Page<Sence_four> getResourceAsFour(Sence_four so, Pageable pager, String opUser, String sysName , String startTime, String endTime) {
        StringBuffer dataSql = new StringBuffer("select opUser,opTime,sysName,moduleCode,buttonCode,opType,opResult from sencefour where 1=1 ");
        StringBuffer countSql = new StringBuffer("select count(1) from sencefour where 1=1 ");
        Map<String,String> map = new HashMap<String, String>();
        map.put("opUser",opUser);
        map.put("sysName",sysName);
        dataSql = JointStr(dataSql, map);
        dataSql = JointTime(dataSql, "opTime", startTime, endTime);
        countSql = JointStr(countSql, map);
        countSql = JointTime(countSql, "opTime", startTime, endTime);
        dataSql.append("  limit " + pager.getOffset() + "," + pager.getPageSize());
        Page<Sence_four> page = IDikeDao.searchFour(so, pager, dataSql.toString(), countSql.toString());

        return page;
    }

    /**
     * @Author:  ybb
     * @Description : 场景五
     * @Date:   2017/11/22  16:02
     */
    public Page<Sence_five> getResourceAsFive(Sence_five so, Pageable pager, String webName, String sysCodeAndName,String terminalIP, String startTime, String endTime) {
        StringBuffer dataSql = new StringBuffer("SELECT webName,sysCodeAndName,moduCodeAndName,buttNameresultSet,terminalIP,opTime,InterFaceName,opResul FROM senceFive where 1=1 ");
        StringBuffer countSql = new StringBuffer("select count(1) from senceFive where 1=1 ");
        Map<String,String> map = new HashMap<String, String>();
        map.put("webName",webName);
        map.put("sysCodeAndName",sysCodeAndName);
        map.put("terminalIP",terminalIP);
        dataSql = JointStr(dataSql, map);
        dataSql = JointTime(dataSql, "opTime", startTime, endTime);
        countSql = JointStr(countSql, map);
        countSql = JointTime(countSql, "opTime", startTime, endTime);
        dataSql.append("  limit " + pager.getOffset() + "," + pager.getPageSize());
        Page<Sence_five> page = IDikeDao.searchFive(so, pager, dataSql.toString(), countSql.toString());
        return page;
    }
    /**
     * @Author:  ybb
     * @Description :场景六
     * @Date:   2017/11/27  16:45
     */
    public Page<Sence_six> getResourceAsSix(Sence_six so, Pageable pager, String username, String interfacename , String startTime, String endTime) {
        StringBuffer dataSql = new StringBuffer("select username,optime,interfacename,result,count from sencesix where 1=1 ");
        StringBuffer countSql = new StringBuffer("select count(1) from sencesix where 1=1 ");
        Map<String,String> map = new HashMap<String, String>();
        map.put("username",username);
        map.put("interfacename",interfacename);
        dataSql = JointStr(dataSql, map);
        dataSql = JointTime(dataSql, "optime", startTime, endTime);
        countSql = JointStr(countSql, map);
        countSql = JointTime(countSql, "optime", startTime, endTime);
        dataSql.append("  limit " + pager.getOffset() + "," + pager.getPageSize());
        Page<Sence_six> page = IDikeDao.searchSix(so, pager, dataSql.toString(), countSql.toString());
        return page;
    }
    /**
     * @Author:  ybb
     * @Description :场景七
     * @Date:   2017/11/27  16:45
     */
    public Page<Sence_seven> getResourceAsSeven(Sence_seven so, Pageable pager, String opUser, String src , String startTime, String endTime) {
        StringBuffer dataSql = new StringBuffer("select opUser,optime,src from senceseven where 1=1 ");
        StringBuffer countSql = new StringBuffer("select count(1) from senceseven where 1=1 ");
        Map<String,String> map = new HashMap<String, String>();
        map.put("opUser",opUser);
        map.put("src",src);
        dataSql = JointStr(dataSql, map);
        dataSql = JointTime(dataSql, "opTime", startTime, endTime);
        countSql = JointStr(countSql, map);
        countSql = JointTime(countSql, "opTime", startTime, endTime);
        dataSql.append("  limit " + pager.getOffset() + "," + pager.getPageSize());
        Page<Sence_seven> page = IDikeDao.searchSeven(so, pager, dataSql.toString(), countSql.toString());
        return page;
    }

    /**
     * @Author:  ybb
     * @Description : web文本采集规范
     * @Date:   2017/11/28  14:26
     */
    public  Page<TextCollection> getResourceAsText(TextCollection tc, Pageable pager, String optype, String username , String startTime, String endTime) {

        StringBuffer dataSql = new StringBuffer("");
        StringBuffer countSql = new StringBuffer("");
        Map map = new HashMap();
        //if (optype == "1" || "1".equals(optype) || optype ==null || "".equals(optype)){

            dataSql.append("SELECT username ,optime ,(SELECT name from tb_syscode WHERE id=a1.syscode) AS syscode ,(SELECT name from tb_btcode WHERE id=a1.btcode) AS btcode, (SELECT name from tb_modulecode WHERE id=a1.mdcode) AS mdcode ,optype, opinfo ,opresult, ip FROM web_log_A1 a1 WHERE 1=1 ");
            countSql.append("SELECT count(1) FROM web_log_A1 WHERE 1=1 ");

//        }else {
//            dataSql.append("SELECT username ,optime ,(SELECT name from tb_syscode WHERE id=a1.syscode) AS syscode ,(SELECT name from tb_btcode WHERE id=a1.btcode) AS btcode, (SELECT name from tb_modulecode WHERE id=a1.mdcode) AS mdcode ,optype, opinfo ,opresult, ip FROM web_log_A2 a1 WHERE 1=1 ");
//            countSql.append("SELECT count(1) FROM web_log_A2 WHERE 1=1 ");
//
//        }
        map.put("username",username);
        dataSql = JointStr(dataSql,map);
        dataSql = JointTime(dataSql,"optime",startTime,endTime);
        countSql = JointStr(countSql,map);
        countSql = JointTime(countSql,"optime",startTime,endTime);
        dataSql.append("ORDER BY optime DESC  limit " + pager.getOffset() + "," + pager.getPageSize());
        Page<TextCollection> pages = IDikeDao.getTextCollection(tc,pager,dataSql.toString(),countSql.toString());
        return  pages;
    }

    /**
     * @Author:  ybb
     * @Description :
     * @Date:   2017/11/22  15:21
     */
    /******************************************************************************************************************/
    public static  StringBuffer JointStr(StringBuffer sql,Map<String,String> map){
        for (String key:map.keySet()){
            if ((null != map.get(key) && !"".equals(map.get(key)))){
                sql.append(" and " + key + " =" + "'" + map.get(key) + "'");
            }
        }
        return sql;
    }
    public static StringBuffer JointTime(StringBuffer sql,String str,String startTime,String endTime){
        if (null != startTime && !"".equals(startTime)){
            sql.append(" and "+str +" >=  '"+startTime+"'");
        }
        if (null != endTime && !"".equals(endTime)){
            sql.append(" and "+str +" <= '"+endTime+"'");
        }
        return sql;
    }
    /******************************************************************************************************************/
    /**
     * 动态拼接查询条件
     * @param dikeDto
     * @param pager
     * @param loginIP
     * @param actionUser
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    public Page<DikeDto> getData(DikeDto dikeDto, Pageable pager, String loginIP, String actionUser, String startTime, String endTime,String type) {
        /**
         * 查询最近三个月的表
         */
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        StringBuffer dataSql = new StringBuffer("");
        StringBuffer countSql= new StringBuffer("");

        String suffix = year+""+(month+1<10?"0"+(month+1):month+1);
        System.out.println(suffix+"---------------------------------");
        //当前月
        String tools_one = "dop_dev_tools_"+suffix;
        String loginfo_one = "dop_mining_loginfo_"+suffix;
        String openapi_one = "dop_openapi_"+suffix;
        //上月
        suffix =  year+""+(month<10?"0"+month:month);
        System.out.println(suffix+"---------------------------------");
        String tools_two = "dop_dev_tools_"+suffix;
        String loginfo_two = "dop_mining_loginfo_"+suffix;
        String openapi_two = "dop_openapi_"+suffix;
        //上上月
        suffix = year+""+(month-1<10?"0"+(month-1):month-1);
        System.out.println(suffix+"---------------------------------");
        String tools_three = "dop_dev_tools_"+suffix;
        String loginfo_three = "dop_mining_loginfo_"+suffix;
        String openapi_three = "dop_openapi_"+suffix;

        String condition1="";
        String condition2="";
        String condition3="";
        String condition4="";
        if (!StringUtils.isEmpty(loginIP)) {
            condition1=" and login_ip = '" + loginIP + "'";
        }
        if (!StringUtils.isEmpty(actionUser)) {
            condition2=" and action_user = '" + actionUser + "'";
        }
        if (!StringUtils.isEmpty(startTime)) {
            condition3=" and action_time > '" + startTime + "'";
        }
        if (!StringUtils.isEmpty(endTime)) {
            condition4= " and action_time  < '" + endTime + "'";
        }

        System.out.println("#####################################################################################");
        System.out.println(loginIP+"--"+actionUser+"---"+startTime+"--------"+endTime+"----");
        System.out.println("#####################################################################################");
        if(StringUtils.isEmpty(type)){
            type="0";
        }

        if(!StringUtils.isEmpty(type)){
            if("1".equals(type)){
                StringBuffer sql = this.getSql(tools_one,tools_two,tools_three);
                dataSql = this.AddOtherLike(sql,pager,loginIP,actionUser,startTime,endTime);
                countSql = this.AddOtherLike(this.getCount(tools_one,tools_two,tools_three),pager,loginIP,actionUser,startTime,endTime);
            }else if("2".equals(type)){
                dataSql = this.AddOtherLike(this.getSql(loginfo_one,loginfo_two,loginfo_three),pager,loginIP,actionUser,startTime,endTime);
                countSql = this.AddOtherLike(this.getCount(loginfo_one,loginfo_two,loginfo_three),pager,loginIP,actionUser,startTime,endTime);
            }else if("3".equals(type)){
                dataSql = this.AddOtherLike(this.getSql(openapi_one,openapi_two,openapi_three),pager,loginIP,actionUser,startTime,endTime);
                countSql = this.AddOtherLike(this.getCount(openapi_one,openapi_two,openapi_three),pager,loginIP,actionUser,startTime,endTime);
            }else{
                dataSql.append("select * from (SELECT a.action_module AS action_module,a.action_type AS action_type,a.action_user AS action_user,a.action_time AS action_time,a.action_info AS action_info,a.action_result AS action_result,a.login_ip AS login_ip,a.action_date AS action_date FROM " +tools_one+  " AS a UNION ALL SELECT b.action_module AS action_module,b.action_type AS action_type,b.action_user AS action_user,b.action_time AS action_time,b.action_info AS action_info,b.action_result AS action_result,b.login_ip AS login_ip,b.action_date AS action_date FROM "+loginfo_one+" AS b UNION ALL SELECT c.action_module AS action_module,c.action_type AS action_type,c.action_user AS action_user,c.action_time AS action_time,c.action_info AS action_info,c.action_result AS action_result,c.login_ip AS login_ip,c.action_date AS action_date FROM "+openapi_one+" AS c UNION ALL "+
                "SELECT d.action_module AS action_module,d.action_type AS action_type,d.action_user AS action_user,d.action_time AS action_time,d.action_info AS action_info,d.action_result AS action_result,d.login_ip AS login_ip,d.action_date AS action_date FROM " +tools_two+  " AS d UNION ALL SELECT e.action_module AS action_module,e.action_type AS action_type,e.action_user AS action_user,e.action_time AS action_time,e.action_info AS action_info,e.action_result AS action_result,e.login_ip AS login_ip,e.action_date AS action_date FROM "+loginfo_two+" AS e UNION ALL SELECT f.action_module AS action_module,f.action_type AS action_type,f.action_user AS action_user,f.action_time AS action_time,f.action_info AS action_info,f.action_result AS action_result,f.login_ip AS login_ip,f.action_date AS action_date FROM "+openapi_two+" AS f UNION ALL "+
                "SELECT g.action_module AS action_module,g.action_type AS action_type ,g.action_user AS action_user,g.action_time AS action_time,g.action_info AS action_info,g.action_result AS action_result,g.login_ip AS login_ip,g.action_date AS action_date FROM " +tools_three+  " AS g UNION ALL SELECT h.action_module AS action_module,h.action_type AS action_type,h.action_user AS action_user,h.action_time AS action_time,h.action_info AS action_info,h.action_result AS action_result,h.login_ip AS login_ip,h.action_date AS action_date FROM "+loginfo_three+" AS h UNION ALL SELECT i.action_module AS action_module,i.action_type AS action_type,i.action_user AS action_user,i.action_time AS action_time,i.action_info AS action_info,i.action_result AS action_result,i.login_ip AS login_ip,i.action_date AS action_date FROM "+openapi_three+" AS i )as result where 1=1  ");
                //countSql.append("SELECT SUM(a.len) FROM(SELECT COUNT(1)  as len FROM " +tools_one+  " where 1=1 "+ condition1+condition2+condition3+condition4+" UNION ALL SELECT COUNT(1) as len FROM dop_mining_loginfo_201707 where 1=1"  + condition1+condition2+condition3+condition4+ " UNION ALL SELECT COUNT(1) as len FROM dop_openapi_201707 where 1=1 "+ condition1+condition2+condition3+condition4+") AS a where 1=1 ") ;
                countSql.append("select count(1) from (SELECT a.action_module AS action_module,a.action_type AS action_type,a.action_user AS action_user,a.action_time AS action_time,a.action_info AS action_info,a.action_result AS action_result,a.login_ip AS login_ip,a.action_date AS action_date FROM " +tools_one+  " AS a UNION ALL SELECT b.action_module AS action_module,b.action_type AS action_type,b.action_user AS action_user,b.action_time AS action_time,b.action_info AS action_info,b.action_result AS action_result,b.login_ip AS login_ip,b.action_date AS action_date FROM "+loginfo_one+" AS b UNION ALL SELECT c.action_module AS action_module,c.action_type AS action_type,c.action_user AS action_user,c.action_time AS action_time,c.action_info AS action_info,c.action_result AS action_result,c.login_ip AS login_ip,c.action_date AS action_date FROM "+openapi_one+" AS c UNION ALL "+
                        "SELECT d.action_module AS action_module,d.action_type AS action_type,d.action_user AS action_user,d.action_time AS action_time,d.action_info AS action_info,d.action_result AS action_result,d.login_ip AS login_ip,d.action_date AS action_date FROM " +tools_two+  " AS d UNION ALL SELECT e.action_module AS action_module,e.action_type AS action_type,e.action_user AS action_user,e.action_time AS action_time,e.action_info AS action_info,e.action_result AS action_result,e.login_ip AS login_ip,e.action_date AS action_date FROM "+loginfo_two+" AS e UNION ALL SELECT f.action_module AS action_module,f.action_type AS action_type,f.action_user AS action_user,f.action_time AS action_time,f.action_info AS action_info,f.action_result AS action_result,f.login_ip AS login_ip,f.action_date AS action_date FROM "+openapi_two+" AS f UNION ALL "+
                        "SELECT g.action_module AS action_module,g.action_type AS action_type ,g.action_user AS action_user,g.action_time AS action_time,g.action_info AS action_info,g.action_result AS action_result,g.login_ip AS login_ip,g.action_date AS action_date FROM " +tools_three+  " AS g UNION ALL SELECT h.action_module AS action_module,h.action_type AS action_type,h.action_user AS action_user,h.action_time AS action_time,h.action_info AS action_info,h.action_result AS action_result,h.login_ip AS login_ip,h.action_date AS action_date FROM "+loginfo_three+" AS h UNION ALL SELECT i.action_module AS action_module,i.action_type AS action_type,i.action_user AS action_user,i.action_time AS action_time,i.action_info AS action_info,i.action_result AS action_result,i.login_ip AS login_ip,i.action_date AS action_date FROM "+openapi_three+" AS i )as result where 1=1  ");
            }
            dataSql.append("  limit " + pager.getOffset() + "," + pager.getPageSize());
        }

        return  IDikeDao.search(dikeDto,pager,dataSql.toString(),countSql.toString());
    }

    private StringBuffer AddOtherLike(StringBuffer sql,Pageable pager,String loginIP,String actionUser,String startTime,String endTime){


            if (!StringUtils.isEmpty(loginIP)) {
                sql.append(" and login_ip = '" + loginIP + "'");
            }
            if (!StringUtils.isEmpty(actionUser)) {
                sql.append(" and action_user = '" + actionUser + "'");
            }
            if (!StringUtils.isEmpty(startTime)) {
                sql.append(" and action_time > '" + startTime + "'");
            }
            if (!StringUtils.isEmpty(endTime)) {
                sql.append(" and action_time  < '" + endTime + "'");
            }

          return sql;
        }



     private StringBuffer getSql(String one,String two,String three){
         StringBuffer sql = new StringBuffer(
         "select * from (SELECT a.action_module AS action_module,a.action_type AS action_type,a.action_user AS action_user," +
                 "a.action_time AS action_time,a.action_info AS action_info,a.action_result AS action_result,a.login_ip AS login_ip," +
                 "a.action_date AS action_date FROM " +one +  " AS a  UNION ALL SELECT b.action_module AS action_module," +
                 "b.action_type AS action_type,b.action_user AS action_user,b.action_time AS action_time,b.action_info AS action_info," +
                 "b.action_result AS action_result,b.login_ip AS login_ip,b.action_date AS action_date  FROM " +two+  "" +
                 " AS b  UNION ALL SELECT c.action_module AS action_module,c.action_type AS action_type," +
                 "c.action_user AS action_user,c.action_time AS action_time,c.action_info AS action_info," +
                 "c.action_result AS action_result,c.login_ip AS login_ip,c.action_date AS action_date" +
                 " FROM " +three+  " AS c  )as result where 1=1 ");
         return sql;
     }
    private StringBuffer getCount(String one,String two,String three){
        StringBuffer sql = new StringBuffer("select count(1) from (SELECT a.action_module AS action_module,a.action_type AS action_type,a.action_user AS action_user," +
                "a.action_time AS action_time,a.action_info AS action_info,a.action_result AS action_result,a.login_ip AS login_ip," +
                "a.action_date AS action_date FROM " +one +  " AS a  UNION ALL SELECT b.action_module AS action_module," +
                "b.action_type AS action_type,b.action_user AS action_user,b.action_time AS action_time,b.action_info AS action_info," +
                "b.action_result AS action_result,b.login_ip AS login_ip,b.action_date AS action_date  FROM " +two+  "" +
                " AS b  UNION ALL  SELECT c.action_module AS action_module,c.action_type AS action_type," +
                "c.action_user AS action_user,c.action_time AS action_time,c.action_info AS action_info," +
                "c.action_result AS action_result,c.login_ip AS login_ip,c.action_date AS action_date" +
                " FROM " +three+  " AS c  )as result where 1=1 ");
        return sql;
    }



}
