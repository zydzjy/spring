package springCloudCircuitBreaker.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springCloudCircuitBreaker.service.TestService;


@Controller
public class TestController {
	@Autowired
	TestService serviceResillenceImpl;
	
	@RequestMapping(path="/test1",produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String test1() {
		String resultStr = serviceResillenceImpl.slow();
		System.out.println(resultStr);
		return resultStr;
	}
	
	@Value("${someParam}")
	private String someParam;
	
	@RequestMapping(path="/test2",produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String test2() {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("instanceId", someParam);
		return result.toString();
	}
}