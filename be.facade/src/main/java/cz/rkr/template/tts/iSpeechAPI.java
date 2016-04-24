package cz.rkr.template.tts;
import com.iSpeech.InvalidApiKeyException;

public class iSpeechAPI {

	public static String api = "developerdemokeydeveloperdemokey";
	// "a6634a7fad484c8729d7aa4817aa9e81";// web evaluation key
	// "0c9d0c182fa43cedfded7a961e5212bf";// Mobile Production Key:
	// "9f1219fb83a9692a0e045ea026da1eb0";// muj test key
	public static boolean production = true; // Your API key server access, false is development and true is production

	public static void main(String[] args) throws InvalidApiKeyException, Exception {
		long time = System.currentTimeMillis();

		iSpeechTTS iSpeechTTS = new iSpeechTTS(api, production);
		// downloads text to file tts.wav

		// iSpeechASR iSpeechASR = new iSpeechASR();
		// iSpeechASR.runFile(api, production);
		// tries to recognize speech from tts.wav

		System.out.println("Transaction took: " + (System.currentTimeMillis() - time) + " milliseconds");
	}

}
