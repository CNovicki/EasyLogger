package com.novicki.easylogger.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logs information to the console. Logs can be in the form of error, debug,
 * infor, verbose, warning, and wtf. Each type of log requires a tag, that is indexed
 * to be searchable, and a message, which is output to the console. Each log has a timestamp
 * in the form <b>yyyy/MM/dd HH:mm:ss</b>.
 * @author Calvin Novicki
 *
 */
public class Log {
	
	/**
	 * Prints data to a log file located in <code>user.home/logs</code>. If there is
	 * more than one log statement, the log gets appended to the existing file.
	 * @param data
	 */
	private static void printToLogFile(String data) {
		
		try {
			
			String userHome = System.getProperty("user.home");
									
			File directory = new File(userHome + "/logs/");
						
			File file = new File(directory.getAbsoluteFile() + "/data.log");
									
			if(!directory.exists()) {
				
				directory.mkdir();
								
			}
			
			if(!file.exists()) {
				
				file.createNewFile();
								
			}
			
			FileWriter fileWriter = new FileWriter(file, true);
			
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.append(data);
			
			bufferedWriter.newLine();
			
			bufferedWriter.flush();
			
			bufferedWriter.close();
			
		}
		
		catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}

	/**
	 * Formats the date for logging.
	 * @param tag
	 * @param type
	 * @return
	 */
	private static String formatDate(String tag, String type) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		LocalDateTime now = LocalDateTime.now();

