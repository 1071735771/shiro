package com.ideal.audit.warning.service;

import com.ideal.audit.dike.dao.IDikeDao;
import com.ideal.audit.dike.dto.DikeDto;
import com.ideal.audit.warning.dao.ISendDao;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yaoshunyu on 2017/10/11.
 */
public class job{

    @Resource
    private IDikeDao sendDao;

    @Resource
    private ISendDao iSendDao;



    public void dojob () throws ParseException {
            StringBuffer dataSql =new StringBuffer("");
            Date date = new Date();
            Date bdate = new Date();
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(date);//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
            bdate = calendar.getTime();   //得到前一天的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(date);
            String yesterday = sdf.format(bdate);
            String[] time = yesterday.split("-");
            String s = time[0] + time[1];
            Integer a = Integer.parseInt(s);
            System.out.println(a);
            String dtname = "dop_dev_tools_" + a;
            String mlname = "dop_mining_loginfo_" + a;
            String oname = "dop_openapi_" + a;
            List<DikeDto> dtList = new ArrayList<DikeDto>();
            dataSql.append("select * from "+dtname+" where action_date='"+yesterday+"' and action_type='登录'");
            System.out.println(dataSql.toString());
            dtList = sendDao.getData(dataSql.toString());
            dataSql.setLength(0);
            List<DikeDto> mlList = new ArrayList<DikeDto>();
            dataSql.append("select * from "+mlname+" where action_date='"+yesterday+"' and action_type='登录'");
            mlList = sendDao.getMlData(dataSql.toString());
            dataSql.setLength(0);
            List<DikeDto> oList = new ArrayList<DikeDto>();
            dataSql.append("select * from "+oname+" where action_date='"+yesterday+"' and action_type='登录'");
            oList = sendDao.getData(dataSql.toString());
            dataSql.setLength(0);

            //判断有无同时登录
        if (dtList.size()!=0){
            for (int i = 0; i < dtList.size(); i++) {
                dataSql.append("select count(id) from "+dtname+" where action_time='"+dtList.get(i).getAction_time()+"' and action_user='"+dtList.get(i).getAction_user()+"' and action_date='"+yesterday+"' and action_type='登录'");
                System.out.println(dataSql);
                long dtst = sendDao.checkSameTime(dataSql.toString());
                dataSql.setLength(0);
                if (dtst > 1) {
                    dataSql.append("select count(action_user) from "+dtname+" where action_time='"+dtList.get(i).getAction_time()+"' and action_user='"+dtList.get(i).getAction_user()+"' and action_date='"+yesterday+"' and login_ip='"+dtList.get(i).getLogin_ip()+"' and action_type='登录'");
                    long dtIP = sendDao.checkIp(dataSql.toString());
                    dataSql.setLength(0);
                    if (dtst != dtIP) {
                        //插入告警表
                        iSendDao.insertLog(dtList.get(i).getAction_module(),dtList.get(i).getAction_type(),dtList.get(i).getAction_user(),dtList.get(i).getAction_time(),dtList.get(i).getAction_info(),dtList.get(i).getAction_result(),dtList.get(i).getLogin_ip(),dtList.get(i).getAction_date());
                    }
                }
            }
        }

        if (mlList.size()!=0){
            for (int i = 0; i < mlList.size(); i++) {
                dataSql.append("select count(action_user) from "+mlname+" where action_time='"+mlList.get(i).getAction_time()+"' and action_user='"+mlList.get(i).getAction_user()+"' and action_date='"+yesterday+"' and action_type='登录'");
                long dtst = sendDao.checkSameTime(dataSql.toString());
                dataSql.setLength(0);
                if (dtst > 1) {
                    dataSql.append("select count(action_user) from "+mlname+" where action_time='"+mlList.get(i).getAction_time()+"' and action_user='"+mlList.get(i).getAction_user()+"' and action_date='"+yesterday+"' and login_ip='"+mlList.get(i).getLogin_ip()+"' and action_type='登录'");
                    long dtIP = sendDao.checkIp(dataSql.toString());
                    dataSql.setLength(0);
                    if (dtst != dtIP) {
                        //插入告警表
                        iSendDao.insertLog(mlList.get(i).getAction_module(),mlList.get(i).getAction_type(),mlList.get(i).getAction_user(),mlList.get(i).getAction_time(),mlList.get(i).getAction_info(),mlList.get(i).getAction_result(),mlList.get(i).getLogin_ip(),mlList.get(i).getAction_date());
                    }
                }
            }
        }

        if (oList.size()!=0){
            for (int i = 0; i < oList.size(); i++) {
                dataSql.append("select count(action_user) from "+oname+" where action_time='"+oList.get(i).getAction_time()+"' and action_user='"+oList.get(i).getAction_user()+"' and action_date='"+yesterday+"' and action_type='登录'");
                long dtst = sendDao.checkSameTime(dataSql.toString());
                dataSql.setLength(0);
                if (dtst > 1) {
                    dataSql.append("select count(action_user) from "+oname+" where action_time='"+oList.get(i).getAction_time()+"' and action_user='"+oList.get(i).getAction_user()+"' and action_date='"+yesterday+"' and login_ip='"+oList.get(i).getLogin_ip()+"' and action_type='登录'");
                    long dtIP = sendDao.checkIp(dataSql.toString());
                    dataSql.setLength(0);
                    if (dtst != dtIP) {
                        //插入告警表
                        iSendDao.insertLog(oList.get(i).getAction_module(),oList.get(i).getAction_type(),oList.get(i).getAction_user(),oList.get(i).getAction_time(),oList.get(i).getAction_info(),oList.get(i).getAction_result(),oList.get(i).getLogin_ip(),oList.get(i).getAction_date());
                    }
                }
            }
        }

            //查询是否短时间内异地登录
            dataSql.append("select interval_time from rule");
            String interval_time = sendDao.getInterval_time(dataSql.toString());//拿到最小间隔时间
            dataSql.setLength(0);
        if (dtList.size()!=0){
            for (int i = 0; i < dtList.size(); i++) {
                for (int j = i; j < dtList.size(); j++) {
                    if (!dtList.get(i).getLogin_ip().equals(dtList.get(j).getLogin_ip()) && dtList.get(i).getAction_user().equals(dtList.get(j).getAction_user())) {
                        //分别拿到两个时间
                        dataSql.append("select action_time from "+dtname+" where login_ip='"+dtList.get(i).getLogin_ip()+"' and action_user='"+dtList.get(i).getAction_user()+"' and action_date='"+yesterday+"' and action_type='登录'");
                        Timestamp t1=sendDao.getTime(dataSql.toString());
                        String ti1=t1.toString();
                        Date time1 = new Date();
                        DateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        time1=sf.parse(ti1);
                        dataSql.setLength(0);
                        dataSql.append("select action_time from "+dtname+" where login_ip='"+dtList.get(j).getLogin_ip()+"' and action_user='"+dtList.get(j).getAction_user()+"' and action_date='"+yesterday+"' and action_type='登录'");
                        Timestamp t2=sendDao.getTime(dataSql.toString());
                        String ti2=t1.toString();
                        Date time2 = new Date();
                        time2=sf.parse(ti2);
                        dataSql.setLength(0);
                        double temp = time2.getTime() - time1.getTime();
                        double hours = temp / 1000 / 3600;                //相差小时数
                        if (hours < Double.parseDouble(interval_time)) {
                            //插入告警表
                            iSendDao.insertLog(dtList.get(i).getAction_module(),dtList.get(i).getAction_type(),dtList.get(i).getAction_user(),dtList.get(i).getAction_time(),dtList.get(i).getAction_info(),dtList.get(i).getAction_result(),dtList.get(i).getLogin_ip(),dtList.get(i).getAction_date());
                        }
                    }

                }
            }
        }


//        double interval_time=sendDao.getInterval_time();//拿到最小间隔时间
        if (mlList.size()!=0){
            for (int i = 0; i < mlList.size(); i++) {
                for (int j = i; j < mlList.size(); j++) {
                    if (!mlList.get(i).getLogin_ip().equals(mlList.get(j).getLogin_ip()) && mlList.get(i).getAction_user().equals(mlList.get(j).getAction_user())) {
                        //分别拿到两个时间
                        dataSql.append("select action_time from "+mlname+" where login_ip='"+mlList.get(i).getLogin_ip()+"' and action_user='"+mlList.get(i).getAction_user()+"' and action_date='"+yesterday+"' and action_type='登录'");
                        Timestamp t1=sendDao.getTime(dataSql.toString());
                        String ti1=t1.toString();
                        Date time1 = new Date();
                        DateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        time1=sf.parse(ti1);
                        dataSql.setLength(0);
                        dataSql.append("select action_time from "+mlname+" where login_ip='"+mlList.get(j).getLogin_ip()+"' and action_user='"+mlList.get(j).getAction_user()+"' and action_date='"+yesterday+"' and action_type='登录'");
                        Timestamp t2=sendDao.getTime(dataSql.toString());
                        String ti2=t1.toString();
                        Date time2 = new Date();
                        time2=sf.parse(ti2);
                        dataSql.setLength(0);
                        double temp = time2.getTime() - time1.getTime();
                        double hours = temp / 1000 / 3600;                //相差小时数
                        if (hours < Double.parseDouble(interval_time)) {
                            //插入告警表
                            iSendDao.insertLog(mlList.get(i).getAction_module(),mlList.get(i).getAction_type(),mlList.get(i).getAction_user(),mlList.get(i).getAction_time(),mlList.get(i).getAction_info(),mlList.get(i).getAction_result(),mlList.get(i).getLogin_ip(),mlList.get(i).getAction_date());
                        }
                    }

                }
            }
        }

//        double interval_time=sendDao.getInterval_time();//拿到最小间隔时间
        if (oList.size()!=0){
            for (int i = 0; i < oList.size(); i++) {
                for (int j = i; j < oList.size(); j++) {
                    if (!oList.get(i).getLogin_ip().equals(oList.get(j).getLogin_ip()) && oList.get(i).getAction_user().equals(oList.get(j).getAction_user())) {
                        //分别拿到两个时间
                        dataSql.append("select action_time from "+oname+" where login_ip='"+oList.get(i).getLogin_ip()+"' and action_user='"+oList.get(i).getAction_user()+"' and action_date='"+yesterday+"' and action_type='登录'");
                        Timestamp t1=sendDao.getTime(dataSql.toString());
                        String ti1=t1.toString();
                        Date time1 = new Date();
                        DateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        time1=sf.parse(ti1);
                        dataSql.setLength(0);
                        dataSql.append("select action_time from "+oname+" where login_ip='"+oList.get(j).getLogin_ip()+"' and action_user='"+oList.get(j).getAction_user()+"' and action_date='"+yesterday+"' and action_type='登录'");
                        Timestamp t2=sendDao.getTime(dataSql.toString());
                        String ti2=t1.toString();
                        Date time2 = new Date();
                        time2=sf.parse(ti2);
                        dataSql.setLength(0);
                        double temp = time2.getTime() - time1.getTime();
                        double hours = temp / 1000 / 3600;                //相差小时数
                        if (hours < Double.parseDouble(interval_time)) {
                            //插入告警表
                            iSendDao.insertLog(oList.get(i).getAction_module(),oList.get(i).getAction_type(),oList.get(i).getAction_user(),oList.get(i).getAction_time(),oList.get(i).getAction_info(),oList.get(i).getAction_result(),oList.get(i).getLogin_ip(),oList.get(i).getAction_date());
                        }
                    }

                }
            }
        }

            //查询尝试登录
            dataSql.append("select error_try_number from rule");
            int error_try_number=sendDao.getError_try_number(dataSql.toString());
            dataSql.setLength(0);
        if (dtList.size()!=0){
            for (int i = 0; i < dtList.size(); i++){
                dataSql.append("select count(action_user) from "+dtname+" where action_user='"+dtList.get(i).getAction_user()+"' and login_ip='"+dtList.get(i).getLogin_ip()+"' and action_date='"+yesterday+"' and action_type='登录' and action_result='失败'");
                long deflog=sendDao.checkdeflog(dataSql.toString());
                dataSql.setLength(0);
                if(deflog>error_try_number){
                    //插入告警表
                    iSendDao.insertLog(dtList.get(i).getAction_module(),dtList.get(i).getAction_type(),dtList.get(i).getAction_user(),dtList.get(i).getAction_time(),dtList.get(i).getAction_info(),dtList.get(i).getAction_result(),dtList.get(i).getLogin_ip(),dtList.get(i).getAction_date());
                }
            }
        }

        if (mlList.size()!=0){
            for (int i = 0; i < mlList.size(); i++){
                dataSql.append("select count(action_user) from "+mlname+" where action_user='"+mlList.get(i).getAction_user()+"' and login_ip='"+mlList.get(i).getLogin_ip()+"' and action_date='"+yesterday+"' and action_type='登录' and action_result='失败'");
                long deflog=sendDao.checkdeflog(dataSql.toString());
                dataSql.setLength(0);
                if(deflog>error_try_number){
                    //插入告警表
                    iSendDao.insertLog(mlList.get(i).getAction_module(),mlList.get(i).getAction_type(),mlList.get(i).getAction_user(),mlList.get(i).getAction_time(),mlList.get(i).getAction_info(),mlList.get(i).getAction_result(),mlList.get(i).getLogin_ip(),mlList.get(i).getAction_date());
                }
            }
        }

        if (oList.size()!=0){
            for (int i = 0; i < oList.size(); i++){
                dataSql.append("select count(action_user) from "+oname+" where action_user='"+oList.get(i).getAction_user()+"' and login_ip='"+oList.get(i).getLogin_ip()+"' and action_date='"+yesterday+"' and action_type='登录' and action_result='失败'");
                long deflog=sendDao.checkdeflog(dataSql.toString());
                dataSql.setLength(0);
                if(deflog>error_try_number){
                    //插入告警表
                    iSendDao.insertLog(oList.get(i).getAction_module(),oList.get(i).getAction_type(),oList.get(i).getAction_user(),oList.get(i).getAction_time(),oList.get(i).getAction_info(),oList.get(i).getAction_result(),oList.get(i).getLogin_ip(),oList.get(i).getAction_date());
                }
            }
        }

            //查询ip登录次数
            dataSql.append("select common_number from rule");
            int common_number=sendDao.getCommon_number(dataSql.toString());
            dataSql.setLength(0);
        if (dtList.size()!=0){
            for (int i=0;i<dtList.size();i++){
                a = Integer.parseInt(s);
                dataSql.append("select count(action_user) from "+dtname+" where action_user='"+dtList.get(i).getAction_user()+"' and login_ip='"+dtList.get(i).getLogin_ip()+"' and action_type='登录'");
                long logtimes=sendDao.checkLogtime(dataSql.toString());
                dataSql.setLength(0);
                if (logtimes<common_number){
                    a=a-1;
                    dtname = "dop_dev_tools_" + a;
                    dataSql.append("select count(action_user) from "+dtname+" where action_user='"+dtList.get(i).getAction_user()+"' and login_ip='"+dtList.get(i).getLogin_ip()+"' and action_type='登录'");
                    logtimes=logtimes+sendDao.checkLogtime(dataSql.toString());
                    dataSql.setLength(0);
                    if(logtimes<common_number){
                        a=a-1;
                        dtname = "dop_dev_tools_" + a;
                        dataSql.append("select count(action_user) from "+dtname+" where action_user='"+dtList.get(i).getAction_user()+"' and login_ip='"+dtList.get(i).getLogin_ip()+"' and action_type='登录'");
                        logtimes=logtimes+sendDao.checkLogtime(dataSql.toString());
                        dataSql.setLength(0);
                        if(logtimes<common_number){
                            //插入告警表
                            iSendDao.insertLog(dtList.get(i).getAction_module(),dtList.get(i).getAction_type(),dtList.get(i).getAction_user(),dtList.get(i).getAction_time(),dtList.get(i).getAction_info(),dtList.get(i).getAction_result(),dtList.get(i).getLogin_ip(),dtList.get(i).getAction_date());
                        }
                    }
                }
            }
        }

        if (mlList.size()!=0){
            for (int i=0;i<mlList.size();i++){
                a = Integer.parseInt(s);
                dataSql.append("select count(action_user) from "+mlname+" where action_user='"+mlList.get(i).getAction_user()+"' and login_ip='"+mlList.get(i).getLogin_ip()+"' and action_type='登录'");
                long logtimes=sendDao.checkLogtime(dataSql.toString());
                dataSql.setLength(0);
                if (logtimes<common_number){
                    a=a-1;
                    mlname = "dop_mining_loginfo_" + a;
                    dataSql.append("select count(action_user) from "+mlname+" where action_user='"+mlList.get(i).getAction_user()+"' and login_ip='"+mlList.get(i).getLogin_ip()+"' and action_type='登录'");
                    logtimes=sendDao.checkLogtime(dataSql.toString());
                    dataSql.setLength(0);
                    if(logtimes<common_number){
                        a=a-1;
                        mlname = "dop_mining_loginfo_" + a;
                        dataSql.append("select count(action_user) from "+mlname+" where action_user='"+mlList.get(i).getAction_user()+"' and login_ip='"+mlList.get(i).getLogin_ip()+"' and action_type='登录'");
                        logtimes=sendDao.checkLogtime(dataSql.toString());
                        dataSql.setLength(0);
                        if(logtimes<common_number){
                            //插入告警表
                            iSendDao.insertLog(mlList.get(i).getAction_module(),mlList.get(i).getAction_type(),mlList.get(i).getAction_user(),mlList.get(i).getAction_time(),mlList.get(i).getAction_info(),mlList.get(i).getAction_result(),mlList.get(i).getLogin_ip(),mlList.get(i).getAction_date());
                        }
                    }
                }
            }
        }

        if (oList.size()!=0){
            for (int i=0;i<oList.size();i++){
                a = Integer.parseInt(s);
                dataSql.append("select count(action_user) from "+oname+" where action_user='"+oList.get(i).getAction_user()+"' and login_ip='"+oList.get(i).getLogin_ip()+"' and action_type='登录'");
                long logtimes=sendDao.checkLogtime(dataSql.toString());
                dataSql.setLength(0);
                if (logtimes<common_number){
                    a=a-1;
                    oname = "dop_openapi_" + a;
                    dataSql.append("select count(action_user) from "+oname+" where action_user='"+oList.get(i).getAction_user()+"' and login_ip='"+oList.get(i).getLogin_ip()+"' and action_type='登录'");
                    logtimes=sendDao.checkLogtime(dataSql.toString());
                    dataSql.setLength(0);
                    if(logtimes<common_number){
                        a=a-1;
                        oname = "dop_openapi_" + a;
                        dataSql.append("select count(action_user) from "+oname+" where action_user='"+oList.get(i).getAction_user()+"' and login_ip='"+oList.get(i).getLogin_ip()+"' and action_type='登录'");
                        logtimes=sendDao.checkLogtime(dataSql.toString());
                        dataSql.setLength(0);
                        if(logtimes<common_number){
                            //插入告警表
                            iSendDao.insertLog(oList.get(i).getAction_module(),oList.get(i).getAction_type(),oList.get(i).getAction_user(),oList.get(i).getAction_time(),oList.get(i).getAction_info(),oList.get(i).getAction_result(),oList.get(i).getLogin_ip(),oList.get(i).getAction_date());
                        }
                    }
                }
            }
        }

        //查询是否有报警
        dataSql.append("select count(id) from log_warning_infor where action_date='"+yesterday+"'");
        long warning=sendDao.getWarning(dataSql.toString());
        dataSql.setLength(0);
        if (warning>0){
            //获取邮箱
            dataSql.append("select send_email from rule");
            String send_email=sendDao.getSend_email(dataSql.toString());
            String [] toman=send_email.split(";");
            dataSql.setLength(0);
            try {
//                mail.mail(toman,yesterday+"报警",yesterday+"有异常");
                JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
                // 设定邮件服务器
                //senderImpl.setHost("smtp.163.com");
                senderImpl.setHost("smtp.163.com");
                // 建立邮件消息
                // SimpleMailMessage mailMessage = new SimpleMailMessage();
                MimeMessage mailMessage = senderImpl.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");
                // 设置收件人,群发邮件
               // String[] array = toman;

                helper.setTo(toman);
                //mailMessage.setTo("iamzken@163.com");
                helper.setFrom("ysytxdr@163.com");
                helper.setSubject(yesterday+"报警");
                helper.setText(yesterday+"有异常", true);
                //添加附件
//        ClassPathResource resource = new ClassPathResource("test.jpg");
//        helper.addAttachment("hello.jpg", resource);
                // 根据自己的情况,设置username
                //senderImpl.setUsername("iamzken@163.com");
                senderImpl.setUsername("ysytxdr");
                senderImpl.setPassword("zqmp1379"); // 根据自己的情况, 设置password
 /* Properties prop = new Properties();
 prop.put(" mail.smtp.auth ", " true "); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
 prop.put(" mail.smtp.timeout ", " 25000 ");
 senderImpl.setJavaMailProperties(prop);*/
                // 发送邮件
                senderImpl.send(mailMessage);
                System.out.println(" 邮件发送成功.. ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }


//        public static void main(String args[]) throws MessagingException {
//
//        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
//        // 设定邮件服务器
//        //senderImpl.setHost("smtp.163.com");
//        senderImpl.setHost("smtp.163.com");
//        // 建立邮件消息
//        // SimpleMailMessage mailMessage = new SimpleMailMessage();
//        MimeMessage mailMessage = senderImpl.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true,"utf-8");
//        // 设置收件人,群发邮件
//        String[] array = new String[]
//                {"ysytxdr123@sina.com"};
//        helper.setTo(array);
//        //mailMessage.setTo("iamzken@163.com");
//        helper.setFrom("ysytxdr@163.com");
//        helper.setSubject("这是我的主题！");
//        helper.setText("<p style='color:red;'>这是我的内容！</p>",true);
//        //添加附件
////        ClassPathResource resource = new ClassPathResource("test.jpg");
////        helper.addAttachment("hello.jpg", resource);
//        // 根据自己的情况,设置username
//        //senderImpl.setUsername("iamzken@163.com");
//        senderImpl.setUsername("ysytxdr");
//        senderImpl.setPassword("zqmp1379"); // 根据自己的情况, 设置password
// /* Properties prop = new Properties();
// prop.put(" mail.smtp.auth ", " true "); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
// prop.put(" mail.smtp.timeout ", " 25000 ");
// senderImpl.setJavaMailProperties(prop);*/
//        // 发送邮件
//        senderImpl.send(mailMessage);
//        System.out.println(" 邮件发送成功.. ");
//    }
}

