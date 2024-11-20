package hs.to;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class HsTransferObject {

    private String prefix;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private Map displayMap;

    public HsTransferObject() {
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedDateString() {
        return getDateString(getUpdatedDate(), "MM/dd/yy");
    }

    public String getCreatedDateString() {
        return getDateString(getCreatedDate(), "MM/dd/yy");
    }

    public static String getHTML(String str) {
        if (str == null)
            return "";

        return str.replaceAll("\n", "<br />");
    }


    public static String getDateString(Date date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        if (date == null) {
            return "";
        } else {
            return formatter.format(date);
        }
    }


    public static Date getDate(String dateParam, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(dateParam);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCurrencyString(double amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    public static String getDoubleString(double amount) {
        return Double.toString(amount);
    }

    public static String getPercentString(double amount) {
        return NumberFormat.getPercentInstance().format(amount);
    }


    public static double getDoubleValue(String newAmount) {
        try {
            return NumberFormat.getNumberInstance().parse(newAmount).doubleValue();
        } catch (Exception e) {
            try {
                return NumberFormat.getCurrencyInstance().parse(newAmount).doubleValue();
            } catch (Exception e2) {
                return 0;
            }
        }
    }

    public static int getIntValue(String newAmount) {
        try {
            return NumberFormat.getNumberInstance().parse(newAmount).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public Map getDisplayMap() {
        return displayMap;
    }

    public void setDisplayMap(Map displayMap) {
        this.displayMap = displayMap;
    }

    public void putDisplayMap(String key, String value) {
        if (displayMap == null) {
            displayMap = new HashMap();
        }
        displayMap.put(key, value);
    }

    public String getDisplayMap(String key) {
        return (displayMap == null) ? "" : (String)displayMap.get(key);
    }


    public String getPrefix() {
        return prefix;
    }
    
    public static String getString(String txt,String defaultTxt) {
        return (txt == null) ? defaultTxt : txt;
    }
    
}
