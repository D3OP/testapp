package com.org.testapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

public class APTTest {

	//endpoint example - http://localhost:8080/v2/menuitems (GET / POST both)
	
	public static String POST_PARAMS = "{\"restaurantName\":\"MCDONALDS\",\"name\":\"Sandwich-206\",\"description\":\"yummy\",\"rating\":6,\"cityName\":\"BOSTON\"}";
	
	public static String json = null;
	public static String endpoint = null;
	public static String protocol = null;
	public static String UIParameter = null;	

	public static void MyGETRequest(String url, String port, Logger logger, String newJson) throws IOException {
		try {
			
			//String getAPIUEndpoint = "/v2/menuitems";
			String getAPIUEndpoint = endpoint;
			//getAPIUEndpoint = "http://"+url+":"+port+getAPIUEndpoint;
			getAPIUEndpoint = protocol+"://"+url+":"+port+getAPIUEndpoint;
			
			URL urlForGetRequest = new URL(getAPIUEndpoint);
			String readLine = null;
			HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
			conection.setRequestMethod("GET");

			int responseCode = conection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
				StringBuffer response = new StringBuffer();
				
				while ((readLine = in.readLine()) != null) {				
					logger.info("json=" + readLine);
					logger.warning("   --- PASS ---   ");
		
					if (readLine.contains(newJson))
					{
						logger.info("********** Matched ***************"+newJson);
						logger.warning("   --- PASS ---   ");
					}
					response.append(readLine);
				}
				in.close();

			} else {
				System.out.println("GET NOT WORKED");
			}
		} catch (Exception e) {
			logger.info(e.toString());
			logger.warning("   --- FAIL ---   ");
			logger.warning("   1   ");
			System.exit(1);
		}
	}
	
	public static void POSTRequest(String url, String port, Logger logger, String[] parameters) throws IOException {

		try {
			
			json = parameters[0];
			endpoint = parameters[1];
			protocol = parameters[2];
			
			logger.info("API ip entered = "+ url);
		 	logger.info("API port entered = "+ port);
			
			String getAPIUEndpoint = parameters[1];
			getAPIUEndpoint = "http://"+url+":"+port+getAPIUEndpoint;
			//logger.info(POST_PARAMS);
			logger.info(json);
			
			URL obj = new URL(getAPIUEndpoint);
			HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
			postConnection.setRequestMethod("POST");
			postConnection.setRequestProperty("Content-Type", "application/json");
			postConnection.setDoOutput(true);
			OutputStream os = postConnection.getOutputStream();
			
			//os.write(POST_PARAMS.getBytes());
			os.write(json.getBytes());
			os.flush();
			os.close();
			
			String readLine = null;
			
			// Reading the response
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			if ((readLine = in.readLine()) != null) {
				//logger.warning("created json="+readLine);
			}
			
			if(readLine.length()>10)
			{
				logger.warning("   --- PASS ---   ");
			}
			logger.info("END ...................... POSTRequest\n");
			
			logger.info("Starting .................. MyGETRequest");
			MyGETRequest(url, port, logger, readLine);
			logger.info("END ...................... MyGETRequest\n");
			
		} catch (Exception e)
		{
			logger.info(e.toString());
			logger.warning("   --- FAIL ---   ");
			logger.warning("   1   ");
			System.exit(1);
		}
	}

}
