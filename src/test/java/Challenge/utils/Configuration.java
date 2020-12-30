package Challenge.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
		
	public static String getValue(String key) {
		File configFile = new File("config.properties");

		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);

			String value = props.getProperty(key);

			reader.close();
			return value;
		} catch (FileNotFoundException ex) {
			// file does not exist

		} catch (IOException ex) {
			// TODO: io

		}
		return null;
	}


}
