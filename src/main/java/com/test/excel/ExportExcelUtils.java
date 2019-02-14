package com.test.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.OutputStream;
import java.util.List;

/**
 * @author wujc
 * @ClassName ExportExcelUtils
 * @Description: TODO
 * @create 2018-12-05 14:09
 */
public class ExportExcelUtils {
    /**
     * @MethodsName: exportExcel
     * @Description: 导出Excel的方法
     * @auther: wujc
     * @date: 2018-12-5 14:11 
     * @param workbook
     * @param sheetNum sheet的位置，0表示第一个表格中的第一个sheet
     * @param sheetTile sheet名称
     * @param headers 表格是标题
     * @param result 表格是数据
     * @param out 输出流
     * @return:
     */
    public void exportExcel(HSSFWorkbook workbook, int sheetNum, String sheetTile,
                            String [] headers, List<List<String>> result,
                            OutputStream out) throws Exception {
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTile);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell((short) i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }
        // 遍历集合数据，产生数据行
        if (result != null) {
            int index = 1;
            for (List<String> m : result) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (String str : m) {
                    HSSFCell cell = row.createCell((short) cellIndex);
                    cell.setCellValue(str.toString());
                    cellIndex++;
                }
                index++;
            }
        }
    }
}
