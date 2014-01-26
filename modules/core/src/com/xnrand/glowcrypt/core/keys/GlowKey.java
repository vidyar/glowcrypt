package com.xnrand.glowcrypt.core.keys;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;

/**
 * abstract class for all wrapped keys in glowcrypt, aiming to be more
 * convenient than java.security.*
 * 
 * @author xnrand <http://xnrand.com> <https://github.com/xnrand>
 */
public abstract class GlowKey<T extends Key> {
	protected final int keylen;
	protected final T key;
	protected final int keytype;
	
	/** should <em>never</em> be changed because it will break saved keys */
	protected static final int AESKEY = 1;
	/** should <em>never</em> be changed because it will break saved keys */
	protected static final int RSAPRIVATEKEY = 2;
	/** should <em>never</em> be changed because it will break saved keys */
	protected static final int RSAPUBLICKEY = 3;

	/**
	 * Creates the key object from the {@link java.security.Key} object and the
	 * keylen (not necessarily the length of getBytes)
	 * 
	 * @param keylen
	 * @param key
	 */
	protected GlowKey(int keylen, T key, int type) {
		this.keylen = keylen;
		this.key = key;
		this.keytype = type;
	}

	/**
	 * @return the key raw data
	 */
	public byte[] getBytes() {
		return key.getEncoded();
	}

	/**
	 * @return the keylen
	 */
	public int getKeylen() {
		return keylen;
	}

	/**
	 * @return the key
	 */
	public T getKey() {
		return key;
	}

	/**
	 * write the key in glowcrypt's format to an {@link OutputStream}
	 */
	public void writeGlowKey(OutputStream os) throws IOException {
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeInt(keytype);
		dos.write(keylen);
		byte[] keyBytes = getBytes();
		dos.write(keyBytes.length);
		dos.write(keyBytes);
	}
	
	
}
