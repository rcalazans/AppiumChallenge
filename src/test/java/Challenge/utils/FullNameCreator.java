package Challenge.utils;

import Challenge.dto.results.Name;

public class FullNameCreator {
	public static String GetFullName(Name name) {
		return name.title + " " + name.first + " " + name.last;
	}
}
