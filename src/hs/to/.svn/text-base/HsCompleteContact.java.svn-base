package hs.to;

import java.io.Serializable;


public class HsCompleteContact extends HsContact implements Serializable {
    private int addressId;
    private String addressType;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String county;

    private int phoneId;
    private String phoneType;
    private String phoneNumber;

    private int phone2Id;
    private String phone2Type;
    private String phone2Number;

    private int phone3Id;
    private String phone3Type;
    private String phone3Number;

    private int faxId;
    private String faxType;
    private String faxNumber;

    private int emailId;
    private String emailType;
    private String emailAddress;

    private String orgName;

    public HsCompleteContact() {

    }


    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int newAddressId) {
        addressId = newAddressId;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String newAddressType) {
        addressType = newAddressType;
    }

    public String getCity() {
        return (city == null) ? "" : city;
    }

    public void setCity(String newCity) {
        city = newCity;
    }

    public String getEmailAddress() {
        return (emailAddress == null) ? "" : emailAddress;
    }

    public void setEmailAddress(String newEmailAddress) {
        emailAddress = newEmailAddress;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int newEmailId) {
        emailId = newEmailId;
    }

    public String getEmailType() {
        return (emailType == null) ? "" : emailType;
    }

    public void setEmailType(String newEmailType) {
        emailType = newEmailType;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int newPhoneId) {
        phoneId = newPhoneId;
    }

    public String getPhoneNumber() {
        return (phoneNumber == null) ? "" : phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }

    public String getPhoneType() {
        return (phoneType == null) ? "" : phoneType;
    }

    public void setPhoneType(String newPhoneType) {
        phoneType = newPhoneType;
    }

    public String getState() {
        return (state == null) ? "" : state;
    }

    public void setState(String newState) {
        state = newState;
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

    public String getZip() {
        return (zip == null) ? "" : zip;
    }

    public void setZip(String newZip) {
        zip = newZip;
    }

    public int getFaxId() {
        return faxId;
    }

    public void setFaxId(int newFaxId) {
        faxId = newFaxId;
    }

    public String getFaxNumber() {
        return (faxNumber == null) ? "" : faxNumber;
    }

    public void setFaxNumber(String newFaxNumber) {
        faxNumber = newFaxNumber;
    }

    public String getFaxType() {
        return faxType;
    }

    public void setFaxType(String newFaxType) {
        faxType = newFaxType;
    }

    public void setAddress(HsAddress address) {
        if (address == null)
            return;
        addressId = address.getId();
        addressType = address.getType();
        street1 = address.getStreet1();
        street2 = address.getStreet2();
        city = address.getCity();
        state = address.getState();
        zip = address.getZip();
        county = address.getCounty();
    }

    public void setPhone(HsPhone phone) {
        if (phone == null)
            return;
        phoneId = phone.getId();
        phoneType = phone.getType();
        phoneNumber = phone.getNumber();
    }

    public void setFax(HsPhone phone) {
        if (phone == null)
            return;
        faxId = phone.getId();
        faxType = phone.getType();
        faxNumber = phone.getNumber();
    }

    public void setEmail(HsEmail email) {
        if (email == null)
            return;
        emailId = email.getId();
        emailType = email.getType();
        emailAddress = email.getEmail();
    }

    public String getCounty() {
        return (county == null) ? "" : county;
    }

    public void setCounty(String newCounty) {
        county = newCounty;
    }


    public String getOrgName() {
        return (orgName == null) ? "" : orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getPhone2Id() {
        return phone2Id;
    }

    public void setPhone2Id(int phone2Id) {
        this.phone2Id = phone2Id;
    }

    public String getPhone2Number() {
        return (phone2Number == null) ? "" : phone2Number;
    }

    public void setPhone2Number(String phone2Number) {
        this.phone2Number = phone2Number;
    }

    public String getPhone2Type() {
        return phone2Type;
    }

    public void setPhone2Type(String phone2Type) {
        this.phone2Type = phone2Type;
    }

    public int getPhone3Id() {
        return phone3Id;
    }

    public void setPhone3Id(int phone3Id) {
        this.phone3Id = phone3Id;
    }

    public String getPhone3Number() {
        return (phone3Type == null) ? "" : phone3Type;
    }

    public void setPhone3Number(String phone3Number) {
        this.phone3Number = phone3Number;
    }

    public String getPhone3Type() {
        return (phone3Type == null) ? "" : phone3Type;
    }

    public void setPhone3Type(String phone3Type) {
        this.phone3Type = phone3Type;
    }


}
