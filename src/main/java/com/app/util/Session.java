package com.app.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class Session {

	private Session() {
	}

	public static void put(HttpServletRequest request, String key, Object value) {
		HttpSession httpSession = request.getSession();//Crear SESION
		final String ID = httpSession.getId();//Obtener ID SESION 
		httpSession.setAttribute("ID", ID); //Almacenar datos SESION
		httpSession.setAttribute(key, value);
	}

	public static Object get(HttpServletRequest request, String key) {
		HttpSession httpSession = request.getSession();
		return httpSession.getAttribute(key);
	}

}