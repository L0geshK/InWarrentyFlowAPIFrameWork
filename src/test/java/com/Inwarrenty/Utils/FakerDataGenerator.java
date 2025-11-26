package com.Inwarrenty.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.Inwarrenty.request.model.CreateJobAPIPayload;
import com.Inwarrenty.request.model.Customer;
import com.Inwarrenty.request.model.CustomerAddress;
import com.Inwarrenty.request.model.CustomerProduct;
import com.Inwarrenty.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {

	private FakerDataGenerator() {

	}

	private final static Faker faker = new Faker(new Locale("en-IND"));
	private final static String COUNTRY = "India";
	private static Random random = new Random();
	private final static int MST_SERVICE_LOCATION_ID = 0;
	private final static int MST_PLATFORM_ID = 2;
	private final static int MST_WARRANTY_STATUS_ID = 1;
	private final static int MST_OEM_ID = 1;
	private final static int PRODUCT_ID = 1;
	private final static int MST_MODEL_ID = 1;
	private final static int validProblemsId[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24,
			26, 27, 28, 29 };

	public static CreateJobAPIPayload generateFakeCreateJobData() {
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddress();
		CustomerProduct customerproduct = generateFakeCustomerProduct();
		List<Problems> problemsList = generateFakeProblemsList();
		CreateJobAPIPayload payload = new CreateJobAPIPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_MODEL_ID,
				MST_OEM_ID, customer, customerAddress, customerproduct, problemsList);
		return payload;

	}

	public static Iterator<CreateJobAPIPayload> generateFakeCreateJobData(int count) {
		List<CreateJobAPIPayload> payloadlist = new ArrayList<CreateJobAPIPayload>();
		for (int i = 1; i <= count; i++) {
			Customer customer = generateFakeCustomerData();
			CustomerAddress customerAddress = generateFakeCustomerAddress();
			CustomerProduct customerproduct = generateFakeCustomerProduct();
			List<Problems> problemsList = generateFakeProblemsList();
			CreateJobAPIPayload payload = new CreateJobAPIPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
					MST_MODEL_ID, MST_OEM_ID, customer, customerAddress, customerproduct, problemsList);
			payloadlist.add(payload);

		}
		return payloadlist.iterator();

	}

	private static List<Problems> generateFakeProblemsList() {
		int count = random.nextInt(3) + 1;
		int randomIndex;
		String fakeRemark;
		Problems problems;
		List<Problems> problemList = new ArrayList<Problems>();

		for (int i = 1; i <= count; i++) {
			// Generating a random Problem ID and adding it to the list
			randomIndex = random.nextInt(validProblemsId.length);
			fakeRemark = faker.lorem().sentence(5);
			problems = new Problems(validProblemsId[randomIndex], fakeRemark);
			problemList.add(problems); //
		}
		return problemList;
	}

	private static CustomerProduct generateFakeCustomerProduct() {

		String dop = DateTimeUtils.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();
		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, PRODUCT_ID, MST_MODEL_ID);

		return customerProduct;
	}

	private static CustomerAddress generateFakeCustomerAddress() {
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pinCode = faker.numerify("#####");

		String state = faker.address().state();
		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,
				pinCode, COUNTRY, state);

		return customerAddress;
	}

	private static Customer generateFakeCustomerData() {
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("70########");
		String alternateMobileNumber = faker.numerify("70########");
		String customerEmailAddress = faker.internet().emailAddress();
		String altCustomerEmailAddress = faker.internet().emailAddress();

		Customer customer = new Customer(fname, lname, mobileNumber, alternateMobileNumber, customerEmailAddress,
				altCustomerEmailAddress);
		return customer;

	}

}
