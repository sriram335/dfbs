package main.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;




public class FileNames   implements Serializable
{ private int fileId;
  private String fileName;
  private String fileType;
  private Date fileDate;
  private String fileLoadedBy;
  private String fileConnector;
  private String filePlanType;
  public FileNames()
  {
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
    public Date getFileDate()
  { 
   return fileDate;
  }
public void setFileDate(Date fileDate)
  { 
  this.fileDate = fileDate;
  }
  public void setFileDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  fileDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  fileDate = null;
  }
}
public String getFileDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(fileDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(fileDate);
  }
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