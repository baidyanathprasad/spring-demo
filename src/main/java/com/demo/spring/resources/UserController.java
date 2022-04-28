package com.demo.spring.resources;

import com.demo.spring.api.GenericResponse;
import com.demo.spring.api.users.UserResponse;
import com.demo.spring.domain.UserInput;
import com.demo.spring.exception.CustomException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	Map<String, String> users = new HashMap<>();
	
	@GetMapping
	public GenericResponse<?> getAllUsers() {
		if (users.isEmpty()) {
			return new GenericResponse<>("Success", new ArrayList<>());
		}
		
		List<UserResponse> userResponses = new ArrayList<>();
		for (String userId : users.keySet()) {
			userResponses.add(new UserResponse(userId, users.get(userId)));
		}
		
		return new GenericResponse<>("Success", userResponses);
	}
	
	@PostMapping
	public GenericResponse<?> createUser(@RequestBody UserInput input) {
		if (input == null || input.getUsername() == null) {
			return new GenericResponse<>("Failed", CustomException.BadRequest
					.getResponse("Request body can not be null."));
		}
		
		if (users.get(input.getUsername()) != null) {
			return new GenericResponse<>("Failed", CustomException.BadRequest
					.getResponse("User with this already exists in the system.")
			);
		}
		
		String userId = UUID.randomUUID().toString();
		users.put(userId, input.getUsername());
		
		return new GenericResponse<>("Success", new UserResponse(userId, input.getUsername()));
	}

	@GetMapping("/{userId}")
	public GenericResponse<?> getUserById(@PathVariable("userId") String userId) {
		String username = users.get(userId);
		if (username == null) {
			return new GenericResponse<>("User not found", CustomException.BadRequest
					.getResponse("User with ID: " + userId + " not found."));
		}
		return new GenericResponse<>("Success", new UserResponse(userId, username));
	}
}

