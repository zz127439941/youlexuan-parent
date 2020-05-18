package com.offcn.mail;

import com.offcn.util.mailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.xml.ws.Action;

@Component
public class mailListener implements MessageListener {

    @Autowired
    private mailUtil mailUtil;

    @Override
    public void onMessage(Message message) {
        MapMessage map = (MapMessage)message;
         System.out.println("监听到了mail");
        try {
            System.out.println(map.getString("sendTo")+"-----"+map.getString("sendText"));
            mailUtil.sendMail(map.getString("sendTo"),map.getString("sendText"));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
