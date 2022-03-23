# javarush-cryptoanalyser
# javarush-project-1
## Author: Danir Khutsaev
### Description:

This program made in Java and applied to encrypt and decrypt files with Caesar Cipher.

You(user) can encrypt file with key.
User can decrypt file with key(how many steps you have to take using the Caesar cipher logic) if you know it.
You can use "Brute force" option to hack cipher.
The program has an option - "Static analysis". All letters in file(of same author for ex.) will be counted, and you can see the most usable letter in file.
You should check encrypted file and see the most usable letter there. The program will show you what key you should use to decrypt file!

This project contains classes:
1.Main - this class calls Dialog class
2.Dialog - dialog with user and shows all options of the program. Contains method "start()";
3.CryptoLogic - class to encrypt file by using method "encryption()". 
Contains method "addProhibitedFiles()". This method put all system files to List and if user will try to use some file of them - it will be prohibited.
4.HackingMessage - class to decrypt file. Extends CryptoLogic class. Contains method "unblock()";
5.BruteForce - decrypt file with "Brute force" method. User should put all possible keys to decrypt file(he should choose if it's correct or no).
Extends HackingMessage class. Contains extended method "unblock()" and method "ItIsOK" to ask user if he decrypted file or no.
6.StaticAnalysis - count all letters in file and show the key to hack file. Contains methods "checkEncryptedText()" and "checkSimilarText()"(text of same Author).

How to use program:
### When user run the program then can see menu:
Hello dear user!
Choose an option of the program:
1: Encrypt file.
2: Decrypt file with
3: Decrypt file with brute - force.
4: Decrypt file with static analysis.
For cancel - use command: "CANCEL"
### User should choose an option of the program and click "Enter"
### 1: Encrypt file
1.Choose original file and click "Enter"
2.Choose destination file and click "Enter"
3.Write a key
Then we have our encrypted file
### 2: Decrypt file with key
1.Choose the way to encrypted file and click "Enter"
2.Put the way where do you want to save decrypted file and click "Enter"
3.Write a key and click "Enter"
Then we have decrypted file
### 3: Decrypt file with brute - force
1.Put encrypted file and click "Enter"
2.Write destination file to put decrypted message and click "Enter"
3.Key + "Enter"
4.You will see "This file seems decrypted?" - write "yes" if it was decrypted or "no" and press "Enter"
Check your file and see if you decrypted file or no
### 4: Decrypt file with static analysis
1.You will see "Lets check a different book of the same author and discover the most usable letters.
Just put the way to file:" - Just choose a file and click "Enter"
2.Then you will see the most usable letters in the text
3.After you have to do same step with encrypted file
4.Program will show you what KEY you should use to decrypt file
5.Write encrypted file and click "Enter"
6.Write destination file to put decrypted message and press "Enter"
7.Put key
8.You decrypted file!
