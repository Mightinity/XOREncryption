package modul3.tugas2;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Kelas untuk enkripsi dan dekripsi teks menggunakan metode XOR
 */

public class XOREncryption {
    /**
     * Fungsi untuk meng-encrypt teks dengan rumus panjang dari string itu sendiri dan metode XOR.
     *
     * @param realString Teks yang akan di-encrypt.
     * @param keyEncryption Key untuk encrypt.
     * @return String ter-encrypt.
     */
    private static String encrypt(String realString, String keyEncryption) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < realString.length(); i++) {
            char textChar = realString.charAt(i);
            char keyChar = keyEncryption.charAt(i % keyEncryption.length());

            char encryptedChar = (char) (textChar ^ keyChar);
            encryptedText.append(encryptedChar);
        }

        return encryptedText.toString();
    }

    /**
     * Fungsi untuk men-decrypt teks yang ter-encrypt dengan rumus yang sama pada encryption.
     *
     * @param encryptedText String ter-encrypt.
     * @param keyDecryption Key yang digunakan untuk decryption.
     * @return String asli yang sudah didecrypt.
     */
    private static String decrypt(String encryptedText, String keyDecryption) {
        return encrypt(encryptedText, keyDecryption);
    }

    /**
     *
     * @param encryptedText teks untuk di tulis didalam file yang ingin disimpan
     */
    private static void saveToFile(String encryptedText){
        int i = 1;
        File file = new File("encrypted-text-" + i + ".txt");
        while (file.exists()){
            i++;
            file = new File("encrypted-text-" + i + ".txt");
        }
        try{
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(encryptedText);
            fileWriter.close();
            System.out.println("File saved!");
        } catch (IOException err) {
            System.out.println("Caught Error: " + err);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Select option 1 or 2:\n1. Encrypt text\n2. Decrypt text\n\nChoose: ");
        int optionInput = input.nextInt();
        input.nextLine();

        switch (optionInput) {
            case 1:
                System.out.print("Masukkan plaintext: ");
                String plainText = input.nextLine();

                System.out.print("Masukkan key: ");
                String keyEncrypt = input.nextLine();

                String encryptedText = encrypt(plainText, keyEncrypt);

                System.out.print("Select option 1 or 2:\n1. Print text and save to file\n2. Print text without save to file\n\nChoose: ");
                int optionSavetoFile = input.nextInt();

                if (optionSavetoFile == 1){
                    System.out.println("Text Encrypted: " + encryptedText);
                    saveToFile(encryptedText);
                } else if (optionSavetoFile == 2){
                    System.out.println("Text Encrypted: " + encryptedText);
                } else {
                    System.out.println("Something an error");
                }
                break;
            case 2:
                System.out.print("Masukkan decryptedtext: ");
                String encryptedTextForDecrypt = input.nextLine();

                System.out.print("Masukkan key: ");
                String keyForDecrypt = input.nextLine();

                String decryptedText = decrypt(encryptedTextForDecrypt, keyForDecrypt);

                System.out.println("Text Decrypted: " + decryptedText);
                break;
            default:
                System.out.println("Something an error");
                break;
        }
    }
}
