package com.Inwarrenty.CSV;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {
	public static void main(String[] args) throws IOException, CsvException {
		InputStream isr = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("TestData/LoginCredentials.csv");
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

	}

}
