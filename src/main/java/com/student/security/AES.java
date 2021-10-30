package com.student.security;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class AES {

	public String decrypt(String token) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] decryptedBytes = null;
		final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		final PBEKeySpec pbeKeySpec = new PBEKeySpec("VISHAL".toCharArray(),
				new byte[] { 0x49, 0x76, 0x61, 0x6e, 0x20, 0x4d, 0x65, 0x64, 0x76, 0x65, 0x64, 0x65, 0x76 }, 1000,
				384);
		final Key secretKey = factory.generateSecret(pbeKeySpec);
		final byte[] key = new byte[32];
		final byte[] iv = new byte[16];
		System.arraycopy(secretKey.getEncoded(), 0, key, 0, 32);
		System.arraycopy(secretKey.getEncoded(), 32, iv, 0, 16);
		final SecretKeySpec secret = new SecretKeySpec(key, "AES");
		final AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, ivSpec);
		decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(token));
		
		
		return new String(decryptedBytes);
	}

	public String encrypt(String clearText) throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		byte[] bytes = clearText.getBytes(StandardCharsets.UTF_8);
		byte[] uncleanedResult = null;
		String result = null;

		final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		final PBEKeySpec pbeKeySpec = new PBEKeySpec("VISHAL".toCharArray(),
				new byte[] { 0x49, 0x76, 0x61, 0x6e, 0x20, 0x4d, 0x65, 0x64, 0x76, 0x65, 0x64, 0x65, 0x76 }, 1000, 384);
		final Key secretKey = factory.generateSecret(pbeKeySpec);
		final byte[] key = new byte[32];
		final byte[] iv = new byte[16];
		System.arraycopy(secretKey.getEncoded(), 0, key, 0, 32);
		System.arraycopy(secretKey.getEncoded(), 32, iv, 0, 16);

		final SecretKeySpec secret = new SecretKeySpec(key, "AES");
		final AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret, ivSpec);
		uncleanedResult = cipher.doFinal(bytes);
		result = Base64.getEncoder().encodeToString(uncleanedResult);
		return result;
	}

}
