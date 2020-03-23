package com.org.testapp;

import java.net.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.*;

public class StartHere {
	
	
	public static void main(String args[]) throws Exception
	{
		Logger logger = Logger.getLogger("MyLog");  
	    FileHandler fh; 
        fh = new FileHandler("MyLogFile.log");  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);
        
        //Getting inputs
		if(args.length !=4)
	    {
	    	 logger.info("Please pass ip and port of UI and API application");
	    }
	    
		String ip = args[0];
		String port = args[1];
		String APIIP = args[2];
		String APIPort = args[3];
		
		//Getting parameters
		ReadProperty properties = new ReadProperty();
		String[] parameters = properties.getProperties(logger);
		
		logger.info("Start ..........CD Regression Test........Application\n");
		
		logger.info("Starting .................. testUI");
		UITest.TestUI(ip, port, logger, parameters);
		logger.info("END ......................  testUI\n");
		
		logger.info("Starting .................. POSTRequest");
		APTTest.POSTRequest(APIIP, APIPort, logger, parameters);
		
		
		logger.info("END..........CD Regression Test........Application");
		
		
		
		
        
	}

}
