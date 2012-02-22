package com.chamelaeon.numberstation;


/**
 * Doesn't actually encrypt, but instead just converts characters into bytes.
 * 
 * @author Chamelaeon
 */
public class NullEncrypter extends AbstractEncrypter {

	/** {@inheritDoc} */
	@Override
	public byte[] encrypt(String plaintext) {
		if (null == plaintext || plaintext.trim().equals("")) {
			throw new IllegalArgumentException("Plaintext must not be null or empty!");
		}
		
		return getBytes(plaintext);
	}

}
