package hs.to;

import java.io.Serializable;

import java.util.List;


public class HsOrg extends HsEntity implements Serializable {

    private String name;

    private List emails;
    private List addresses;
    private List URLs;
    private List phones;
    private List contacts;

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public List getURLs() {
        return URLs;
    }

    public void setURLs(List URLs) {
        this.URLs = URLs;
    }

    public List getAddresses() {
        return addresses;
    }

    public void setAddresses(List addresses) {
        this.addresses = addresses;
    }

    public List getPhones() {
        return phones;
    }

    public void setPhones(List phones) {
        this.phones = phones;
    }

    public List getEmails() {
        return emails;
    }

    public void setEmails(List emails) {
        this.emails = emails;
    }

    public List getContacts() {
        return contacts;
    }

    public void setContacts(List contacts) {
        this.contacts = contacts;
    }


}
