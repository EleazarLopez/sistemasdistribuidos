package es.urjc.laliga;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.urjc.laliga.User.User;
import es.urjc.laliga.User.UserRepository;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initDatabase() {
    	if ( userRepository.findByName("user")  == null ){
    		userRepository.save(new User("user", "pass", "ROLE_USER"));
    	}
    	if ( userRepository.findByName("admin")  == null ){
    		userRepository.save(new User("admin", "adminpass", "ROLE_USER", "ROLE_ADMIN"));
    	}
		
    }

}
