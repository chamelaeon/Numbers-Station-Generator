import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.AudioSystem;

import com.xuggle.xuggler.IAudioResampler;
import com.xuggle.xuggler.IAudioSamples;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IPacket;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.IAudioSamples.Format;
import com.xuggle.xuggler.IStreamCoder.Direction;


public class Haetz {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Intro rate: " + AudioSystem.getAudioFileFormat(new File("1906__nicStage__pianoLoop.wav")).getFormat().getSampleRate());
		System.out.println("Generated rate: " + AudioSystem.getAudioFileFormat(new File("rolled.wav")).getFormat().getSampleRate());

		
		List<byte[]> bytes = new ArrayList<byte[]>();
		IContainer container = IContainer.make();
		// Guess the format
		if (container.open(new FileInputStream(new File("rolled.wav")), null) < 0) {
			throw new RuntimeException("Xuggler can't open the file.");
		}
		
		
		IStream stream = container.getStream(0);
		IStreamCoder coder = stream.getStreamCoder();
		System.out.println("Intro rate: " + coder.getSampleRate());
		
		if (coder.open() < 0) {
			throw new RuntimeException("Can't open the stream coder.");
		}
		coder.setFlag(IStreamCoder.Flags.FLAG_QSCALE, false);
		IPacket packet = IPacket.make();
		while (container.readNextPacket(packet) >= 0) {
			IAudioSamples inSamples = IAudioSamples.make(1, coder.getChannels());
			IAudioSamples outSamples = IAudioSamples.make(1, coder.getChannels());
			int offset = 0;
			while (offset < packet.getSize()) {
				int bytesDecoded = coder.decodeAudio(inSamples, packet, offset);
				if (bytesDecoded < 1) {
					throw new RuntimeException("Got an error while reading");
				}
				offset += bytesDecoded;
				
				if (inSamples.isComplete()) {
					IAudioResampler resampler = IAudioResampler.make(1, inSamples.getChannels(), 44100, 16000);
					resampler.resample(outSamples, inSamples, inSamples.getNumSamples());
					bytes.add(outSamples.getData().getByteArray(0, outSamples.getSize()));
					//System.out.println(outSamples.getSampleRate());
					//System.out.println((int) IAudioSamples.findSampleBitDepth(outSamples.getFormat()));
					//System.out.println(outSamples.getChannels());
					
				}
			}
			
			byte[] appender = new byte[bytes.get(0).length * bytes.size()];
			offset = 0;
			for (byte[] bs : bytes) {
				System.arraycopy(bs, 0, appender, offset, bs.length);
				offset += bs.length;
			}
		}
		

		
	}

}
