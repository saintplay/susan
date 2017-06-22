package com.susan.app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.susan.app.entity.UserRole;
import com.susan.app.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<GrantedAuthority> authorities = new ArrayList<>();

		com.susan.app.entity.User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("No user found with email:" + username);
		}

		authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}

	private User buildUser(com.susan.app.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnable(), true, true, true, authorities);
	}

	//
	private List<GrantedAuthority> buildAuthorities(List<UserRole> userRoles) {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for (UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return auths;
	}
}