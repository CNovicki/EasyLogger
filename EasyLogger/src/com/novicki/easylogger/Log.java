package com.novicki.easylogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logs information to the console. Logs can be in the form of error, debug,
 * info, verbose, warning. Each type of log requires a tag, that is indexed
 * to be search-able, and a message, which is output to the console. Each log has a time-stamp
 * in the form <b>yyyy/MM/dd HH:mm:ss</b>.
 * @author Calvin Novicki
 *
 */
public class Log {
	
	/** 
	 * Enables or Disables logging to a file.
	 */
	private static boolean enableLogging = false;
	
	/**
	 * Sets the name of the file to save logs.
	 */
	private static String filename = "data.log";
	
	/**
	 * The DateTimeFormatter for saving the file.
	 */
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd.HH-mm-ss");
	
	/**
	 * The current date and time represented as a string.
	 */
	private static String dateTime = LocalDateTime.now().format(dtf).toString();
	
	/**
	 * The user home directory of the current operating system.
	 */
	private static String userHome = System.getProperty("user.home");
	
	/**
	 * The log directory of the user home.
	 */
	private static File directory = new File(userHome + "/logs/");
		
	/**
	 * The file to be created in the log directory.
	 */
	private static File file;
	
	/**
	 * The <code>FileWriter</code> for the logging system.
	 */
	private static FileWriter fileWriter;
	
	/**
	 * The <code>BufferedWriter</code> for the logging system.
	 */
	private static BufferedWriter bufferedWriter;

	/**
	 * Enables logging to a file.
	 */
	public static void enableLogging() {
		Log.enableLogging = true;
	}
	
	/**
	 * Enables logging to file with a custom filename.
	 * @param filename The filename to use.
	 */
	public static void enableLogging(String filename) {
		Log.enableLogging = true;
		Log.filename = filename;
	}
		
