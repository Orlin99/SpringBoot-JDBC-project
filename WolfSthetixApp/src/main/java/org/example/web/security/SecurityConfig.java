package org.example.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/api/general_information").permitAll()
                .antMatchers(HttpMethod.GET, "/api/general_information/{ID}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/general_information").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/general_information/{ID}").permitAll()

                .antMatchers(HttpMethod.POST, "/api/more_information").permitAll()
                .antMatchers(HttpMethod.GET, "/api/more_information/{secondary_ID}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/more_information").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/more_information/{user_ID}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/more_information/{secondary_ID}").permitAll()

                .antMatchers(HttpMethod.POST, "/api/foods").permitAll()
                .antMatchers(HttpMethod.GET, "/api/foods/{food_name}").permitAll()

                .antMatchers(HttpMethod.GET, "/api/foods").permitAll()

                .antMatchers(HttpMethod.DELETE, "/api/foods").permitAll()

                .antMatchers(HttpMethod.POST, "/api/activity").permitAll()
                .antMatchers(HttpMethod.GET, "/api/activity/{activity_ID}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/activity").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/activity/{activity_ID}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/activity/{activity_ID}").permitAll()

                .antMatchers(HttpMethod.POST, "/api/feedback").permitAll()
                .antMatchers(HttpMethod.GET, "/api/feedback/{feedback_ID}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/feedback").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/feedback/{feedback_ID}").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
}