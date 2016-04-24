package cz.rkr.test.acm;

import cz.rkr.test.acm.tools.StringReaderUtil;

public class LongestSequenceLength {

	public static void main(String[] args) throws Exception {
		new LongestSequenceLength().start();
	}

	public void start() throws Exception {
		// aaakkcccccczz
		// aaabbbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccccdddvvvfffggg
		String msg = StringReaderUtil.readLineFromStdIn("Zadej retezec:");

		int length = myRunLongestIndex(msg);
		System.out.format("MainLongestSequenceSameChars, [strLen=0-%d], length:%d\n%s", msg.length() - 1, length, msg);
	}

	public static int myRunLongestIndex(String setofletters) {
		int length = 1;
		int maxLength = length;
		int outputIndex = 0;
		int currentIndex = outputIndex;
		int strLen = setofletters.length();

		for (int i = 0; i < strLen - 1; i++) {
			if (i < strLen - 1 //
					&& setofletters.charAt(i) != setofletters.charAt(i + 1)) {
				if (maxLength < length) {
					maxLength = length;
					outputIndex = currentIndex;
				}
				currentIndex = i + 1;
				length = 1;
			} else {
				length++;
			}
		}
		return maxLength;
	}

}
