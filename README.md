Glowcrypt
=========
...because privacy matters!

Glowcrypt is a cross-platform (Linux, Windows and OS X) encryption and
decryption utility, its code is structured in modules:

* core: library for easy encryption and decryption of files and messages
* cli: command line interface to the functions the library offers (depends on core)
* gui: easy-to-use graphical user interface (depends on core)

This project's IRC channel is #glowcrypt on freenode, [check it out][webchat].

[webchat]:  https://kiwiirc.com/client/irc.freenode.net/?nick=glow?&theme=cli#glowcrypt

Build Status
------------
master: [![Build Status](https://travis-ci.org/xnrand/glowcrypt.png?branch=master)](https://travis-ci.org/xnrand/glowcrypt)

Building
--------
Glowcrypt is written in java and uses ant for building. You will need
a Java Development Toolkit, [Apache Ant][ant] and [Ant-Contrib][ant-contrib]
installed to build glowcrypt.

[ant]: https://ant.apache.org/
[ant-contrib]: http://ant-contrib.sourceforge.net/
