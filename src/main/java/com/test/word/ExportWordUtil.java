package com.test.word;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wujc
 * @ClassName ExportWordUtil
 * @Description: 导出word工具类
 * @create 2019-04-13
 */
public class ExportWordUtil {
    private static Configuration configuration = null;
    private static Map<String, Template> templateMap = null;

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("utf-8");
        templateMap = new HashMap<>();
        try {
            configuration.setDirectoryForTemplateLoading(new File(getTemplatePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @MethodsName: getTemplate
     * @Description: 获取模板
     * @date: 2019-4-16 8:52
     * @return:
     */
    public static void getTemplate(String ftlName) {
        try {
            templateMap.put("student", configuration.getTemplate(ftlName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @MethodsName: createDoc
     * @Description: 生成word文档方法
     * @date: 2019-4-16 10:06
     * @param dataMap
     * @param type
     * @return:
     */
    public static File createDoc(Map<?, ?> dataMap, String type,String filename) {
        File f = new File(filename);
        Template t = templateMap.get(type);
        try {
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return f;
    }

    public static String getTemplatePath() {
        String path = ExportWordUtil.class.getResource("/").toString();
        path = path.replace("file:", "");
        if (path.contains(":"))
            path = path.substring(1, path.length());
        path = path + "template";
        return path;
    }

    /**
     * @MethodsName: exportWord
     * @Description: 导出单个word
     * @date: 2019-4-15 14:16
     * @param map
     * @param request
     * @param response
     * @return:
     */
    public static void exportWord(String title, Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        String fileName11 = "";
        String fileName = title + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + ".doc";
        try {
            // 调用工具类WordGenerator的createDoc方法生成Word文档
            file = createDoc(map, "student",fileName);
            fin = new FileInputStream(file);
            String userAgent = request.getHeader("USER-AGENT");
            if (StringUtils.contains(userAgent, "Firefox") || StringUtils.contains(userAgent, "firefox")
                    || StringUtils.contains(userAgent,"Macintosh")) {//火狐浏览器和safari浏览器
                fileName11 = new String(fileName.getBytes(), "ISO8859-1");
            } else {
                fileName11 = URLEncoder.encode(fileName, "UTF-8");//其他浏览器
            }
            String headStr = "attachment; filename=\"" + fileName11 + "\"";
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件默认名为新生报到注册单模板.doc
            response.addHeader("Content-Disposition", headStr);
            out = response.getOutputStream();
            byte[] buffer = new byte[512];    // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } finally {
            if (fin != null) fin.close();
            if (out != null) out.close();
            if (file != null) file.delete();    // 删除临时文件
        }
    }

    /**
     * @MethodsName: exportWordBatch
     * @Description: 压缩方式导出多个word
     * @date: 2019-4-16 10:23
     * @param mapList
     * @param titleList
     * @param request
     * @param response
     * @return:
     */
    public static void exportWordBatch(List<Map<String, Object>> mapList, List<String> titleList, HttpServletRequest request, HttpServletResponse response,String zipName) {
        File file = null;
        File zipfile=null;
        File directory=null;
        InputStream fin = null;
        ServletOutputStream out = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        String userAgent = request.getHeader("USER-AGENT");
        zipName = zipName + ".zip";
        try {
            if (StringUtils.contains(userAgent, "Firefox") || StringUtils.contains(userAgent, "firefox")
                    || StringUtils.contains(userAgent,"Macintosh")) {//火狐浏览器和safari浏览器
                zipName = new String(zipName.getBytes(), "ISO8859-1");
            } else {
                zipName = URLEncoder.encode(zipName, "UTF-8");//其他浏览器
            }
            String headStr = "attachment; filename=\"" + zipName + "\"";
            response.addHeader("Content-Disposition", headStr);
            out = response.getOutputStream();
            //根据当前时间和用户id创建临时目录
            String path = "temp";
            directory=new File(path);
            directory.mkdirs();
            for(int i=0;i<mapList.size();i++) {
                Map<String, Object> map=mapList.get(i);
                String title=titleList.get(i);
                // 调用工具类的createDoc方法在临时目录下生成Word文档
                file = createDoc(map, "student",directory.getPath()+"/"+title+".doc");
            }
            //压缩目录
            ZipUtil.createZip(path, path+"zip.zip");
            //根据路径获取刚生成的zip包文件
            zipfile=new File(path+"zip.zip");
            fin=new FileInputStream(zipfile);
            byte[] buffer = new byte[512]; // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fin!=null) fin.close();
                if (out!=null) out.close();
                if (zipfile!=null) zipfile.delete();
                if (directory!=null) {
                    //递归删除目录及目录下文件
                    ZipUtil.deleteFile(directory);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
    }

}
