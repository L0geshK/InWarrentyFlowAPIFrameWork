package com.Inwarrenty.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Step;

public class JsonReaderUtil {
	
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(JsonReaderUtil.class);

	
	@Step("LoadTest Data From Json")
	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz) {
		log.info("Enter : the LoadJson file name {}",fileName);
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
		T[] classArray;
		List<T> list = null;
		try {
			classArray = objectMapper.readValue(is, clazz);
			list = Arrays.asList(classArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Exit : the LoadJson datafile List {}",list);

		return list.iterator();
	}

}
