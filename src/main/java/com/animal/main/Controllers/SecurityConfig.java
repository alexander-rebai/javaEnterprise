package com.animal.main.Controllers;

import com.animal.main.DAO.UserRepo;
import com.animal.main.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepo userRepo;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        List<User> list = userRepo.findAll();
        InMemoryUserDetailsManagerConfigurer imudmc = auth.inMemoryAuthentication();
        for (User user : list) {
            imudmc.withUser(user.getUsername()).password(user.getPassword()).roles(user.getRole());
        }

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/LoginForm").permitAll()
                .antMatchers("/doLogin").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/api/**").permitAll()

                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/LoginForm")
                .loginProcessingUrl("/doLogin")
                .successHandler(new CustomAuthenticationSuccessHandler());

        http.logout().permitAll();
    }

}
