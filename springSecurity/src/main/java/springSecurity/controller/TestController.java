package springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springSecurity.service.security.TestRedisService;

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
	
	@Autowired
	TestRedisService testRedisServiceImpl;
	
	@ResponseBody
	@RequestMapping(path="security/testPutStringWithTTL",produces=MediaType.TEXT_PLAIN_VALUE)
	public String testPutStringWithTTL() {
		try {
			testRedisServiceImpl.testPutStringWithTTL("testKey", "testVal");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "testPutStringWithTTL!";
	}
}
