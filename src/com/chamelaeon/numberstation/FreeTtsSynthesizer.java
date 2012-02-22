package com.chamelaeon.numberstation;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.AudioFileFormat;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

/**
 * Implementation which uses FreeTTS. 
 * @author Chamelaeon
 */
public class FreeTtsSynthesizer implements Synthesizer {

	/** The {@link VoiceManager} instance. */
	private VoiceManager manager;
	
	/** The actual voice to use. */
	private Voice voice;
	
	/** The name of the voice to use. */
	private final String voiceName;
	
	/** Whether we're started or not. */
	private final AtomicBoolean started;
	
	/** Constructor. */
	public FreeTtsSynthesizer() {
		this("");
	}
	
	/**
	 * Constructor.
	 * @param voiceName The name of the voice to use.
	 */
	public FreeTtsSynthesizer(String voiceName) {
		if (null == voiceName || voiceName.trim().equals("")) {
			voiceName = "kevin16";
		}
		
		this.voiceName = voiceName;
		started = new AtomicBoolean(false);
	}
	
	@Override
	public synchronized byte[] synthesize(List<String> text) throws IOException {
		if (null == text || 0 == text.size()) {
			throw new IllegalArgumentException("Text cannot be null or empty!");
		}
		
		if (!started.get()) {
			return null;
		}
		
		if (started.get()) {
			ByteAudioPlayer player = new ByteAudioPlayer();
			//voice.setAudioPlayer(new SingleFileAudioPlayer("foo", AudioFileFormat.Type.WAVE));
			voice.setAudioPlayer(player);
			for (String sourceString : text) {						
				voice.speak(sourceString);
			}
			voice.getAudioPlayer().close();
			return player.getBytes();
		}
	
		return new byte[0];
	}

	/** {@inheritDoc} */
	@Override
	public synchronized void start() {
		// Perform FreeTTS initialization 
		manager = VoiceManager.getInstance();
//		Voice[] voices = manager.getVoices();
//		System.out.println("Available voices:");
//        for (int i = 0; i < voices.length; i++) {
//            System.out.println("    " + voices[i].getName()
//                               + " (" + voices[i].getDomain() + " domain)");
//        }
        
        voice = manager.getVoice(voiceName);
        if (voice == null) {
            throw new RuntimeException( "Cannot find a voice named " + voiceName + ".  Please specify a different voice.");
        }
        
        /* Allocates the resources for the voice. */
        voice.allocate();
        // Flag that we're started.
        started.compareAndSet(false, true);
	}

	/** {@inheritDoc} */
	@Override
	public synchronized void stop() {
		started.compareAndSet(true, false);
		voice.deallocate();
		manager = null;
	}
}
