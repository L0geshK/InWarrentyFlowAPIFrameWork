package com.Inwarrenty.Utils;

import java.util.Iterator;
import java.util.Locale;

import com.Inwarrenty.dataproviderbean.CreateJobBean;
import com.github.javafaker.Faker;

public class Demo {
	
	
 public static void fackers() {
	 Faker faker = new Faker(new Locale("en-IND"));
	 System.out.println(faker.name().firstName());
	 System.out.println(faker.name().firstName());
	 
	 System.out.println(faker.address().cityName());
	 
	 System.out.println(faker.number().digits(5));
	 
	 System.out.println(faker.numerify("93##########"));
	 
	
	
	 
		 
	 }
	public static void main(String[] args) {
		fackers();
		
		
	/* Iterator<CreateJobBean>iterator=CSVReaderUtils.loadCSV("TestData/CreateJobData.csv", CreateJobBean.class);
	 while(iterator.hasNext()) {
		 System.out.println(iterator.next()); */
	 }
	 
	 
	
	

}
