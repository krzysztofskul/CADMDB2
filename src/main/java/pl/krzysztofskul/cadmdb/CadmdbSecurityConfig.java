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
	       .passwordEncoder(passwordEncoder())
	           .withUser("krzysztofskul")
	           .password(passwordEncoder().encode("password"))
	           .roles("ADMIN")
	           .and()
	           .withUser("userguest")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("guest_investor").password(passwordEncoder().encode("password")).roles("USER_GUEST_INVESTOR")
	           .and()
	           .withUser("guest_hospital").password(passwordEncoder().encode("password")).roles("USER_GUEST_HOSPITAL")
	           .and()
	           .withUser("guest_manufacturer").password(passwordEncoder().encode("password")).roles("USER_GUEST_MANUFACTURER")
	           .and()
	           .withUser("guest_designer").password(passwordEncoder().encode("password")).roles("USER_GUEST_DESIGNER");

	       ;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/login", "/users/register", "/test/**", "/css/**", "/js/**", "/img/**").permitAll()
			.antMatchers("/").hasAnyRole("USER, ADMIN, USER_GUEST_INVESTOR, USER_GUEST_HOSPITAL, USER_GUEST_MANUFACTURER, USER_GUEST_DESIGNER")
			.anyRequest().authenticated()
			.and()
	        	.formLogin()
	            .loginPage("/login").permitAll()
	            .defaultSuccessUrl("/home", true)
	            .failureUrl("/login?error=true")
	            //.failureUrl("/login").permitAll()
            .and()
            	.logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .permitAll()
	            ;
	}
	
	
}
