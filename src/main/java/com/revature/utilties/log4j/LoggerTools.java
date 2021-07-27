package com.revature.utilties.log4j;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.varia.LevelRangeFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class of convenience tools related to the Log4J API.
 * Currently relies on the dependency log4j.info, which is a 
 * properties file to configure pieces of the logger.
 * 
 * @author Gavin Moore
 * @version 1.1
 */
public class LoggerTools {
	
	/**
	 * Create, configure, and return the root logger.
	 * 
	 * Configure a Log4J system to record several levels of data into 
	 * files and the console. Output is based on several levels: A error-level 
	 * logger, which records error logs into the file src/logs/errorLog.out,
	 * a trace-level logger which records all trace and above level
	 * activity to the file src/logs/traceLog.out, and a logger to output
	 * to the console, which relies on the custom exception level USERINFO
	 * to print low-level user data to the console.
	 * 
	 * @param className: The className to create the logger under.
	 * @return: The configured root logger.
	 * */
	public static Logger getAndConfigureLogger(String className){
		// get configuration file
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("log4j.info");

		// Retrieve and configure logger.
		Logger log = Logger.getRootLogger();
		log.setLevel(Level.ALL);
		
		PropertyConfigurator.configure(input);
		
		// Create and add filters.
		LevelRangeFilter errorFilter = new LevelRangeFilter();
		errorFilter.setAcceptOnMatch(true);
		errorFilter.setLevelMin(Level.ERROR);
		errorFilter.setLevelMax(Level.ERROR);
		log.getAppender("ERRORS").addFilter(errorFilter);
		
		LevelRangeFilter consoleFilter = new LevelRangeFilter();
		consoleFilter.setAcceptOnMatch(true);
//		consoleFilter.setLevelMax(UserInfoLog4JLevel.USERINFO);
//		consoleFilter.setLevelMin(UserInfoLog4JLevel.USERINFO);
		log.getAppender("CONSOLE").addFilter(consoleFilter);
		
		LevelRangeFilter traceFilter = new LevelRangeFilter();
		traceFilter.setAcceptOnMatch(true);
		traceFilter.setLevelMin(Level.TRACE);
		log.getAppender("RECORDS").addFilter(traceFilter);
	
		return log;
	}

}
