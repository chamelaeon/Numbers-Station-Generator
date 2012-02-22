package com.chamelaeon.numberstation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;

import com.sun.speech.freetts.audio.AudioPlayer;

/**
 * @author Chamelaeon
 *
 */
public class ByteAudioPlayer implements AudioPlayer {

	private AudioFormat audioFormat;
	private byte[] outputData;
	private int totBytes = 0;
    private final List<byte[]> outputList;
    private int curIndex = 0;
    
	public ByteAudioPlayer() {
		outputList = new ArrayList<byte[]>();	
	}
	
	@Override
	public void cancel() {
		// Do nothing.
	}
	
	@Override
	public void pause() {
		// Do nothing.
	}

	@Override
	public void reset() {
		// Do nothing.
	}
	
	@Override
	public void resume() {
		// Do nothing.
	}
	
	
	@Override
	public void startFirstSampleTimer() {
		// Do nothing.
	}
	
	@Override
	public void resetTime() {
		// Do nothing.
	}
	
	@Override
	public void showMetrics() {
		// Do nothing.
	}
	
	@Override
	public void setVolume(float volume) {
		// Do nothing.
	}
	
	@Override
	public boolean drain() {
		// Do nothing
		return true;
	}
	
	@Override
	public float getVolume() {
		return 1.0f;
	}
	
	@Override
	public long getTime() {
		return -1L;
	}
	
	@Override
	public void setAudioFormat(AudioFormat format) {
		this.audioFormat = format;
	}

	@Override
	public AudioFormat getAudioFormat() {
		return audioFormat;
	}

	@Override
	public void begin(int size) {
		outputData = new byte[size];
		curIndex = 0;
	}
	
	@Override
	public boolean end() {
		outputList.add(outputData);
		totBytes += outputData.length;
		return true;
	}
	
	public byte[] getBytes() throws IOException {
		byte[] retVal = new byte[totBytes];
		int copyIdx = 0;
		for (byte[] bytes : outputList) {
			System.arraycopy(bytes, 0, retVal, copyIdx, bytes.length);
			copyIdx += bytes.length;
		}
		
		return retVal;
	}
	
	@Override
	public void close() {
        // Do nothing.
    }

	@Override
	public boolean write(byte[] audioData) {
		return write(audioData, 0, audioData.length);
	}

	@Override
	public boolean write(byte[] audioData, int offset, int size) {
		System.arraycopy(audioData, offset, outputData, curIndex, size);
		curIndex += size;
		return true;
	}
}
