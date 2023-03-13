package com.example.spacenter.config;

import com.example.spacenter.model.entity.RoleEnum;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.service.ApplicationUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class ApplicationBeanConfiguration {

    private final UserRepository userRepository;

    public ApplicationBeanConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/","/home", "/medical-procedures",  "/sapropel/details/**", "/SapropelProcedures/sapropel-procedures",
                        "/LaserProcedures/laser-procedures" , "/LaserProcedures/laser/details/**").permitAll()
                .requestMatchers("/login", "/register").anonymous()
                .requestMatchers("/logout", "/cart", "/sapropel/buy/**", "/sapropel/delete/**",
                        "/laser/buy/**", "/laser/delete/**").authenticated()
                .requestMatchers("/").hasRole(RoleEnum.USER.name())
                .requestMatchers("/medical/add/**","/laser/add/**")
                .hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MODERATOR.name())
                .requestMatchers("/change/role", "/delete/role").hasRole(RoleEnum.ADMIN.name())
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/home", true)
                .failureForwardUrl("/login-error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true);
//                .and()
//                .rememberMe()
//                .key("someUniqueKey")
//                .tokenValiditySeconds(3)
//                .userDetailsService(userDetailsService(userRepository));

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ApplicationUserDetailsService(userRepository);
    }


}
