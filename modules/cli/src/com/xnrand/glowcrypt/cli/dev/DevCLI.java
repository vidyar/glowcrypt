package com.xnrand.glowcrypt.cli.dev;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import com.xnrand.glowcrypt.core.Base64;
import com.xnrand.glowcrypt.core.Glowcrypt;
import com.xnrand.glowcrypt.core.encryption.AESEncryption;
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
	private final InputStream in;
	private final PrintStream out;
	@SuppressWarnings("unused")
	private final PrintStream err;

	protected DevCLI(String[] args, InputStream in, PrintStream out,
			PrintStream err) {
		this.args = args;
		this.in = in;
		this.out = out;
		this.err = err;
	}

	public static void main(String[] args) throws Exception {
		new DevCLI(args, System.in, System.out, System.err).devcli();
	}

	protected void devcli() throws Exception {
		out.println(Glowcrypt.INFO);
		minArgs(1);
		switch (args[0]) {
		case "keygen":
			numArgs(3);
			Base64.OutputStream genout = new Base64.OutputStream(out,
					Base64.ENCODE);
			switch (args[1]) {
			case "rsa":
				RSAKeypair genkeypair = RSAKeypair.generate(Integer
						.parseInt(args[2]));
				out.println("Private:");
				out.println(genkeypair.getPrivateKey().getBytes().length
						+ " bytes");
				genkeypair.getPrivateKey().writeGlowKey(genout);
				genout.flushBase64();
				out.println("\nPublic:");
				out.println(genkeypair.getPublicKey().getBytes().length
						+ " bytes");
				genkeypair.getPublicKey().writeGlowKey(genout);
				genout.flushBase64();
				out.println();
				break;
			case "aes":
				AESKey genaeskey = AESKey.generate(Integer.parseInt(args[2]));
				out.println(genaeskey.getBytes().length + " bytes");
				genaeskey.writeGlowKey(genout);
				genout.flushBase64();
				out.println();
				break;
			default:
				unknownCommand("keygen");
			}
			break;
		case "readkey":
			minArgs(2);
			Base64.InputStream readin = new Base64.InputStream(in);
			switch (args[1]) {
			case "rsa":
				numArgs(3);
				switch (args[2]) {
				case "private":
					RSAPrivateKey readrsaprivatekey = RSAPrivateKey
							.readGlowKey(readin);
					out.println("Keylen " + readrsaprivatekey.getKeylen());
					break;
				case "public":
					RSAPublicKey readrsapublickey = RSAPublicKey
							.readGlowKey(readin);
					out.println("Keylen " + readrsapublickey.getKeylen());
					break;
				}
				break;
			case "aes":
				numArgs(2);
				AESKey readaeskey = AESKey.readGlowKey(readin);
				out.println("Keylen " + readaeskey.getKeylen());
				break;
			default:
				unknownCommand("readkey");
			}
			break;
		case "encrypt":
		case "decrypt":
			numArgs(5);
			File keyfile = new File(args[2]);
			File infile = new File(args[3]);
			File outfile = new File(args[4]);
			FileInputStream keyfis = new FileInputStream(keyfile);
			switch (args[1]) {
			case "aes":
				AESKey cryptaeskey = AESKey.readGlowKey(keyfis);
				switch (args[0]) {
				case "encrypt":
					AESEncryption cryptaesen = new AESEncryption(cryptaeskey);
					cryptaesen.crypt(infile, outfile);
					break;
				case "decrypt":
					// TODO: aes decryption
					break;
				}
				break;
			case "aesrsa":
				// TODO: aes-rsa encryption and decryption
				break;
			}
			keyfis.close();
			break;
		case "help":
			out.println("" + //
					"<> keygen rsa <keylen>\n" + //
					"<> keygen aes <keylen>\n" + //
					"<> readkey rsa private\n" + //
					"<> readkey rsa public\n" + //
					"<> readkey aes\n" + //
					"<> encrypt aes <keyfile> <infile> <outfile>\n" + //
					"<> encrypt rsaaes <keyfile> <infile> <outfile>\n" + //
					"<> decrypt aes <keyfile> <infile> <outfile>\n" + //
					"<> decrypt rsaaes <keyfile> <infile> <outfile>\n" + //
					"<> help");
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
