package com.test.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class ExcelExportUtil {
	
	/*public static ExcelExportSimpleKit exportAbsenceExcel(String title, List<Record> data, long studentNum) {
        int[] colWidth = {6*255, 8*255, 6*255, 10*255, 8*255, 11*255, 10*255, 10*255, 10*255, 10*255, 10*255, 10*255, 8*255, 12*255};
		return export(title, "因病缺课情况登记表.xlsx", studentNum, data, colWidth, "A3:M3", 11);
	}
	
	public static ExcelExportSimpleKit exportRegisterExcel(String title, List<Record> data, long studentNum) {
        int[] colWidth = {8*255, 10*255, 8*255, 15*255, 15*255, 15*255, 15*255, 15*255, 18*255};
		return export(title, "晨（午）检情况登记表.xlsx", studentNum, data, colWidth, null, 12);
	}
	
	
	public static ExcelExportSimpleKit exportStatisticsExcel(String title, List<Record> data) {
        int[] colWidth = {5*255, 7*255, 5*255, 9*255, 7*255, 10*255, 9*255, 9*255, 9*255, 6*255, 6*255, 7*255, 9*255, 9*255, 8*255, 9*255};
		return export(title, "学生晨（午）检及因病缺课追踪日统计表（校医用）.xlsx", 0, data, colWidth, "A2:P2", 9);
	}
	
	public static ExcelExportSimpleKit export(String title, String fileName, long studentNum, List<Record> data, int[] colWidth, String filterCol, int fontSize) {
		ExcelExportSimpleKit excel = new ExcelExportSimpleKit();
        Workbook wb = excel.getWorkBook();
        CellStyle simpleCellStyle = wb.createCellStyle();
        simpleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        simpleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        simpleCellStyle.setWrapText(true);
        Font simplefont = wb.createFont();
        simplefont.setFontHeightInPoints((short)fontSize);
        simpleCellStyle.setFont(simplefont);
        CellStyle titleCellStyle = wb.createCellStyle();
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleCellStyle.setWrapText(true);
        titleCellStyle.setBorderBottom(BorderStyle.THICK);
        CellStyle headCellStyle = wb.createCellStyle();
        headCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headCellStyle.setWrapText(true);
        Font font = wb.createFont();
        font.setFontHeightInPoints((short)18);
        headCellStyle.setFont(font);
        Sheet sheet = excel.getSheet();
        Row row;
        
        String[] header = getHeaders(data);
        int rowIndex = 0, colIndex = 0;
        row = createSimpleRow(sheet, rowIndex);
        createSimpleCell(row, colIndex, title, headCellStyle);        
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,colIndex,colWidth.length-1));
        rowIndex++;
     
        if(studentNum != 0){	//全校人数不为空0显示统计行
        	row = createSimpleRow(sheet, rowIndex);
            createSimpleCell(row, colIndex, "全校人数：" + studentNum, simpleCellStyle);        
            sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,colIndex,colIndex+2));
            if("因病缺课情况登记表.xlsx".equals(fileName)){
            	createSimpleCell(row, colIndex+3, "因病缺课人数：" + data.size(), simpleCellStyle);          	
            }else if("晨（午）检情况登记表.xlsx".equals(fileName)){
            	createSimpleCell(row, colIndex+3, "登记人数：" + data.size(), simpleCellStyle);
            }
            sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,colIndex+3,colIndex+5));
        	rowIndex++;
        }
        
        if (header.length > 0) {
			row = createSimpleRow(sheet, rowIndex);
			for (int h = 0, lenH = header.length; h < lenH; h++) {
				// 支持renderExcel导出，相同列名处理
				String name = header[h];
				createSimpleCell(row, h, name.replace("-C" + h, ""), titleCellStyle);			
			}
			rowIndex++;
		}

        for(int i= 0, len = colWidth.length; i < len; i++){
        	sheet.setColumnWidth(i, colWidth[i]);
        }
        
        if(filterCol != null && header.length > 0){      	
        	CellRangeAddress range = CellRangeAddress.valueOf(filterCol);
        	sheet.setAutoFilter(range);
        }
        
        for (int j = 0, len = data.size(); j < len; j++) {
			row = createSimpleRow(sheet, rowIndex);
			Record record = data.get(j);
			if (record == null) {
				continue;
			}
			processAsRecord(row, record, simpleCellStyle);
			rowIndex++;
		}
        
        excel.setFileName(fileName);
        return excel;
	}
	
	private static void processAsRecord(Row row, Record record, CellStyle ct) {
		Cell cell;
		Map<String, Object> map = record.getColumns();
		record.getColumns();
		Set<String> keys = map.keySet();
		int columnIndex = 0;
		for (String key : keys) {
			cell = row.createCell(columnIndex);
			cell.setCellValue(record.get(key) + "");
			cell.setCellStyle(ct);
			columnIndex++;
		}
	}	
	
	public static Cell createSimpleCell(Row row, int index, String value, CellStyle style){
        Cell cell = row.createCell(index);
        cell.setCellValue(value);
        cell.setCellStyle(style);
        return cell;
    }
	
	public static Row createSimpleRow(Sheet sheet, int index){
        return createSimpleRow(sheet, index, 800);
    }
	
	public static Row createSimpleRow(Sheet sheet, int index, int height){
        Row row = sheet.createRow(index);
        row.setHeight((short)height);
        return row;
    }
	
	private static String[] getHeaders(List<Record> data) {
		String[] header = {};
		if(data.size() > 0){
			Record record = data.get(0);
			header = record.getColumnNames();
		}
		return header;
	}*/
}
