package hs.to;

import java.io.Serializable;

import java.util.List;


public class HsContact extends HsEntity implements Serializable {

    private String firstname;
    private String lastname;
    private String title;
    private int staffId;
    private int orgId;

    private HsOrg agency;
    private HsOrg division;
    private HsOrg section;
    private int deptNo;
    private String deptId;

    private List emails;
    private List addresses;
    private List URLs;
    private List phones;


    public HsContact() {

    }

    public HsContact(String xfirstname, String xlastname, String xtype) {
        this.setFirstname(xfirstname);
        this.setLastname(xlastname);
        this.setType(xtype);
    }

    public String getFirstname() {
        return (firstname == null) ? "" : firstname;
    }

    public void setFirstname(String newFirstname) {
        firstname = newFirstname;
    }

    public String getLastname() {
        return (lastname == null) ? "" : lastname;
    }

    public void setLastname(String newLastname) {
        lastname = newLastname;
    }

    public String getTitle() {
        return (title == null) ? "" : title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }


    public String getFirstnameLastname() {
        StringBuffer sb = new StringBuffer();
        if (firstname != null) {
            sb.append(firstname);
        }
        if (lastname != null) {
            sb.append(" ").append(lastname);
        }

        return sb.toString();
    }

    public String getLastnameFirstname() {
        StringBuffer sb = new StringBuffer();
        if (lastname != null) {
            sb.append(lastname);
        }
        if (firstname != null) {
            sb.append(", ").append(firstname);
        }

        return sb.toString();


    }


    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public HsOrg getAgency() {
        return agency;
    }

    public void setAgency(HsOrg agency) {
        this.agency = agency;
    }

    public HsOrg getDivision() {
        return division;
    }

    public void setDivision(HsOrg division) {
        this.division = division;
    }

    public HsOrg getSection() {
        return section;
    }

    public void setSection(HsOrg section) {
        this.section = section;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
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


    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return deptId;
    }
}
