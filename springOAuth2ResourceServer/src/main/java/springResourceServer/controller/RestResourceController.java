package springResourceServer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestResourceController {
	
	@RequestMapping("/api/someService")
    public ResponseEntity<String> profile(){
        
        return ResponseEntity.ok("someService is working!");
    }
}
