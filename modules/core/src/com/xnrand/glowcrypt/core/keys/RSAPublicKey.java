package com.xnrand.glowcrypt.core.keys;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * Class for an RSA private key in glowcrypt
 * 
 * @author xnrand <http://xnrand.com/> <https://github.com/xnrand>
 */
public final class RSAPublicKey extends GlowKey<PublicKey> {

	protected static final int type = RSAPUBLICKEY;

	protected RSAPublicKey(int keylen, PublicKey key) {
		super(keylen, key, type);
	}

	/**
	 * get {@link RSAPublicKey} from keylen and encoded key data
	 */
	public static RSAPublicKey fromBytes(int keylen, byte[] keyBytes)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(
				keySpec);
		return new RSAPublicKey(keylen, publicKey);
	}

	/**
	 * get {@link RSAPublicKey} from an {@link InputStream} in glowcrypt's key
	 * format
	 */
	public static RSAPublicKey readGlowKey(InputStream is) throws IOException,
			InvalidKeyException, InvalidKeySpecException,
			NoSuchAlgorithmException {
		DataInputStream dis = new DataInputStream(is);
		int keytype = dis.readInt();
		int keylen = dis.readInt();
		int byteslen = dis.readInt();
		byte[] bytes = new byte[byteslen];
		dis.readFully(bytes);
		if (keytype != type) {
			throw new InvalidKeyException("wrong glowcrypt key type");
		}
		return fromBytes(keylen, bytes);
	}

}
