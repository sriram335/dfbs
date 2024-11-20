package magazine.control.form;
import magazine.to.*;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;

public class DfbsStartForm extends ActionForm
{ private String streetNumber;
  private String permitNumber;
  private String idNumber;
  private String currentSql;
  private String portalReceiptId;
  private int ownerId;
  public DfbsStartForm()
  {
  }
  public int getOwnerId()
    { 
     return ownerId;
    }
  public void setOwnerId(int ownerId)
    { 
    this.ownerId = ownerId;
    }
  public String getPermitNumber()
  { 
   return permitNumber;
  }
public void setPermitNumber(String permitNumber)
  { 
  this.permitNumber =permitNumber;
  }
public String getStreetNumber()
  { 
   return streetNumber;
  }
public void setStreetNumber(String streetNumber)
  { 
  this.streetNumber = streetNumber;
  }
public String getIdNumber()
  { 
   return idNumber;
  }
public void setIdNumber(String idNumber)
  { 
  this.idNumber =idNumber;
  }
  public String getPortalReceiptId()
  { 
   return portalReceiptId;
  }
public void setPortalReceiptId(String portalReceiptId)
  { 
  this.portalReceiptId =portalReceiptId;
  }
}
