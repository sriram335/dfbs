package main.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ApplicationContacts   implements Serializable
{ private String contact1Name;
  private String contact1Email;
  private String contact1Phone;
  private String contact2Name;
  private String contact2Email;
  private String contact2Phone;
  private String contact1IP;
  private String contact2IP;
  public ApplicationContacts()
  {
  }
  public String getContact1Email()
  { 
   return contact1Email;
  }
public void setContact1Email(String contact1Email)
  { 
  this.contact1Email = contact1Email;
  }
public String getContact1Name()
  { 
   return contact1Name;
  }
public void setContact1Name(String contact1Name)
  { 
  this.contact1Name = contact1Name;
  }
public String getContact1Phone()
  { 
   return contact1Phone;
  }
public void setContact1Phone(String contact1Phone)
  { 
  this.contact1Phone = contact1Phone;
  }
public String getContact2Email()
  { 
   return contact2Email;
  }
public void setContact2Email(String contact2Email)
  { 
  this.contact2Email = contact2Email;
  }
public String getContact2Name()
  { 
   return contact2Name;
  }
public void setContact2Name(String contact2Name)
  { 
  this.contact2Name = contact2Name;
  }
public String getContact2Phone()
  { 
   return contact2Phone;
  }
public void setContact2Phone(String contact2Phone)
  { 
  this.contact2Phone = contact2Phone;
  }
   public String getContact1IP()
  { 
   return contact1IP;
  }
public void setContact1IP(String contact1IP)
  { 
  this.contact1IP = contact1IP;
  }
   public String getContact2IP()
  { 
   return contact2IP;
  }
public void setContact2IP(String contact2IP)
  { 
  this.contact2IP = contact2IP;
  }
}