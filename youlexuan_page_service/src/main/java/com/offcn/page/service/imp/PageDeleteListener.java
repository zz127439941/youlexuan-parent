package com.offcn.page.service.imp;

import com.offcn.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.io.Serializable;

@Component
public class PageDeleteListener implements MessageListener {

    @Autowired
    private ItemPageService itemPageService;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            Long[] goodsIds = (Long[]) objectMessage.getObject();
            System.out.println("PageDeleteListener 监听到消息:" + goodsIds);
            boolean b = itemPageService.deleteItemHtml(goodsIds);
            System.out.println("静态页删除结果:" + b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
