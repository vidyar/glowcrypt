package com.xnrand.glowcrypt.core.keys;

import java.security.PrivateKey;

/**
 * Class for an RSA private key in glowcrypt
 */
public final class RSAPrivateKey extends GlowKey<PrivateKey> {

	protected RSAPrivateKey(int keylen, PrivateKey key) {
		super(keylen, key);
	}

	
}