	/**
	 * Creates the log directory in the <code>user.home</code> directory if
	 * it does not exist. 
	 * Then is creates a file called <b>data.log</b> if it does not exists, and
	 * if it does, then it copies the contents of that file to another log file
	 * with the date and time, then creates a new <b>data.log</b> file.
	 */
	private static void createLog() {
		try {
			file = new File(directory.getAbsoluteFile() + "/" + filename);
			if(!directory.exists()) {	
				directory.mkdir();
			}
			if(!file.exists()) {	
				file.createNewFile();
			}
			else if(file.exists()) {
				File oldFile = file;
				oldFile.renameTo(new File(directory.getAbsoluteFile() + "/" + dateTime + "." + filename));
				oldFile.createNewFile();
				file = new File(directory.getAbsoluteFile() + "/" + filename);
			}
		}
		catch(IOException e) {	
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints data to a log file located in <code>user.home/logs</code>. If there is
	 * more than one log statement, the log gets appended to the existing file.
	 * @param data The data to save.
	 */
	private static void printToLogFile(String data) {
		try {
			fileWriter = new FileWriter(Log.directory.getAbsoluteFile() + "/" + filename, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.append(data);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		}
		catch(IOException e) {	
			e.printStackTrace();
			
		}	
	}

	/**
	 * Formats the date for logging.
	 * @param tag The tag to use.
	 * @param type The type of log.
	 * @return formatted String
	 */
	private static String formatDate(String tag, String type) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return "[" + dtf.format(now) + "]" + "[" + type + ":" + tag + "] ";
	}

	/**
	 * Formats the date for logging.
	 * @param type The type of log.
	 * @return formatted String
	 */
	private static String formatDate(String type) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return "[" + dtf.format(now) + "]" + "[" + type + "] ";
	}

	/**
	 * Logs to the console.
	 * @param tag The tag to use.
	 * @param type The type of log.
	 * @param message The message to log.
	 * @return log
	 */
	private static String log(String tag, String type, String message) {
		String log = formatDate(tag, type) + message;
		if(enableLogging) {
			createLog();
			printToLogFile(log);
		}
		return log;
	}

	/**
	 * Logs to the console.
	 * @param type The type of log.
	 * @param message The message to log.
	 * @return log
	 */
	private static String log(String type, String message) {
		String log = formatDate(type) + message;
		if(enableLogging) {
			createLog();
			printToLogFile(log);
		}
		return log;
	}

	/**
	 * Use this for logging errors.
	 */
	public static void e() {
		System.err.println();
	}
	
	/**
	 * Use this for logging debugging.
	 */
	public static void d() {
		System.out.println();
	}
	
	/**
	 * Use this for logging information.
	 */
	public static void i() {
		System.out.println();
	}
	
	/**
	 * Use this for logging verbose information.
	 */
	public static void v() {
		System.out.println();
	}
	
	/**
	 * Use this for logging warnings.
	 */
	public static void w() {
		System.out.println();
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 */
	public static void wtf() {
		System.out.println();
	}
	
	/**
	 * Use this for logging errors.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void e(String tag, String message) {
		System.err.println(log(tag.toLowerCase(), "ERROR", message));
	}

	/**
	 * Use this for logging errors.
	 * @param message The message to log.
	 */
	public static void e(String message) {
		System.err.println(log("ERROR", message));
	}

	/**
	 * Use this for logging debugging.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void d(String tag, String message) {
		System.out.println(log(tag.toLowerCase(), "DEBUG", message));
	}

	/**
	 * Use this for logging debugging.
	 * @param message The message to log.
	 */
	public static void d(String message) {
		System.out.println(log("DEBUG", message));
	}

	/**
	 * Use this for logging information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void i(String tag, String message) {
		System.out.println(log(tag.toLowerCase(), "INFO", message));
	}

	/**
	 * Use this for logging information.
	 * @param message The message to log.
	 */
	public static void i(String message) {
		System.out.println(log("INFO", message));
	}

	/**
	 * Use this for logging verbose information.
	 * @param tag The tag to log.
	 * @param message The message to log.
	 */
	public static void v(String tag, String message) {
		System.out.println(log(tag.toLowerCase(), "VERBOSE", message));
	}

	/**
	 * Use this for logging verbose information.
	 * @param message The message to log.
	 */
	public static void v(String message) {
		System.out.println(log("VERBOSE", message));
	}

	/**
	 * Use this for logging warnings.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void w(String tag, String message) {
		System.out.println(log(tag.toLowerCase(), "WARN", message));
	}

	/**
	 * Use this for logging warnings.
	 * @param message The message to log.
	 */
	public static void w(String message) {
		System.out.println(log("WARN", message));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void wtf(String tag, String message) {
		System.out.println(log(tag.toLowerCase(), "WTF", message));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param message The message to log.
	 */
	public static void wtf(String message) {
		System.out.println(log("WTF", message));
	}

	/**
	 * Use this for logging errors.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void e(String tag, int message) {
		System.err.println(log(tag.toLowerCase(), "ERROR", Integer.toString(message)));

	}

	/**
	 * Use this for logging errors.
	 * @param message The message to log.
	 */
	public static void e(int message) {
		System.err.println(log("ERROR", Integer.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void d(String tag, int message) {
		System.out.println(log(tag.toLowerCase(), "DEBUG", Integer.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param message The message to use.
	 */
	public static void d(int message) {
		System.out.println(log("DEBUG", Integer.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void i(String tag, int message) {
		System.out.println(log(tag.toLowerCase(), "INFO", Integer.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param message The message to log.
	 */
	public static void i(int message) {
		System.out.println(log("INFO", Integer.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void v(String tag, int message) {
		System.out.println(log(tag.toLowerCase(), "VERBOSE", Integer.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param message The message to log.
	 */
	public static void v(int message) {
		System.out.println(log("VERBOSE", Integer.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void w(String tag, int message) {
		System.out.println(log(tag.toLowerCase(), "WARN", Integer.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param message The message to log.
	 */
	public static void w(int message) {
		System.out.println(log("WTF", Integer.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void wtf(String tag, int message) {
		System.out.println(log(tag.toLowerCase(), "WTF", Integer.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param message The message to log.
	 */
	public static void wtf(int message) {
		System.out.println(log("WTF", Integer.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void e(String tag, boolean message) {
		System.err.println(log(tag.toLowerCase(), "ERROR", Boolean.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param message The message to log.
	 */
	public static void e(boolean message) {
		System.err.println(log("ERROR", Boolean.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void d(String tag, boolean message) {
		System.out.println(log(tag.toLowerCase(), "DEBUG", Boolean.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param message The message to log.
	 */
	public static void d(boolean message) {
		System.out.println(log("DEBUG", Boolean.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param tag The tag to use.
	 * @param message The message to use.
	 */
	public static void i(String tag, boolean message) {
		System.out.println(log(tag.toLowerCase(), "INFO", Boolean.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param message The message to log.
	 */
	public static void i(boolean message) {
		System.out.println(log("INFO", Boolean.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void v(String tag, boolean message) {
		System.out.println(log(tag.toLowerCase(), "VERBOSE", Boolean.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param message The message to log.
	 */
	public static void v(boolean message) {
		System.out.println(log("VERBOSE", Boolean.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void w(String tag, boolean message) {
		System.out.println(log(tag.toLowerCase(), "WARN", Boolean.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param message The message to log.
	 */
	public static void w(boolean message) {
		System.out.println(log("WARN", Boolean.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void wtf(String tag, boolean message) {
		System.out.println(log(tag.toLowerCase(), "WTF", Boolean.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param message The message to log.
	 */
	public static void wtf(boolean message) {
		System.out.println(log("WTF", Boolean.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void e(String tag, byte message) {
		System.err.println(log(tag.toLowerCase(), "ERROR", Byte.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param message The message to log.
	 */
	public static void e(byte message) {
		System.err.println(log("ERROR", Byte.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void d(String tag, byte message) {
		System.out.println(log(tag.toLowerCase(), "DEBUG", Byte.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param message The message to log.
	 */
	public static void d(byte message) {
		System.out.println(log("DEBUG", Byte.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void i(String tag, byte message) {
		System.out.println(log(tag.toLowerCase(), "INFO", Byte.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param message The message to log.
	 */
	public static void i(byte message) {
		System.out.println(log("INFO", Byte.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void v(String tag, byte message) {
		System.out.println(log(tag.toLowerCase(), "VERBOSE", Byte.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param message The message to log.
	 */
	public static void v(byte message) {
		System.out.println(log("VERBOSE", Byte.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void w(String tag, byte message) {
		System.out.println(log(tag.toLowerCase(), "WARN", Byte.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param message The message to log.
	 */
	public static void w(byte message) {
		System.out.println(log("WARN", Byte.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void wtf(String tag, byte message) {
		System.out.println(log(tag.toLowerCase(), "WTF", Byte.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param message The message to log.
	 */
	public static void wtf(byte message) {
		System.out.println(log("WTF", Byte.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void e(String tag, long message) {
		System.err.println(log(tag.toLowerCase(), "ERROR", Long.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param message The message to log.
	 */
	public static void e(long message) {
		System.err.println(log("ERROR", Long.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void d(String tag, long message) {
		System.out.println(log(tag.toLowerCase(), "DEBUG", Long.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param message The message to use.
	 */
	public static void d(long message) {
		System.out.println(log("DEBUG", Long.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void i(String tag, long message) {
		System.out.println(log(tag.toLowerCase(), "INFO", Long.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param message The message to log.
	 */
	public static void i(long message) {
		System.out.println(log("INFO", Long.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void v(String tag, long message) {
		System.out.println(log(tag.toLowerCase(), "VERBOSE", Long.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param message The message to log.
	 */
	public static void v(long message) {
		System.out.println(log("VERBOSE", Long.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void w(String tag, long message) {
		System.out.println(log(tag.toLowerCase(), "WARN", Long.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param message The message to log.
	 */
	public static void w(long message) {
		System.out.println(log("WARN", Long.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void wtf(String tag, long message) {
		System.out.println(log(tag.toLowerCase(), "WTF", Long.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param message The message to log.
	 */
	public static void wtf(long message) {
		System.out.println(log("WTF", Long.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void e(String tag, float message) {
		System.err.println(log(tag.toLowerCase(), "ERROR", Float.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param message The message to log.
	 */
	public static void e(float message) {
		System.err.println(log("ERROR", Float.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void d(String tag, float message) {
		System.out.println(log(tag.toLowerCase(), "DEBUG", Float.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param message The message to log.
	 */
	public static void d(float message) {
		System.out.println(log("DEBUG", Float.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void i(String tag, float message) {
		System.out.println(log(tag.toLowerCase(), "INFO", Float.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param message The message to log.
	 */
	public static void i(float message) {
		System.out.println(log("INFO", Float.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void v(String tag, float message) {
		System.out.println(log(tag.toLowerCase(), "VERBOSE", Float.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param message The message to log.
	 */
	public static void v(float message) {
		System.out.println(log("VERBOSE", Float.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void w(String tag, float message) {
		System.out.println(log(tag.toLowerCase(), "WARN", Float.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param message The message to log.
	 */
	public static void w(float message) {
		System.out.println(log("WARN", Float.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void wtf(String tag, float message) {
		System.out.println(log(tag.toLowerCase(), "WTF", Float.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param message The message to log.
	 */
	public static void wtf(float message) {
		System.out.println(log("WTF", Float.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param tag The tag to use.
	 * @param message The message to use.
	 */
	public static void e(String tag, double message) {
		System.err.println(log(tag.toLowerCase(), "ERROR", Double.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param message The message to log.
	 */
	public static void e(double message) {
		System.err.println(log("ERROR", Double.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void d(String tag, double message) {
		System.out.println(log(tag.toLowerCase(), "DEBUG", Double.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param message The message to log.
	 */
	public static void d(double message) {
		System.out.println(log("DEBUG", Double.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void i(String tag, double message) {
		System.out.println(log(tag.toLowerCase(), "INFO", Double.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param message The message to log.
	 */
	public static void i(double message) {
		System.out.println(log("INFO", Double.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void v(String tag, double message) {
		System.out.println(log(tag.toLowerCase(), "VERBOSE", Double.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param message The message to log.
	 */
	public static void v(double message) {
		System.out.println(log("VERBOSE", Double.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void w(String tag, double message) {
		System.out.println(log(tag.toLowerCase(), "WARN", Double.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param message The message to log.
	 */
	public static void w(double message) {
		System.out.println(log("WARN", Double.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void wtf(String tag, double message) {
		System.out.println(log(tag.toLowerCase(), "WTF", Double.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param message The message to log.
	 */
	public static void wtf(double message) {
		System.out.println(log("WTF", Double.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void e(String tag, short message) {
		System.err.println(log(tag.toLowerCase(), "ERROR", Short.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param message The message to log.
	 */
	public static void e(short message) {
		System.err.println(log("ERROR", Short.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void d(String tag, short message) {
		System.out.println(log(tag.toLowerCase(), "DEBUG", Short.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param message The message to log.
	 */
	public static void d(short message) {
		System.out.println(log("DEBUG", Short.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void i(String tag, short message) {
		System.out.println(log(tag.toLowerCase(), "INFO", Short.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param message The message to log.
	 */
	public static void i(short message) {
		System.out.println(log("INFO", Short.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void v(String tag, short message) {
		System.out.println(log(tag.toLowerCase(), "VERBOSE", Short.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param message The message to log.
	 */
	public static void v(short message) {
		System.out.println(log("VERBOSE", Short.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void w(String tag, short message) {
		System.out.println(log(tag.toLowerCase(), "WARN", Short.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param message The message to log.
	 */
	public static void w(short message) {
		System.out.println(log("WARN", Short.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void wtf(String tag, short message) {
		System.out.println(log(tag.toLowerCase(), "WTF", Short.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param message The message to log.
	 */
	public static void wtf(short message) {
		System.out.println(log("WTF", Short.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void e(String tag, char message) {
		System.err.println(log(tag.toLowerCase(), "ERROR", Character.toString(message)));
	}

	/**
	 * Use this for logging errors.
	 * @param message The message to log.
	 */
	public static void e(char message) {
		System.err.println(log("ERROR", Character.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void d(String tag, char message) {
		System.out.println(log(tag.toLowerCase(), "DEBUG", Character.toString(message)));
	}

	/**
	 * Use this for logging debugging.
	 * @param message The message to log.
	 */
	public static void d(char message) {
		System.out.println(log("DEBUG", Character.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void i(String tag, char message) {
		System.out.println(log(tag.toLowerCase(), "INFO", Character.toString(message)));
	}

	/**
	 * Use this for logging information.
	 * @param message The message to log.
	 */
	public static void i(char message) {
		System.out.println(log("INFO", Character.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void v(String tag, char message) {
		System.out.println(log(tag.toLowerCase(), "VERBOSE", Character.toString(message)));
	}

	/**
	 * Use this for logging verbose information.
	 * @param message The message to log.
	 */
	public static void v(char message) {
		System.out.println(log("VERBOSE", Character.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void w(String tag, char message) {
		System.out.println(log(tag.toLowerCase(), "WARN", Character.toString(message)));
	}

	/**
	 * Use this for logging warnings.
	 * @param message The message to log.
	 */
	public static void w( char message) {
		System.out.println(log("WARN", Character.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag The tag to use.
	 * @param message The message to log.
	 */
	public static void wtf(String tag, char message) {
		System.out.println(log(tag.toLowerCase(), "WTF", Character.toString(message)));
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param message The message to log.
	 */
	public static void wtf(char message) {
		System.out.println(log("WTF", Character.toString(message)));
	}

}
