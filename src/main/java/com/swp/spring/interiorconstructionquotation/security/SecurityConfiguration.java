package com.swp.spring.interiorconstructionquotation.security;

import com.swp.spring.interiorconstructionquotation.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(IUserService iuserService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(iuserService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configurer -> configurer
//                        .requestMatchers(HttpMethod.GET, "/test").authenticated() những đường dẫn cần phải xác thực
//                        .requestMatchers(HttpMethod.GET, "/test").hasRole("STAFF") những đường dẫn cần phải có quyền cố định
                        .anyRequest().permitAll()
        ).formLogin(
                form -> form.loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(authenticationSuccessHandler) //xử lý phần gán User vào session
                        .permitAll()

        ).logout(
                LogoutConfigurer::permitAll
        ).exceptionHandling(
                config -> config.accessDeniedPage("/showPage403")
        );

        return http.build();
    }
}
