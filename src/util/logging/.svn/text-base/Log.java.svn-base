/**
 * <p>Title:ServerProperties</p>
 * <p>Description:This class will be inisitated on first call and then Individual calls Log methods to add logging to project.</p> 
 * <p>Company: DOR INDIANA STATE </p>
 * @author Bhargav Gogineni
 * @version 1.0
 * Mar 15, 2006
 * 
 */
package util.logging;


public class Log {

	private static DHSLogger mDORLogger;

	private final static String CLASS_NAME = "com.dor.util.logging.Log";
	static {
		try {
			mDORLogger = new DHSLogger();
		    System.out.println("In com.dor.util.logging.Log class*********************************");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void log(String mModuleName, int mLogLevel,
			String mClassName, String mMethodName, String mCustomMessage,
			Throwable t) {
		mDORLogger.log(mModuleName, mLogLevel, mClassName, mMethodName,
				mCustomMessage, t);
	}

	public static void log(String mModuleName, int mLogLevel,
			String mClassName, String mMethodName, String mCustomMessage) {
		mDORLogger.log(mModuleName, mLogLevel, mClassName, mMethodName,
				mCustomMessage);
	}
}
