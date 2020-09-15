package springCas.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
public class TestController {
	
	@RequestMapping(path="/security/test",produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String securityTest() {
		RequestContextHolder.getRequestAttributes()
			.setAttribute("SOMESEESIONATTR", "someSessionAttrVal",
					RequestAttributes.SCOPE_SESSION);
		return "This is a security resource!";
	}
	
	@RequestMapping(path="/unsecurity/test",produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String unsecurityTest() {
		return "This is a unsecurity resource!";
	}
}
