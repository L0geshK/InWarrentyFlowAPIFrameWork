package com.Inwarrenty.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Inwarrenty.request.model.UserCredentials;
import com.poiji.bind.Poiji;

import io.qameta.allure.Step;

public class ExcelReaderUtils {
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(ExcelReaderUtils.class);


	private ExcelReaderUtils() {

	}

	public static void demo() throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet mysheet = workbook.getSheet("LogintestData");
		XSSFRow myrow;
		XSSFCell mycell;
		int lastrowindex = mysheet.getLastRowNum();
		XSSFRow rowHeader = mysheet.getRow(0);
		int lastIndexOfCol = rowHeader.getLastCellNum() - 1;

		for (int rowIndex = 0; rowIndex <= lastrowindex; rowIndex++) {
			for (int colIndex = 0; colIndex <= lastIndexOfCol; colIndex++) {
				myrow = mysheet.getRow(rowIndex);
				mycell = myrow.getCell(colIndex);
				System.out.println(mycell + "  ");

			}
			System.out.println(" ");
		}

	}

	@Step("LoadTest Data From Excell")
	public static Iterator<UserCredentials> loadTestData()  {
		log.info("Enter: the LoadTestData from Excell");

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/TestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet mysheet = workbook.getSheet("LogintestData");

		XSSFRow headerow = mysheet.getRow(0);
		int usernameindex = -1;
		int passwordindex = -1;
		for (Cell cell : headerow) {
			if (cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				usernameindex = cell.getColumnIndex();
			}
			if (cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
				passwordindex = cell.getColumnIndex();
			}
		}
		System.out.println(usernameindex + "  " + passwordindex);
		int lastRowIndex = mysheet.getLastRowNum();
		XSSFRow rowData;
		ArrayList<UserCredentials> userlist = new ArrayList<UserCredentials>();
		for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
			rowData = mysheet.getRow(rowIndex);
			UserCredentials usercredentials = new UserCredentials(rowData.getCell(usernameindex).toString(),
					rowData.getCell(passwordindex).toString());
			userlist.add(usercredentials);

		}
		log.info("Exit: the LoadTestData from Excell with userlist {}",userlist);
		return userlist.iterator();

	}
	@Step("LoadTest Data From ExcellPoiji")
	public static <T> Iterator<T> loadTestDataUsingPoiji(String xlsxFile, String sheetName, Class<T> clazz)  {
		// APACHE POI OOXML LIB
		log.info("Enter: the loadTestDataUsingPoiji with file{} with name {}",xlsxFile,sheetName);
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(xlsxFile);  
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Focus on the Sheet

		XSSFSheet mySheet = myWorkBook.getSheet(sheetName);   
		
		List<T> list=Poiji.fromExcel(mySheet, clazz);
		log.info("Exit:loadTestDataUsingPoiji with data {}",list);
		return list.iterator();
	}

		
	

}
