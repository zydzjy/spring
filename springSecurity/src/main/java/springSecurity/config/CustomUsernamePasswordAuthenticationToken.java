package springSecurity.config;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import springSecurity.bean.security.SystemUser;

@SuppressWarnings("serial")
public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String extInfo;

	public CustomUsernamePasswordAuthenticationToken(SystemUser principal, 
			String credentials, String extInfo,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.extInfo = extInfo;
	}

	public String getExtInfo() {
		return extInfo;
	}
}
