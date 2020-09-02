package app_checking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserPrincipalDetailsService userPrincipalDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		//auth.userDetailsService(this.userPrincipalDetailsService).passwordEncoder(passwordEncoder);
		//auth.userDetailsService(userPrincipalDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/swagger-ui.html")
			.hasAuthority("ROLE_ADMIN")
//			.hasRole("ADMIN")
//			//.hasAnyRole("ADMIN", "STUDENT")
			.antMatchers("/users").hasAuthority("ROLE_ADMIN")
			.antMatchers("/users/*").hasAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.PUT,"/users/*").hasAuthority("ROLE_ADMIN")
			//.antMatchers(HttpMethod.OPTIONS, "/users/*").permitAll()
			.and()
			.httpBasic();
		
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

		return daoAuthenticationProvider;
	}

}