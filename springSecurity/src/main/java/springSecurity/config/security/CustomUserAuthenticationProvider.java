package springSecurity.config.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import springSecurity.bean.security.SystemUser;
import springSecurity.service.security.UserService;

@Component("customUserAuthenticationProvider")
public class CustomUserAuthenticationProvider implements AuthenticationProvider {
	private PasswordEncoder passwordEncoder;

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	private UserService userServiceImpl;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
		SystemUser user = null;
		if (userName != null) {
			try {
				user = this.userServiceImpl.getUserByLogin(userName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username/password");
		}
		String password = user.getLoginPwd();
		System.out.println("password:" + password);
		Object credentials = authentication.getCredentials();
		System.out.println("credentials:" + credentials);
		Object principal = authentication.getPrincipal();
		System.out.println("principal:" + principal);
		if (!passwordEncoder.matches((String)credentials, password)) {
			//throw new BadCredentialsException("Invalid username/password");
		}
		Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
		return new CustomUsernamePasswordAuthenticationToken(user, password, details.getExtInfo(), 
				authorities);
	}

	public boolean supports(Class<?> authentication) {
		return true;
		// return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
