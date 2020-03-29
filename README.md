
[![GitHub Release](https://img.shields.io/github/release/magnasilvar/vigenere-cipher.svg)](https://github.com/magnasilvar/vigenere-cipher/releases) 
[![Follow @oliviermistral](https://img.shields.io/twitter/follow/oliviermistral.svg?style=social)](https://twitter.com/intent/follow?screen_name=oliviermistral) 

# vigenere-cipher

Java implementation of the **Vigenère cipher**.

## Setup

* Java >= `OpenJDK 11`

## How to

Clone or download the _vigenere-cipher_ project :
* Package it with `maven`.
```powershell
PS C:\GIT\vigenere-cipher> mvn clean package -U
```
* Run the compiled JAR with the `java` command and the following ordered arguments:
  1. encrypt|decrypt (depending on whether you want to encrypt or decrypt your text)
  2. Text to encrypt / decrypt (with the **Vigenère cipher**),
  3. Decryption key (case insensitive),
  4. Alphabet to use (optional / case insensitive) / default alphabet: 'ABCDEFGHIJKLMNOPQRSTUVWXYZ').
  > Following examples are executed with `Powershell`.

### Encrypt (with default alphabet)
```powershell
PS C:\GIT\vigenere-cipher> java -jar .\target\vigenere-cipher-2.0.0-SNAPSHOT.jar encrypt "Welcome to the Vigenère cipher !" MyPrivateKey
key=MYPRIVATEKEY
alphabet=ABCDEFGHIJKLMNOPQRSTUVWXYZ
Cipher text: Icatwhe ms dlc Hgvvvème vmzlcd !
```

### Decrypt (with default alphabet)
```powershell
PS C:\GIT\vigenere-cipher> java -jar .\target\vigenere-cipher-2.0.0-SNAPSHOT.jar decrypt "Icatwhe ms dlc Hgvvvème vmzlcd !" MyPrivateKey
key=MYPRIVATEKEY
alphabet=ABCDEFGHIJKLMNOPQRSTUVWXYZ
Plain text: Welcome to the Vigenère cipher !
```

### Encrypt (with custom alphabet)
```powershell
PS C:\GIT\vigenere-cipher> java -jar .\target\vigenere-cipher-2.0.0-SNAPSHOT.jar encrypt "Hello !" key ehkloy
key=KEY
alphabet=EHKLOY
Cipher text: Lekyo !
```

### Decrypt (with custom alphabet)
```powershell
PS C:\GIT\vigenere-cipher> java -jar .\target\vigenere-cipher-2.0.0-SNAPSHOT.jar decrypt "Lekyo !" key ehkloy
key=KEY
alphabet=EHKLOY
Plain text: Hello !
```
