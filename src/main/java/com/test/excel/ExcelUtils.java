package com.test.excel;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 功能说明:
 *
 * @Copyright: 优芽网络科技-版权所有
 * @version V1.0
 * @author qzq
 * @date   2018年11月17日
 * @since  JDK1.6
 */
public class ExcelUtils {
    private static String column = "";
    
	/**
	 * excel读取 修正基类不支持小数问题
	 * @param is
	 * @param cols
	 * @return
	 * @author qzq
	 * @date 2018年11月17日 下午5:14:25
	 */
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
			int start = sheet.getFirstRowNum() + 1; // 默认第一行数据为标题行。不读取数据
			int rows = sheet.getLastRowNum();

			for (int rowIndex = start; rowIndex <= rows; rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				if (row == null) continue;
				Record columns = new Record();
				int cellNum = row.getLastCellNum() > cols.length ? cols.length : row.getLastCellNum();
				int nullBlackRecordNum = 0;
				int cellIndex = row.getFirstCellNum();
				int firstCellIndex = cellIndex;
				for (; cellIndex < cellNum; cellIndex++) {
					Cell cell = row.getCell(cellIndex);
					column = "";
                    nullBlackRecordNum = getNullBlackRecordNum(nullBlackRecordNum, cell);
                    columns.set(cols[cellIndex], column);
				}
				if(nullBlackRecordNum!=(cellNum-firstCellIndex)){
					sheetList.add(columns);
				}
			}

			result.add(sheetList);
		}
		return result;
	}
	/**
	 * excel读取数据和列名
	 * @param is
	 * @return
	 * @author xml
	 * @date 2019年03月28日 下午5:14:25
	 */
	public static Record readExcelAndColumn(InputStream is, String[] cols) {
	    Record record = new Record();
        List<List<Record>> result = Lists.newArrayList();
        List<String> col_list = new ArrayList<>();
        Workbook wb;
        try {
            wb = WorkbookFactory.create(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            List<Record> sheetList = Lists.newArrayList();
            int start = sheet.getFirstRowNum();
            int rows = sheet.getLastRowNum();

            for (int rowIndex = start; rowIndex <= rows; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                Record columns = new Record();
                int cellNum = row.getLastCellNum() > cols.length ? cols.length : row.getLastCellNum();
                int nullBlackRecordNum = 0;
                int cellIndex = row.getFirstCellNum();
                int firstCellIndex = cellIndex;
                for (; cellIndex < cellNum; cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    if(rowIndex == 0){
                        col_list.add(cell.getStringCellValue());
                        continue;
                    }
                    column = "";
                    nullBlackRecordNum = getNullBlackRecordNum(nullBlackRecordNum, cell);
                    columns.set(cols[cellIndex], column);
                }
                // 默认第一行数据为标题行。读取数据不添加
                if(rowIndex == 0){
                    continue;
                }
                if(nullBlackRecordNum!=(cellNum-firstCellIndex)){
                    sheetList.add(columns);
                }
            }

            result.add(sheetList);
        }
        record.set("list", result);
        record.set("columns", col_list);
        return record;
	}

    private static int getNullBlackRecordNum(int nullBlackRecordNum, Cell cell) {
        if(cell==null){
            nullBlackRecordNum++;
        }else{
            int cellType = cell.getCellType();
            switch (cellType) {
                case Cell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        column = df.format(cell.getDateCellValue());
                    } else {
                        //支持整数和小数格式化
                        DecimalFormat df = new DecimalFormat("#.####");
                        column = df.format(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    column = cell.getStringCellValue().trim();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    column = cell.getBooleanCellValue() + "";
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    column = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_ERROR:

                case Cell.CELL_TYPE_BLANK:
                    column = "";
                    nullBlackRecordNum++;
                    break;
                default:
            }
        }
        return nullBlackRecordNum;
    }
    
    
    /**
     * 读取excel文件转成list
     * @param is
     * @return
     */
    public static List<List<List<Object>>> readExcel(InputStream is){
    	List<List<List<Object>>> result = Lists.newArrayList();
    	Workbook wb;
		try {
			wb = WorkbookFactory.create(is);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    	
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			List<List<Object>> sheetList = Lists.newArrayList();
			int start = sheet.getFirstRowNum(); //读取所有
			int rows = sheet.getLastRowNum();

			int colEnd = 0;
			int cindex = 0;
			for (int rowIndex = start; rowIndex <= rows; rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				if (row == null) continue;
				if(rowIndex == start){
					colEnd = row.getLastCellNum();
					cindex = row.getFirstCellNum();
				}
				int cellIndex = cindex;
				List<Object> rowList = new ArrayList<Object>();
				for (; cellIndex < colEnd; cellIndex++) {
					Cell cell = row.getCell(cellIndex);
                    rowList.add(getColumnValue(cell));
				}
				sheetList.add(rowList);
			}

			result.add(sheetList);
		}
    	return result;
    }
    
    //读取数值
    private static String getColumnValue(Cell cell) {
        if(cell==null){
            return "";
        }else{
            int cellType = cell.getCellType();
            switch (cellType) {
                case Cell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        column = df.format(cell.getDateCellValue());
                    } else {
                        //支持整数和小数格式化
                        DecimalFormat df = new DecimalFormat("#.####");
                        column = df.format(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    column = cell.getStringCellValue().trim();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    column = cell.getBooleanCellValue() + "";
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    column = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_ERROR:

                case Cell.CELL_TYPE_BLANK:
                    column = "";
                    break;
                default:
            }
        }
        return column;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	List<List<List<Object>>> list = readExcel(new FileInputStream("D:\\1.xls"));
    	System.out.println(list);
	}
}
