package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormat;

import ObjectProperties.PublicVariables;

public class ExcelConnections {
	public static HSSFRow Row;
	public static HSSFCell Cell;

	public static List<Integer> findRows(String testName) throws IOException {

		try {

			List<Integer> arrRows = new ArrayList<Integer>();
			String filePath = System.getProperty("user.dir") + "\\TestData\\TestData.xls";
			File fs = new File(filePath);
			FileInputStream fis = new FileInputStream(fs);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
//		XSSFWorkbook wb = new XSSFWorkbook(fis);
			HSSFSheet sh = wb.getSheet("Scenarios");
			int lastRow = sh.getLastRowNum();
			int firstRow = sh.getFirstRowNum();
			ArrayList<String> countries = new ArrayList<String>();
			for (int i = 0; i <= lastRow; i++) {
				if (sh.getRow(i).getCell(2).getStringCellValue().contains("Y")) {
					String count = sh.getRow(i).getCell(1).getStringCellValue();
					countries.add(count);
					PublicVariables.country = count;
					HSSFSheet subSheet = wb.getSheet(PublicVariables.country);
					int lastsubSheetRow = subSheet.getLastRowNum();
					int firstsubSheetRow = subSheet.getFirstRowNum();
					for (int k = firstsubSheetRow; k <= lastsubSheetRow; k++) {
						if (subSheet.getRow(k).getCell(0).getStringCellValue().contentEquals(testName) && (subSheet
								.getRow(k).getCell(2).getStringCellValue().toUpperCase().contentEquals("Y"))) {
							arrRows.add(k);

						}
					}
				}
			}
			sh = null;
			wb = null;
			fis = null;
			fs = null;
			return arrRows;
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println(e + "File not found exception");
		}
		return null;

	}

	public static HashMap<String, String> GetTestData(int pRow) {
		try {
			String strFilepath = System.getProperty("user.dir") + "\\TestData\\TestData.xls";
			HashMap<String, String> map = new HashMap<>();
			File fs = new File(strFilepath);
			PublicVariables pv = new PublicVariables();
			FileInputStream ofis = new FileInputStream(fs);
			HSSFWorkbook wb = new HSSFWorkbook(ofis);
			HSSFSheet sh = wb.getSheet(PublicVariables.country);
			int intlastrow = sh.getLastRowNum();
			int intfirstrow = sh.getFirstRowNum();
			for (int j = 0; j <= sh.getRow(pRow).getLastCellNum()- 1; j++) {
				try {
					map.put(sh.getRow(0).getCell(j).getStringCellValue(),sh.getRow(pRow).getCell(j).getStringCellValue());
				} catch (Exception e) {
					if (e.getMessage() == null) {
						map.put(sh.getRow(0).getCell(j).getStringCellValue(), "");
					} else {
						System.out.println("Error reading the data from excel file");
					}
				}
			}
				sh = null;
				wb = null;
				ofis = null;
				fs = null;
				return map;
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static void writeToExcel(String columnName, String value) throws IOException {
		try {
			String filePath = System.getProperty("user.dir") + "\\TestData\\TestData.xls";
			HashMap<String, String> map = new HashMap<>();
			File fs = new File(filePath);
			boolean flagModified = false;
			FileInputStream fis = new FileInputStream(fs);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet shss = wb.getSheet(PublicVariables.country);
			DataFormat fmt = wb.createDataFormat();
			HSSFCellStyle textStyle = wb.createCellStyle();
			textStyle.setDataFormat(fmt.getFormat("@"));
			int lastRow = shss.getLastRowNum();
			int firstRow = shss.getFirstRowNum();
			int pRow = PublicVariables.pRow;

			for (int j = 0; j <= shss.getRow(0).getLastCellNum()- 1; j++) {
				if (shss.getRow(0).getCell(j).getStringCellValue().contains(columnName)) {
					Row = (HSSFRow) shss.getRow(pRow);
					Cell = Row.getCell(j, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
					if (Cell == null) {
						Cell = Row.createCell(j);
						Cell.setCellType(Cell.CELL_TYPE_STRING);
						Cell.setCellValue(value);

					} else {
						Cell.setCellType(Cell.CELL_TYPE_STRING);
						Cell.setCellValue(value);
					}
					flagModified = true;
					break;
				}
			}
			if (flagModified) {
				if (PublicVariables.hmTestData.containsKey("BatchID")) {
					PublicVariables.hmTestData.remove("BatchID");
				}
				PublicVariables.hmTestData.put(columnName, value);
			} else {
				System.out.println("Column not found in excel");
			}
			FileOutputStream outputstream = new FileOutputStream(
					System.getProperty("user.dir") + "\\TestData\\TestData.xls");
			wb.write(outputstream);
			shss = null;
			fis = null;
			fs = null;
			wb = null;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception at writing the excel" + e);
		}

	}

	public static String getFieldValue(String column) {
		try {
			System.out.println("ColumnName::::" +column);
			System.out.println("Column Value::::" +PublicVariables.hmTestData.get(column).toString());
			return PublicVariables.hmTestData.get(column).toString();
			
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		
	}
}