```
 |A|B|C|E              Still using the Vigenère square? Really?
-+-+-+-+               It's now over with the 'Vigenère cipher CLI'!
A|B|C|D                                 \(ᵔᵕᵔ)/
-+-+-+   🤔
B|C|C                  For the others, don't say we didn't warn you! ¯\_(ツ)_/¯
``` 

[![GitHub Release](https://img.shields.io/github/release/magnasilvar/vigenere-cipher.svg)](https://github.com/magnasilvar/vigenere-cipher/releases)
![Java CI with Maven](https://github.com/magnasilvar/vigenere-cipher/workflows/Java%20CI%20with%20Maven/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=magnasilvar_vigenere-cipher&metric=alert_status)](https://sonarcloud.io/dashboard?id=magnasilvar_vigenere-cipher)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=magnasilvar_vigenere-cipher&metric=coverage)](https://sonarcloud.io/dashboard?id=magnasilvar_vigenere-cipher)
[![Known Vulnerabilities](https://snyk.io/test/github/magnasilvar/vigenere-cipher/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/magnasilvar/vigenere-cipher?targetFile=pom.xml)
[![Follow @oliviermistral](https://img.shields.io/twitter/follow/oliviermistral.svg?style=social)](https://twitter.com/intent/follow?screen_name=oliviermistral)

# vigenere-cipher - a Java implementation with a convenient **CLI**

## Vignère cipher

A little bit of history (extract from [wikipedia](https://en.wikipedia.org/wiki/Vigenère_cipher)) : the **Vigenère cipher** is a method of encrypting alphabetic text by using a series of interwoven Caesar ciphers, based on the letters of a keyword. It employs a form of polyalphabetic substitution.

First described by **Giovan Battista Bellaso** in _1553_, the cipher is easy to understand and implement, but it resisted all attempts to break it until _1863_, three centuries later. This earned it the description le chiffre indéchiffrable (French for 'the indecipherable cipher'). Many people have tried to implement encryption schemes that are essentially Vigenère ciphers. In _1863_, **Friedrich Kasiski** was the first to publish a general method of deciphering Vigenère ciphers.

In the 19th century the scheme was misattributed to **Blaise de Vigenère** (_1523–1596_), and so acquired its present name.

## How to build the CLI

> You need at least `JDK 11` ([OpenJDK](https://openjdk.java.net/) is recommended).
> For Windows users, you need to set the `JAVA_HOME` environment variable.

Clone or download the _vigenere-cipher_ project.

Package it with `maven`:
```powershell
PS C:\GIT\vigenere-cipher> mvn clean package -U
```

[Picocli](https://github.com/remkop/picocli) is used to provide the CLI. Thanks to the `maven-shade-plugin`, this external dependency is included in the packaged JAR.

You can directly run the compiled JAR with the `java -jar` command.

Example :
```powershell
PS C:\GIT\vigenere-cipher> java -jar .\target\vigenere-cipher-2.0.0.jar encrypt --key=MyPrivateKey "Welcome to the Vigenère cipher !"
Icatwhe ms dlc Hgvvvème vmzlcd !
```

### GraalVM Native Image Toolchain

**GraalVM** provides a great tool : "**Native Image**" which allows us to generate an executable for our CLI. A maven plugin (`native-image-maven-plugin`) exists but it's not really convenient on Windows.

This InfoQ article [Build Great Native CLI Apps in Java with Graalvm and Picocli](https://www.infoq.com/articles/java-native-cli-graalvm-picocli/) provides details on setting up the GraalVM toolchain for creating native images. It pays special attention to Windows, where setting up the compiler toolchain can be tricky.

#### Installation on Windows Platforms

1. Navigate to the [GraalVM Releases repository on GitHub](https://github.com/graalvm/graalvm-ce-builds/releases). Select **graalvm-ce-java11-windows-amd64-20.0.0.zip** and download.
2. Unzip the archive to your file system.
3. Configure `PATH` environment variable. Setting environment variables via the command line will work the same way for Windows 7, 8 and 10.
    * Add the GraalVM **bin** folder to the `PATH` environment variable:
      ```powershell
      setx /M PATH "C:\Progra~1\Java\graalvm-ce-java11-20.0.0\bin;%PATH%"
      ```
      Note that the `/M` flag, equivalent to `-m`, requires elevated user privileges.
    * Restart Command Prompt to reload the environment variables. Then use the following command to check whether the variable was set correctly:
      ```powershell
      echo %PATH%
      ```
4. To build native images using the Java 11 version of GraalVM (19.3.0 and greater), you can install the Visual C Build Tools Workload for Visual Studio 2017 Build Tools using [chocolatey](https://chocolatey.org/docs/installation):
    ```powershell
    choco install visualstudio2017-workload-vctools
    ```
    After installation, set up the environment from the cmd prompt with this command:
    ```bat
    call "C:\Program Files (x86)\Microsoft Visual Studio\2017\BuildTools\VC\Auxiliary\Build\vcvars64.bat"
    ```
    Then run `native-image` in that Command Prompt window.

#### Creating a Native Image

```powershell
native-image --static -jar .\target\vigenere-cipher-2.0.0.jar vigenere
```

Add `vigenere.exe` parent folder to `PATH` environment variable. That's all ! You can now use **Vigenère cipher CLI** everywhere you want on your computer !

#### Running Native Images on different Windows

The native binary we just created works fine on the machine where we just built it, but when you run it on a different Windows machine, you may have an error which reports that the `VCRUNTIME140.dll` is missing.

This dll (from [Microsoft Visual C++ 2015 Redistributable Update 3 RC](https://www.microsoft.com/en-us/download/details.aspx?id=52685)) can be placed in the same directory as the exe, or in `C:\Windows\System32`.

## CLI Usage

### Encrypt

#### With default alphabet
```powershell
PS C:\GIT\vigenere-cipher> vigenere encrypt --key=MyPrivateKey "Welcome to the Vigenère cipher !"
Icatwhe ms dlc Hgvvvème vmzlcd !
```

#### With custom alphabet
```powershell
PS C:\GIT\vigenere-cipher> vigenere encrypt "Hello !" --key=key --alphabet=ehkloy
Lekyo !
```

### Decrypt

#### With default alphabet
```powershell
PS C:\GIT\vigenere-cipher> vigenere decrypt --key=MyPrivateKey "Icatwhe ms dlc Hgvvvème vmzlcd !"
Welcome to the Vigenère cipher !
```

#### With custom alphabet
```powershell
PS C:\GIT\vigenere-cipher> vigenere decrypt "Lekyo !" --key=key --alphabet=ehkloy
Hello !
```

### Version
```powershell
PS C:\GIT\vigenere-cipher> vigenere --version
2.0.0
```

### Using Aliases

#### Command Options
* `-h` is the `--help` option alias.
* `-V` is the `--version` option alias.

#### Subcommands
* `ec` is the `encrypt` subcommand alias.
* `dc` is the `decrypt` subcommand alias.

#### Subcommand Options
* `-k` is the `--key` subcommand option alias.
* `-a` is the `--alphabet` subcommand option alias.
