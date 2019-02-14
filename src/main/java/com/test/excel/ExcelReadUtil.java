package com.test.excel;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author wujc
 * @ClassName ExcelReadUtil
 * @Description: TODO
 * @create 2018-11-09 16:30
 */
public class ExcelReadUtil {
    public static List<List<Record>> readExcel(InputStream is, String[] cols) {
        List<List<Record>> result = Lists.newArrayList();
        Workbook wb;
        try {
            wb = WorkbookFactory.create(is);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            List<Record> sheetList = Lists.newArrayList();
            int start = sheet.getFirstRowNum() + 1; //默认第一行数据为标题行，不读取数据
            int rows = sheet.getLastRowNum();

            for (int rowIndex = start; rowIndex <= rows; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Record columns = new Record();
                int cellNum = row.getLastCellNum() > cols.length ? cols.length : row.getLastCellNum();
                int nullBlackRecordNum = 0;
                int cellIndex = row.getFirstCellNum();
                int firstCellIndex = cellIndex;
                for (; cellIndex < cellNum; cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    String column = "";
                    if (cell == null) {
                        nullBlackRecordNum++;
                    }else{
                        int cellType = cell.getCellType();
                        switch (cellType) {
                            case 0:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    column = df.format(cell.getDateCellValue());
                                }else{
                                    column = String.valueOf(cell.getNumericCellValue());
                                }
                                break;
                            case 1:
                                column = cell.getStringCellValue();
                                break;
                            case 3:
                                column = "";
                                nullBlackRecordNum++;
                                break;
                            case 4:
                                column = cell.getBooleanCellValue() + "";
                                break;
                            case 5:
                            default:
                        }
                    }
                    columns.set(cols[cellIndex], column);
                }
                if (nullBlackRecordNum != (cellNum - firstCellIndex)) {
                    sheetList.add(columns);
                }
            }
            result.add(sheetList);
        }
        return result;
    }
}
