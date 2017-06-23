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

	// Se encargara de llamar a nuestro repository y se obtendra una entidad
	// usuario(com.susan.app.entity.User) la cual se tendra que transformar a un userDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// Se obtiene el usuario en base a username
		com.susan.app.entity.User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("No user found with email:" + username);
		}
		
		// Transformar los roles a autorities
		authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);

	}

	// Retorna un User tipo de objeto que spring security necesita, recibe por
	// parametro usuario(com.susan.app.entity.User) y una lista
	// de authorities(GrantedAuthority) que en realidad es nuestra entidad rol
	private User buildUser(com.susan.app.entity.User user,
		List<GrantedAuthority> authorities) {
			return new User(
				user.getUsername(), 
				user.getPassword(), 
				user.isEnable(), 
				true, true, true, authorities
			);
	}

	// Asigna nuestros roles a una lista de grantedautorities
	private List<GrantedAuthority> buildAuthorities(List<UserRole> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserRole rol : userRoles) {
			authorities.add(new SimpleGrantedAuthority(rol.getRole()));
		}

		return authorities;
	}

}
