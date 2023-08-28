package com.animal.main;

import com.animal.main.DAO.AnimalRepo;
import com.animal.main.DAO.UserRepo;
import com.animal.main.Entity.User;
import com.animal.main.validator.AccommodationValidator;
import com.animal.main.validator.IdentificationcodeValidator;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	@Bean
	LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}

	@Bean
	public IdentificationcodeValidator identificationcodeValidator() {
		return new IdentificationcodeValidator();
	}

	@Bean
	public AccommodationValidator accommodationValidator() {
		return new AccommodationValidator();
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// userRepo.deleteAll();

		// User user1 = new User();
		// user1.setUsername("asielfan");
		// user1.setPassword(passwordEncoder.encode("asielfan"));
		// user1.setRole("USER");

		// User user2 = new User();
		// user2.setUsername("admin");
		// user2.setPassword(passwordEncoder.encode("admin"));
		// user2.setRole("ADMIN");

		// userRepo.save(user1);
		// userRepo.save(user2);

		System.out.println("Application Started !!");

	}
}
