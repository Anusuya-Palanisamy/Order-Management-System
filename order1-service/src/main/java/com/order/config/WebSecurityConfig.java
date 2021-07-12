package com.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
        .authorizeExchange()
                .pathMatchers("/apis/CreateOrder").hasAnyRole("CUSTOMER")
                .pathMatchers("/apis/invoiceData").hasAnyRole("CUSTOMER")
                .pathMatchers("/apis/dateRange").hasRole("SYSTEM_ADMIN")
                .pathMatchers("/apis/updateById").hasRole("SYSTEM_ADMIN")
                .pathMatchers("/apis/dateRange").hasRole("SUPER_ADMIN")
                .pathMatchers("/apis/cityname").hasRole("SUPER_ADMIN")
                .pathMatchers("/apis/dateAndStaus").hasRole("SUPER_ADMIN")
                .pathMatchers("/apis/dateByOrder").hasRole("SUPER_ADMIN")
                .pathMatchers("/apis/StatusCount").hasRole("SUPER_ADMIN")
                .pathMatchers("/apis/orders").hasRole("SUPER_ADMIN")
                
                .anyExchange()
                .authenticated()
              
                .and()
                .formLogin();
        return http.build();
    }

}
