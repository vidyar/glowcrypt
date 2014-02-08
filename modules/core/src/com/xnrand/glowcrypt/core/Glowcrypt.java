package com.xnrand.glowcrypt.core;

/**
 * General class for the core library
 * 
 * @author xnrand <http://xnrand.com/> <https://github.com/xnrand>
 */
public class Glowcrypt {
	public static final String INFO = "Glowcrypt by xnrand: https://github.com/xnrand/glowcrypt";
	
	/** should <em>never</em> be changed because that would break all saved keys */
	public static final short FILETYPE_KEY = 0;
	/**
	 * should <em>never</em> be changed because that would break all saved
	 * encrypted files
	 */
	public static final short FILETYPE_ENC = 1;
}
