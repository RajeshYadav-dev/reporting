package com.reportanalytics.utilities;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecryptionAES {

	public static String encrypt(String plainText, String secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(secretKey));
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}

	public static String decrypt(String encryptedText, String secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, getSecretKey(secretKey));
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}

	private static SecretKey getSecretKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] keyBytes = key.getBytes();
		SecretKey originalKey = new SecretKeySpec(keyBytes, "AES");
		return originalKey;
	}

	public static String convertToBase64(String input) {
		return Base64.getEncoder().encodeToString(input.getBytes());
	}

	public static boolean isBase64(String value) {
		return Pattern.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$", value.trim());
	}
}
