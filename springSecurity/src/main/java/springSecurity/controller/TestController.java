package springSecurity.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/test")
public class TestController {

	@ResponseBody
	@RequestMapping(path="security",produces=MediaType.TEXT_PLAIN_VALUE)
	public String security() {
		return "this is security resource!";
	}
	
	@ResponseBody
	@RequestMapping(path="unsecurity",produces=MediaType.TEXT_PLAIN_VALUE)
	public String unsecurity() {
		return "this is unsecurity resource!";
	}
}
