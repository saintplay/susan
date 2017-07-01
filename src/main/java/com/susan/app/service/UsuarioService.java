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

import com.susan.app.entity.Rol;
import com.susan.app.entity.Usuario;
import com.susan.app.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	// Se encargara de llamar a nuestro repository y se obtendra una entidad
	// usuario(com.susan.app.entity.User) la cual se tendra que transformar a un userDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// Se obtiene el usuario en base a username
		com.susan.app.entity.Usuario usuario = userRepository.findByUsername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("No user found with email:" + username);
		}
		
		// Transformar los roles a autorities
		authorities = buildAuthorities(usuario.getRol());
		return buildUser(usuario, authorities);

	}

	// Retorna un User tipo de objeto que spring security necesita, recibe por
	// parametro usuario(com.susan.app.entity.User) y una lista
	// de authorities(GrantedAuthority) que en realidad es nuestra entidad rol
	private User buildUser(com.susan.app.entity.Usuario usuario,
		List<GrantedAuthority> authorities) {
			return new User(
				usuario.getUsername(), 
				usuario.getPassword(), 
				usuario.isEnable(), 
				true, true, true, authorities
			);
	}

	// Asigna nuestros roles a una lista de grantedautorities
	private List<GrantedAuthority> buildAuthorities(List<Rol> usuarioRoles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Rol rol : usuarioRoles) {
			authorities.add(new SimpleGrantedAuthority(rol.getRole()));
		}

		return authorities;
	}

	public Usuario findOne(String username) {
		return userRepository.findOne(username);
	}

}
