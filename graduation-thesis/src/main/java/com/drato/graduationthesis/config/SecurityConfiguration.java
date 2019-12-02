package com.drato.graduationthesis.config;

import com.drato.graduationthesis.dto.UserDto;
import com.drato.graduationthesis.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private MyAuthenticationEntryPoint authEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                    .antMatchers("/resources/**", "/login*", "/static/**", "/js/**", "/css/**", "/img/**", "/plugins/**", "/dist/**", "/webjars/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/api/**").hasAnyRole("API")
                    .antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                    .anyRequest().authenticated()
                .and().httpBasic()
                    .authenticationEntryPoint(authEntryPoint)
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout().deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                .and()
                    .rememberMe().key("RememberMeDrato").userDetailsService(userService);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        if (!userService.usernameExisted("admin")) {
            UserDto regUser = new UserDto("admin", "Admin", "User", "admin", "admin", "admin@drato.com", "ROLE_ADMIN");
            userService.createUser(regUser);
        }

        if (!userService.usernameExisted("api_admin")) {
            UserDto regUser = new UserDto("api_admin", "Admin API", "User", "0RbK9DsFyBSJvI8kxedz", "0RbK9DsFyBSJvI8kxedz", "api_admin@drato.com", "ROLE_API");
            userService.createUser(regUser);
        }
    }
}
