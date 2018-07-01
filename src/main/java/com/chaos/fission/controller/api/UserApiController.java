package com.chaos.fission.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chaos.fission.user.User;
import com.chaos.fission.user.UserRepository;

@RestController
@RequestMapping("/api")
public class UserApiController {
	
	private UserRepository userRepository;

	@Autowired
	public UserApiController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> findAll() {
		System.out.println("get");
		return userRepository.finaAll();
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		System.out.println("post");
		System.out.println(user.getEmail());
		return userRepository.save(user);
	}
	
	@RequestMapping(value="/user/{email}", method=RequestMethod.PUT)
	public User updateUser(@PathVariable String email, @RequestBody User user) {
		System.out.println("put");
		return userRepository.save(email, user);
	}
	
	@RequestMapping(value="/user/{email}", method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable String email) {
		System.out.println("delete");
		userRepository.delete(email);
	}
}
