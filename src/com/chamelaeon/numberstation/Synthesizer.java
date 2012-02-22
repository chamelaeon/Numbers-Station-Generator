package com.chamelaeon.numberstation;

import java.io.IOException;
import java.util.List;

/**
 * Interface which describes a way to synthesize text into speech, which works like a queue (or pretends it does). 
 * Includes lifecycle methods and output specification.
 * 
 * @author Chamelaeon
 */
public interface Synthesizer {

	/** Starts the synthesizer. */
	public void start();
	
	/** Stops the synthesizer. */
	public void stop(); 
	
	/**
	 * Synthesizes list of input text.
	 * @param text The text to synthesize. Each {@link String} will have a longer than usual pause between it and the next.
	 * @return the synthesized audio bytes.
	 */
	public byte[] synthesize(List<String> text) throws IOException;
}
