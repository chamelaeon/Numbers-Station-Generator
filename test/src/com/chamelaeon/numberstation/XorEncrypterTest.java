package com.chamelaeon.numberstation;

import junit.framework.TestCase;

public class XorEncrypterTest extends TestCase {

	static byte[] expectedSingleMaskResults = new byte[] {'`', 'c', 'b', 'e', 'd', 'g', 'f', 'i', 'h', 'k', 'j', 'm', 'l',
		'o', 'n', 'q', 'p', 's', 'r', 'u', 't', 'w', 'v', 'y', 'x', '{'};
	
	public void testEncryptStringWithSingleByteInputAndMask() {
		char testChar = 'a';
		String mask = createStringFromBytes((byte) 0x01);
		XorEncrypter encrypter = new XorEncrypter(mask);
		
		for (byte expected : expectedSingleMaskResults) {
			String input = createStringFromBytes((byte) testChar++);
			byte actual = encrypter.encrypt(input)[0];
			
			assertEquals(expected, actual);
		}
	}
	
	public void testEncryptStringWithMaskSameLength() {
		String input = "I am an input string";
		String mask =  "MASKMASKMASKMASKMASK";
		XorEncrypter encrypter = new XorEncrypter(mask);
		
		byte[] actual = encrypter.encrypt(input);
		byte[] expected = new byte[] {4, 97, 50, 38, 109, 32, 61, 107, 36, 47, 35, 62, 57, 97, 32, 63, 63, 40, 61, 44};
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < actual.length; i++) {
			byte expectedByte = expected[i];
			byte actualByte = actual[i];
			assertEquals(expectedByte, actualByte);
		}
	}
	
	public void testEncryptStringWithSmallerMask() {
		String input = "I am an input string";
		String mask =  "MASK";
		XorEncrypter encrypter = new XorEncrypter(mask);
		
		byte[] actual = encrypter.encrypt(input);
		byte[] expected = new byte[] {4, 97, 50, 38, 109, 32, 61, 107, 36, 47, 35, 62, 57, 97, 32, 63, 63, 40, 61, 44};
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < actual.length; i++) {
			byte expectedByte = expected[i];
			byte actualByte = actual[i];
			assertEquals(expectedByte, actualByte);
		}
	}
	
	public void testValidateXorCorrectness() {
		String input = "I am an input string";
		String mask =  "I am an input string";
		XorEncrypter encrypter = new XorEncrypter(mask);
		
		byte[] actual = encrypter.encrypt(input);
		byte[] expected = new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < actual.length; i++) {
			byte expectedByte = expected[i];
			byte actualByte = actual[i];
			assertEquals(expectedByte, actualByte);
		}
	}
	
	private String createStringFromBytes(byte... bytes) {
		return new String(bytes);
	}
}
