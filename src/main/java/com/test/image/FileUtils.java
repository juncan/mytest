package com.test.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wujc
 * @ClassName FileUtils
 * @Description: TODO
 * @create 2018-09-30 8:38
 */
public class FileUtils {
    public static Map<String,Object> getExif(String fileName){
        Map<String,Object> map = new HashMap<String,Object>();
        File file = new File(fileName);
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            map = printExif(file,metadata);
        } catch (ImageProcessingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return map;
    }
    //获取exif信息，将旋转角度信息拿到
    private static Map<String,Object> printExif(File file, Metadata metadata){
        Map<String,Object> map = new HashMap<String,Object>();
        String tagName = null;
        String desc = null;
        for(Directory directory : metadata.getDirectories()){
            for(Tag tag : directory.getTags()){
                tagName = tag.getTagName();
                desc = tag.getDescription();
                /*  System.out.println(tagName+","+desc);*/
                if(tagName.equals("Orientation")){
                    map.put("Orientation", desc);
                }
            }
        }
        return map;
    }

    public static int getAngle(Map<String,Object> map){
        int ro = 0;
        if (null == map.get("Orientation")) {
            return ro;
        }
        String ori = map.get("Orientation").toString();
        if(ori.indexOf("90")>=0){
            ro=1;
        }else if(ori.indexOf("180")>=0){
            ro=2;
        }else if(ori.indexOf("270")>=0){
            ro=3;
        }
        return ro;
    }
}
