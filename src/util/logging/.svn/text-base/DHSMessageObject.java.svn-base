package util.logging;

import org.apache.log4j.Level;
/**
 * Title:DORMessageObject
 * Description: This class construts Message object for logging.
 * @author Bhargav Gogineni
 * 
 */
public class DHSMessageObject {
	private String mClassName = "";

	private String mMethodName = "";

	private String mCustomMessage = "";

	private int mLogLevel=1 ;

	private String mModule = "";
	
	private Throwable mThrowable=null;

	/**
	 * This utility method maps  DOR Log Level to a LOG4J system log level
	 * @param lvl the specified DOR Log Level.
	 * @return the corresponding LOG4J Log Level (Level object).
	 */
	protected static Level MaptoLog4jLevel(int lvl) {
		switch (lvl) {
		case DHSLogLevel.OFF:
			return Level.OFF;
		case DHSLogLevel.SEVERE:
			return Level.FATAL;
		case DHSLogLevel.WARNING:
			return Level.WARN;
		case DHSLogLevel.INFO:
			return Level.INFO;
		case DHSLogLevel.DEBUG: // map to FINER
			return Level.DEBUG;
		default:
			return Level.INFO; // default to INFO
		}
	}
    /*
     * Constructor with  parameters for logging
     */ 
	public DHSMessageObject(String mModule, int mLogLevel,
			String mClassName, String mMethodName, String mCustomMessage) {
		this.mModule = mModule;
		this.mLogLevel = mLogLevel;
		this.mClassName = mClassName;
		this.mMethodName = mMethodName;
		this.mCustomMessage = mCustomMessage;
	}
	
	/*
     * Constructor with  parameters for logging
     */ 
	public DHSMessageObject(String mModule, int mLogLevel,
			String mClassName, String mMethodName, String mCustomMessage, Throwable mThrowable) {
		this.mModule = mModule;
		this.mLogLevel = mLogLevel;
		this.mClassName = mClassName;
		this.mMethodName = mMethodName;
		this.mCustomMessage = mCustomMessage;
		this.mThrowable=mThrowable;
	}
	public String getMClassName() {
		return mClassName;
	}

	public String getMCustomMessage() {
		return mCustomMessage;
	}

	public int getMLogLevel() {
		return mLogLevel;
	}

	public String getMMethodName() {
		return mMethodName;
	}

	public String getMModule() {
		return mModule;
	}
	public Throwable getMThrowable() {
		return mThrowable;
	}

}
