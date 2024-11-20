package main.control.form;
import hs.control.form.*;

import hs.to.*;

public class CompleteContactForm extends ContactForm
{
  private int addressId;
  private String street1;
  private String street2;
  private String city;
  private String state;
  private String zip;
   

  private int phoneId;
  private String phoneNumber;

  private int faxId;
  private String faxNumber;

  private int emailId;
  private String emailAddress;
  private String county;
  private int parentId;

  private String orgName;

  public void setProperties(HsCompleteContact cc)
  {
    
    super.setProperties(cc);
    
    addressId = cc.getAddressId();
    street1 = cc.getStreet1();
    street2 = cc.getStreet2();;
    city = cc.getCity();;
    state = cc.getState();
    zip = cc.getZip();
    county = cc.getCounty();
    orgName = cc.getOrgName();

    
    phoneId = cc.getPhoneId();
    phoneNumber =cc.getPhoneNumber();

    faxId = cc.getFaxId();
    faxNumber = cc.getFaxNumber();

    emailId = cc.getEmailId();
    emailAddress = cc.getEmailAddress();
    
  }
  public HsCompleteContact getHsCompleteContact() 
  {
    HsCompleteContact cc = new HsCompleteContact();

    cc.setId(this.getId());
    cc.setFirstname(this.getFirstname());
    cc.setLastname(this.getLastname());
    cc.setTitle(this.getTitle());
    
  
    cc.setAddressId(addressId);
    cc.setStreet1(street1);
    cc.setStreet2(street2);
    cc.setCity(city);
    cc.setState(state);
    cc.setZip(zip);
    cc.setCounty(county);

    cc.setPhoneId(phoneId);
    cc.setPhoneNumber(phoneNumber);

    cc.setFaxId(faxId);
    cc.setFaxNumber(faxNumber);

    cc.setEmailId(emailId);
    cc.setEmailAddress(emailAddress);
    cc.setOrgName(orgName);
    cc.setActive(this.isActive());  
    return cc;

  }
 
  public HsContact getHsContact() 
  {
    HsContact cc = new HsContact();

    cc.setId(this.getId());
    cc.setFirstname(this.getFirstname());
    cc.setLastname(this.getLastname());
    cc.setTitle(this.getTitle());
    
    return cc;

  }
   public void setProperties(HsAddress address) 
  {    
    this.setStreet1(address.getStreet1());
    this.setStreet2(address.getStreet2());
    this.setCity(address.getCity());
    this.setState(address.getState());
    this.setZip(address.getZip());
    this.setCounty(address.getCounty());
  }
  
   public HsAddress getHsAddress() 
  {
    HsAddress cc = new HsAddress();
    cc.setId(addressId);
    cc.setStreet1(street1);
    cc.setStreet2(street2);
    cc.setCity(city);
    cc.setState(state);
    cc.setZip(zip);
    cc.setStatus("Y");
    cc.setType("ADDRESS00");
    cc.setCounty(county);
    cc.setId(this.getId());

    return cc;
  }
  public HsPhone getHsPhone() 
  {
    HsPhone cc = new HsPhone();
    cc.setId(phoneId);
    cc.setNumber(phoneNumber);
    cc.setStatus("Y");
    cc.setType("PHONE00");
    cc.setId(this.getId());
    return cc;
  }
    
  public HsPhone getHsFax() 
  {
    HsPhone cc = new HsPhone();

    cc.setId(faxId);
    cc.setNumber(faxNumber);
    cc.setStatus("Y");
    cc.setType("FAX00");
    cc.setId(this.getId());
    return cc;
  }
  public HsEmail getHsEmail() 
  {
    HsEmail cc = new HsEmail();
    cc.setId(emailId);
    cc.setStatus("Y");
    cc.setType("EMAIL00");
    cc.setId(this.getId());
    return cc;
  }


  public int getAddressId()
  {
    return addressId;
  }

  public void setAddressId(int newAddressId)
  {
    addressId = newAddressId;
  }

  
  

  public String getCity()
  {
    return city;
  }

  public void setCity(String newCity)
  {
    city = newCity;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public void setEmailAddress(String newEmailAddress)
  {
    emailAddress = newEmailAddress;
  }

  public int getEmailId()
  {
    return emailId;
  }

  public void setEmailId(int newEmailId)
  {
    emailId = newEmailId;
  }

 

  public int getPhoneId()
  {
    return phoneId;
  }

  public void setPhoneId(int newPhoneId)
  {
    phoneId = newPhoneId;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String newPhoneNumber)
  {
    phoneNumber = newPhoneNumber;
  }

 

  public String getState()
  {
    return state;
  }

  public void setState(String newState)
  {
    state = newState;
  }

  public String getStreet1()
  {
    return street1;
  }

  public void setStreet1(String newStreet1)
  {
    street1 = newStreet1;
  }

  public String getStreet2()
  {
    return street2;
  }

  public void setStreet2(String newStreet2)
  {
    street2 = newStreet2;
  }

  public String getZip()
  {
    return zip;
  }

  public void setZip(String newZip)
  {
    zip = newZip;
  }

  public int getFaxId()
  {
    return faxId;
  }

  public void setFaxId(int newFaxId)
  {
    faxId = newFaxId;
  }

  public String getFaxNumber()
  {
    return faxNumber;
  }

  public void setFaxNumber(String newFaxNumber)
  {
    faxNumber = newFaxNumber;
  }

  public String getCounty()
  {
    return county;
  }

  public void setCounty(String newCounty)
  {
    county = newCounty;
  }

  public int getParentId()
  {
    return parentId;
  }

  public void setParentId(int newParentId)
  {
    parentId = newParentId;
  }

  public String getOrgName()
  {
    return orgName;
  }

  public void setOrgName(String orgName)
  {
    this.orgName = orgName;
  }

 
  
  
}