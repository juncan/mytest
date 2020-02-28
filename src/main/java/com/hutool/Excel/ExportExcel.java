package com.hutool.Excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.List;

/**
 * @author wujc
 * @ClassName ExportExcel
 * @create 2019-08-08
 */
public class ExportExcel {
    public static void main(String[] args) {
        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2");
        List<String> row4 = CollUtil.newArrayList("aa4", "bb4", "cc4");

        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4);

        ExcelWriter writer = ExcelUtil.getWriter("F:/writeTest.xlsx");

        writer.passCurrentRow();

        writer.merge(row1.size() - 1, "测试标题");
        writer.write(rows, true);
        writer.close();
    }


}
