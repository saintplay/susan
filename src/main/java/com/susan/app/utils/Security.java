package com.susan.app.utils;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class Security {
	public static boolean tieneRol(Authentication authentication, String rol_buscado) {
		Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
		String rol;

		for (int i = 0; i < roles.size(); i++) {
			rol = roles.toArray()[i] + "";
			if (rol.equals(rol_buscado)) {
				return true;
			}
		}
		
		return false;
	}
}
