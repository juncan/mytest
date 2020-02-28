package com.test.excel;


import com.yynf.of.kit.IExcelExport;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelExportSimpleKit implements IExcelExport{

    private String fileName = "";
	private static final String VERSION_2003 = "2003";
	private static final int MAX_ROWS = 65535;
	// 默认使用2007
	private String version = "2007";
	private String sheetName = "sheet1";
	private int cellWidth = 8000;
	private Workbook wb;
	private Sheet sheet;

	public ExcelExportSimpleKit() {
	    createWbSheet();
	}
	
    public ExcelExportSimpleKit(String version) {
        this.version = version;
        createWbSheet();
    }
    
    public ExcelExportSimpleKit(String version, String sheetName) {
        this.version = version;
        this.sheetName = sheetName;
        createWbSheet();
    }
    
    private void createWbSheet(){
        if (VERSION_2003.equals(version)) {
            wb = new HSSFWorkbook();
        } else {
            wb = new XSSFWorkbook();
        }
        createSheet(sheetName);
    }
    
    public Sheet createSheet(String sheetName){
        Sheet sheet = wb.createSheet(sheetName);
        this.sheet = sheet;
        return sheet;
    }
    
    public Workbook getWorkBook(){
        return wb;
    }
    
    public Sheet getSheet(){
        return sheet;
    }

	public Workbook export() {
	    return wb;
	}
	
	public void setFileName(String fileName){
	    this.fileName = fileName;
	}
	
	public String getFileName(){
	    return this.fileName;
	}

	public static void main(String[] args) {
		
	}

}