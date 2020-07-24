package com.changgou.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图片压缩质量接口
 * zhangrui
 */
public class MyThumbnailatorUtils {

    public void compressor(MultipartFile multipartFile,OutputStream outputStream){
        try {
            //得到图片文件的对象,过去输入流
            InputStream inputStream = multipartFile.getInputStream();
            //压缩图片质量,传到对应的位置
            Thumbnails.of(inputStream).scale(0.5).toOutputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
