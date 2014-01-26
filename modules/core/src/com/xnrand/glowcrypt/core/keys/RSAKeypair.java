package com.xnrand.glowcrypt.core.keys;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Class for an RSA keypair in glowcrypt, aiming to be more convenient than
 * java.security.*
 * 
 * @author xnrand <http://xnrand.com> <https://github.com/xnrand>
 */
public final class RSAKeypair {
	private final int keylen;
	private final RSAPrivateKey privateKey;
	private final RSAPublicKey publicKey;

	protected RSAKeypair(int keylen, RSAPrivateKey privateKey,
			RSAPublicKey publicKey) {
		this.keylen = keylen;
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}

	/**
	 * Generate an RSA keypair
	 * 
	 * @param keylen
	 *            key length
	 * @return the keypair
	 * @throws NoSuchAlgorithmException
	 */
	public static RSAKeypair generate(int keylen) throws NoSuchAlgorithmException {
		KeyPairGenerator kgen = KeyPairGenerator.getInstance("RSA");
		kgen.initialize(keylen);
		KeyPair kpair = kgen.generateKeyPair();
		RSAPrivateKey privateKey = new RSAPrivateKey(keylen, kpair.getPrivate());
		RSAPublicKey publicKey = new RSAPublicKey(keylen, kpair.getPublic());
		return new RSAKeypair(keylen, privateKey, publicKey);
	}

	/**
	 * @return the keylen
	 */
	public int getKeylen() {
		return keylen;
	}

	/**
	 * @return the privateKey
	 */
	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * @return the publicKey
	 */
	public RSAPublicKey getPublicKey() {
		return publicKey;
	}
}
