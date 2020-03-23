package com.org.testapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Logger;

public class ReadProperty {
	
	public static String json = null;
	public static String endpoint = null;
	public static String protocol = null;
	public static String UIParameter = null;	
	
	public String[] getProperties(Logger logger) throws IOException
	{
		String[] parameters = new String[4];
		BufferedReader reader;
		String path = "./data.txt";
		File file = new File(path); 
		
		if (file.exists()) {
			logger.info("Reading data from external file : data.txt");
			
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			int dataCounter = 0;
			while (line != null) {
				parameters[dataCounter] = line;
				System.out.println(dataCounter+" dataCounter="+line);
				// read next line
				line = reader.readLine();
				dataCounter++;
			}
			reader.close();				
			
		}
		else
		{		
		logger.info("Reading data from internal file : config.properties");	
		Properties prop = new Properties();
		String propFileName = "config.properties";
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		 
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		
		 json = prop.getProperty("json");
		 endpoint = prop.getProperty("endpoint");
		 protocol = prop.getProperty("protocol");
		 UIParameter = prop.getProperty("UIParameter");	
		 
		
		 
		 parameters[0] = json;
		 parameters[1] = endpoint;
		 parameters[2] = protocol;
		 parameters[3] = UIParameter;
		 
		}
		
		//System.out.println("json="+json);
		//System.out.println("endpoint="+endpoint);
		//System.out.println("protocol="+protocol);
		//System.out.println("UIParameter="+UIParameter);
		logger.info("Parameter=");
		for(int counter = 0; counter < parameters.length ; counter++)
		{
			logger.info(parameters[counter]);
		}
		
		return parameters;
	}

}
