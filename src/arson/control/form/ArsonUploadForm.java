package arson.control.form;

import arson.to.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
public class ArsonUploadForm extends ActionForm
{ 
  private transient FormFile caseFile = null;//get & set methods
  private String xlName;//get & set methods
  private String idNumber;
  private String idType;

  public ArsonUploadForm()
  {
  }
    public FormFile getCaseFile()
  { 
   return caseFile;
  }
public void setCaseFile( FormFile caseFile)
  { 
  this.caseFile = caseFile;
  } 
   public String getXlName()
  { 
   return xlName;
  }
public void setXlName(String xlName)
  { 
  this.xlName = xlName;
  } 
   public String getIdNumber()
  { 
   return idNumber;
  }
public void setIdNumber(String idNumber)
  { 
  this.idNumber = idNumber;
  }
   public String getIdType()
  { 
   return idType;
  }
public void setIdType(String idType)
  { 
  this.idType = idType;
  }
}

