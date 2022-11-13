package com.ups.auth_server.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ups.auth_server.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServices implements UserDetailsService{
	
	private Logger log = LoggerFactory.getLogger(UserServices.class);

	@Autowired
	RestTemplate restTemplateUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("username", username);
		User user= restTemplateUser.getForObject("http://localhost:8783/api/user/find/{username}",User.class,pathVariables);
		
		if(user == null) {
			log.error("No existe: "+username);
			throw new UsernameNotFoundException("Error no existe el usuario: "+username);
		}

		List<GrantedAuthority> authorities
	      = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return new org.springframework.security.core.userdetails.User(user.getUsername(),
						user.getPassword(),
						true, true, true, true, authorities);
	}

}
