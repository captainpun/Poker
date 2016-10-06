package net.sourceforge.javapoker.server;

/**
 * @author Martin Cavanagh
 *
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class ServerLogger {
	
	private static final String DEFAULT_LOGFILE = "serverlog.txt";
	private static SimpleDateFormat dateformat;
	private static File logfile;
	private static FileWriter fw;
	private static BufferedWriter out;

	/**
	 * Creates a ServerLogger which logs to the file specified by
	 * <code>path</code>.
	 * 
	 * <p>If the <code>File</code> at <code>path</code> doesn't exist, this is
	 * created first.
	 * 
	 * <p>Sets up this <code>ServerLogger</code>'s <code>BufferedWriter</code>
	 * for appending to the logfile.
	 * 
	 * @param path The path of the logfile to be used
	 * 
	 * @see File
	 * @see BufferedWriter
	 */
	public static void init(String path) {
		dateformat = new SimpleDateFormat();
		logfile = new File(path);
		
		try {
			logfile.createNewFile(); // Only creates if if doesn't already exist
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Logging to '" + logfile.getAbsolutePath() + "'");

		// Open the log file
		try {
			out = new BufferedWriter(new FileWriter(logfile, true));
		}
		catch (IOException e) {
			System.out.println("Error opening logfile: " + e.getMessage());
		}
	}

	/**
	 * Create a <code>ServerLogger</code> with the default logfile path.
	 * 
	 */
	public static void init() {
		init(DEFAULT_LOGFILE);
	}


	/**
	 * Write an entry to the log, along with a timestamp.
	 * 
	 * @param message The text to be entered into the log
	 */
	public static void addEntry(String message) {
		
		String timestamp = dateformat.format(new Date(), new StringBuffer(), new FieldPosition(0)).toString();

		try {
			out.write(timestamp + " - " + message);
			out.newLine();
			out.flush(); // Remove this later
		}
		catch (IOException e) {
			System.out.println("problem writing to log" + e.getMessage());
		}
		
	}

}
