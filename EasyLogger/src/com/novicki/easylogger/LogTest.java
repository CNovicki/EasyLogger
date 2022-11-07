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
public class LogTest {
	
	/**
	 * Log level 0;
	 */
	public static final int DEBUG  = 0;
	
	/**
	 * Log level 1.
	 */
	public static final int INFO = 1;
	
	/**
	 * Log level 2.
	 */
	public static final int VERBOSE = 2;
	
	/**
	 * Log level 3.
	 */
	public static final int WARNING = 3;
	
	/**
	 * Log level 4.
	 */
	public static final int ERROR = 4;
	
	/**
	 * Log level 5.
	 */
	public static final int FATAL = 5;
	
	private static boolean debug = true;
	
	private static boolean info = false;
	
	private static boolean verbose = false;
	
	private static boolean warn = false;
	
	private static boolean error = false;
	
	private static boolean fatal = false;
	
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
	 * Sets the log level.
	 */
	public static void setLogLevel(int logLevel) {
		switch(logLevel) {
			case LogTest.FATAL :
				fatal = true;
			case LogTest.ERROR :
				error = true;
			case LogTest.WARNING :
				warn = true;
			case LogTest.VERBOSE :
				verbose = true;
			case LogTest.INFO :
				info = true;
			case LogTest.DEBUG :
				debug = true;
			default :
				debug = true;
		}
	}
	
	/**
	 * Enables logging to a file.
	 */
	public static void enableLogging() {
		LogTest.enableLogging = true;
	}
	
	/**
	 * Enables logging to file with a custom filename.
	 * @param filename The filename to use.
	 */
	public static void enableLogging(String filename) {
		LogTest.enableLogging = true;
		LogTest.filename = filename;
	}
	
	/**
	 * Disables logging to a file.
	 */
	public static void disableLogging() {
		LogTest.enableLogging = false;
	}
	
	/**
	 * Passes args from the main method to the Log.
	 * Possible args to use:<br>
	 * 	<code>--verbose</code><br>
	 * 	<code>--debug</code><br>
	 * @param args The args to pass through.
	 */
	public static void passArgs(String[] args) {
		for(int i = 0; i < args.length; i++) {
			if(args[i].contains("--level=")) {
				int level = Integer.parseInt(Character.toString(args[i].charAt(args[i].length() - 1)));
				setLogLevel(level);
			}
		}
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
			if(!directory.exists())	
				directory.mkdir();
			if(!file.exists())	
				file.createNewFile();
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
			fileWriter = new FileWriter(LogTest.directory.getAbsoluteFile() + "/" + filename, true);
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
		String message = "[" + dtf.format(now) + "]" + "[" + type;
		if(!tag.equals(""))
			message += ":" + tag;
		message += "] ";
		return message;
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
	 * Use this for logging errors.
	 * @param object The object to log.
	 */
	public static void e(Object object) {
		e("", object);
	}
	
	/**
	 * Use this for logging errors.
	 * @param tag The tag to use.
	 * @param object The object to log.
	 */
	public static void e(String tag, Object object) {
		if(error) {
			System.err.println(log(tag, "ERROR", object.toString()));
		}
	}
	
	/**
	 * Use this for logging debugging.
	 * @param object The object to log.
	 */
	public static void d(Object object) {
		d("", object);
	}
	
	/**
	 * Use this for logging debugging.
	 * @param tag The tag to use.
	 * @param object The object to log.
	 */
	public static void d(String tag, Object object) {
		if(debug) {
			System.out.println(log(tag, "DEBUG", object.toString()));
		}
	}
	
	/**
	 * Use this for logging fatal information.
	 * @param object The object to log.
	 */
	public static void f(Object object) {
		f("", object);
	}
	
	/**
	 * Use this for logging fatal information.
	 * @param tag The tag to use.
	 * @param object The object to log.
	 */
	public static void f(String tag, Object object) {
		if(fatal) {
			System.err.println(log(tag, "FATAL", object.toString()));
		}
	}
	
	/**
	 * Use this for logging information.
	 * @param object The object to log.
	 */
	public static void i(Object object) {
		i("", object);
	}
	
	/**
	 * Use this for logging information.
	 * @param tag The tag to use.
	 * @param object The object to log.
	 */
	public static void i(String tag, Object object) {
		if(info) {
			System.out.println(log(tag, "INFO", object.toString()));
		}
	}
	
	/**
	 * Use this for logging verbose information.
	 * @param object The object to log.
	 */
	public static void v(Object object) {
		v("", object);
	}
	
	/**
	 * Use this for logging verbose information.
	 * @param tag The tag to use.
	 * @param object The object to log.
	 */
	public static void v(String tag, Object object) {
		if(verbose) {
			System.out.println(log(tag, "VERBOSE", object.toString()));
		}
	}
	
	/**
	 * Use this for logging warnings.
	 * @param object The object to log.
	 */
	public static void w(Object object) {
		w("", object);
	}
	
	/**
	 * Use this for logging warnings.
	 * @param tag The tag to use.
	 * @param object The object to log.
	 */
	public static void w(String tag, Object object) {
		if(warn) {
			System.out.println(log(tag, "WARNING", object.toString()));
		}
	}

	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param object The object to log.
	 */
	public static void wtf(Object object) {
		wtf("", object);
	}
	
	/**
	 * Use this for logging when something goes absolutely wrong
	 * and there is no other solution.
	 * @param tag The tag to use.
	 * @param object The object to log.
	 */
	public static void wtf(String tag, Object object) {
		if(debug) {
			System.out.println(log(tag, "WTF", object.toString()));
		}
	}

}