package cz.rkr.test.acm;

import cz.rkr.test.acm.tools.StringReaderUtil;

public class LongestSequencePosition {

	public static void main(String[] args) throws Exception {
		new LongestSequencePosition().start();
	}

	public void start() throws Exception {
		// aaakkcccccczz
		// aaabbbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccccdddvvvfffggg
		String msg = StringReaderUtil.readLineFromStdIn("Zadej retezec:");

		int pos = myRunLongestIndex(msg);
		System.out.format("MainLongestSequenceSameChars, [strLen=0-%d], index:%d\n%s", msg.length() - 1, pos, msg);
		System.out.format("%" + (pos + 1) + "s", "^");
	}

	public static int runLongestIndexWrongFromWEB(String setofletters) {
		// int ctr = 0;
		int ctr = 1; // every character is repeated at least once, so you should initialize it to 1, not 0
		int ctrstor = 0;
		int ii = 0;
		int output = 0;

		// loops until the last character in the string
		for (int i = 0; i < setofletters.length() - 1; i++) {
			// checks if the letter is the same to the next
			// if (setofletters.charAt(i) == setofletters.charAt(i++)) {
			if (i < setofletters.length() - 1 && setofletters.charAt(i) == setofletters.charAt(i + 1)) { // i++ is not same as i+1
				ctr++;
				ii = i++;
				// loops until the letter in the index is no longer equal
				while (setofletters.charAt(i) == setofletters.charAt(ii)) {
					// ii++;
					ii = i + 1; // i++ is not same as i+1
					ctr++;
				}

				if (ctr > ctrstor) {
					output = i;
				}
				// storing purposes
				ctrstor = ctr;
			}
			// resets the counter
			// ctr = 0;
			ctr = 1; // for the same reason I mentioned above
		}
		return output;
	}

	public static int runLongestIndexCorrectFromWEB(String setofletters) {
		int ctr = 1;
		int output = 0;
		int j = 0;
		for (int i = 0; i < setofletters.length() - 1; i++) {
			j = i;
			while (i < setofletters.length() - 1 && setofletters.charAt(i) == setofletters.charAt(i + 1)) {
				i++;
				ctr++;
			}
			if (ctr > output) {
				output = j;
			}
			ctr = 1;
		}
		return output;
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
		return outputIndex;
	}

}
