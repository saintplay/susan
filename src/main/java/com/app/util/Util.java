package com.app.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class Util {

	private Util() {
	}
	
	public static String toUTF8(String isocharset)
			throws UnsupportedEncodingException {
		return new String(isocharset.getBytes("iso-8859-1"), "utf-8");
	}
}
