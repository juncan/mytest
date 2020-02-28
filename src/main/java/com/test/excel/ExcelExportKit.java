package com.test.excel;


import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.yynf.of.kit.IExcelExport;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * excel帮助类 （自定义扩展属性）
 * 
 * @author xiezq
 *
 */
public class ExcelExportKit implements IExcelExport{

	private static final String VERSION_2003 = "2003";
	private static final int HEADER_ROW = 1;
	private static final int MAX_ROWS = 65535;
	// 默认使用2007
	private String version = "2007";
	private String[] sheetNames = new String[] { "sheet" };
	private int cellWidth = 8000;
	private String[][] headers;
	private List<List<Record>> data;
	private List<Select> selectList = new ArrayList<>();
	private List<Comment> commentList = new ArrayList<>();
	private List<CellRange> cellRangeList = new ArrayList<>();
	private List<CellValue> writeCellsList = new ArrayList<CellValue>();

	public ExcelExportKit(List<List<Record>> data) {
		this.data = data;
		this.headers = getHeaders(data);
	}

	public static ExcelExportKit data(List<List<Record>> data) {
		ExcelExportKit instance = new ExcelExportKit(data);
		instance.headers = getHeaders(data);
		return instance;
	}

	public Workbook export() {
		Preconditions.checkNotNull(data, "data can not be null");
		Preconditions.checkArgument(data.size() == sheetNames.length && sheetNames.length == headers.length,
				"data,sheetNames and headers'length should be the same." + "(data:" + data.size() + ",sheetNames:"
						+ sheetNames.length + ",headers:" + headers.length + ")");
		Preconditions.checkArgument(cellWidth >= 0, "cellWidth can not be less than 0");
		Workbook wb;
		if (VERSION_2003.equals(version)) {
			wb = new HSSFWorkbook();
			if (data.size() > 1) {
				for (int i = 0; i < data.size(); i++) {
					List<Record> item = data.get(i);
					Preconditions.checkArgument(item.size() < MAX_ROWS,
							"Data [" + i + "] is invalid:invalid data size (" + item.size()
									+ ") outside allowable range (0..65535)");
				}
			} else if (data.size() == 1 && data.get(0).size() > MAX_ROWS) {
				data = dice(data.get(0), MAX_ROWS);
				String sheetName = sheetNames[0];
				sheetNames = new String[data.size()];
				for (int i = 0; i < data.size(); i++) {
					sheetNames[i] = sheetName + (i == 0 ? "" : (i + 1));
				}
				String[] header = headers[0];
				headers = new String[data.size()][];
				for (int i = 0; i < data.size(); i++) {
					headers[i] = header;
				}
			}
		} else {
			wb = new XSSFWorkbook();
		}
		if (data.size() == 0) {
			return wb;
		}
		for (int i = 0; i < data.size(); i++) {
			Sheet sheet = wb.createSheet(sheetNames[i]);
			Row row;
			Cell cell;
			if (headers[i].length > 0) {
				row = sheet.createRow(0);
				for (int h = 0, lenH = headers[i].length; h < lenH; h++) {
					if (cellWidth > 0) {
						sheet.setColumnWidth(h, cellWidth);
					}
					// 支持renderExcel导出，相同列名处理
					// 预设列名加 + “-C”+ 单元格列号
					String name = headers[i][h];
					cell = row.createCell(h);
					cell.setCellValue(name.replace("-C" + h, ""));
				}
			}
			CellStyle ct = wb.createCellStyle();
			DataFormat df = wb.createDataFormat();
			ct.setDataFormat(df.getFormat("@"));
			for(int k=0;k<20;k++){
				sheet.setDefaultColumnStyle(k, ct);
			}
			for (int j = 0, len = data.get(i).size(); j < len; j++) {
				row = sheet.createRow(j + HEADER_ROW);
				Record record = data.get(i).get(j);
				if (record == null) {
					continue;
				}
				processAsRecord(row, record,ct);
			}
			
			//设置值
			for(int j=0; j < writeCellsList.size(); j++){
				CellValue v = writeCellsList.get(j);
				if(v.getSheetIndex() == i){
					row = sheet.getRow(v.getRow());
					if(row!=null){
						cell = row.getCell(v.getCol());
						if(cell!=null){
							cell.setCellValue(v.getValue());
						}
					}
				}
			}
			
			// 设置跨行跨列
			for (int j = 0; j < cellRangeList.size(); j++) {
				CellRange cr = cellRangeList.get(j);
				if(cr.getSheetIndex() == i){
					sheet.addMergedRegion(cr.getCellRangeAddress());
				}
			}
			// 设置单元格有效数据
			for(int j = 0; j < selectList.size(); j++){
			    Select select = selectList.get(j);
			    if(select.getSheetIndex() == i){
			        int[] regions = select.getRegions();
			        setValidationData(sheet, regions[0], regions[1], regions[2], regions[3], select.getData());
			    }
			}
			//设置批注
			for(int j = 0; j < commentList.size(); j++){
			    Comment comment = commentList.get(j);
			    if(comment.getSheetIndex() == i){
			        Row r = sheet.getRow(comment.getRow());
			        Cell c = r.getCell(comment.getCol());
			        Drawing p = sheet.createDrawingPatriarch();
			        CreationHelper helper = wb.getCreationHelper();
			        ClientAnchor anchor = helper.createClientAnchor();
			        anchor.setCol1((short)comment.getCol());
			        anchor.setCol2((short)(comment.getCol()+1));
			        anchor.setRow1(comment.getRow());
			        anchor.setRow2(comment.getRow()+3);
			        org.apache.poi.ss.usermodel.Comment hcomment = p.createCellComment(anchor);
			        RichTextString str = helper.createRichTextString(comment.getComment());
			        hcomment.setString(str);
	                //将批注添加到单元格对象中
	                c.setCellComment(hcomment);
			    }
			}
		}
		return wb;
	}
	
