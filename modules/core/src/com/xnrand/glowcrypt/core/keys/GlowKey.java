package com.xnrand.glowcrypt.core.keys;

import java.security.Key;

/**
 * abstract class for all wrapped keys in glowcrypt, aiming to be more
 * convenient than java.security.*
 */
public abstract class GlowKey<T extends Key> {
	protected final int keylen;
	protected final T key;

	/**
	 * Creates the key object from the {@link java.security.Key} object and the
	 * keylen (not necessarily the length of getBytes)
	 * 
	 * @param keylen
	 * @param key
	 */
	protected GlowKey(int keylen, T key) {
		this.keylen = keylen;
		this.key = key;
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
	
	// TODO: method to get key from byte[] array [abstract?]
	// TODO: method to get Base64 String from key (own key format?) [abstract?]
	// TODO: method to get key from Base64 String (own key format?) [abstract?]
}
