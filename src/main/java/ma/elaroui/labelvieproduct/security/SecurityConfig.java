package ma.elaroui.labelvieproduct.security;

import lombok.AllArgsConstructor;
import ma.elaroui.labelvieproduct.security.services.UserDetailServiceImplement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig{

    private UserDetailServiceImplement userDetailServiceImplement;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
       httpSecurity.formLogin();

       httpSecurity.rememberMe();
       httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
       httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
       httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
       httpSecurity.userDetailsService(userDetailServiceImplement);

        return httpSecurity.build();
    }
}