	/**
     * 添加数据有效性检查.
     * @param sheet 要添加此检查的Sheet
     * @param firstRow 开始行
     * @param lastRow 结束行
     * @param firstCol 开始列
     * @param lastCol 结束列
     * @param explicitListValues 有效性检查的下拉列表
     * @throws IllegalArgumentException 如果传入的行或者列小于0(< 0)或者结束行/列比开始行/列小
     */
    private void setValidationData(Sheet sheet, int firstRow,  int lastRow,
            int firstCol,  int lastCol,String[] explicitListValues) throws IllegalArgumentException{
        if (firstRow < 0 || lastRow < 0 || firstCol < 0 || lastCol < 0 || lastRow < firstRow || lastCol < firstCol) {
            throw new IllegalArgumentException("Wrong Row or Column index : " + firstRow+":"+lastRow+":"+firstCol+":" +lastCol);
        }
        if (sheet instanceof XSSFSheet) {
            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet)sheet);
            XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                    .createExplicitListConstraint(explicitListValues);
            CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
            XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
            sheet.addValidationData(validation);
        } else if(sheet instanceof HSSFSheet){
            CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
            DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(explicitListValues);
            DataValidation validation = new HSSFDataValidation(addressList, dvConstraint);
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
            sheet.addValidationData(validation);
        }
    }  

	private static void processAsRecord(Row row, Record record,CellStyle ct) {
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

	private static String[][] getHeaders(List<List<Record>> data) {
		String[][] headers = new String[data.size()][];
		for (int i = 0; i < data.size(); i++) {
			// 读取头部字段名
			if(data.get(i).size()>0){
				Record record = data.get(i).get(0);
				String[] header = {};
				header = record.getColumnNames();
				headers[i] = header;
			}
		}
		return headers;
	}
	//作用的sheet、下拉内容数组、pos=[起始行、终止行、起始列、终止列]
	public void setSelectData(int sheetIndex, String[] dlData, int[] regions){
	    Select select = new Select(sheetIndex, dlData, regions);
	    selectList.add(select);
	}
	//设置合并单元格、pos=[起始行、终止行、起始列、终止列]
	public void setCellRangeAddress(int sheetIndex, int[] regions){
		CellRange cr =  new CellRange(sheetIndex, regions);
	    cellRangeList.add(cr);
	}
	//设置批注
	public void setComment(int sheetIndex, int row, int col, String msg){
	    Comment comment = new Comment();
	    comment.setSheetIndex(sheetIndex);
	    comment.setRow(row);
	    comment.setCol(col);
	    comment.setComment(msg);
	    commentList.add(comment);
	}

	public void setCellValue(int sheetIndex, int row, int col, String text){
		CellValue v = new CellValue();
		v.setSheetIndex(sheetIndex);
		v.setRow(row);
		v.setCol(col);
		v.setValue(text);
		writeCellsList.add(v);
	}
	
	private static List<List<Record>> dice(List<Record> num, int chunkSize) {
		int size = num.size();
		int chunk_num = size / chunkSize + (size % chunkSize == 0 ? 0 : 1);
		List<List<Record>> result = Lists.newArrayList();
		for (int i = 0; i < chunk_num; i++) {
			result.add(Lists.newArrayList(num.subList(i * chunkSize, i == chunk_num - 1 ? size : (i + 1) * chunkSize)));
		}
		return result;
	}

	public ExcelExportKit version(String version) {
		if (!Strings.isNullOrEmpty(version)) {
			this.version = version;
		}
		return this;
	}

	public ExcelExportKit sheetNames(String... sheetNames) {
		if (null != sheetNames) {
			this.sheetNames = sheetNames;
		}
		return this;
	}

	public int getCellWidth() {
		return cellWidth;
	}

	public void setCellWidth(int cellWidth) {
		this.cellWidth = cellWidth;
	}
	
	/**
	 * 设置下拉有效数据对象
	 */
	class Select{
	    private int sheetIndex;
	    private String[] data;
	    private int[] regions;
	    
	    public Select(int sheetIndex, String[] data, int[] regions){
	        this.sheetIndex = sheetIndex;
	        this.data = data;
	        this.regions = regions;
	    }
	    public int getSheetIndex(){
	        return this.sheetIndex;
	    }
	    public String[] getData(){
	        return this.data;
	    }
	    public int[] getRegions(){
	        return this.regions;
	    }
	}

	/**
	 * 合并单元格对象
	 */
	class CellRange{
		private int sheetIndex;
		private int[] regions;
		private CellRangeAddress cra;

		public CellRange(int sheetIndex, int[] regions){
			this.sheetIndex = sheetIndex;
			this.regions = regions;
			this.cra = new CellRangeAddress(regions[0],regions[1],regions[2],regions[3]);
		}
		public int getSheetIndex(){
			return this.sheetIndex;
		}
		public int[] getRegions(){
			return this.regions;
		}
		public CellRangeAddress getCellRangeAddress(){
			return this.cra;
		}
	}
	
	class CellValue{
		private int sheetIndex;
		private int row;
	    private int col;
		private String value;
		public int getSheetIndex() {
			return sheetIndex;
		}
		public void setSheetIndex(int sheetIndex) {
			this.sheetIndex = sheetIndex;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getCol() {
			return col;
		}
		public void setCol(int col) {
			this.col = col;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	class Comment{
	    private int row;
	    private int col;
	    private String comment;
	    private int sheetIndex;
	    
        public int getSheetIndex() {
            return sheetIndex;
        }
        public void setSheetIndex(int sheetIndex) {
            this.sheetIndex = sheetIndex;
        }
        public int getRow() {
            return row;
        }
        public void setRow(int row) {
            this.row = row;
        }
        public int getCol() {
            return col;
        }
        public void setCol(int col) {
            this.col = col;
        }
        public String getComment() {
            return comment;
        }
        public void setComment(String comment) {
            this.comment = comment;
        }
	    
	}

	public ExcelExportKit cellWidth(Integer cellWidth) {
		if (null != cellWidth) {
			this.cellWidth = cellWidth;
		}
		return this;
	}

	public static void main(String[] args) {
		List<List<Record>> records = new ArrayList<List<Record>>();
		List<Record> recordList = new ArrayList<Record>();
		Record record = new Record();
		record.set("name", "xzq");
		record.set("age", "1");
		record.set("gender", "1");
		recordList.add(record);
		record = new Record();
		record.set("name", "xxx");
		record.set("age", "11");
		record.set("gender", "1");
		recordList.add(record);
		records.add(recordList);
		File file = new File("D:\\t.xlsx");
		try {
		    ExcelExportKit ee = ExcelExportKit.data(records);
		    ee.setComment(0, 0, 0, "hello1");
		    ee.setComment(0, 0, 1, "hello2");
		    ee.setComment(0, 0, 2, "hello3");
		    ee.setComment(0, 1, 0, "hello4");
		    ee.setComment(0, 2, 0, "hello5");
			ee.export().write(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

}