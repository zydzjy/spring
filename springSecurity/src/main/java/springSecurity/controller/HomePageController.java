package springSecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContext;

@Controller
public class HomePageController {
	
	@RequestMapping(path="/homePage")
	public String homePage() {
		Object principal = 
			SecurityContextHolder.getContext()
			.getAuthentication().getPrincipal();
		RequestContextHolder.getRequestAttributes()
				.setAttribute("USER", principal,
				RequestAttributes.SCOPE_REQUEST);
		RequestContextHolder.getRequestAttributes()
			.setAttribute("SOMESEESIONATTR", "someSessionAttrVal",
			RequestAttributes.SCOPE_SESSION);
		return "homePage";
	}
}
