package springOAuth2AuthServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.antMatchers("/oauth2/keys").permitAll()
//				.anyRequest().authenticated()
//				.and()
//			.formLogin();
		http
		.authorizeRequests()
			.mvcMatchers("/.well-known/jwks.json").permitAll()
			.anyRequest().authenticated()
			.and()
		.httpBasic()
			.and()
		.csrf().ignoringRequestMatchers((request) -> "/introspect".equals(request.getRequestURI()));
	}
	// @formatter:on

	@Bean
	public UserDetailsService users() throws Exception {
		return new InMemoryUserDetailsManager(
				User.withDefaultPasswordEncoder()
					.username("subject")
					.password("password")
					.roles("USER")
					.build());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}