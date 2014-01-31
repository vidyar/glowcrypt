package com.xnrand.glowcrypt.cli;

import com.xnrand.glowcrypt.cli.dev.DevCLI;

/**
 * Main class for the Glowcrypt command line interface
 * 
 * @author xnrand <http://xnrand.com/> <https://github.com/xnrand>
 */
public class GlowcryptCLI {
	/**
	 * Main method for the Glowcrypt command line interface
	 */
	public static void main(String[] args) throws Exception {
		// until the real CLI gets implemented, use the development CLI
		// this *will* change, do not rely on GlowcryptCLI.main for now
		DevCLI.main(args);
	}
}
