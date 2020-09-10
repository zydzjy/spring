package eurekaServer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override  
    protected void configure(HttpSecurity http) throws Exception {  
		http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
	
//	@Value("${spring.security.user.name}")
//	private String user;
//	@Value("${spring.security.user.password}")
//	private String password;
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser(this.user)
//		.password(this.passwordEncoder.encode(this.password))
//		.authorities(Collections.emptyList());
//	}
}
