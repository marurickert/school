package aiwa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	public static String toDate(String d) {

		try {
			SimpleDateFormat in = new SimpleDateFormat("YYYY-MM-DD");
			SimpleDateFormat out = new SimpleDateFormat("YYYY年MM月DD日");

			Date dt = in.parse(d);
			String s = out.format(dt);

			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String toMoney(int num) {
		return String.format("%,d", num) + "円";
	}

	public static String toBreak(String str) {
		return str.replace("\r\n", "<br>");
	}

	public static String toRating(double num) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < (int) num; i++) {
			sb.append("<img src='images/star.png' height='50px'>");
		}

		if (num % 1 != 0) {
			sb.append("<img src='images/halfstar.png' height='50px'>");
		}
		return sb.toString();
	}
}
