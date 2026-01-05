package com.Inwarrenty.Utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.Inwarrenty.dataproviderbean.UserPOJO;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtils {
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(CSVReaderUtils.class);

	private CSVReaderUtils() {

	}

	public static <T> Iterator<T>  loadCSV(String pathOfCSVFile,Class<T>bean) {
		log.info("Load the CSV file from the path {}",pathOfCSVFile);
		
		InputStream isr = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader inr = new InputStreamReader(isr);
		CSVReader csvreader = new CSVReader(inr);
		/*
		 * List<String[]> datalist = csvreader.readAll(); for(String[] dataArray
		 * :datalist) { for(String data:dataArray) { System.out.print(data+" "); }
		 * System.out.println(""); }
		 */
		// Write code to map csv to pojo

		CsvToBean<T> csvtobean = new CsvToBeanBuilder(csvreader)
				.withType(bean)
				.withIgnoreEmptyLine(true).build();

		List<T> List = csvtobean.parse();
		System.out.println(List);
		log.info("Exit:: List of the CSV data list {}",List);
		return List.iterator();
		

	}

}
