package com.app.util;
import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class Util {

	private Util() {
	}

	public static void forward(HttpServletRequest request, 
			HttpServletResponse response, String destino)
			throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(destino);//Vista
		rd.forward(request, response);
	}
}
