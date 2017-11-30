/*
package com.ideal.audit.warning;

import com.ideal.audit.common.global.Global;
import com.ideal.audit.sys.util.UserUtils;
import com.ideal.audit.warning.entity.Send;
import com.ideal.audit.warning.service.SendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.ServletContextAware;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.util.*;

*/
/**
 * Created by chu on 2017/9/19.
 *//*


public class SendMonitor implements   ServletContextAware,ApplicationContextAware,Runnable,ApplicationListener<ApplicationEvent>,InitializingBean  {
    private static Logger log = LoggerFactory.getLogger("SendMonitor");
    private static String userName="";
    @Autowired
    private SendService sendService;

    private void init(){
        log.info("告警监听线程...");
        */
/*userName= UserUtils.getCurrentUser().getUserName();*//*

         new Thread(this,"system config check thread").start();
    }

    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(10000); //5秒
                sendMail();
                System.out.println("2222");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMail() throws Exception {
        System.out.println("开始");
        Properties props =null;;
        List<Send> list = sendService.getReadySendInfo(); //根据当前登录用户查询当前用户下没有发送的邮件
        for (Send entity : list) {
            if (entity.getReceiverEmail() == null || entity.getReceiverEmail().trim().equals("")) {
                continue;
            }
            try {
                props=new Properties();
                String nick = Global.getProperty("mail.nick");
                String from = Global.getProperty("mail.from");
                String password = Global.getProperty("mail.password");
                props.put("mail.smtp.host", Global.getProperty("mail.smtp.host"));
                props.put("mail.smtp.port", Global.getProperty("mail.smtp.port"));
                Session session = Session.getInstance(props, null);
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(nick + "<" + from + ">");
                msg.setRecipients(Message.RecipientType.TO, entity.getReceiverEmail());
                msg.setSubject("出现错误");
                msg.setSentDate(new Date());
                msg.setContent(entity.getSendContent(), "text/html;charset=utf-8");
                Transport.send(msg, from, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {

    }
}
*/
