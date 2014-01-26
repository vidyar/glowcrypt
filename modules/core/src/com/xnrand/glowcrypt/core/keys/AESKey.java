package com.xnrand.glowcrypt.core.keys;

import javax.crypto.SecretKey;

/**
 * Class for an AES key in glowcrypt
 * 
 * @author xnrand <http://xnrand.com> <https://github.com/xnrand>
 */
public class AESKey extends GlowKey<SecretKey> {

	protected AESKey(int keylen, SecretKey key) {
		super(keylen, key);
	}

	public AESKey generate() {
		// TODO: implement this.
		return null;
	}

}
