package zookeeperConfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zookeeperConfig.config.AppConfig;

@Controller
public class TestController {

	@Autowired
	AppConfig appConfig;
	
	@ResponseBody
	@RequestMapping(path="/test",produces=MediaType.TEXT_PLAIN_VALUE)
	public String test() {
		return appConfig.getTestPropFromZK();
	}
}