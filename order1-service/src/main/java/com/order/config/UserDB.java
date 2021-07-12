package com.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Map;

@Configuration
public class UserDB {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public Map<String, UserDetails> map(){
        return Map.of(
                "customer", User.withUsername("customer").password(passwordEncoder.encode("customer")).roles("CUSTOMER").build(),
                "system_admin", User.withUsername("system_admin").password(passwordEncoder.encode("system_admin")).roles("SYSTEM_ADMIN").build(),
                "super_admin", User.withUsername("super_admin").password(passwordEncoder.encode("super_admin")).roles("SUPER_ADMIN").build()
        );
    }

}
