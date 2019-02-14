package com.test.excel;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通用的导出Excel类，通过poi实现
 * dataList为导出的数据，其中每一个Object数组第一个元素（object[0]）都是序号，不可放真实数据
 *
 * @author wujc
 */
public class ExportExcelUtil {

    private String title; // 导出表格的表名

    private String[] rowName;// 导出表格的列名

    private String filterColum; //过滤列

    private List<Object[]> dataList = new ArrayList<Object[]>(); // 对象数组的List集合

    private HttpServletResponse response;

    private HttpServletRequest request;


    /**
     * 实例化导出类
     *
     * @param title       标题
     * @param rowName     表头
     * @param dataList    导出数据
     * @param filterColum 过滤项，只能是单列或者连续的列
     * @param request
     * @param response
     */
    public ExportExcelUtil(String title, String[] rowName, List<Object[]> dataList, String filterColum, HttpServletRequest request, HttpServletResponse response) {
        this.title = title;
        this.rowName = rowName;
        this.filterColum = filterColum;
        this.dataList = dataList;
        this.response = response;
        this.request = request;
    }

    /**
     * @MethodsName: ExportExcelUtil
     * @Description: 构造函数
     * @auther: wujc
     * @date: 2018-12-5 14:38
     * @return:
     */
    public ExportExcelUtil(String title,HttpServletRequest request, HttpServletResponse response) {
        this.title = title;
        this.request = request;
        this.response = response;
    }

    // 导出数据
    public void exportData() throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(); // 创建一个excel对象
        HSSFSheet sheet = workbook.createSheet(title); // 创建表格

