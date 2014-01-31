package com.xnrand.glowcrypt.cli.dev;

import com.xnrand.glowcrypt.core.Base64;
import com.xnrand.glowcrypt.core.Glowcrypt;
import com.xnrand.glowcrypt.core.keys.AESKey;
import com.xnrand.glowcrypt.core.keys.RSAKeypair;
import com.xnrand.glowcrypt.core.keys.RSAPrivateKey;
import com.xnrand.glowcrypt.core.keys.RSAPublicKey;

/**
 * Temporary CLI for development
 * 
 * This will be quick and dirty but it should do its job for testing the core
 * functions, the plan is to remove this development CLI once the main CLI is
 * good enough.
 * 
 * @author xnrand <http://xnrand.com/> <https://github.com/xnrand>
 */
public class DevCLI {

	private final String[] args;

	private DevCLI(String[] args) {
		this.args = args;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(Glowcrypt.INFO);
		new DevCLI(args).devcli();
	}

	private void devcli() throws Exception {
		minArgs(1);
		switch (args[0]) {
		case "keygen":
			numArgs(3);
			Base64.OutputStream genout = new Base64.OutputStream(System.out,
					Base64.ENCODE);
			switch (args[1]) {
			case "rsa":
				RSAKeypair genkeypair = RSAKeypair.generate(Integer
						.parseInt(args[2]));
				System.out.println("Private:");
				System.out.println(genkeypair.getPrivateKey().getBytes().length
						+ " bytes");
				genkeypair.getPrivateKey().writeGlowKey(genout);
				genout.flushBase64();
				System.out.println("\nPublic:");
				System.out.println(genkeypair.getPublicKey().getBytes().length
						+ " bytes");
				genkeypair.getPublicKey().writeGlowKey(genout);
				genout.flushBase64();
				System.out.println();
				break;
			case "aes":
				AESKey genaeskey = AESKey.generate(Integer.parseInt(args[2]));
				System.out.println(genaeskey.getBytes().length + " bytes");
				genaeskey.writeGlowKey(genout);
				genout.flushBase64();
				System.out.println();
				break;
			default:
				unknownCommand("keygen");
			}
			break;
		case "readkey":
			minArgs(2);
			Base64.InputStream readin = new Base64.InputStream(System.in);
			switch (args[1]) {
			case "rsa":
				numArgs(3);
				switch (args[2]) {
				case "private":
					RSAPrivateKey readrsaprivatekey = RSAPrivateKey.readGlowKey(readin);
					System.out.println("Keylen " + readrsaprivatekey.getKeylen());
					break;
				case "public":
					RSAPublicKey readrsapublickey = RSAPublicKey.readGlowKey(readin);
					System.out.println("Keylen " + readrsapublickey.getKeylen());
					break;
				}
				break;
			case "aes":
				numArgs(2);
				AESKey readaeskey = AESKey.readGlowKey(readin);
				System.out.println("Keylen " + readaeskey.getKeylen());
				break;
			default:
				unknownCommand("readkey");
			}
			break;
		case "encrypt":
			// TODO: encryption
			break;
		case "decrypt":
			// TODO: decryption
			break;
		default:
			unknownCommand("main");
		}
	}

	private void minArgs(int num) {
		if (args.length < num)
			throw new IllegalArgumentException("not enough arguments.");
	}

	private void maxArgs(int num) {
		if (args.length > num)
			throw new IllegalArgumentException("too many arguments.");
	}

	private void numArgs(int num) {
		minArgs(num);
		maxArgs(num);
	}

	private void unknownCommand(String at) {
		throw new IllegalArgumentException("unknown command at " + at);
	}

}
