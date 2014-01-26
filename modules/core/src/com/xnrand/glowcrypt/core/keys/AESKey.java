package com.xnrand.glowcrypt.core.keys;

import java.io.DataInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.xnrand.glowcrypt.core.Base64.InputStream;

/**
 * Class for an AES key in glowcrypt
 * 
 * @author xnrand <http://xnrand.com> <https://github.com/xnrand>
 */
public class AESKey extends GlowKey<SecretKey> {
	
	protected static final int type = AESKEY;

	protected AESKey(int keylen, SecretKey key) {
		super(keylen, key, type);
	}

	/**
	 * generate new {@link AESKey}
	 */
	public static AESKey generate(int keylen) throws NoSuchAlgorithmException {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(keylen);
		SecretKey secretKey = kgen.generateKey();
		return new AESKey(keylen, secretKey);
	}
	
	/**
	 * get {@link AESKey} from keylen and encoded key data
	 */
	public static AESKey fromBytes(int keylen, byte[] keyBytes) {
		SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
		return new AESKey(keylen, secretKey);
	}
	
	/**
	 * get {@link AESKey} from an {@link InputStream} in glowcrypt's key format
	 */
	public static AESKey readGlowKey(InputStream is) throws IOException, InvalidKeyException {
		DataInputStream dis = new DataInputStream(is);
		int keytype = dis.readInt();
		int keylen = dis.readInt();
		int byteslen = dis.readInt();
		byte[] bytes = new byte[byteslen];
		dis.readFully(bytes);
		if (keytype != type) {
			throw new InvalidKeyException();
		}
		return fromBytes(keylen, bytes);
	}

}
