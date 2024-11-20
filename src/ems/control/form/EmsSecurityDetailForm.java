package ems.control.form;
import ems.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;

public class EmsSecurityDetailForm extends ActionForm
{ private String userId;
  private String providerType;
  private int providerId;
  private int detailId;
  public EmsSecurityDetailForm()
  {
  }
  public String getUserId()
  { 
   return userId;
  }
public void setUserId(String userId)
  { 
  this.userId = userId;
  }
  public int getDetailId()
  { 
   return detailId;
  }
public void setDetailId(int detailId)
  { 
  this.detailId = detailId;
  }
  public String getProviderType()
  { 
   return providerType;
  }
public void setProviderType(String providerType)
  { 
  this.providerType = providerType;
  }
  public int getProviderId()
  { 
   return providerId;
  }
public void setProviderId(int providerId)
  { 
  this.providerId = providerId;
}
public void setProperties(EmsSecurityDetail securityDetail) 
  {System.out.println("22");
  
  setUserId(securityDetail.getUserId());
  setProviderId(securityDetail.getProviderId());
  setDetailId(securityDetail.getDetailId());
  setProviderType(securityDetail.getProviderType());
  }
   public EmsSecurityDetail getEmsSecurityDetail() 
  {
    EmsSecurityDetail securityDetail = new EmsSecurityDetail();
   
  securityDetail.setUserId(getUserId());
  securityDetail.setProviderId(getProviderId());
  securityDetail.setDetailId(getDetailId());
  securityDetail.setProviderType(getProviderType());
  return securityDetail;
  }
  
}