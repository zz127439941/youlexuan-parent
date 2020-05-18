package com.offcn.util;

import com.offcn.util.mailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class mailUtil {

    @Autowired
    private JavaMailSenderImpl springmail;

   public void sendMail(String sendTo,String sendText){
       //创建简单的邮件
       SimpleMailMessage msg = new SimpleMailMessage();
       msg.setFrom("zz1274399341@163.com");
       msg.setTo(sendTo);
       msg.setSubject("zz邮件");
       msg.setText(sendText);

       //发送邮件

       springmail.send(msg);

       System.out.println("send ok");
   }
}