        // sheet样式定义
        HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook, 18); // 头样式
        HSSFCellStyle columnStyle = this.getColumnStyle(workbook, 12); // 标题样式
        HSSFCellStyle style = this.getStyle(workbook, 11);  // 单元格样式

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (rowName.length - 1)));// 合并第一行的所有列
        // 产生表格标题行
        HSSFRow rowm = sheet.createRow(0);  // 行
        rowm.setHeightInPoints(26f);
        HSSFCell cellTiltle = rowm.createCell(0);  // 单元格

        cellTiltle.setCellStyle(columnTopStyle);
        cellTiltle.setCellValue(title);

        int columnNum = rowName.length;  // 表格列的长度
        HSSFRow rowRowName = sheet.createRow(1);  // 在第二行创建行
        HSSFCellStyle cells = workbook.createCellStyle();
        cells.setBottomBorderColor(IndexedColors.BLACK.index);
        rowRowName.setRowStyle(cells);

        //设置列名
        setRowName(columnNum, rowRowName, columnStyle,rowName);

        //判断是否要加过滤功能
        if (!Strings.isNullOrEmpty(filterColum)) {
            CellRangeAddress c = CellRangeAddress.valueOf(filterColum);
            sheet.setAutoFilter(c);
        }

        // 将查询到的数据设置到对应的单元格中
        setDataToCell(dataList, sheet, style);

        //  让列宽随着导出的列长自动适应
        for (int i = 0; i < columnNum; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 888);//适当再宽点
        }
        //导出到浏览器
        this.exportToBrowser(title, workbook, this.request, this.response);
    }

    /**
     * @MethodsName: exportData
     * @Description: 导出数据到Excel【多页签】
     * @auther: wujc
     * @date: 2018-12-5 14:43
     * @param workbook
     * @param sheetNum sheet的位置，0表示第一个表格中的第一个sheet
     * @param sheetTitle sheet名称
     * @param headers 表格的标题
     * @param dataList 表格的数据
     * @param filterColum 标题过滤列
     * @return:
     */
    public void exportData(HSSFWorkbook workbook, int sheetNum, String sheetTitle,
                           String[] headers,String filterColum, List<Object[]> dataList,boolean isToBrowser) throws Exception {
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        workbook.setSheetName(sheetNum, sheetTitle);

        // sheet样式定义
        HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook, 18); // 头样式
        HSSFCellStyle columnStyle = this.getColumnStyle(workbook, 12); // 标题样式
        HSSFCellStyle style = this.getStyle(workbook, 11);  // 单元格样式

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (headers.length - 1)));// 合并第一行的所有列
        // 产生表格标题行
        HSSFRow rowm = sheet.createRow(0);  // 行
        rowm.setHeightInPoints(26f);
        HSSFCell cellTiltle = rowm.createCell(0);  // 单元格

        cellTiltle.setCellStyle(columnTopStyle);
        cellTiltle.setCellValue(sheetTitle);

        int columnNum = headers.length;  // 表格列的长度
        HSSFRow rowRowName = sheet.createRow(1);  // 在第二行创建行
        HSSFCellStyle cells = workbook.createCellStyle();
        cells.setBottomBorderColor(IndexedColors.BLACK.index);
        rowRowName.setRowStyle(cells);

        //设置列名
        setRowName(columnNum, rowRowName, columnStyle,headers);

        //判断是否要加过滤功能
        if (!Strings.isNullOrEmpty(filterColum)) {
            CellRangeAddress c = CellRangeAddress.valueOf(filterColum);
            sheet.setAutoFilter(c);
        }

        // 将查询到的数据设置到对应的单元格中
        setDataToCell(dataList, sheet, style);

        //  让列宽随着导出的列长自动适应
        for (int i = 0; i < columnNum; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 888);//适当再宽点
        }

        //导出到浏览器
        if (isToBrowser) {
            this.exportToBrowser(title, workbook, this.request, this.response);
        }
    }

    /**
     * @MethodsName: setDataToCell
     * @Description: 数据设置到单元格中
     * @auther: wujc
     * @date: 2018-12-5 14:52
     * @param dataList
     * @param sheet
     * @param style
     * @return:
     */
    private void setDataToCell(List<Object[]> dataList,HSSFSheet sheet,HSSFCellStyle style) {
        for (int i = 0; i < dataList.size(); i++) {
            Object[] obj = dataList.get(i);//遍历每个对象
            HSSFRow row = sheet.createRow(i + 2);//创建所需的行数
            for (int j = 0; j < obj.length; j++) {
                HSSFCell cell = null;   //设置单元格的数据类型
                if (j == 0) {
                    // 第一列设置为序号
                    cell = row.createCell(j, CellType.NUMERIC);
                    cell.setCellValue(i + 1);
                } else {
                    cell = row.createCell(j, CellType.STRING);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        cell.setCellValue(obj[j].toString());  //设置单元格的值
                    } else {
                        cell.setCellValue("  ");
                    }
                }
                cell.setCellStyle(style); // 样式
            }
        }
    }

    private void setRowName(int columnNum, HSSFRow rowRowName, HSSFCellStyle columnStyle,String[] rowName) {
        // 循环 将列名放进去
        for (int i = 0; i < columnNum; i++) {
            HSSFCell cellRowName = rowRowName.createCell(i);
            cellRowName.setCellType(CellType.STRING);// 单元格类型
            HSSFRichTextString text = new HSSFRichTextString(rowName[i]);  // 得到列的值
            cellRowName.setCellValue(text); // 设置列的值
            cellRowName.setCellStyle(columnStyle); // 样式
        }
    }

    public static void exportToBrowser(String title, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (workbook != null) {
            // 输出到用户浏览器上
            OutputStream out = response.getOutputStream();
            try {
                // excel 表文件名
                String fileName = title + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + ".xls";
                String fileName11 = "";
                String userAgent = request.getHeader("USER-AGENT");
                if (StringUtils.contains(userAgent, "Firefox") || StringUtils.contains(userAgent, "firefox")) {//火狐浏览器
                    fileName11 = new String(fileName.getBytes(), "ISO8859-1");
                } else {
                    fileName11 = URLEncoder.encode(fileName, "UTF-8");//其他浏览器
                }
                String headStr = "attachment; filename=\"" + fileName11 + "\"";
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", headStr);
                workbook.write(out);
                out.flush();
                workbook.close();
            } catch (Exception e) {
                throw e;
            } finally {
                if (null != out) {
                    out.close();
                }
            }
        }
    }

    public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook, int fontSize) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) fontSize);
        //字体加粗
        // font.setBold(true);
        //设置字体名字
        font.setFontName("微软雅黑");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    public static HSSFCellStyle getColumnStyle(HSSFWorkbook workbook, int fontSize) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) fontSize);
        //设置字体颜色
        font.setColor(IndexedColors.WHITE.index);
        //字体加粗
        // font.setBold(true);
        //设置字体名字
        font.setFontName("微软雅黑");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        //设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        //设置右边框;
        style.setBorderTop(BorderStyle.THIN);
        //设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        //设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //设置背景颜色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    public static HSSFCellStyle getStyle(HSSFWorkbook workbook, int fontSize) {
        //设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) fontSize);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("微软雅黑");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //文本格式
        HSSFDataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));
        //设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        //设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.index);
        //设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.index);
        //设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }
}
