package springCas.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import springCas.bean.SystemUser;

public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		// 结合具体的逻辑去实现用户认证，并返回继承UserDetails的用户对象;
		System.out.println("SSO local auth:" + token.getAssertion().getPrincipal().getName());
		UserDetails user = new SystemUser(token.getAssertion().getPrincipal().getName(), "",
				Collections.emptyList());
		return user;
	}
}
