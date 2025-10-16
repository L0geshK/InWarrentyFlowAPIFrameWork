package com.Inwarrenty.Utils;

import java.util.Iterator;

import com.Inwarrenty.dataproviderbean.CreateJobBean;

public class Demo {
	public static void main(String[] args) {
		
	 Iterator<CreateJobBean>iterator=CSVReaderUtils.loadCSV("TestData/CreateJobData.csv", CreateJobBean.class);
	 while(iterator.hasNext()) {
		 System.out.println(iterator.next());
	 }
	}

}
