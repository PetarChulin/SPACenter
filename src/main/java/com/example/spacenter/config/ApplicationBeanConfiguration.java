package com.example.spacenter.config;

import com.example.spacenter.model.entity.RoleEnum;
import com.example.spacenter.repositories.UserRepository;
import com.example.spacenter.service.ApplicationUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


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
                .requestMatchers("/login-error").permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/login", "/register").anonymous()
                .requestMatchers("/","/home","/medical-procedures", "/spa-procedures", "/sapropel/details/**", "/laser/details/**",
                        "/SapropelProcedures/sapropel-procedures", "/SPARituals/spa-rituals",
                        "/LaserProcedures/laser-procedures" , "/LaserProcedures/laser/details/**").permitAll()
                .requestMatchers("/logout", "/cart", "/sapropel/buy/**", "/sapropel/delete/**",
                        "/laser/buy/**", "/laser/delete/**" , "/delete/all", "/change/username").authenticated()
                .requestMatchers("/").hasRole(RoleEnum.USER.name())
                .requestMatchers("/medical/add/**","/laser/add/**", "/spa/add/**")
                .hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MODERATOR.name())
                .requestMatchers("/change/role").hasRole(RoleEnum.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
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
//                .tokenValiditySeconds(300)
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
