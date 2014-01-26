package com.xnrand.glowcrypt.core.keys;

import javax.crypto.SecretKey;

/**
 * Class for an AES key in glowcrypt
 */
public class AESKey extends GlowKey<SecretKey>{

	protected AESKey(int keylen, SecretKey key) {
		super(keylen, key);
	}
	
	public AESKey generate() {
		// TODO: implement this.
		return null;
	}

}
