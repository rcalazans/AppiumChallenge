package Challenge.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	public static String GetCurrentDateFormatted() {
		Date date = new Date(System.currentTimeMillis());
		return GetDateFormatted(date);
	}

	public static String GetDateFormatted(Date date) {

		SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
		return formatter.format(date);
	}
}
