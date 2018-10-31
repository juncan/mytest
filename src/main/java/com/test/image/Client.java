package com.test.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-09-29 16:18
 */
public class Client {
    public static void main(String[] args) {
        String url = "http://nss-public.yoya.com/pro-dy-activity-pro/5a9ca108e4b0cd28f9c8a0c500000000/workPath/5baf24fae4b06538c1d9f48400000000/08921695-b6d2-4776-89ac-ba1d3d089c5e.jpeg";//"C:\\Users\\wujc\\Desktop\\Desert.jpg";//"C:\\Users\\wujc\\Desktop\\08921695-b6d2-4776-89ac-ba1d3d089c5e.jpeg";
        System.out.println(url);
        File img = new File(url);
        int angle = FileUtils.getAngle(FileUtils.getExif(url));
        System.out.println(FileUtils.getExif(url));
        System.out.println(angle);

        //-----------------
        InputStream is = null;
        BufferedImage src = null;
        try {
            is = new FileInputStream(img);
            src = ImageIO.read(is);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //旋转
        BufferedImage bf = ImageUtil.getBufferedImg(src, url, getWidth(img), getHeight(img), angle);
        System.out.println(bf.getWidth() + "," + bf.getHeight());
        /*BufferedImage bft = ImageTest.draw(url);
        BufferedImage bf = ImageTest.rotate(bft);*/
        try {
            ImageIO.write(bf, "jpg", new File("C:\\Users\\wujc\\Desktop\\3.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int height = -1;
        try {
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            height = src.getHeight();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return height;
    }

    public static int getWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int width = -1;
        try {
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            width = src.getWidth();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return width;
    }
}
