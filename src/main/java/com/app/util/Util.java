package com.app.util;
import java.io.UnsupportedEncodingException;

public final class Util {

	private Util() {
	}
	
	public static String toUTF8(String isocharset)
			throws UnsupportedEncodingException {
		return new String(isocharset.getBytes("iso-8859-1"), "utf-8");
	}
}
