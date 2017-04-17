package es.urjc.laliga.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	@Autowired
	MailSender mailSender;

    @RequestMapping(path = "/email/trigger/{to}", method = RequestMethod.GET)
    public String triggerEmail(@PathVariable String to) {
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hay una actualización en uno de tus partidos");
        message.setTo(to);
        message.setFrom("beny.aux@gmail.com");
        try {
            mailSender.send(message);
            return "{\"message\": \"OK\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\": \"Error\", \"error\":" + e +  "}";
        }
    }
}