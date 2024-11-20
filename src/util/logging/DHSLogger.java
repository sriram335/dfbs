package util.logging;

import org.apache.log4j.Logger;

/**
 * Title:Interface IDORLogger Description: This class finally writes to log file
 * defined for project.
 * 
 * @author Bhargav Gogineni
 * 
 */
public class DHSLogger implements IDHSLogger {

	private DHSMessageObject mErrorMessageObject;

	private final String mModuleName = "Module Name :";

	private final String mClassName = "Class Name : ";

	private final String mMethodName = "Method Name :";

	private final String mCustomErrorMessage = "User Message :";

	private final String mErrorObject = "Error :";

	/**
	 * This convenience method logs to log file
	 * 
	 * @param mMessageObject
	 */
	private void LogToFile(DHSMessageObject mMessageObject) {
		StringBuffer mMessageString = new StringBuffer();
		Logger mLoogger = Logger.getLogger(mMessageObject.getMModule());
		if (mMessageObject != null) {
			mMessageString.append(mModuleName).append(
					mMessageObject.getMModule()).append("\n")
					.append(mClassName).append(mMessageObject.getMClassName())
					.append("\n").append(mMethodName).append(
							mMessageObject.getMMethodName()).append("\n")
					.append(mCustomErrorMessage).append(
							mMessageObject.getMCustomMessage()).append("\n");
			if(mMessageObject.getMThrowable()!=null) {
				mMessageString.append(mErrorObject).append(mMessageObject.getMThrowable());	
			}
					
		}
		mLoogger.log(DHSMessageObject.MaptoLog4jLevel(mMessageObject
				.getMLogLevel()), mMessageString.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dor.util.logging.IDORLogger#log(java.lang.String, int,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.Throwable)
	 */
	public void log(String mModuleName, int mLevel, String mClassName,
			String mMethodName, String mCustomMessage, Throwable mThrowable) {
		// TODO Auto-generated method stub
		mErrorMessageObject = new DHSMessageObject(mModuleName, mLevel,
				mClassName, mMethodName, mCustomMessage, mThrowable);
		LogToFile(mErrorMessageObject);
		mErrorMessageObject=null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dor.util.logging.IDORLogger#log(java.lang.String, int,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public void log(String mModuleName, int mLevel, String mClassName,
			String mMethodName, String mCustomMessage) {
		// TODO Auto-generated method stub
		mErrorMessageObject = new DHSMessageObject(mModuleName, mLevel,
				mClassName, mMethodName, mCustomMessage);
		LogToFile(mErrorMessageObject);
		mErrorMessageObject=null;
	}

}
