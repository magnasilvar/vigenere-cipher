# vigenere-cipher

Java implementation of the **Vigenère cipher** (decryption part) as part of the "_hack mon annonce_" event organized by [Parrot](https://github.com/Parrot-Developers) in **2015**.

## Setup

* Java: `Oracle JDK 1.8`

## How to

At the root level of the project :
* Compile Vigenere java class with the `javac` command.
* Run the compiled class with the `java` command and the following arguments (respecting this order):
  1. Decryption key (Hexadecimal format needed ! It was the key's format provided during the event),
  2. Alphabet to use,
  3. Encrypted words (with Vigenère cipher).

> Powershell example:
```powershell
PS C:\GIT\vigenere-cipher> javac .\Vigenere.java
PS C:\vigenere-cipher> java Vigenere 434c45 ABCDEFGHIJKLMNOPQRSTUVWXYZ JPPNZ
Clé: CLE
Alphabet: ABCDEFGHIJKLMNOPQRSTUVWXYZ
Message clair: HELLO
```

> CAUTION - Use the same case for your 3 arguments !
