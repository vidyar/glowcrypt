package com.xnrand.glowcrypt.core.encryption;

import java.io.InputStream;
import java.io.OutputStream;

import com.xnrand.glowcrypt.core.keys.AESKey;

public class AESEncryption extends Cryption<AESKey> {

	public AESEncryption(AESKey key) {
		super(key);
	}

	@Override
	public void crypt(InputStream in, OutputStream outfile) {
		// TODO: encryption logic with glowcrypt encrypted file format
	}

}
