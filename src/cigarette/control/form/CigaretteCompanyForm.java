package cigarette.control.form;
import cigarette.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.control.form.*;
import main.to.*;

public class CigaretteCompanyForm extends CompleteContactForm
{ private int companyId;
  private String companyName;
  private String companyAddress1;
  private String companyAddress2;
  private String companyCity;
  private String companyState;
  private String companyZip;
  private String companyZip2;
  private String companyPhone;
  private String companyFax;
  private int receiptId;
  private double amount;
  private String checkoutId;
  private String paymentType;
  private String companyProvince;
  private String companyCountry;
  private String companyType;
   private int agentId;
  private String agentName;
  private String agentAddress1;
  private String agentAddress2;
  private String agentCity;
  private String agentState;
  private String agentZip;
  private String agentZip2;
  private String agentPhone;
  private String agentFax;
  private String agentEmail;
  private String agentEndDate;
  public CigaretteCompanyForm()
  {
  }
  
  public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }
    public String getPaymentType()
  { 
   return paymentType;
  }
public void setPaymentType(String paymentType)
  { 
  this.paymentType = paymentType;
  }
   public String getCheckoutId()
  { 
   return checkoutId;
  }
public void setCheckoutId(String checkoutId)
  { 
  this.checkoutId = checkoutId;
  }
  public String getCompanyAddress1()
  { 
   return companyAddress1;
  }
public void setCompanyAddress1(String companyAddress1)
  { 
  this.companyAddress1 = companyAddress1;
  }
public String getCompanyAddress2()
  { 
   return companyAddress2;
  }
public void setCompanyAddress2(String companyAddress2)
  { 
  this.companyAddress2 = companyAddress2;
  }
public String getCompanyCity()
  { 
   return companyCity;
  }
public void setCompanyCity(String companyCity)
  { 
  this.companyCity = companyCity;
  }

public String getCompanyFax()
  { 
   return companyFax;
  }
public void setCompanyFax(String companyFax)
  { 
  this.companyFax = companyFax;
  }
public String getCompanyName()
  { 
   return companyName;
  }
public void setCompanyName(String companyName)
  { 
  this.companyName = companyName;
  }
public String getCompanyPhone()
  { 
   return companyPhone;
  }
public void setCompanyPhone(String companyPhone)
  { 
  this.companyPhone = companyPhone;
  }
public String getCompanyState()
  { 
   return companyState;
  }
public void setCompanyState(String companyState)
  { 
  this.companyState = companyState;
  }

public String getCompanyZip()
  { 
   return companyZip;
  }
public void setCompanyZip(String companyZip)
  { 
  this.companyZip = companyZip;
  }
public String getCompanyZip2()
  { 
   return companyZip2;
  }
public void setCompanyZip2(String companyZip2)
  { 
  this.companyZip2 = companyZip2;
  }
public int getCompanyId()
  { 
   return companyId;
  }
