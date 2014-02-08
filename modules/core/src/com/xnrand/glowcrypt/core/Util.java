package com.xnrand.glowcrypt.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility functions for glowcrypt
 * 
 * @author xnrand <http://xnrand.com/> <https://github.com/xnrand>
 */
public class Util {
	/**
	 * Copies everything from <code>in</code> to <code>out</code>
	 * 
	 * @param in
	 *            the {@link InputStream} to copy from
	 * @param out
	 *            the {@link OutputStream} to copy to
	 * @param bufsize
	 *            buffer size
	 */
	public static void copyStream(InputStream in, OutputStream out, int bufsize)
			throws IOException {
		byte[] buffer = new byte[bufsize];
		int len;
		while ((len = in.read(buffer)) != -1)
			out.write(buffer, 0, len);
	}
}
