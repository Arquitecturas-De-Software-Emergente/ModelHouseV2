package com.upc.coreservice.configuration;

import com.upc.coreservice.Service.Security.AccountServiceImpl;
import com.upc.coreservice.Util.AuthError;
import com.upc.coreservice.Util.AuthTokenHandler;
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
    private final AccountServiceImpl userDetailsService;
    private final AuthError unauthorizedHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public WebSecurityConfig(AccountServiceImpl userDetailsService, AuthError unauthorizedHandler){
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
                .antMatchers("/**").permitAll()
                //.antMatchers("/user/**",
                //        "/business_profile",
                //        "/media/**",
                //        "/account/{accountId}/business_profile",
                //        "/business_profile/upload/{id}",
                //        "/user_profile/upload/{id}",
                //        "/project/upload/{id}",
                //        "/project_activity/upload/{id}",
                //        "/project_resource/upload/{id}",
                //        "/business_profile/{id}",
                //        "/project",
                //        "/business_profile/{businessId}/project",
                //        "/project/{id}/profile",
                //        "/order/create-checkout-session",
                //        "/product",
                //        "/add",
                //        "/update/{productID}",
                //        "/addCart",
                //        "/carts",
                //        "/cart",
                //        "/carts/{cartId}",
                //        "/plans").permitAll()
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
