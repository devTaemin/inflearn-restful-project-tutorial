//package com.example.restfulwebservice.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
////@EnableWebSecurity
////@RequiredArgsConstructor
////@Configuration(proxyBeanMethods = false)
////@ConditionalOnDefaultWebSecurity
////@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//@EnableWebSecurity
//@Configuration
//@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//
////    private final Environment ENV;
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        String gatewayIpAddress = env.getProperty("gateway.ip");
////        String challengeIpAddress = env.getProperty("challenge-service.ip");
////        String payIpAddress = env.getProperty("pay-service.ip");
////
////        http
////                .httpBasic().disable()
////                .csrf().disable()
////                .authorizeRequests()
////                .antMatchers("/error/**").permitAll()
////                .antMatchers("/h2-console/**").permitAll()
////                .antMatchers("/**")
////                .access("hasIpAddress('" + gatewayIpAddress + "') or " +
////                        "hasIpAddress('" + challengeIpAddress + "') or " +
////                        "hasIpAddress('" + payIpAddress + "')"
////                )
////                .and()
////                .headers().frameOptions().disable();
////        return http.build();
////    }
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////
////        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
////        http.csrf().disable();
////        http.headers().frameOptions().disable();
////
////        return http.build();
////    }
//
////    @Bean
////    public UserDetailsService userDetailsManager(BCryptPasswordEncoder bCryptPasswordEncoder) {
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withUsername("user")
////                .password(bCryptPasswordEncoder.encode("userPass"))
////                .roles("USER")
////                .build());
////
////        manager.createUser(User.withUsername("admin")
////                .password(bCryptPasswordEncoder.encode("adminPass"))
////                .roles("USER", "ADMIN")
////                .build());
////
////        return manager;
////    }
////    @Bean
////    public BCryptPasswordEncoder bCryptPasswordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//}
