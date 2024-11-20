package idhsInspections.control.form;

import codeEducation.to.CodeEducationCourse;

import hs.control.form.*;
import hs.to.*;
import idhsInspections.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

public class InspectionSearchForm extends  ActionForm{
    private int streetNumber;
    private String streetName;
    private String facilityName;
    private String city;
    private String county;
    private String zip;
    private String idNumber;
    private String searchFor;
    private String searchActive;
    private String searchFacType;
    private String searchSequence;
    private String inspStartDate;
    private String inspEndDate;
    private int fileCount;
    private transient FormFile fileToUpload1 = null;//get & set methods
    private transient FormFile fileToUpload2 = null;//get & set methods
    private transient FormFile fileToUpload3 = null;//get & set methods
    private transient FormFile fileToUpload4 = null;//get & set methods
    private transient FormFile fileToUpload5 = null;//get & set methods
    private String xlName;//get & set methods
    private FileItem multFileUploadPath;
      public void setMultFileUploadPath(FileItem multFileUploadPath)
        { 
        this.multFileUploadPath = multFileUploadPath;
        }
      public FileItem getMultFileUploadPath()
        { 
         return multFileUploadPath;
        }
    public InspectionSearchForm() {
    }
    public String getSearchActive()
      { 
       return searchActive;
      }
    public void setSearchActive(String searchActive)
      { 
      this.searchActive = searchActive;
      }
    public String getCity()
      { 
       return city;
      }
    public void setCity(String city)
      { 
      this.city = city;
      }
    public String getCounty()
      { 
       return county;
      }
    public void setCounty(String county)
      { 
      this.county = county;
      }
    public String getIdNumber()
      { 
       return idNumber;
      }
    public void setIdNumber(String idNumber)
      { 
      this.idNumber = idNumber;
      }
    public String getSearchFor()
      { 
       return searchFor;
      }
    public void setSearchFor(String searchFor)
      { 
      this.searchFor = searchFor;
      }
    public String getStreetName()
      { 
       return streetName;
      }
    public void setStreetName(String streetName)
      { 
      this.streetName = streetName;
      }
    public String getFacilityName()
      { 
       return facilityName;
      }
    public void setFacilityName(String facilityName)
      { 
      this.facilityName = facilityName;
      }
    public String getZip()
      { 
       return zip;
      }
    public void setZip(String zip)
      { 
      this.zip = zip;
      }
    public int getStreetNumber()
      { 
       return streetNumber;
      }
    public void setStreetNumber(int streetNumber)
      { 
      this.streetNumber = streetNumber;
}
    public String getSearchSequence()
      { 
       return searchSequence;
      }
    public void setSearchSequence(String searchSequence)
      { 
      this.searchSequence = searchSequence;
      }
    public int getFileCount()
      { 
       return fileCount;
      }
    public void setFileCount(int  fileCount)
      { 
      this.fileCount =  fileCount;
      }
      
    public inspectionSearch  getInspectionSearch()
    {
    
    inspectionSearch search =new inspectionSearch();
        search.setCity(this.getCity());
        search.setCounty(this.getCounty());
        search.setIdNumber(this.getIdNumber());
        search.setSearchFor(this.getSearchFor());
        search.setSearchSequence(this.getSearchSequence());
        search.setStreetName(this.getStreetName());
        search.setFacilityName(this.getFacilityName());
        search.setZip(this.getZip());
        search.setStreetNumber(this.getStreetNumber());
        search.setFileCount(this.getFileCount()); 
        search.setSearchFacType(this.getSearchFacType());
    return search;
    }
    public void setProperties(inspectionSearch search) 
    {
        this.setCity(search.getCity());
        this.setCounty(search.getCounty());
        this.setIdNumber(search.getIdNumber());
        this.setSearchFor(search.getSearchFor());
        this.setSearchSequence(search.getSearchSequence());
        this.setStreetName(search.getStreetName());
        this.setFacilityName(search.getFacilityName());
        this.setZip(search.getZip());
        this.setStreetNumber(search.getStreetNumber());
        this.setFileCount(search.getFileCount());
        this.setSearchFacType(search.getSearchFacType());
    }
    public FormFile getFileToUpload1()
    {
    return fileToUpload1;
    }
    public void setFileToUpload1( FormFile fileToUpload1)
    {
    this.fileToUpload1 = fileToUpload1;
    }
    public FormFile getFileToUpload2()
    {
    return fileToUpload2;
    }
    public void setFileToUpload2( FormFile fileToUpload2)
    {
    this.fileToUpload2 = fileToUpload2;
    }
    public String getXlName()
    {
    return xlName;
    }
    public void setXlName(String xlName)
    {
    this.xlName = xlName;
    }
    public FormFile getFileToUpload3()
    {
    return fileToUpload3;
    }
    public void setFileToUpload3( FormFile fileToUpload3)
    {
    this.fileToUpload3 = fileToUpload3;
    }
    public FormFile getFileToUpload4()
    {
    return fileToUpload4;
    }
    public void setFileToUpload4( FormFile fileToUpload4)
    {
    this.fileToUpload4 = fileToUpload4;
    }
    public FormFile getFileToUpload5()
    {
    return fileToUpload5;
    }
    public void setFileToUpload5( FormFile fileToUpload5)
    {
    this.fileToUpload5 = fileToUpload5;
    }
    public String getSearchFacType()
      { 
       return searchFacType;
      }
    public void setSearchFacType(String searchFacType)
      { 
      this.searchFacType = searchFacType;
      }
  public String getInspStartDate()
  {
  return inspStartDate;
  }
  public void setInspStartDate(String inspStartDate)
  {
  this.inspStartDate = inspStartDate;
  }
  public String getInspEndDate()
  {
  return inspEndDate;
  }
  public void setInspEndDate(String inspEndDate)
  {
  this.inspEndDate = inspEndDate;
  }
}