package springCas.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import springCas.service.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
public class CASSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${security.cas.serviceUrl}")
	private String casServiceUrl;
	@Value("${security.cas.ticketValidatorUrl}")
	private String ticketValidatorUrl;
	@Value("${security.cas.callbackUrl}")
	private String callbackUrl;
	@Value("${security.cas.loginUrl}")
	private String casLoginUrl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
	}

	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService(this.casServiceUrl);
		serviceProperties.setAuthenticateAllArtifacts(false);
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}

	// cas 认证 Provider
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService()); // 这里只是接口类型，实现的接口不一样，都可以的。
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(cas30ServiceTicketValidator());
		// casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
		casAuthenticationProvider.setKey("casAuthenticationProviderKey");
		return casAuthenticationProvider;
	}

	public Cas30ServiceTicketValidator cas30ServiceTicketValidator() {
		return new Cas30ServiceTicketValidator(this.ticketValidatorUrl);
	}

	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
		return new Cas20ServiceTicketValidator(this.ticketValidatorUrl);
	}

	// 用户自定义的AuthenticationUserDetailsService
	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
		return new CustomUserDetailsService();
	}

	// CAS认证过滤器
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		casAuthenticationFilter.setFilterProcessesUrl(this.callbackUrl);
		casAuthenticationFilter.setServiceProperties(serviceProperties());
		return casAuthenticationFilter;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		SingleSignOutFilter ssoFilter = new SingleSignOutFilter();
		//ssoFilter.setCasServerUrlPrefix("http://192.168.56.1:8011/cas");
		//ssoFilter.setIgnoreInitConfiguration(true);
		
		// 配置安全策略
		http.authorizeRequests().antMatchers("/security/**").authenticated()
			.and().anonymous().disable();
		http.csrf().disable()
		.httpBasic()
		.authenticationEntryPoint(casAuthenticationEntryPoint()).and()
		.addFilterAt(casAuthenticationFilter(), CasAuthenticationFilter.class)
		.addFilterBefore(requestSingleLogoutFilter(),LogoutFilter.class)
		.addFilterBefore(ssoFilter, CasAuthenticationFilter.class)
		;
	}
	
	@Value("${security.cas.logoutUrl}")
	private String logoutUrl;
	protected LogoutFilter requestSingleLogoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter(logoutUrl,
				new SecurityContextLogoutHandler(),
				new LogoutHandler() {
					@Override
					public void logout(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) {
						System.out.println("local logout success...");
					}
		});
		logoutFilter.setFilterProcessesUrl("/security/logout");
		return logoutFilter;
	}
	
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(this.casLoginUrl);
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}
}