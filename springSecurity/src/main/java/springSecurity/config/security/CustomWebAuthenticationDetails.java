package springSecurity.config.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
/*
 *扩展用户名密码认证身份对象，提供除用户名密码之外的认证信息
*/
@SuppressWarnings("serial")
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
	private String extInfo;
	
	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String verifyCode) {
		this.extInfo = verifyCode;
	}

	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		extInfo = request.getParameter("extInfo");
	}

}
