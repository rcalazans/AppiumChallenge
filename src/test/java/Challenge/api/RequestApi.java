package Challenge.api;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;

import Challenge.dto.ApiReturn;
import Challenge.utils.Configuration;

public class RequestApi {

	public static ApiReturn getRandomUser() throws IOException, InterruptedException
	{

		URL url = new URL(Configuration.getValue("api.randomuser"));

		return getApiResponseWithRetry(url, ApiReturn.class);

	}

	private static <T> T getApiResponseWithRetry(URL url, Class<T> classType) throws InterruptedException, MalformedURLException {

		int maxRetries = 3;
		boolean gotResponse = false;
		int attempt = 1;

		do {
			try {
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				InputStream inputStream = connection.getInputStream();

				if(connection.getResponseCode() == HttpURLConnection.HTTP_OK || attempt == maxRetries) {
					gotResponse = true;

					ObjectMapper mapper = new ObjectMapper();
					return mapper.readValue(inputStream, classType);

				}
			}
			catch(ConnectException ce) {
				Thread.sleep(Duration.ofSeconds(3).toMillis());
			}
			catch(Exception e) {
				Thread.sleep(Duration.ofSeconds(3).toMillis());
			}
			attempt++;
		}
		while(!gotResponse);

		return null;
	}


}