public void setCompanyId(int companyId)
  { 
  this.companyId = companyId;
  }
 

   
     public int getReceiptId()
  {
    return receiptId;
  }

  public void setReceiptId(int receiptId)
  {
    this.receiptId = receiptId;
  }
 public CigaretteCompany getCigaretteCompany() throws Exception
  {
    
    CigaretteCompany company = new CigaretteCompany ();
    company.setCompanyAddress1(getCompanyAddress1());
    company.setCompanyAddress2(getCompanyAddress2());
    company.setCompanyCity(getCompanyCity());
    company.setCompanyFax(getCompanyFax());
    company.setCompanyId(getCompanyId());
    company.setCompanyName(getCompanyName());
    company.setCompanyPhone(getCompanyPhone());
    company.setCompanyState(getCompanyState());
    company.setCompanyZip(getCompanyZip());
    company.setCompanyZip2(getCompanyZip2());
    company.setCompanyProvince(getCompanyProvince());
    company.setCompanyCountry(getCompanyCountry());
 return company;
  }
   public CigaretteAgent getCigaretteAgent() throws Exception
  {
    
    CigaretteAgent agent = new CigaretteAgent ();
    agent.setAgentAddress1(getAgentAddress1());
    agent.setAgentAddress2(getAgentAddress2());
    agent.setAgentCity(getAgentCity());
    agent.setAgentFax(getAgentFax());
    agent.setAgentId(getAgentId());
    agent.setAgentName(getAgentName());
    agent.setAgentPhone(getAgentPhone());
    agent.setAgentState(getAgentState());
    agent.setAgentZip(getAgentZip());
    agent.setAgentZip2(getAgentZip2());
    agent.setAgentEmail(getAgentEmail());
 return agent;
  }
 public void setProperties(CigaretteCompany company) throws Exception
  { 
    this.setCompanyAddress1(company.getCompanyAddress1());
    this.setCompanyAddress2(company.getCompanyAddress2());
    this.setCompanyCity(company.getCompanyCity());
    this.setCompanyFax(company.getCompanyFax());
    this.setCompanyId(company.getCompanyId());
    this.setCompanyName(company.getCompanyName());
    this.setCompanyPhone(company.getCompanyPhone());
    this.setCompanyState(company.getCompanyState());
    this.setCompanyZip(company.getCompanyZip());
    this.setCompanyZip2(company.getCompanyZip2());
    this.setCompanyProvince(company.getCompanyProvince());
    this.setCompanyCountry(company.getCompanyCountry());
    
  }
   public void setAgentProperties(CigaretteAgent agent) throws Exception
  { 
    this.setAgentAddress1(agent.getAgentAddress1());
    this.setAgentAddress2(agent.getAgentAddress2());
    this.setAgentCity(agent.getAgentCity());
    this.setAgentFax(agent.getAgentFax());
    this.setAgentId(agent.getAgentId());
    this.setAgentName(agent.getAgentName());
    this.setAgentPhone(agent.getAgentPhone());
    this.setAgentState(agent.getAgentState());
    this.setAgentZip(agent.getAgentZip());
    this.setAgentZip2(agent.getAgentZip2());
    this.setAgentEmail(agent.getAgentEmail());
    
  }
  public void setUpdatedProperties(CigaretteCompany updatedCompany,CigaretteCompany company) throws Exception
  { 
    company.setCompanyAddress1(updatedCompany.getCompanyAddress1());
    company.setCompanyAddress2(updatedCompany.getCompanyAddress2());
    company.setCompanyCity(updatedCompany.getCompanyCity());
    company.setCompanyFax(updatedCompany.getCompanyFax());
    company.setCompanyId(updatedCompany.getCompanyId());
    company.setCompanyName(updatedCompany.getCompanyName());
    company.setCompanyPhone(updatedCompany.getCompanyPhone());
    company.setCompanyState(updatedCompany.getCompanyState());
    company.setCompanyZip(updatedCompany.getCompanyZip());
    company.setCompanyZip2(updatedCompany.getCompanyZip2());
    company.setCompanyProvince(updatedCompany.getCompanyProvince());
    company.setCompanyCountry(updatedCompany.getCompanyCountry());
  }
   public void setAgentUpdatedProperties(CigaretteAgent updatedAgent,CigaretteAgent company) throws Exception
  { 
    company.setAgentAddress1(updatedAgent.getAgentAddress1());
    company.setAgentAddress2(updatedAgent.getAgentAddress2());
    company.setAgentCity(updatedAgent.getAgentCity());
    company.setAgentFax(updatedAgent.getAgentFax());
    company.setAgentId(updatedAgent.getAgentId());
    company.setAgentName(updatedAgent.getAgentName());
    company.setAgentPhone(updatedAgent.getAgentPhone());
    company.setAgentState(updatedAgent.getAgentState());
    company.setAgentZip(updatedAgent.getAgentZip());
    company.setAgentZip2(updatedAgent.getAgentZip2());
    company.setAgentEmail(updatedAgent.getAgentEmail());
  }
   public String getCompanyCountry()
  { 
   return companyCountry;
  }
public void setCompanyCountry(String companyCountry)
  { 
  this.companyCountry = companyCountry;
  }
   public String getCompanyProvince()
  { 
   return companyProvince;
  }
public void setCompanyProvince(String companyProvince)
  { 
  this.companyProvince = companyProvince;
  }
   public String getCompanyType()
  { 
   return companyType;
  }
public void setCompanyType(String companyType)
  { 
  this.companyType = companyType;
  }
   public String getAgentAddress1()
  { 
   return agentAddress1;
  }
public void setAgentAddress1(String agentAddress1)
  { 
  this.agentAddress1 = agentAddress1;
  }
public String getAgentAddress2()
  { 
   return agentAddress2;
  }
public void setAgentAddress2(String agentAddress2)
  { 
  this.agentAddress2 = agentAddress2;
  }
public String getAgentCity()
  { 
   return agentCity;
  }
public void setAgentCity(String agentCity)
  { 
  this.agentCity = agentCity;
  }

public String getAgentFax()
  { 
   return agentFax;
  }
public void setAgentFax(String agentFax)
  { 
  this.agentFax = agentFax;
  }
public String getAgentName()
  { 
   return agentName;
  }
public void setAgentName(String agentName)
  { 
  this.agentName = agentName;
  }
public String getAgentPhone()
  { 
   return agentPhone;
  }
public void setAgentPhone(String agentPhone)
  { 
  this.agentPhone = agentPhone;
  }
public String getAgentState()
  { 
   return agentState;
  }
public void setAgentState(String agentState)
  { 
  this.agentState = agentState;
  }

public String getAgentZip()
  { 
   return agentZip;
  }
public void setAgentZip(String agentZip)
  { 
  this.agentZip = agentZip;
  }
public String getAgentZip2()
  { 
   return agentZip2;
  }
public void setAgentZip2(String agentZip2)
  { 
  this.agentZip2 = agentZip2;
  }
public int getAgentId()
  { 
   return agentId;
  }
public void setAgentId(int agentId)
  { 
  this.agentId = agentId;
  }
 
   public String getAgentEndDate()
  { 
   return agentEndDate;
  }
public void setAgentEndDate(String agentEndDate)
  { 
  this.agentEndDate = agentEndDate;
  }
  public String getAgentEmail()
  { 
   return agentEmail;
  }
public void setAgentEmail(String agentEmail)
  { 
  this.agentEmail = agentEmail;
  }
}
