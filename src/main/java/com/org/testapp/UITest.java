package com.org.testapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

public class UITest {
	
	public static void TestUI(String url, String port, Logger logger, String[] parameters)
	{
		 try {	   	  
			 	boolean serverUpStatus = false;
			 
			 	logger.info("UI ip entered = "+ url);
			 	logger.info("UI port entered = "+ port);
				
			 	url = "http://"+url+":"+port;
				URL oracle = new URL(url);
		        BufferedReader in = new BufferedReader(
		        new InputStreamReader(oracle.openStream()));

		        String inputLine;
		        while ((inputLine = in.readLine()) != null)
		        {
		        	if(inputLine.contains(parameters[3])){
		        	//if(inputLine.contains("title")){
		        		//if(inputLine.contains("Food for Thought")){
		        		serverUpStatus = true;
		        		logger.info(" ************ Server is up *************** ");
		        		logger.warning("   --- PASS ---   ");
		        		break;
		        	}
		        }
		        if(!serverUpStatus)
		        {
		        	logger.info(" ************ Server is down *************** ");
		        }
		        in.close();
		        
		        logger.info(" ************ End  ***************");
				}
				catch(Exception e)
				{
					logger.info(e.toString());
					logger.warning("   --- FAIL ---   ");
					logger.warning("   1   ");
					System.exit(1);
				}
	}

}
