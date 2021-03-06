package cz.rkr.template.tts;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.iSpeech.*;

public class iSpeechTTS {

	public iSpeechTTS(String api, boolean production) {
		try {
			iSpeechSynthesis iSpeechTTS = iSpeechSynthesis.getInstance(api, production);
			String format = "wav"; // or mp3
			iSpeechTTS.setOptionalCommand("format", format);
			File file = new File("/tmp/tts." + format);

			FileOutputStream out = new FileOutputStream(file);

			// iSpeechTTS.setOptionalCommand("voice", "eurczechfemale");
			// TTSResult result = iSpeechTTS.speak("zdravím tě ? jak se máš !");

			iSpeechTTS.setOptionalCommand("voice", "usenglishfemale");
			TTSResult result = iSpeechTTS.speak("confirmed and acknowledged. opening channel 2");
			// iSpeechTTS.setOptionalCommand("voice", "usspanishfemale");
			// TTSResult result = iSpeechTTS.speak("hola, �c�mo est�s hoy");

			DataInputStream in = result.getDataInputStream();

			byte[] buffer = new byte[2048];
			int size = 0;
			while ((size = in.read(buffer)) > -1) {
				out.write(buffer, 0, size);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("file created.");
	}

}