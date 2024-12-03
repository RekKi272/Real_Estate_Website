package com.example.realestate;

import com.example.realestate.Model.User;
import com.example.realestate.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class RealestateApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(RealestateApplication.class, args);
	}

	public void createAdmin(){
		User admin = new User();
		admin.setEmail("admin1@gmail.com");
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setIsEnable(true);
		admin.setCheckStatus(true);
		admin.setPhone("1234567890");
		admin.setRole("ROLE_ADMIN");
		if(userRepository.findByEmail(admin.getEmail()) == null){
			userRepository.save(admin);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		createAdmin();
	}
}
