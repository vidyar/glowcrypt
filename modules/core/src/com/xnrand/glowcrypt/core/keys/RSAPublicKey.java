package com.xnrand.glowcrypt.core.keys;

import java.security.PublicKey;

/**
 * Class for an RSA private key in glowcrypt
 * 
 * @author xnrand <http://xnrand.com> <https://github.com/xnrand>
 */
public final class RSAPublicKey extends GlowKey<PublicKey> {

	protected RSAPublicKey(int keylen, PublicKey key) {
		super(keylen, key);
	}
}
