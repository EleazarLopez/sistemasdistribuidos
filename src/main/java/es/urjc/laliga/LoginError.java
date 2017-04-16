package es.urjc.laliga;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginError {

	@RequestMapping("/loginerror")
	public String index() {

		
		return "loginerror";
	}

}