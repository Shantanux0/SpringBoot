package com.SecurityApp.SecurityAuth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class webSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(auth-> auth
                        .requestMatchers("/posts").permitAll() //permission to excess to all
                        .requestMatchers("/posts/**").hasAnyRole("ADMIN") // only to admins or specified Roles
                        .anyRequest().authenticated()) // used to authenticate the user
//                .csrf(csrfConfig->csrfConfig.disable()) // this statement will disable csrf to generate
                      .formLogin(Customizer.withDefaults()); //provide a  default login page for auth task
        return httpSecurity.build();
    }

    @Bean
    UserDetailsService myInMemoryUserDetailService(){
        UserDetails normaluser = User
                .withUsername("Shantanu")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();
        UserDetails vipUser = User
                .withUsername("Anuj")
                .password(passwordEncoder().encode("456"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(normaluser,vipUser);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
