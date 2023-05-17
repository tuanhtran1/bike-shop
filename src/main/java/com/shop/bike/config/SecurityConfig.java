package com.shop.bike.config;

import com.shop.bike.security.AuthoritiesConstants;
import com.shop.bike.security.jwt.JwtAuthenticationEntryPoint;
import com.shop.bike.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http
					.cors()// Sử dụng CorsFilter
				.and()
					.csrf().disable()// Vô hiệu hóa CSRF protection
				.exceptionHandling()
					.authenticationEntryPoint(unauthorizedHandler)
				.and()
					.headers()
					.referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
				.and()
					.permissionsPolicy().policy("camera=(), fullscreen=(self), geolocation=(), gyroscope=(), magnetometer=(), microphone=(), midi=(), payment=(), sync-xhr=()")
				.and()
					.frameOptions()
					.deny()
				.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					.authorizeRequests()
					//permit all
					.antMatchers("/api/v1/consumer/otp/**").permitAll()
					.antMatchers("/api/v1/consumer/register/**").permitAll()
					.antMatchers("/api/v1/consumer/authenticate").permitAll()
					.antMatchers("/api/v1/admin/authenticate").permitAll()
					.antMatchers("/api/v1/global/**").permitAll()
					.antMatchers("/api/v1/countries/**").permitAll()
					.antMatchers("/api/v1/provinces/**").permitAll()
					.antMatchers("/api/v1/districts/**").permitAll()
					.antMatchers("/api/v1/wards/**").permitAll()
					//config each other role
					.antMatchers("/api/**").authenticated()
					.antMatchers("/api/v1/admin/**").hasAuthority(AuthoritiesConstants.ADMIN)
					.antMatchers("/api/v1/consumer/**").hasAuthority(AuthoritiesConstants.CONSUMER)
					//swagger
					.antMatchers(AUTH_WHITELIST).permitAll()
					.anyRequest().authenticated();
		
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
	
	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v2
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**",
			"/swagger-ui/**"
			// other public endpoints of your API may be appended to this array
	};
}
