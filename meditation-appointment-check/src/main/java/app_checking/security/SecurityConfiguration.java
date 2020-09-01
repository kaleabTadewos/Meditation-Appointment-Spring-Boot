package app_checking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
			.and()
			.withUser("user").password(passwordEncoder().encode("user")).roles("USER")
			.and()
			.withUser("manager").password(passwordEncoder().encode("manager")).roles("MANAGER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			//.anyRequest().authenticated()
			.antMatchers("/swagger-ui.html").hasAnyRole("ADMIN" , "MANAGER") //home page accessed by everyone
            .antMatchers("/users").hasAnyRole("ADMIN" , "MANAGER") //any authenticated user can access profile page
            .antMatchers("/users/*").hasRole("ADMIN") //any authenticated user can access profile page
//            .antMatchers("/admin/**").hasRole("ADMIN") //only admin can access the admin routes
//            .antMatchers("/management/**").hasAnyRole("ADMIN" , "MANAGER") //only admin and management role can access management pages
			.and()
			.httpBasic();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}