package com.offcn.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.offcn.pojo.TbItem;
import com.offcn.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;
import java.util.Map;

/**
 * Created by travelround on 2019/7/6.
 */
@Component
public class ItemSearchListener implements MessageListener {

    @Autowired
    private ItemSearchService itemSearchService;

    @Override
    public void onMessage(Message message) {
        System.out.println("接收到监听消息...");
        try {
            // 将传递的json串转回对象类型, 得到通过审核的商品
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            List<TbItem> list = JSON.parseArray(text, TbItem.class);

            for (TbItem item : list) {
                System.out.println(item.getId() + " " + item.getTitle());
                Map specMap = JSON.parseObject(item.getSpec());
                item.setSpecMap(specMap);
            }
            itemSearchService.importList(list);
            System.out.println("成功导入到索引库");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
