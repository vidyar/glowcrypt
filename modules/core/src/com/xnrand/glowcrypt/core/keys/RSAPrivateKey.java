package com.xnrand.glowcrypt.core.keys;

import java.io.DataInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import com.xnrand.glowcrypt.core.Base64.InputStream;

/**
 * Class for an RSA private key in glowcrypt
 * 
 * @author xnrand <http://xnrand.com/> <https://github.com/xnrand>
 */
public final class RSAPrivateKey extends GlowKey<PrivateKey> {

	protected static final int type = RSAPRIVATEKEY;

	protected RSAPrivateKey(int keylen, PrivateKey key) {
		super(keylen, key, type);
	}

	/**
	 * get {@link RSAPrivateKey} from keylen and encoded key data
	 */
	public static RSAPrivateKey fromBytes(int keylen, byte[] keyBytes)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(
				keySpec);
		return new RSAPrivateKey(keylen, privateKey);
	}

	/**
	 * get {@link RSAPrivateKey} from an {@link InputStream} in glowcrypt's key format
	 */
	public static RSAPrivateKey readGlowKey(InputStream is) throws IOException,
			InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException {
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
