import com.chamelaeon.numberstation.BroadcastGenerator;
import com.chamelaeon.numberstation.Encrypter;
import com.chamelaeon.numberstation.FreeTtsSynthesizer;
import com.chamelaeon.numberstation.Synthesizer;
import com.chamelaeon.numberstation.XorEncrypter;
import com.chamelaeon.numberstation.BroadcastGenerator.Intro;
import com.chamelaeon.numberstation.BroadcastGenerator.Language;

public class TesterMain {


    public static void main(String[] args) throws Exception {
    	System.setProperty("mbrola.base", "C:\\Program Files\\Mbrola Tools");
    	
    	
    	Encrypter encrypter = new XorEncrypter("FNORD");
    	Synthesizer synth = new FreeTtsSynthesizer("mbrola_us2");
    	Intro intro = new BroadcastGenerator.WaveIntro(1, "1906__nicStage__pianoLoop.wav");
    	BroadcastGenerator generator = new BroadcastGenerator(encrypter, Language.ENGLISH, synth, intro);
    	
    	generator.generateBroadcast("Never gonna give you up, never gonna let you down...");
    	//generator.generateBroadcast("Never gonna give you up, never gonna let you down...", new Synthesizer.AudioOutput());
		generator.stop();
	}
}
