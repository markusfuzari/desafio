package br.com.desafio.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = {
			"/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**",
            "/resources/public/**",
            "/resources/static/**"
    };
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder
        .inMemoryAuthentication()
        .withUser("desafio").password("{noop}desafio")
            .roles("USER")
		.and()
        .withUser("admin").password("{noop}admin")
            .roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(AUTH_WHITELIST).permitAll()
		.antMatchers("/pessoas/**").hasRole("ADMIN")
		.antMatchers("/setor/**").hasAnyRole("USER","ADMIN")
		.anyRequest().authenticated()
	    .and().formLogin();
	}
	
}