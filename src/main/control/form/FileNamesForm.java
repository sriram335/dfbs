package main.control.form;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.to.*;
import org.apache.struts.upload.FormFile;

public class FileNamesForm extends ActionForm{
    private int fileId;
  private String fileName;
  private String fileType;
  private String fileDate;
  private String fileLoadedBy;
  private String fileConnector;
  private String filePlanType;
    
    public FileNamesForm() {
    }
     public String getFileName()
  { 
   return fileName;
  }
public void setFileName(String fileName)
  { 
  this.fileName = fileName;
  }
   public int getFileId()
  { 
   return fileId;
  }
public void setFileId(int fileId)
  { 
  this.fileId = fileId;
  }
   public String getFileLoadedBy()
  { 
   return fileLoadedBy;
  }
public void setFileLoadedBy(String fileLoadedBy)
  { 
  this.fileLoadedBy = fileLoadedBy;
  }
   public String getFileType()
  { 
   return fileType;
  }
public void setFileType(String fileType)
  { 
  this.fileType = fileType;
  }
    public String getFileDate()
  { 
   return fileDate;
  }
public void setFileDate(String fileDate)
  { 
  this.fileDate = fileDate;
  }
 
 public String getFilePlanType()
  { 
   return filePlanType;
  }
public void setFilePlanType(String filePlanType)
  { 
  this.filePlanType = filePlanType;
  }
 public String getFileConnector()
  { 
   return fileConnector;
  }
public void setFileConnector(String fileConnector)
  { 
  this.fileConnector = fileConnector;
  }
}
