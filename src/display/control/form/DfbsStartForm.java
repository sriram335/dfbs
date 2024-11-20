package display.control.form;

import display.to.*;
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
  
  public DfbsStartForm()
  {
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
}
