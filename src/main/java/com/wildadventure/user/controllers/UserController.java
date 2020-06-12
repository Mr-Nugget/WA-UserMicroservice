package com.wildadventure.user.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wildadventure.user.models.AuthentificationRequest;
import com.wildadventure.user.models.AuthentificationResponse;
import com.wildadventure.user.models.User;
import com.wildadventure.user.services.IUserService;
import com.wildadventure.user.services.MyUserDetailsService;
import com.wildadventure.user.tools.JwtUtil;
import com.wildadventure.user.exceptions.UserNotFoundException;

/**
 * Controller for user operations
 * @author Titouan
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired
	private AuthenticationManager authentificationManager;
	@Autowired
	private JwtUtil jwtService;
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private IUserService service;


	/**
	 * Get user from DB by its ID
	 * @param id
	 * @return
	 * @throws UserNotFoundException
	 */
	@GetMapping(value="/byId/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) throws UserNotFoundException {
		Optional<User> option = service.getById(new Long(id));

		if(option.isPresent()) {
			return ResponseEntity.ok(option.get());
		}else {
			throw new UserNotFoundException("Cannot find user with id : " + id);
		}
	}
	
	/**
	 * Entrypoint to get a user with JWT token
	 * @param jwt
	 * @return
	 * @throws UserNotFoundException
	 */
	@GetMapping(value="/byToken/{jwt}")
	public ResponseEntity<User> getUserbyToken(@PathVariable String jwt) throws UserNotFoundException{
		if(jwt != null) {
			String mail = jwtService.extractUsername(jwt);
			if(mail != null) {
				User user = service.getByMail(mail);
				if(user != null) {
					return ResponseEntity.ok(user);
				}else {
					throw new UserNotFoundException("User not found with mail " + mail);
				}
			}else {
				throw new UserNotFoundException("Cannot extract username with this token");
			}
		}else {
			throw new UserNotFoundException("Invalid JWT token");
		}
	}

	/**
	 * Entrypoint to get a Userobject by it's mail/login
	 * @param mail
	 * @return Response entity
	 * @throws UserNotFoundException
	 */
	@GetMapping(value="/byMail/{mail}")
	public ResponseEntity<User> getUserByMail(@PathVariable String mail) throws UserNotFoundException{
		User user = service.getByMail(mail);

		if(user == null) {
			throw new UserNotFoundException("Cannot find user with mail : " + mail);
		}else {
			return ResponseEntity.ok(user);
		}
	}

	/**
	 * Entrypoint to save a new user into DB
	 * @param user
	 * @return
	 */
	@PostMapping(value="/add")
	public ResponseEntity<Void> createUser(@RequestBody User user){
		User userAdded = service.add(user);

		if(userAdded == null) {
			return ResponseEntity.notFound().build();
		}else {
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/byId/{id}")
					.buildAndExpand(userAdded.getId())
					.toUri();

			return ResponseEntity.created(location).build();
		}
	}
	
	/**
	 * EndPoint to authentificate a user with mail and password, generate a new Json Web Token
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 */
	@PostMapping(value="/authenticate")
	public ResponseEntity<AuthentificationResponse> authentificate(@RequestBody AuthentificationRequest request) throws UserNotFoundException{
		try {
			authentificationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword())
					);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(request.getMail());
			String jwt = jwtService.generateToken(userDetails);
			
			return ResponseEntity.ok(new AuthentificationResponse(jwt));
			
		}catch (BadCredentialsException e) {
			throw new UserNotFoundException("Cannot find username or password", e);
		}
	}
}
