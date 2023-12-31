package com.animal.main;

import com.animal.main.DAO.AnimalRepo;
import com.animal.main.DAO.UserRepo;
import com.animal.main.Entity.User;
import com.animal.main.perform.AnimalWebClient;
import com.animal.main.validator.AccommodationValidator;
import com.animal.main.validator.IdentificationcodeValidator;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@SpringBootApplication
public class MainApplication implements CommandLineRunner, WebMvcConfigurer {

	@Bean
	LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang"); // Set the parameter name to change the locale
		return interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages"); // Use the basename of your properties files
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);

		try {
			new AnimalWebClient();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("TEST ERROR");

		}
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