		return "[" + dtf.format(now) + "]" + "[" + type + ":" + tag + "] ";

	}

	/**
	 * Formats the date for logging.
	 * @param tag
	 * @param type
	 * @return
	 */
	private static String formatDate(String type) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		LocalDateTime now = LocalDateTime.now();

		return "[" + dtf.format(now) + "]" + "[" + type + "] ";

	}

	/**
	 * Logs to the console.
	 * @param tag
	 * @param type
	 * @param message
	 * @return
	 */
	private static String log(String tag, String type, String message) {

		String log = formatDate(tag, type) + message;
										
		printToLogFile(log);
		
		return log;

	}

	/**
	 * Logs to the console.
	 * @param tag
	 * @param type
	 * @param message
	 * @return
	 */
	private static String log(String type, String message) {

		String log = formatDate(type) + message;
								
		printToLogFile(log);

		return log;

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, String message) {

		System.err.println(log(tag.toLowerCase(), "ERROR", message));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(String message) {

		System.err.println(log("ERROR", message));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, String message) {

		System.out.println(log(tag.toLowerCase(), "DEBUG", message));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(String message) {

		System.out.println(log("DEBUG", message));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, String message) {

		System.out.println(log(tag.toLowerCase(), "INFO", message));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(String message) {

		System.out.println(log("INFO", message));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(String tag, String message) {

		System.out.println(log(tag.toLowerCase(), "VERBOSE", message));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(String message) {

		System.out.println(log("VERBOSE", message));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(String tag, String message) {

		System.out.println(log(tag.toLowerCase(), "WARN", message));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(String message) {

		System.out.println(log("WARN", message));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(String tag, String message) {

		System.out.println(log(tag.toLowerCase(), "WTF", message));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(String message) {

		System.out.println(log("WTF", message));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, int message) {

		System.err.println(log(tag.toLowerCase(), "ERROR", Integer.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(int message) {

		System.err.println(log("ERROR", Integer.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, int message) {

		System.out.println(log(tag.toLowerCase(), "DEBUG", Integer.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(int message) {

		System.out.println(log("DEBUG", Integer.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, int message) {

		System.out.println(log(tag.toLowerCase(), "INFO", Integer.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(int message) {

		System.out.println(log("INFO", Integer.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(String tag, int message) {

		System.out.println(log(tag.toLowerCase(), "VERBOSE", Integer.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(int message) {

		System.out.println(log("VERBOSE", Integer.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(String tag, int message) {

		System.out.println(log(tag.toLowerCase(), "WARN", Integer.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(int message) {


	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(String tag, int message) {

		System.out.println(log(tag.toLowerCase(), "WTF", Integer.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(int message) {

		System.out.println(log("WTF", Integer.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, boolean message) {

		System.err.println(log(tag.toLowerCase(), "ERROR", Boolean.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(boolean message) {

		System.err.println(log("ERROR", Boolean.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, boolean message) {

		System.out.println(log(tag.toLowerCase(), "DEBUG", Boolean.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(boolean message) {

		System.out.println(log("DEBUG", Boolean.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, boolean message) {

		System.out.println(log(tag.toLowerCase(), "INFO", Boolean.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(boolean message) {

		System.out.println(log("INFO", Boolean.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(String tag, boolean message) {

		System.out.println(log(tag.toLowerCase(), "VERBOSE", Boolean.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(boolean message) {

		System.out.println(log("VERBOSE", Boolean.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(String tag, boolean message) {

		System.out.println(log(tag.toLowerCase(), "WARN", Boolean.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(boolean message) {

		System.out.println(log("WARN", Boolean.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(String tag, boolean message) {

		System.out.println(log(tag.toLowerCase(), "WTF", Boolean.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(boolean message) {

		System.out.println(log("WTF", Boolean.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, byte message) {

		System.err.println(log(tag.toLowerCase(), "ERROR", Byte.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(byte message) {

		System.err.println(log("ERROR", Byte.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, byte message) {

		System.out.println(log(tag.toLowerCase(), "DEBUG", Byte.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(byte message) {

		System.out.println(log("DEBUG", Byte.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, byte message) {

		System.out.println(log(tag.toLowerCase(), "INFO", Byte.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(byte message) {

		System.out.println(log("INFO", Byte.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(String tag, byte message) {

		System.out.println(log(tag.toLowerCase(), "VERBOSE", Byte.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(byte message) {

		System.out.println(log("VERBOSE", Byte.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(String tag, byte message) {

		System.out.println(log(tag.toLowerCase(), "WARN", Byte.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(byte message) {

		System.out.println(log("WARN", Byte.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(String tag, byte message) {

		System.out.println(log(tag.toLowerCase(), "WTF", Byte.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(byte message) {

		System.out.println(log("WTF", Byte.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, long message) {

		System.err.println(log(tag.toLowerCase(), "ERROR", Long.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(long message) {

		System.err.println(log("ERROR", Long.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, long message) {

		System.out.println(log(tag.toLowerCase(), "DEBUG", Long.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(long message) {

		System.out.println(log("DEBUG", Long.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, long message) {

		System.out.println(log(tag.toLowerCase(), "INFO", Long.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(long message) {

		System.out.println(log("INFO", Long.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(String tag, long message) {

		System.out.println(log(tag.toLowerCase(), "VERBOSE", Long.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(long message) {

		System.out.println(log("VERBOSE", Long.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(String tag, long message) {

		System.out.println(log(tag.toLowerCase(), "WARN", Long.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(long message) {

		System.out.println(log("WARN", Long.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(String tag, long message) {

		System.out.println(log(tag.toLowerCase(), "WTF", Long.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(long message) {

		System.out.println(log("WTF", Long.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, float message) {

		System.err.println(log(tag.toLowerCase(), "ERROR", Float.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(float message) {

		System.err.println(log("ERROR", Float.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, float message) {

		System.out.println(log(tag.toLowerCase(), "DEBUG", Float.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(float message) {

		System.out.println(log("DEBUG", Float.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, float message) {

		System.out.println(log(tag.toLowerCase(), "INFO", Float.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(float message) {

		System.out.println(log("INFO", Float.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(String tag, float message) {

		System.out.println(log(tag.toLowerCase(), "VERBOSE", Float.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(float message) {

		System.out.println(log("VERBOSE", Float.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(String tag, float message) {

		System.out.println(log(tag.toLowerCase(), "WARN", Float.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(float message) {

		System.out.println(log("WARN", Float.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(String tag, float message) {

		System.out.println(log(tag.toLowerCase(), "WTF", Float.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(float message) {

		System.out.println(log("WTF", Float.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, double message) {

		System.err.println(log(tag.toLowerCase(), "ERROR", Double.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(double message) {

		System.err.println(log("ERROR", Double.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, double message) {

		System.out.println(log(tag.toLowerCase(), "DEBUG", Double.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(double message) {

		System.out.println(log("DEBUG", Double.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, double message) {

		System.out.println(log(tag.toLowerCase(), "INFO", Double.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(double message) {

		System.out.println(log("INFO", Double.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(String tag, double message) {

		System.out.println(log(tag.toLowerCase(), "VERBOSE", Double.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(double message) {

		System.out.println(log("VERBOSE", Double.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(String tag, double message) {

		System.out.println(log(tag.toLowerCase(), "WARN", Double.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(double message) {

		System.out.println(log("WARN", Double.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(String tag, double message) {

		System.out.println(log(tag.toLowerCase(), "WTF", Double.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(double message) {

		System.out.println(log("WTF", Double.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, short message) {

		System.err.println(log(tag.toLowerCase(), "ERROR", Short.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(short message) {

		System.err.println(log("ERROR", Short.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, short message) {

		System.out.println(log(tag.toLowerCase(), "DEBUG", Short.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(short message) {

		System.out.println(log("DEBUG", Short.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, short message) {

		System.out.println(log(tag.toLowerCase(), "INFO", Short.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(short message) {

		System.out.println(log("INFO", Short.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(String tag, short message) {

		System.out.println(log(tag.toLowerCase(), "VERBOSE", Short.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(short message) {

		System.out.println(log("VERBOSE", Short.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(String tag, short message) {

		System.out.println(log(tag.toLowerCase(), "WARN", Short.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(short message) {

		System.out.println(log("WARN", Short.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(String tag, short message) {

		System.out.println(log(tag.toLowerCase(), "WTF", Short.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(short message) {

		System.out.println(log("WTF", Short.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, char message) {

		System.err.println(log(tag.toLowerCase(), "ERROR", Character.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param tag
	 * @param message
	 */
	public static void e(char message) {

		System.err.println(log("ERROR", Character.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, char message) {

		System.out.println(log(tag.toLowerCase(), "DEBUG", Character.toString(message)));

	}

	/**
	 * Use this for logging debugging.
	 * @param tag
	 * @param message
	 */
	public static void d(char message) {

		System.out.println(log("DEBUG", Character.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, char message) {

		System.out.println(log(tag.toLowerCase(), "INFO", Character.toString(message)));

	}

	/**
	 * Use this for logging information.
	 * @param tag
	 * @param message
	 */
	public static void i(char message) {

		System.out.println(log("INFO", Character.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(String tag, char message) {

		System.out.println(log(tag.toLowerCase(), "VERBOSE", Character.toString(message)));

	}

	/**
	 * Use this for logging verbose information.
	 * @param tag
	 * @param message
	 */
	public static void v(char message) {

		System.out.println(log("VERBOSE", Character.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w(String tag, char message) {

		System.out.println(log(tag.toLowerCase(), "WARN", Character.toString(message)));

	}

	/**
	 * Use this for logging warnings.
	 * @param tag
	 * @param message
	 */
	public static void w( char message) {

		System.out.println(log("WARN", Character.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(String tag, char message) {

		System.out.println(log(tag.toLowerCase(), "WTF", Character.toString(message)));

	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag
	 * @param message
	 */
	public static void wtf(char message) {

		System.out.println(log("WTF", Character.toString(message)));

	}

}
