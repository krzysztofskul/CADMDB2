package pl.krzysztofskul.cadmdb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class CadmdbSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
	} 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.inMemoryAuthentication()
	       .passwordEncoder(new BCryptPasswordEncoder())
	           .withUser("krzysztofskul")
	           .password(passwordEncoder().encode("password"))
	           .roles("ADMIN")
	           .and()
	           .withUser("userguest")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	       ;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/home", "/test",  "/css/**", "/img/**").permitAll()
			.antMatchers("/cadmdb2").hasRole("{USER, ADMIN}")
			.anyRequest().authenticated()
			.and()
	        	.formLogin()
	            .loginPage("/login").permitAll()
	            .defaultSuccessUrl("/home", true)
	            .failureUrl("/login").permitAll()
            .and()
            	.logout()
	            .permitAll()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	
}
