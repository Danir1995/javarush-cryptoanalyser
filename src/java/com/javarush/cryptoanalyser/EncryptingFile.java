package com.javarush.cryptoanalyser;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;
import java.nio.channels.FileChannel;

public class EncryptingFile {

   public static List<String> prohibitedFiles = new ArrayList<>();
   public static final char[] ALPHABET = {
            'a','b','c','d','e','f','g','h','i','j','k','l',
            'm','n', 'o','p','q','r','s','t','u','v','w','x','y',
            'z','.', ',', '"', '\'', ':', '!', '?', ' '
             };
   public static HashMap<Character, Character> mapping = new HashMap<>();
   public HashMap<Character, Character> getMapping(){
        return mapping;
   }

   public void addProhibitedFiles(){
        prohibitedFiles.add(".bash_history");
        prohibitedFiles.add(".bash_logout");
        prohibitedFiles.add(".bash_profile");
        prohibitedFiles.add(".bashrc");
        prohibitedFiles.add(".gtkrc");
        prohibitedFiles.add(".login");
        prohibitedFiles.add(".logout");
        prohibitedFiles.add(".profile");
        prohibitedFiles.add(".viminfo");
        prohibitedFiles.add(".wm_style");
        prohibitedFiles.add(".Xdefaults & .Xresources");
        prohibitedFiles.add(".xinitrc");
        prohibitedFiles.add("/boot/vmlinuz");
        prohibitedFiles.add("/dev/fd0");
        prohibitedFiles.add("/dev/fd0H1440");
        prohibitedFiles.add("/dev/hda");
        prohibitedFiles.add("/dev/hdc");
        prohibitedFiles.add("/dev/null");
        prohibitedFiles.add("/etc/aliases");
        prohibitedFiles.add("/etc/bashrc");
        prohibitedFiles.add("/etc/crontab");
        prohibitedFiles.add("/etc/exports");
        prohibitedFiles.add("/etc/fstab");
        prohibitedFiles.add("/etc/group");
        prohibitedFiles.add("/etc/grub.conf");
        prohibitedFiles.add("/etc/hosts");
        prohibitedFiles.add("/etc/hosts.allow");
        prohibitedFiles.add("/etc/hosts.deny");
        prohibitedFiles.add("/etc/inittab");
        prohibitedFiles.add("/etc/issue");
        prohibitedFiles.add("/etc/lilo.conf");
        prohibitedFiles.add("/etc/modules.conf");
        prohibitedFiles.add("/etc/motd");
        prohibitedFiles.add("/etc/mtab");
        prohibitedFiles.add("/etc/passwd");
        prohibitedFiles.add("/etc/printcap");
        prohibitedFiles.add("/etc/profile");
        prohibitedFiles.add("/etc/resolv.conf");
        prohibitedFiles.add("/etc/securetty");
        prohibitedFiles.add("/etc/termcap");
        prohibitedFiles.add("/proc/cpuinfo");
        prohibitedFiles.add("/proc/filesystems");
        prohibitedFiles.add("/proc/interrupts");
        prohibitedFiles.add("/proc/ioports");
        prohibitedFiles.add("/proc/meminfo");
        prohibitedFiles.add("/proc/modules");
        prohibitedFiles.add("/proc/mounts");
        prohibitedFiles.add("/proc/stat");
        prohibitedFiles.add("/proc/swaps");
        prohibitedFiles.add("/proc/version");
        prohibitedFiles.add("/var/log/lastlog");
        prohibitedFiles.add("/var/log/messages");
        prohibitedFiles.add("/var/log/wtmp");
        prohibitedFiles.add("/bin");
        prohibitedFiles.add("/boot");
        prohibitedFiles.add("/dev");
        prohibitedFiles.add("/etc");
        prohibitedFiles.add("/etc/init.d");
        prohibitedFiles.add("/etc/profile.d");
        prohibitedFiles.add("/etc/rc.d");
        prohibitedFiles.add("/etc/rc.d/init.d");
        prohibitedFiles.add("/etc/rc.d/rc?.d");
        prohibitedFiles.add("/etc/skel");
        prohibitedFiles.add("/etc/X11");
        prohibitedFiles.add("/home");
        prohibitedFiles.add("/lib");
        prohibitedFiles.add("/mnt");
        prohibitedFiles.add("/proc");
        prohibitedFiles.add("/root");
        prohibitedFiles.add("/sbin");
        prohibitedFiles.add("/tmp");
        prohibitedFiles.add("/usr");
        prohibitedFiles.add("/usr/bin");
        prohibitedFiles.add("/usr/bin/X11");
        prohibitedFiles.add("/usr/include");
        prohibitedFiles.add("/usr/share");
        prohibitedFiles.add("/usr/lib");
        prohibitedFiles.add("/usr/local/bin");
        prohibitedFiles.add("/usr/sbin");
        prohibitedFiles.add("/var");
    }

    public void encryptFile(Scanner scanner) throws IOException, InvalidPathException {
        addProhibitedFiles();
        scanner = ScannerSingleton.getInstance();
        System.out.println("Write original file: ");
        String originalFile = scanner.nextLine();
        System.out.println("Write destination file: ");
        String encryptedFile = scanner.nextLine();
        System.out.println("Key: ");
        int key = scanner.nextInt();

        Path pathOfOriginalFile = Path.of(originalFile);
        Path pathOfEncryptedFile = Path.of(encryptedFile);

        for (String prohibited:prohibitedFiles){
            if (originalFile.contains(prohibited) || encryptedFile.contains(prohibited)) {
                throw new RuntimeException("You want to change system file");
            }
        }

        if (key < 0) {
            System.out.println("Cipher can not spin to a negative value");
            return;
        }

        if (!Files.isRegularFile(pathOfEncryptedFile)){
            Files.createFile(pathOfEncryptedFile);
        }

        for (int i = 0; i < ALPHABET.length; i++) {
            mapping.put(ALPHABET[i], ALPHABET[((ALPHABET.length) + i + key) % (ALPHABET.length)]);
        }

       if (Files.isRegularFile(pathOfOriginalFile)) {
           char[] data = Files.readString(pathOfOriginalFile).toLowerCase().toCharArray();
           for (int i = 0; i < data.length; i++) {
               if(mapping.containsKey(data[i])) {
                   data[i] = mapping.get(data[i]);
               }
           }
           Files.writeString(pathOfEncryptedFile, String.valueOf(data));
       } else {
           System.out.println("File name is incorrect or file doesn't exist");
       }
    }
}
