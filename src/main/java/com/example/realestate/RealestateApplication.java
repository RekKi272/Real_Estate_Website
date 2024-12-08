package com.example.realestate;

import com.example.realestate.Model.Image;
import com.example.realestate.Model.Property;
import com.example.realestate.Model.UpdateLog;
import com.example.realestate.Model.User;
import com.example.realestate.Repository.UserRepository;
import com.example.realestate.Service.PropertyService;
import com.example.realestate.Service.UpdateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class RealestateApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private UpdateLogService updateLogService;

	public static void main(String[] args) {
		SpringApplication.run(RealestateApplication.class, args);
	}

	public List<User> createAdmin(){
		User admin = new User();
		admin.setEmail("admin1@gmail.com");
		admin.setName("Minh Duc");
		admin.setPassword("admin");
		admin.setIsEnable(true);
		admin.setCheckStatus(true);
		admin.setPhone("1234567890");
		admin.setRole("ROLE_ADMIN");
		if (userRepository.findByEmail(admin.getEmail()) == null) {
			userRepository.save(admin);
		}

		User user1 = new User();
		user1.setEmail("user1@gmail.com");
		user1.setName("Hoang Tuan");
		user1.setPassword("1234");
		user1.setIsEnable(true);
		user1.setCheckStatus(true);
		user1.setPhone("0987654321");
		user1.setRole("ROLE_USER");
		if (userRepository.findByEmail(user1.getEmail()) == null) {
			userRepository.save(user1);
		}

		User user2 = new User();
		user2.setEmail("user2@gmail.com");
		user2.setName("Xuan Canh");
		user2.setPassword("1234");
		user2.setIsEnable(false);
		user2.setCheckStatus(false);
		user2.setPhone("1122334455");
		user2.setRole("ROLE_USER");
		if (userRepository.findByEmail(user2.getEmail()) == null) {
			userRepository.save(user2);
		}

		return List.of(admin, user1, user2);
	}

	@Override
	public void run(String... args) throws Exception {
//		List<User> users = createAdmin();
//		createProperty1(users.get(0));
//		createPropertyUser1(users.get(1));
//		createPropertyUser2(users.get(2));
	}
	public void createProperty1(User admin){
		// Create properties here

		Property adminProperty = new Property();
		Image image1_1 = new Image();
		image1_1.setUrl("hall-img-4.webp");
		image1_1.setProperty(adminProperty);

		Image image1_2 = new Image();
		image1_2.setUrl("hall-img-5.webp");
		image1_2.setProperty(adminProperty);

		adminProperty.setTitle("Luxury Office Space");
		adminProperty.setAddress("123 Business Street");
		adminProperty.setDescription("Spacious office in the heart of the city.");
		adminProperty.setPropertyType("Building");
		adminProperty.setServiceType("Rent");
		adminProperty.setCity("Hanoi");
		adminProperty.setCountry("Vietnam");
		adminProperty.setPrice(15000.0);
		adminProperty.setBedrooms(0);
		adminProperty.setBathrooms(2);
		adminProperty.setFloors(5);
		adminProperty.setBalcony(0);
		adminProperty.setSize(500.0);
		adminProperty.setStatus("Ready to move");
		adminProperty.setIsPublic(true);
		adminProperty.setHasLift(true);
		adminProperty.setHasPlayground(false);
		adminProperty.setHasGarden(false);
		adminProperty.setHasParkingArea(true);
		adminProperty.setHasShoppingMall(false);
		adminProperty.setHasHospital(false);
		adminProperty.setHasSchool(false);
		adminProperty.setCreatedAt(LocalDateTime.now());
		adminProperty.setUpdatedAt(LocalDateTime.now());
		adminProperty.setIsAvailable("Available");
		adminProperty.setPostedBy(admin);
		adminProperty.setFeedbackRequest("");
		adminProperty.setImages(Arrays.asList(image1_1, image1_2));
		propertyService.save(adminProperty);
		UpdateLog updateLog = new UpdateLog();
		updateLog.setProperty(adminProperty);
		updateLog.setUser(admin);
		updateLog.setField(UpdateLog.updateField.SERVICE_TYPE);
		updateLog.setOldPrice(adminProperty.getPrice());
		updateLog.setEvent("Listed for "+ adminProperty.getServiceType());
		updateLogService.saveUpdateLog(updateLog);

	}

	public void createPropertyUser1(User user1) {
		Property user1Property1 = new Property();

		Image image1_1 = new Image();
		image1_1.setUrl("hall-img-6.webp");
		image1_1.setProperty(user1Property1);

		Image image1_2 = new Image();
		image1_2.setUrl("home-bg.jpg");
		image1_2.setProperty(user1Property1);
		user1Property1.setImages(Arrays.asList(image1_1, image1_2));
		user1Property1.setTitle("Modern Apartment");
		user1Property1.setAddress("456 Green Avenue");
		user1Property1.setDescription("Fully furnished apartment.");
		user1Property1.setPropertyType("Flat");
		user1Property1.setServiceType("Sale");
		user1Property1.setCity("Ho Chi Minh City");
		user1Property1.setCountry("Vietnam");
		user1Property1.setPrice(200000.0);
		user1Property1.setBedrooms(3);
		user1Property1.setBathrooms(2);
		user1Property1.setFloors(1);
		user1Property1.setBalcony(2);
		user1Property1.setSize(120.0);
		user1Property1.setStatus("Ready to move");
		user1Property1.setIsPublic(false);
		user1Property1.setHasLift(true);
		user1Property1.setHasPlayground(true);
		user1Property1.setHasGarden(false);
		user1Property1.setHasParkingArea(true);
		user1Property1.setHasShoppingMall(false);
		user1Property1.setHasHospital(true);
		user1Property1.setHasSchool(true);
		user1Property1.setCreatedAt(LocalDateTime.now());
		user1Property1.setUpdatedAt(LocalDateTime.now());
		user1Property1.setIsAvailable("Available");
		user1Property1.setPostedBy(user1);
		user1Property1.setFeedbackRequest("");
		propertyService.save(user1Property1);
		UpdateLog updateLog = new UpdateLog();
		updateLog.setProperty(user1Property1);
		updateLog.setUser(user1);
		updateLog.setField(UpdateLog.updateField.SERVICE_TYPE);
		updateLog.setOldPrice(user1Property1.getPrice());
		updateLog.setEvent("Listed for "+ user1Property1.getServiceType());
		updateLogService.saveUpdateLog(updateLog);

		Property user1Property2 = new Property();

		Image image2_1 = new Image();
		image2_1.setUrl("House_1_1.jpg");
		image2_1.setProperty(user1Property2);

		Image image2_2 = new Image();
		image2_2.setUrl("House_1_2.jpg");
		image2_2.setProperty(user1Property2);
		user1Property2.setImages(Arrays.asList(image2_1, image2_2));
		user1Property2.setTitle("Cozy Studio");
		user1Property2.setAddress("789 Dream Street");
		user1Property2.setDescription("Affordable studio for singles.");
		user1Property2.setPropertyType("Flat");
		user1Property2.setServiceType("For Rent");
		user1Property2.setCity("Da Nang");
		user1Property2.setCountry("Vietnam");
		user1Property2.setPrice(500.0);
		user1Property2.setBedrooms(1);
		user1Property2.setBathrooms(1);
		user1Property2.setFloors(1);
		user1Property2.setBalcony(1);
		user1Property2.setSize(35.0);
		user1Property2.setStatus("Ready to move");
		user1Property2.setIsPublic(false);
		user1Property2.setHasLift(false);
		user1Property2.setHasPlayground(false);
		user1Property2.setHasGarden(false);
		user1Property2.setHasParkingArea(true);
		user1Property2.setHasShoppingMall(false);
		user1Property2.setHasHospital(true);
		user1Property2.setHasSchool(false);
		user1Property2.setCreatedAt(LocalDateTime.now());
		user1Property2.setUpdatedAt(LocalDateTime.now());
		user1Property2.setIsAvailable("Available");
		user1Property2.setPostedBy(user1);
		user1Property2.setFeedbackRequest("");
		propertyService.save(user1Property2);
		UpdateLog updateLog1 = new UpdateLog();
		updateLog1.setProperty(user1Property2);
		updateLog1.setUser(user1);
		updateLog1.setField(UpdateLog.updateField.SERVICE_TYPE);
		updateLog1.setOldPrice(user1Property2.getPrice());
		updateLog1.setEvent("Listed for "+ user1Property2.getServiceType());
		updateLogService.saveUpdateLog(updateLog1);

	}

	public void createPropertyUser2(User user2) {
		Property user2Property1 = new Property();
		Image image1_1 = new Image();
		image1_1.setUrl("house-img-1.webp");
		image1_1.setProperty(user2Property1);

		Image image1_2 = new Image();
		image1_2.setUrl("house-img-2.webp");
		image1_2.setProperty(user2Property1);
		user2Property1.setImages(Arrays.asList(image1_1, image1_2));

		user2Property1.setTitle("Family Home");
		user2Property1.setAddress("12 Peaceful Lane");
		user2Property1.setDescription("Perfect for families with kids.");
		user2Property1.setPropertyType("House");
		user2Property1.setServiceType("Sale");
		user2Property1.setCity("Hue");
		user2Property1.setCountry("Vietnam");
		user2Property1.setPrice(120000.0);
		user2Property1.setBedrooms(4);
		user2Property1.setBathrooms(3);
		user2Property1.setFloors(2);
		user2Property1.setBalcony(3);
		user2Property1.setSize(250.0);
		user2Property1.setStatus("Ready to move");
		user2Property1.setIsPublic(false);
		user2Property1.setHasLift(false);
		user2Property1.setHasPlayground(true);
		user2Property1.setHasGarden(true);
		user2Property1.setHasParkingArea(true);
		user2Property1.setHasShoppingMall(false);
		user2Property1.setHasHospital(false);
		user2Property1.setHasSchool(true);
		user2Property1.setCreatedAt(LocalDateTime.now());
		user2Property1.setUpdatedAt(LocalDateTime.now());
		user2Property1.setIsAvailable("Available");
		user2Property1.setPostedBy(user2);
		user2Property1.setFeedbackRequest("");
		propertyService.save(user2Property1);
		UpdateLog updateLog = new UpdateLog();
		updateLog.setProperty(user2Property1);
		updateLog.setUser(user2);
		updateLog.setField(UpdateLog.updateField.SERVICE_TYPE);
		updateLog.setOldPrice(user2Property1.getPrice());
		updateLog.setEvent("Listed for "+ user2Property1.getServiceType());
		updateLogService.saveUpdateLog(updateLog);

		Property user2Property2 = new Property();

		Image image2_1 = new Image();
		image2_1.setUrl("house-img-3.jpg");
		image2_1.setProperty(user2Property2);

		Image image2_2 = new Image();
		image2_2.setUrl("house-img-4.webp");
		image2_2.setProperty(user2Property2);
		user2Property2.setImages(Arrays.asList(image2_1, image2_2));
		user2Property2.setTitle("Beachfront Villa");
		user2Property2.setAddress("34 Ocean Drive");
		user2Property2.setDescription("Luxury living with a sea view.");
		user2Property2.setPropertyType("Villa");
		user2Property2.setServiceType("Rent");
		user2Property2.setCity("Nha Trang");
		user2Property2.setCountry("Vietnam");
		user2Property2.setPrice(3000.0);
		user2Property2.setBedrooms(5);
		user2Property2.setBathrooms(4);
		user2Property2.setFloors(3);
		user2Property2.setBalcony(4);
		user2Property2.setSize(450.0);
		user2Property2.setStatus("Ready to move");
		user2Property2.setIsPublic(false);
		user2Property2.setHasLift(true);
		user2Property2.setHasPlayground(false);
		user2Property2.setHasGarden(true);
		user2Property2.setHasParkingArea(true);
		user2Property2.setHasShoppingMall(false);
		user2Property2.setHasHospital(false);
		user2Property2.setHasSchool(false);
		user2Property2.setCreatedAt(LocalDateTime.now());
		user2Property2.setUpdatedAt(LocalDateTime.now());
		user2Property2.setIsAvailable("Available");
		user2Property2.setPostedBy(user2);
		user2Property2.setFeedbackRequest("");
		propertyService.save(user2Property2);
		UpdateLog updateLog1 = new UpdateLog();
		updateLog1.setProperty(user2Property2);
		updateLog1.setUser(user2);
		updateLog1.setField(UpdateLog.updateField.SERVICE_TYPE);
		updateLog1.setOldPrice(user2Property2.getPrice());
		updateLog1.setEvent("Listed for "+ user2Property2.getServiceType());
		updateLogService.saveUpdateLog(updateLog1);

	}
}
