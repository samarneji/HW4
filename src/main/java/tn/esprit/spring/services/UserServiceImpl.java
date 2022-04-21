package tn.esprit.spring.services;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger l = LogManager.getLogger(UserServiceImpl.class);

	
	@Override
	public List<User> retrieveAllUsers() { 
		List<User> users = null; 
		try {
	
			l.info("In retrieveAllUsers() : ");
			users = (List<User>) userRepository.findAll();  
			for (User user : users) {
				l.debug("user +++ : " + user);
			} 
			l.info("Out of retrieveAllUsers() : ");
		}catch (Exception e) {
			l.error("Error in retrieveAllUsers() : " + e);
		}

		return users;
	}


	@Override
	public User addUser(User u) {
		return userRepository.save(u); 
	}

	@Override 
	public User updateUser(User u) { 
		return userRepository.save(u);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User retrieveUser(Long id) {
		l.info("in  retrieveUser id = " + id);
		User u =  userRepository.findById(id).get();
		l.info("user returned : " + u);
		return u; 
	}

}
