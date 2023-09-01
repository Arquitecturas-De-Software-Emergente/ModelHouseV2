package com.upc.modelhouse.configuration;

import com.upc.modelhouse.security.service.UserServiceImpl;
import com.upc.modelhouse.security.util.AuthError;
import com.upc.modelhouse.security.util.AuthTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserServiceImpl userDetailsService;
    private final AuthError unauthorizedHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public WebSecurityConfig(UserServiceImpl userDetailsService, AuthError unauthorizedHandler){
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public AuthTokenHandler authenticationJwtTokenFilter() {
        return new AuthTokenHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .and().cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/user/**",
                        "/api/v1/business_profile",
                        "/api/v1/account/{accountId}/business_profile",
                        "/api/v1/business_profile/{id}",
                        "/api/v1/project",
                        "/api/v1/business_profile/{businessId}/project",
                        "/api/v1/project/{id}/profile",
                        "/api/v1/order/create-checkout-session",
                        "/api/v1/product",
                        "/api/v1/add",
                        "/api/v1/update/{productID}",
                        "/api/v1/addCart",
                        "/api/v1/carts",
                        "/api/v1/cart",
                        "/api/v1/carts/{cartId}",
                        "/api/v1/plans").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api-docs/**", "/swagger-ui.html", "/swagger-ui/**","/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**");
    }

}
