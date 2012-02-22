package com.chamelaeon.numberstation;

import java.util.List;

/**
 * Interface which describes a way to convert from bytes, ints, or chars, to strings the
 * Synthesizer interface can speak. The primary reason for this to be an interface is to be able to
 * convert to speakable strings in a foreign language, if desired (e.g. "eins, zwei, drei...").
 * <p><
 * 
 * 
 * 
 * @author Chamelaeon
 */
public interface SpeakableTextConverter {
	
	/**
	 * Converts a byte to a speakable text string.
	 * Bytes are converted into octal, in the range [0-377], where {@link Byte#MIN_VALUE} 
	 * corresponds to "000" and {@link Byte#MAX_VALUE} corresponds to "255". They are parsed 
	 * so that the Synthesizer will pronounce each digit - "three five seven", not 
	 * "three hundred fifty seven". Byte conversions ALWAYS have three digits.
	 * 
	 * @param input The byte to convert.
	 * @return the speakable text string.
	 */
	public String byteToText(byte input);
	
	/**
	 * Converts an int to a speakable text string.
	 * <ul>
	 * <li>Ints in the range [0-9] are converted directly.</li>
	 * <li>Ints in the range (9-{@link Integer#MAX_VALUE}] are treated as though they are an 
	 * array of ints from [0-9], e.g. 123456 is treated as {1, 2, 3, 4, 5, 6}, and are then converted.</li> 
	 * <li>Ints in the range [{@link Integer#MIN_VALUE}-0) are unacceptable input.</li>
	 * </ul>
	 * Examples:
	 * <ul>
	 * <li>0 returns "0".</li>
	 * <li>9 returns "9".</li>
	 * <li>10 returns "1 0"</li>
	 * <li>123456 returns "1 2 3 4 5 6"</li>
	 * </ul>
	 * @param input The int to convert.
	 * @return the speakable text string.
	 */
	public String intToText(int input);
	
	/**
	 * Converts a char to a speakable text string.
	 * The char must be in the standard 52 English alphabet characters (uppercase and 
	 * lowercase). Space is allowed, and is left unchanged. 
	 * This method uses the phonetic alphabet equivalents for chars.
	 *  
	 * @param input The char to convert.
	 * @return the speakable text string.
	 */
	public String charToText(char input);
	
	/**
	 * Converts all given bytes to speakable text strings.
	 * Bytes are converted into octal, in the range [0-377], where {@link Byte#MIN_VALUE} 
	 * corresponds to "000" and {@link Byte#MAX_VALUE} corresponds to "255". They are parsed 
	 * so that the Synthesizer will pronounce each digit - "three five seven", not 
	 * "three hundred fifty seven". Byte conversions ALWAYS have three digits.
	 * 
	 * @param input The bytes to convert.
	 * @return the speakable text strings.
	 */
	public List<String> byteToText(byte... input);
	
	/**
	 * Converts all given ints to speakable text strings.
	 * <ul>
	 * <li>Ints in the range [0-9] are converted directly.</li>
	 * <li>Ints in the range (9-{@link Integer#MAX_VALUE}] are treated as though they are an 
	 * array of ints from [0-9], e.g. 123456 is treated as {1, 2, 3, 4, 5, 6}, and are then converted.</li> 
	 * <li>Ints in the range [{@link Integer#MIN_VALUE}-0) are unacceptable input.</li>
	 * </ul>
	 * Examples:
	 * <ul>
	 * <li>0 returns "0".</li>
	 * <li>9 returns "9".</li>
	 * <li>10 returns "1 0"</li>
	 * <li>123456 returns "1 2 3 4 5 6"</li>
	 * </ul>
	 * @param input The ints to convert.
	 * @return the speakable text strings.
	 */
	public List<String> intToText(int... input);
	
	/**
	 * Converts all given chars to a speakable string.s
	 * The char must be in the standard 52 English alphabet characters (uppercase and 
	 * lowercase). Space is allowed, and is left unchanged. 
	 * This method uses the phonetic alphabet equivalents for chars.
	 *  
	 * @param input The chars to convert.
	 * @return the speakable text strings.
	 */
	public List<String> charToText(char... input);
}
