package hs.to;

import java.io.Serializable;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class HsUser extends HsCompleteContact implements Serializable {
    private String userid;
    private String password;
    private boolean active;
    private Date loginDate;

    private Map tokens;


    private List roles;

    public HsUser() {
        userid = "";
        password = "";
        active = false;
        tokens = null;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String newUserid) {
        userid = newUserid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }


    public boolean isActive() {
        return active;
    }

    public String getActive() {
        return (active) ? "A" : "I";
    }

    public void setActive(boolean newActive) {
        active = newActive;
    }

    public void setActive(String newActive) {
        active = (newActive.equals("A")) ? true : false;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public String getLoginDateDisplay() {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT,
                                              DateFormat.SHORT).format(loginDate);
    }

    public void setLoginDate(Date newLoginDate) {
        loginDate = newLoginDate;
    }

    public boolean hasToken(String pageId) {
        return (tokens == null) ? false : tokens.containsKey(pageId);
    }

    public void addToken(String pageId) {
        if (tokens == null) {
            tokens = new HashMap();
        }
        tokens.put(pageId, "");
    }


    public Map getTokens() {
        return tokens;
    }

    public void setTokens(Map tokens) {
        this.tokens = tokens;
    }

    public int getContactId() {
        return this.getId();
    }

    public void setContactId(int id) {
        this.setId(id);
    }


    public int rolesSize() {
        return (roles == null) ? 0 : roles.size();
    }

    public void setRoles(String[] array) throws Exception {
        List list = new ArrayList();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                list.add(array[i]);
            }
        }
        this.roles = list;
    }

    public String[] getRolesArray() {

        if (roles == null)
            return null;

        int size = roles.size();

        String[] array = new String[size];

        Iterator i = roles.iterator();
        int j = 0;
        while (i.hasNext()) {

            array[j++] = (String)i.next();
        }
        return array;

    }

    public List getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }


    public boolean isOrg() {
        return (this.getOrgId() == 0) ? false : true;
    }

   

}
