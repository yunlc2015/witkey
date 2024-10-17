/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.util;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * 图像处理工具类
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class ImageUtil {

    /**
     * 获取图像缩略图
     * 
     * @param sour 源图像
     * @param width 缩略图宽度
     * @param height 缩略图高度
     * @return 
     */
    public static byte[] getThumbtail(byte[] sour, int width, int height) throws IOException {

        ByteArrayInputStream inStream = new ByteArrayInputStream(sour);
        BufferedImage sourImg = ImageIO.read(inStream);
        boolean hasNotAlpha = !sourImg.getColorModel().hasAlpha();
        
        BufferedImage dest = new BufferedImage(width, height, 
            hasNotAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);

        dest.getGraphics().drawImage(sourImg, 0, 0, width, height, 0, 0, sourImg.getWidth(), sourImg.getHeight(), null);
        
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ImageIO.write(dest, hasNotAlpha ? "jpg" : "png", outStream);
        return outStream.toByteArray();
    }

}
