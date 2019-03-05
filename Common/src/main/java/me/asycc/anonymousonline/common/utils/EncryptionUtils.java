package me.asycc.anonymousonline.common.utils;

import javax.crypto.Cipher;
import java.security.*;

/**
 *
 * A class with utilities to help encrypt outgoing data
 * and encrypt incoming data.
 *
 * @author Asyc
 * @since 3/4/2019
 */
public class EncryptionUtils {

    /**
     *
     * Used to generate a {@link KeyPair} for RSA encryption of packets.
     *
     * @param size The size that the generated keys will be.
     * @return Returns a generated {@link KeyPair}.
     */
    public static KeyPair generateKeyPair(final int size) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(size);
        return generator.generateKeyPair();
    }

    /**
     * @param key The {@link PrivateKey} to decrypt the byte array.
     * @param bytes The byte array to decrypt.
     * @return Returns the decrypted version of the byte array specified in the method parameters.
     * @throws GeneralSecurityException Thrown if there is an issue decrypting the byte array.
     */
    public static byte[] decrypt(PrivateKey key, final byte[] bytes) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(bytes);
    }

    /**
     *
     * @param key The {@link PublicKey} to encrypt the byte array.
     * @param bytes The byte array to encrypt.
     * @return Returns the encrypted version of the byte array specified in the method parameters.
     * @throws GeneralSecurityException Thrown if there is an issue encrypting the byte array.
     */
    public static byte[] encrypt(final PublicKey key, final byte[] bytes) throws GeneralSecurityException{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(bytes);
    }
}
