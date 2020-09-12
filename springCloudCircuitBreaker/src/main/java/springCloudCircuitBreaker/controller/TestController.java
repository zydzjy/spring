package springCloudCircuitBreaker.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springCloudCircuitBreaker.service.TestService;


@Controller
public class TestController {
	@Autowired
	TestService serviceResillenceImpl;
	
	@RequestMapping("/test1")
	@ResponseBody
	public Map<String,Object> test1() {
		System.out.println(serviceResillenceImpl.slow());
		return null;
	}
	
	@Value("${someParam}")
	private String someParam;
	
	@RequestMapping("/test2")
	@ResponseBody
	public Map<String,Object> test2() {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("instanceId", someParam);
		return result;
	}
}