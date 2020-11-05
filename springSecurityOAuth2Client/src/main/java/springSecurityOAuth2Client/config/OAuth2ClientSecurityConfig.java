package springSecurityOAuth2Client.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

@EnableWebSecurity
public class OAuth2ClientSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.oauth2Client(
				oauth2 -> oauth2.clientRegistrationRepository(this.clientRegistrationRepository())
						.authorizedClientRepository(this.authorizedClientRepository())
						.authorizedClientService(this.authorizedClientService())
						.authorizationCodeGrant(codeGrant -> codeGrant
								.authorizationRequestRepository(this.authorizationRequestRepository())
								.authorizationRequestResolver(this.authorizationRequestResolver())
								.accessTokenResponseClient(this.accessTokenResponseClient())));
	}

	private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
		// TODO Auto-generated method stub
		return null;
	}

	private OAuth2AuthorizationRequestResolver authorizationRequestResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	private AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	private OAuth2AuthorizedClientService authorizedClientService() {
		// TODO Auto-generated method stub
		return null;
	}

	private OAuth2AuthorizedClientRepository authorizedClientRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	private ClientRegistrationRepository clientRegistrationRepository() {
		// TODO Auto-generated method stub
		return null;
	}
}
