package com.susan.app.utils;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;

public class Vista {
	public static String enviar(Model model, Authentication authentication, String vista) {
		User user = (User) SecurityContextHolder
				 .getContext().getAuthentication()
				 .getPrincipal();
		model.addAttribute("username", user.getUsername());
		Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
		String rol;
		rol = roles.toArray()[0] + "";
		model.addAttribute("rol", rol);
		return vista;
	}
}
