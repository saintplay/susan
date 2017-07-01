package com.susan.app.utils;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class Security {
	public static boolean tieneRol(Authentication authentication, String rol_buscado) {
		Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
		if (rol_buscado.equals(roles.toArray()[0] + "")) {
			return true;
		}
		return false;
	}
}
