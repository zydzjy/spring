package springSecurity.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserAuthenticationProvider customUserAuthenticationProvider;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		customUserAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		auth.authenticationProvider(customUserAuthenticationProvider);
	}

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource;
	
//	@Override
//	public void configure(final WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/testNoSecurity/**");
//	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().permitAll();
		http.authorizeRequests().antMatchers("/test/security**").authenticated();
		http.authorizeRequests().antMatchers("/homePage").authenticated();
		http.formLogin()
			.loginPage("/login")//登录页面的URL
			.usernameParameter("username")//登录页面form表单的用户名
			.passwordParameter("password")//登录页面form表单的密码
			.loginProcessingUrl("/doLogin")//对应登录页面form表单的action
			.defaultSuccessUrl("/homePage", true)//登录成功之后跳转的页面，false表示如果之前访问的是受保护的页面，那么跳转到之前访问的受保护的页面，true表示总是跳转到设置的默认页面
			.authenticationDetailsSource(authenticationDetailsSource);
		http.logout()
			.logoutUrl("/doLogout")//执行logout的URL
			.clearAuthentication(true)//清除认证信息
			.logoutSuccessUrl("/login")//成功退出之后跳转到登录页面
			.invalidateHttpSession(true)
			.and().csrf().disable()//disable csrf,所以logout url可以GET,POST，否则只能是POST
			;
//		http.authorizeRequests().antMatchers("/login/form").permitAll().antMatchers("/securityPages/**")
//				.access("hasRole('ROLE1')").and().formLogin().defaultSuccessUrl(this.defaultSuccessUrl)// 如果用户登陆之前，没有访问受保护的页面，默认跳转到页面
//				// 登陆
//				.loginPage(this.loginPage).loginProcessingUrl(this.loginProcessingUrl)
//				.usernameParameter(this.usernameParam).passwordParameter(this.passwordParam)
//				// .successForwardUrl(this.successForwardUrl)//登陆成功
//				.failureForwardUrl(this.failureUrl)// 登陆失败
//				.and()
//				// for oauth2 client
//				//.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
//				// 登出
//				.logout().and().csrf().disable();
	}
}
