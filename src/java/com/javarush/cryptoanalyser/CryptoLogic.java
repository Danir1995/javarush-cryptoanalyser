package com.javarush.cryptoanalyser;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.nio.channels.FileChannel;

public class CryptoLogic {

    List<String> prohibitedFiles = new ArrayList<>();

    protected static final char[] ALPHABET = {
            'a','b','c','d','e','f','g','h','i','j','k','l',
            'm','n', 'o','p','q','r','s','t','u','v','w','x','y',
            'z','.', ',', '"', '\'', ':', '!', '?', ' '
             };

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
    public void encryption() throws IOException, InvalidPathException {

        addProhibitedFiles();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write original file: ");
        String originalFile = scanner.nextLine();
        System.out.println("Write destination file: ");
        String encryptedFile = scanner.nextLine();
        System.out.println("Key: ");
        int key = scanner.nextInt();

        Path path1 = Path.of(originalFile);
        Path path2 = Path.of(encryptedFile);

        for (String prohibited:prohibitedFiles){
            if (originalFile.contains(prohibited) || encryptedFile.contains(prohibited)) {
               throw new RuntimeException("You want to change system file");
            }
        }
                if (key < 0) {
                    System.out.println("Can not be less than 0");
                    return;
                }

                    StringBuilder builder = new StringBuilder();

                    if (Files.isRegularFile(path1)) {

                        try(Writer writer = new BufferedWriter(new FileWriter(path2.toString()));
                            BufferedReader reader = new BufferedReader(new FileReader(path1.toString()))) {

                            if (!Files.isRegularFile(path2)){
                            Files.createFile(path2);
                        }
                            while (reader.ready()){
                                builder.append(reader.readLine());
                            }
                            int countOfSteps = key - ((key / (ALPHABET.length)) * (ALPHABET.length));

                            for (int i = 0; i < builder.length(); i++) {
                                for (int j = 0; j < ALPHABET.length; j++) {

                                    if (ALPHABET[(char) j] == builder.toString().toLowerCase(Locale.ROOT).charAt((char) i)) {

                                            if (j + countOfSteps > ALPHABET.length - 1) {
                                                writer.write(ALPHABET[countOfSteps - (ALPHABET.length - j)]);
                                            } else if (j + countOfSteps == ALPHABET.length) {
                                                writer.write(ALPHABET[(char) countOfSteps - 1]);
                                            } else {
                                                writer.write(ALPHABET[(char) (j + countOfSteps)]);
                                            }
                                        }
                                    }
                                }
                        } catch (IOException e) {
                            System.out.println("Some problem, show it to your manager" + e);
                        }
                    } else {
                        System.out.println("File name is incorrect or file doesn't exist");
                    }
                }
            }
