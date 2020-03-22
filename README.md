# vigenere-cipher

Java implementation of the **Vigenère cipher** (decryption part).

## Setup

* Java >= `OpenJDK 11`

## How to

At the root level of the project :
* Compile project with `maven`.
* Run the compiled JAR with the `java` command and the following arguments (respecting this order):
  1. Encrypted sequence (with Vigenère cipher),
  2. Decryption key (Case insensitive),
  3. Alphabet to use (Optional / Case insensitive).

> Powershell example:
```powershell
PS C:\GIT\vigenere-cipher> mvn clean install -U
PS C:\GIT\vigenere-cipher> java -jar .\target\vigenere-cipher-1.0-SNAPSHOT.jar "Icatwhe ms dlc Hgvvvème vmzlcd !" MyPrivateKey                                                                                            key=MYPRIVATEKEY
alphabet=ABCDEFGHIJKLMNOPQRSTUVWXYZ
Decrypted sequence: Welcome to the Vigenère cipher !
```
