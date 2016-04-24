package cz.rkr.test.acm.tools;

import java.io.Console;

public class StringReaderUtil {

	public static String readLineFromStdIn(String message) throws Exception {
		String msg;
		System.out.format("%s", message);

		Console con = System.console();
		if (con != null) {
			msg = con.readLine();
		} else {
			byte[] b = new byte[8192];
			int count = System.in.read(b);
			msg = new String(b, 0, count);
		}
		if (msg.charAt(msg.length() - 1) == '\n') {
			msg = msg.substring(0, msg.length() - 1);
		}
		return msg;
	}

}
