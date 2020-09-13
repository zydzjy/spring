package springSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
	
	@RequestMapping(path="/homePage")
	public String homePage() {
		return "homePage";
	}
}
