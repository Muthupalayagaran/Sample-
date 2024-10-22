package com.Oranium.Demo.DemoApplication.securityConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
{
  http.authorizeHttpRequests(configurer->
          configurer
                  .requestMatchers("/").hasRole("EMPLOYEE")
                  .requestMatchers("/leaders/**").hasRole("MANAGER")
                  .requestMatchers("/systems/**").hasRole("ADMIN")
                  .anyRequest().authenticated()
          )
          .formLogin(form->
                  form

                          .loginPage("/showLoginPage")
                          .loginProcessingUrl("/authenticateTheUser")
                          .permitAll()
          )
          .logout(logout->
                  logout.permitAll()
          )
          .exceptionHandling(configurer ->
                  configurer.accessDeniedPage("/access-denied")
          );



    return http.build();


}
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager()
//    {
//        UserDetails muthu= User.builder()
//                .username("muthu")
//                .password("{noop}1234")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails muthu1= User.builder()
//                .username("muthu1")
//                .password("{noop}1234")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//
//        UserDetails muthu2= User.builder()
//                .username("muthu2")
//                .password("{noop}1234")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(muthu,muthu1,muthu2);
//    }
//


}
