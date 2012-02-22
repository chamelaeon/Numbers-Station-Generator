package com.chamelaeon.numberstation;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of Encrypter to handle common method implementations. 
 * @author Chamelaeon
 */
public abstract class AbstractEncrypter implements Encrypter {

	/** {@inheritDoc} */
	@Override
	public List<byte[]> encrypt(List<String> plaintext) {
		if (null == plaintext) {
			throw new IllegalArgumentException("Plaintext must not be null!");
		}
		
		List<byte[]> retBytes = new ArrayList<byte[]>();
		for (String string : plaintext) {
			if (null != string && 0 != string.length()) {
				retBytes.add(encrypt(string));
			}
		}
		return retBytes;
	}
	
	/**
	 * Gets the bytes from a string using UTF-8.
	 * @param string The string to get bytes from.
	 * @return the bytes.
	 * @throws RuntimeException if UTF-8 is not supported.
	 */
	protected byte[] getBytes(String string) {
		try {
			return string.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("System does not support UTF-8, what the hell.");
		}
	}
}