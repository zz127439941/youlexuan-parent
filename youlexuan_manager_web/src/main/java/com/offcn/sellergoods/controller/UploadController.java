package com.offcn.sellergoods.controller;

import com.offcn.entity.Result;
import com.offcn.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by travelround on 2019/8/2.
 */
@RestController
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;

    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();// 获取文件扩展名
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);

        try {

            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            String path = fastDFSClient.uploadFile(file.getBytes(), extName);
            String url = FILE_SERVER_URL + path;

            System.out.println("url:" + url);

            return new Result(true, url);

        } catch (Exception e) {
            return new Result(false, "上传失败");
        }

    }



}
