/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 *    http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package cz.rkr.template.tts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.ivona.services.tts.IvonaSpeechCloudClient;
import com.ivona.services.tts.model.CreateSpeechRequest;
import com.ivona.services.tts.model.CreateSpeechResult;
import com.ivona.services.tts.model.Input;
import com.ivona.services.tts.model.Voice;

/**
 * Class that generates sample synthesis and retrieves audio stream.
 */
public class SampleIvonaSpeechCloudCreateSpeech {

	private static IvonaSpeechCloudClient speechCloud;

	private static void init() {
		speechCloud = new IvonaSpeechCloudClient(new ClasspathPropertiesFileCredentialsProvider("IvonaCredentials2.properties"));
		speechCloud.setEndpoint("https://tts.eu-west-1.ivonacloud.com");
	}

	public static void main(String[] args) throws Exception {

		init();

		String[][] names = {
				// male dite
				{ "Ivy", "Hi, i am house control computing machine at your service."
						+ "\nplease, ..... i     am     autonomous unit for controlling this house" },
				// nevim
				{ "Salli", "computing results. opening channel" },
				// nevim
				{ "Jennifer", "computing results. opening channel" },
				// takova televizni
				{ "Kendra", "changing program to station NOVA. opening channel 2" },
				// takova dobra na matiku
				{ "Kimberly", "Hi, i am house control computing machine at your service. please, give me an order" } };

		for (int i = 0; i < names.length; i++) {
			String name = names[i][0];
			String outputFileName = "/tmp/speech_" + name + ".mp3";
			CreateSpeechRequest createSpeechRequest = new CreateSpeechRequest();
			Input input = new Input();
			Voice voice = new Voice();

			voice.setName(name);
			// input.setData("This is a sample text to be synthesized.");
			String data = "confirmed. acknowledged. computing results." + " opening channel PRIMA";
			data = names[i][1];
			input.setData(data);

			createSpeechRequest.setInput(input);
			createSpeechRequest.setVoice(voice);

			callService(createSpeechRequest, outputFileName);
		}
	}

	private static void callService(CreateSpeechRequest createSpeechRequest, String outputFileName) throws Exception {
		InputStream in = null;
		FileOutputStream outputStream = null;

		try {

			CreateSpeechResult createSpeechResult = speechCloud.createSpeech(createSpeechRequest);

			System.out.println("\nSuccess sending request:");
			System.out.println(" content type:\t" + createSpeechResult.getContentType());
			System.out.println(" request id:\t" + createSpeechResult.getTtsRequestId());
			System.out.println(" request chars:\t" + createSpeechResult.getTtsRequestCharacters());
			System.out.println(" request units:\t" + createSpeechResult.getTtsRequestUnits());

			System.out.println("\nStarting to retrieve audio stream:");

			in = createSpeechResult.getBody();
			outputStream = new FileOutputStream(new File(outputFileName));

			byte[] buffer = new byte[2 * 1024];
			int readBytes;

			while ((readBytes = in.read(buffer)) > 0) {
				// In the example we are only printing the bytes counter,
				// In real-life scenario we would operate on the buffer
				System.out.println(" received bytes: " + readBytes);
				outputStream.write(buffer, 0, readBytes);
			}

			System.out.println("\nFile saved: " + outputFileName);

		} finally {
			if (in != null) {
				in.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}

	}

}
