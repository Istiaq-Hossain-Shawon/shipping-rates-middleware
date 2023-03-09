package com.middleware.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.middleware.api.config.util.JwtUtil;
import com.middleware.api.request.AuthenticationRequest;
import com.middleware.api.response.ApiErrorResponse;
import com.middleware.api.response.AuthenticationResponse;
import com.middleware.api.service.impl.UserDetailsServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "JWT Token")
@RestController
public class JwtTokenAuth {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@ApiOperation(value = "Get JWT Token")
	@RequestMapping(value = "/requesttoken", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());

			final String jwt = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		}
		catch (Exception e) {
			return new ResponseEntity<ApiErrorResponse>(
					new ApiErrorResponse("Incorrect username or password", HttpStatus.BAD_REQUEST.toString(),
							"", "", "Could not process request.Incorrect username or password"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}


		
	}

}
