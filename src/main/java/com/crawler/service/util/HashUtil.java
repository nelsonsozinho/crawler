package com.crawler.service.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

	public static String makeSha(String value) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.update(value.getBytes());
		byte[] digestBytes = digest.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digestBytes.length; i++) {
			sb.append(Integer.toHexString(0xFF & digestBytes[i]));
		}

		return sb.toString();
	}

}
