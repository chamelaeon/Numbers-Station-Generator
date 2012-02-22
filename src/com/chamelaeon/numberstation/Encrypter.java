package com.chamelaeon.numberstation;

import java.util.List;

/**
 * Interface that describes an object which takes in plaintext and converts it to encrypted 
 * bytes. It is agnostic about the plaintext it receives.
 *  
 * 
 * @author Chamelaeon
 */
public interface Encrypter {

	/**
	 * Encrypts a single string.
	 * 
	 * @param plaintext The plaintext. Must not be null or empty.
	 * @return the encrypted string.
	 */
	public byte[] encrypt(String plaintext);
	
	/**
	 * Encrypts multiple strings into the same byte array.
	 * 
	 * @param plaintext The plaintext list. Must not be null. 
	 * Null or empty values in the list are ignored. 
	 * @return the encrypted strings.
	 */
	public List<byte[]> encrypt(List<String> plaintext);
}
