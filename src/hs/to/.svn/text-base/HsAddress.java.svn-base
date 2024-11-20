package hs.to;

import java.io.Serializable;


public class HsAddress extends HsEntity implements Serializable {
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String county;

    public HsAddress() {
    }

    public HsAddress(String xstreet1, String xstreet2, String xcity,
                     String xstate, String xzip, String xcounty, String type) {
        street1 = xstreet1;
        street2 = xstreet2;
        city = xcity;
        state = xstate;
        zip = xzip;
        county = xcounty;
        this.setType(type);
    }


    public String getStreet1() {
        return (street1 == null) ? "" : street1;
    }

    public void setStreet1(String newStreet1) {
        street1 = newStreet1;
    }

    public String getStreet2() {
        return (street2 == null) ? "" : street2;
    }

    public void setStreet2(String newStreet2) {
        street2 = newStreet2;
    }

    public String getCity() {
        return (city == null) ? "" : city;
    }

    public void setCity(String newCity) {
        city = newCity;
    }

    public String getState() {
        return (state == null) ? "" : state;
    }

    public void setState(String newState) {
        state = newState;
    }

    public String getZip() {
        return (zip == null) ? "" : zip;
    }

    public void setZip(String newZip) {
        zip = newZip;
    }

    public String getCounty() {
        return (county == null) ? "" : county;
    }

    public void setCounty(String newCounty) {
        county = newCounty;
    }
}
