package hs.util;

public class HsStringUtil {
    public static String getPaddedString(String value, int length,
                                         boolean isRightAlign) throws Exception {
        int padLength = 0;
        if (value == null) {
            padLength = length;
            value = "";
        } else {
            padLength = length - value.length();
        }
        if (padLength < 0) {
            throw new Exception("ERROR: String value exceeds max length!");
        }

        StringBuffer sb = new StringBuffer();
        if (isRightAlign) {
            sb.append(getPaddingString(padLength));
            sb.append(value);
        } else {
            sb.append(value);
            sb.append(getPaddingString(padLength));
        }


        return sb.toString();
    }

    public static String getPaddingString(int length) {
        char[] pad = new char[length];
        for (int i = 0; i < length; i++) {
            pad[i] = ' ';
        }
        return new String(pad);
    }
}
