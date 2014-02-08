package com.xnrand.glowcrypt.core.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

import com.xnrand.glowcrypt.core.Glowcrypt;
import com.xnrand.glowcrypt.core.keys.GlowKey;

/**
 * superclass for encryption and decryption classes
 * 
 * @author xnrand <http://xnrand.com/> <https://github.com/xnrand>
 * @param <TKey>
 */
public abstract class Cryption<TGlowKey extends GlowKey<?>> {
	final protected TGlowKey key;

	/**
	 * should <em>never</em> be changed because that would break all saved
	 * encrypted files
	 */
	protected static final int AESENCRYPTED = (Glowcrypt.FILETYPE_ENC << 16) + 0;
	/**
	 * should <em>never</em> be changed because that would break all saved
	 * encrypted files
	 */
	protected static final int AESRSAENCRYPTED = (Glowcrypt.FILETYPE_ENC << 16) + 2;

	public Cryption(TGlowKey key) {
		this.key = key;
	}

	public void crypt(File infile, File outfile) throws FileNotFoundException,
			IOException {
		FileInputStream in = new FileInputStream(infile);
		FileOutputStream out = new FileOutputStream(outfile);
		crypt(in, out);
		in.close();
		out.close();
	}

	/**
	 * Encrypts or decrypts data to or from glowcrypt's encrypted file format
	 * 
	 * @param in
	 * @param outfile
	 */
	public abstract void crypt(InputStream in, OutputStream outfile)
			throws IOException;
}
