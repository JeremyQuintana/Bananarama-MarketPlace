
package com.sept.rest.webservices.restfulwebservices.jwt.resource;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtTokenUtil;
import com.sept.rest.webservices.restfulwebservices.jwt.JwtUserDetails;

@RestController
@CrossOrigin(origins="${spring.crossorigin.url}")
public class JwtAuthenticationRestController {

  @Value("${jwt.http.request.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService jwtInMemoryUserDetailsService;

  private static final String validEmailDomain = "@student.rmit.edu.au";

  @RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody GoogleToken googleToken)
      throws AuthenticationException {
	  
	  //gets payload basically a readable version of the google token
	  Payload payload = getPayloadFromValidToken(googleToken.getGoogleUserToken());
	  
	  boolean valid = true;
	  String token = null;
	  //checks if payload is recieved
	  if (payload != null) {
		  
		  //checks email
		  if (payload.getEmail().contains(validEmailDomain)) {
			  //creates a token with a unique id for the email address placed inside
			  token = jwtTokenUtil.generateToken(payload.getEmail().substring(0,8));
		  } else {
			  valid = false;
		  }
	  } else {
		  valid = false;
	  }
	  
	  //sends a new token if good and if bad sends an unprocessable one creating an error frontend
	  ResponseEntity<?> retVal = null;
	  if (valid == true) {
		  retVal = ResponseEntity.ok(new JwtTokenResponse(token));
		  System.out.println("Logging in User ID: " + payload.getEmail().substring(0,8));
	  } else {
		  throw new AuthenticationException("INVALID_CREDENTIALS", null);
	  }
	  
	  return retVal;
  }
  
  //returns null if token not from google else returns a payload
  public Payload getPayloadFromValidToken(String token) {
	  GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
			    // Specify the CLIENT_ID of the app that accesses the backend:
			    .setAudience(Collections.singletonList("181217491085-qp9tlug637043leq42ahec33f03k9plt.apps.googleusercontent.com"))
			    // Or, if multiple clients access the backend:
			    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
			    .build();
	  
	  //attempt to convert token to googleidtoken
	  GoogleIdToken idToken = null;
	  try {
		  idToken = verifier.verify(token);
	  } catch (Exception e) {
		  throw new AuthenticationException("INVALID_CREDENTIALS", e);
	  }
	  
	  Payload payload = null;
	  
	  //validate token
	  if (idToken != null) {
		  payload = idToken.getPayload();
	  }
	  
	  return payload;
  }

  @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token)) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

 
  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  //dont need this
  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("INVALID_CREDENTIALS", e);
    }
  }
}
