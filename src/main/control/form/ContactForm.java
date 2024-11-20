package main.control.form;
import hs.control.form.*;

import org.apache.struts.action.*;
import hs.to.*;

public class ContactForm extends ActionForm
{
  private int id;
  private String firstname;
  private String lastname;
  private String title;
  private boolean active;

  public ContactForm() 
  {
    id = 0;
    firstname = null;
    lastname = null;
    title = null;
    active = false;
  }

  public ContactForm(HsContact contact)
  {
    id = contact.getId();
    firstname = contact.getFirstname();
    lastname = contact.getLastname();
    title = contact.getTitle();
    active = contact.isActive();
  }
  public HsContact getHsContact()
  {
    HsContact contact = new HsContact();
    contact.setId(id);
    contact.setFirstname(firstname);
    contact.setLastname(lastname);
    contact.setTitle(title);
    contact.setActive(active);
    return contact;
  }
  public void setProperties(HsContact contact)
  {
    id = contact.getId();
    firstname = contact.getFirstname();
    lastname = contact.getLastname();
    title = contact.getTitle();
    active = contact.isActive();
  }

  public String getFirstname()
  {
    return firstname;
  }

  public void setFirstname(String newFirstname)
  {
    firstname = newFirstname;
  }

  public String getLastname()
  {
    return lastname;
  }

  public void setLastname(String newLastname)
  {
    lastname = newLastname;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String newTitle)
  {
    title = newTitle;
  }
  public int getId()
  {
    return id;
  }

  public void setId(int newId)
  {
    id = newId;
  }

 

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean newActive)
  {
    active = newActive;
  }
  
}