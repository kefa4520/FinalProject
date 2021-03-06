
package com.skilldistillery.snitchapp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // this you get for free when you configure the db connection in application.properties file
    @Autowired
    private DataSource dataSource;

    // this bean is created in the application starter class if you're looking for it
    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()     // will hit the OPTIONS on the route
        .antMatchers(HttpMethod.GET, "/api/snitches").permitAll()
        .antMatchers(HttpMethod.GET, "/api/snitches/**/comments").permitAll()
        .antMatchers(HttpMethod.GET, "/api/snitches/search/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/categories").permitAll()
        .antMatchers(HttpMethod.GET, "/api/alerts").permitAll()
        .antMatchers(HttpMethod.POST, "/api/addresses").permitAll()
        .antMatchers(HttpMethod.DELETE, "/api/snitches").hasRole("ADMIN")//FIXME:Controller Delete methods needs Princpal for this.
 
        .antMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN") // For CORS, the preflight request
        .antMatchers(HttpMethod.DELETE, "/api/alerts").hasRole("ADMIN") // Only admin can only delete alert
        .antMatchers(HttpMethod.POST, "/api/alerts").hasRole("ADMIN") // Only admin can only create alert
        .antMatchers("/api/**").authenticated() // Requests for our REST API must be authorized.
        .anyRequest().permitAll()               // All other requests are allowed without authorization.
        .and()
        .httpBasic();                           // Use HTTP Basic Authentication

        http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery = "SELECT username, password, enabled FROM User WHERE username=?";
        String authQuery = "SELECT username, role FROM User WHERE username=?";
        auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(userQuery)
        .authoritiesByUsernameQuery(authQuery)
        .passwordEncoder(encoder);
    }
}
