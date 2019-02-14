package com.test.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-11-09 17:17
 */
public class Client {
    public static void main(String[] args) {
        File file = new File("C:" + File.separator + "Users\\Administrator\\Desktop\\评委打分表格.xlsx");
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            List<List<Record>> list = ExcelReadUtil.readExcel(in, new String[]{"name", "major_name", "class_name", "works_name", "score"});
            for (List<Record> records : list) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
        }

    }
}

