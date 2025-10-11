package com.Inwarrenty.Utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;


import com.Inwarrenty.dataproviderbean.UserPOJO;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtils {

	private CSVReaderUtils() {

	}

	public static Iterator<UserPOJO>  loadCSV(String pathOfCSVFile) {
		InputStream isr = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader inr = new InputStreamReader(isr);
		CSVReader csvreader = new CSVReader(inr);
		/*
		 * List<String[]> datalist = csvreader.readAll(); for(String[] dataArray
		 * :datalist) { for(String data:dataArray) { System.out.print(data+" "); }
		 * System.out.println(""); }
		 */
		// Write code to map csv to pojo

		CsvToBean<UserPOJO> csvtobean = new CsvToBeanBuilder(csvreader).withType(UserPOJO.class)
				.withIgnoreEmptyLine(true).build();

		List<UserPOJO> userList = csvtobean.parse();
		System.out.println(userList);
		return userList.iterator();
		

	}

}
