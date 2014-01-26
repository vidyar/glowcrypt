package com.xnrand.glowcrypt.core.keys;

import java.security.PrivateKey;

/**
 * Class for an RSA private key in glowcrypt
 * 
 * @author xnrand <http://xnrand.com> <https://github.com/xnrand>
 */
public final class RSAPrivateKey extends GlowKey<PrivateKey> {

	protected RSAPrivateKey(int keylen, PrivateKey key) {
		super(keylen, key);
	}

}
