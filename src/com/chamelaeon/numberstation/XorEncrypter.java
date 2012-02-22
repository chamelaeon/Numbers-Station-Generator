package com.chamelaeon.numberstation;


public class XorEncrypter extends AbstractEncrypter {

	/** The mask to apply to the plaintext. */
	private final byte[] mask;
	
	/**
	 * Constructor. 
	 * @param mask The mask to apply to plaintexts. Must not be null or empty.
	 */
	public XorEncrypter(String mask) {
		if (null == mask || mask.length() == 0) {
			throw new IllegalArgumentException("Mask must not be null or empty!");
		}
		this.mask = getBytes(mask);
	}
	
	@Override
	public byte[] encrypt(String plaintext) {
		if (null == plaintext || plaintext.trim().equals("")) {
			throw new IllegalArgumentException("Plaintext must not be null or empty!");
		}
		byte[] bytes = getBytes(plaintext);
		
		int maskIndex = 0;
		for (int iii = 0; iii < bytes.length; iii++) {
			byte b = bytes[iii];
			if (maskIndex == mask.length) {
				maskIndex = 0;
			}
			byte m = mask[maskIndex++];
			
			b = (byte) (b ^ m);
			bytes[iii] = b;
		}
		return bytes;
	}
}
