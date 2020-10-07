package springOAuth2ResourceServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {

	@GetMapping("/messages")
	public String[] getMessages() {
		String[] messages = new String[] {"Message 1", "Message 2", "Message 3"};
		return messages;
	}
}