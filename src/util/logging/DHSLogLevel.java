package util.logging;

public class DHSLogLevel {
	
	/** OFF is a special level that can be used to turn off logging. */
    public static final int OFF = 4;
    /** SEVERE is a message level indicating a serious failure. */
    public static final int SEVERE = 3;
    /** WARNING is a message level indicating a potential problem. */
    public static final int WARNING = 2;
    /** INFO indicates a informational message. */
    public static final int INFO = 1;
    /** DEBUG a message level for debugging or tracing. */
    public static final int DEBUG = 0;
    /** ALL indicates that all messages should be logged. */
    public static final int ALL = -1;
    
    /**
     * Get the name of the specified level.
     * @param level the specified MA log level.
     * @return the name.
     */
    public static String getName(int level) {
        switch (level) {
            case OFF:
                return "OFF";
            case SEVERE:
                return "SEVERE";
            case WARNING:
                return "WARNING";
            case INFO:
                return "INFO";
            case DEBUG:
                return "DEBUG";
            case ALL:
                return "ALL";
            default:
                return "UNKNOWN";
        }
    }
}
