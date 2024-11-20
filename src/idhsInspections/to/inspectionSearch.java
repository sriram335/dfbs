package idhsInspections.to;
import hs.to.*;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.text.*;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
public class inspectionSearch implements Serializable {

  private int streetNumber;
  private String streetName;
  private String city;
  private String county;
  private String zip;
  private String idNumber;
  private String searchFor;
  private String searchActive;
  private String searchFacType;
  private Map searchResultsMap;
  private List searchResultsList;
  private String searchSequence;
  private String facilityName;
  private FileItem multFileUploadPath;
     private int elevCount;
     private int planCount;
     private int otherCount;
     private int bpvCount;
     private int aeCount;
     private int schoolCount;
     private int daycareCount;
     private int hospitalCount;
     private int ustCount;
     private int complaintCount;
     private int fireworksRetailCount;
     private int fireworksWholesaleCount;
    private int indMobileCount;
     private int fileCount;
   private List elevList ;
   private List bpvList ;
   private List aeList ;
   private List dayCareList ;
   private List schoolList ;
   private List planList ;
   private List fireworksRetailList ;
   private List fireworksWholesaleList ;
   private List ustList ;
   private List newInspList ;
   private List otherList ;
   private List hospitalList;
   private List alertList;
   private List indMobileList;
     public inspectionSearch() {
     }
     public void setMultFileUploadPath(FileItem multFileUploadPath)
       { 
       this.multFileUploadPath = multFileUploadPath;
       }
     public FileItem getMultFileUploadPath()
       { 
        return multFileUploadPath;
       }
     public void setFacilityName(String facilityName)
       { 
       this.facilityName = facilityName;
       }
     public String getFacilityName()
       { 
        return facilityName;
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
     public String getSearchActive()
       { 
        return searchActive;
       }
     public void setSearchActive(String searchActive)
       { 
       this.searchActive = searchActive;
       }
     public String getSearchFacType()
       { 
        return searchFacType;
       }
     public void setSearchFacType(String searchFacType)
       { 
       this.searchFacType = searchFacType;
       }
     public String getStreetName()
       { 
        return streetName;
       }
     public void setStreetName(String streetName)
       { 
       this.streetName = streetName;
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
     public int getSearchResultsListCount () 
     {
       return (searchResultsList == null) ? 0 : searchResultsList.size();
     }

     public List getSearchResultsList()
     {
       if(searchResultsList == null) 
       {
         searchResultsList = new ArrayList();
       } 
       return searchResultsList;
     }

     public void setSearchResultsList(List searchResultsList)
     {
       this.searchResultsList = searchResultsList;
     }
     public Map getSearchResultsMap()
       {
         if(searchResultsMap == null) 
         {
           searchResultsMap = new HashMap();
         }
         return searchResultsMap;
       }

       public void setSearchResultsMap(Map searchResultsMap)
       {
         this.searchResultsMap = searchResultsMap;
       }
       public int getSearchResultsMapCount()
       {
         return (this.searchResultsMap == null) ? 0 : searchResultsMap.size();
       }

       public void addResult(searchResults result) throws Exception
       {
         if(result == null) return;
         
         
          StringBuffer sb = new StringBuffer(this.getSearchSequence());
           sb.append(getSearchResultsMapCount() + 1);
           Map map = this.getSearchResultsMap();
           result.setSearchSequence(sb.toString());
           map.put(sb.toString(),result);
          
       }
       public searchResults  getResult(String key)
       {
         
         if(key == null || searchResultsMap == null ) {
            
           return new searchResults();
         }
         searchResults result = (searchResults) searchResultsMap.get(key);
         return (result == null) ? new searchResults() : result;
       }
       
       
       public void removeResult(String key) throws Exception
       {
         if(key == null || searchResultsMap == null ) return;
         
         searchResults result = (searchResults) searchResultsMap.get(key);
         searchResultsMap.remove(key);
       }
       
     public String getSearchSequence()
       { 
        return searchSequence;
       }
     public void setSearchSequence(String searchSequence)
       { 
       this.searchSequence = searchSequence;
       }
     public int getBpvCount()
       { 
        return bpvCount;
       }
     public void setBpvCount(int bpvCount)
       { 
       this.bpvCount = bpvCount;
       }
     public int getDaycareCount()
       { 
        return daycareCount;
       }
     public void setDaycareCount(int daycareCount)
       { 
       this.daycareCount = daycareCount;
       }
     public int getElevCount()
       { 
        return elevCount;
       }
     public void setElevCount(int elevCount)
       { 
       this.elevCount = elevCount;
       }
     public int getComplaintCount()
       { 
        return complaintCount;
       }
     public void setComplaintCount(int complaintCount)
       { 
       this.complaintCount = complaintCount;
       }
     public int getHospitalCount()
       { 
        return hospitalCount;
       }
     public void setHospitalCount(int hospitalCount)
       { 
       this.hospitalCount = hospitalCount;
       }
     public int getOtherCount()
       { 
        return otherCount;
       }
     public void setOtherCount(int otherCount)
       { 
       this.otherCount = otherCount;
       }
     public int getPlanCount()
       { 
        return planCount;
       }
     public void setPlanCount(int planCount)
       { 
       this.planCount = planCount;
       }
     public int getSchoolCount()
       { 
        return schoolCount;
       }
     public void setSchoolCount(int schoolCount)
       { 
       this.schoolCount = schoolCount;
       }
     public int getAeCount()
       { 
        return aeCount;
       }
     public void setAeCount(int aeCount)
       { 
       this.aeCount = aeCount;
     }
     public int getUstCount()
       { 
        return ustCount;
       }
     public void setUstCount(int ustCount)
       { 
       this.ustCount = ustCount;
     }
     public int getFireworksRetailCount()
       { 
        return fireworksRetailCount;
       }
     public void setFireworksRetailCount(int fireworksRetailCount)
       { 
       this.fireworksRetailCount = fireworksRetailCount;
     }
     public int getFireworksWholesaleCount()
       { 
        return fireworksWholesaleCount;
       }
     public void setFireworksWholesaleCount(int fireworksWholesaleCount)
       { 
       this.fireworksWholesaleCount = fireworksWholesaleCount;
     }
     public int getFileCount()
       { 
        return fileCount;
       }
     public void setFileCount(int  fileCount)
       { 
       this.fileCount =  fileCount;
       }
     public List getAeList()
       { 
           if(aeList == null) 
           {
            aeList = new ArrayList();
           } 
        return aeList;
       }
     public void setAeList(List aeList)
       { 
       this.aeList = aeList;
       }
     public List getBpvList()
       { 
           if(bpvList == null) 
           {
             bpvList = new ArrayList();
           } 
        return bpvList;
       }
     public void setBpvList(List bpvList)
       { 
       this.bpvList = bpvList;
       }
     public List getDayCareList()
       { 
           if(dayCareList == null) 
           {
             dayCareList = new ArrayList();
           } 
        return dayCareList;
       }
     public void setDayCareList(List dayCareList)
       { 
       this.dayCareList = dayCareList;
       }
     public List getElevList()
       { 
           if(elevList == null) 
           {
             elevList = new ArrayList();
           } 
        return elevList;
       }
     public void setElevList(List elevList)
       { 
       this.elevList = elevList;
       }
     public List getFireworksRetailList()
       { 
           if(fireworksRetailList == null) 
           {
             fireworksRetailList = new ArrayList();
           } 
        return fireworksRetailList;
       }
     public void setFireworksRetailList(List fireworksRetailList)
       { 
       this.fireworksRetailList = fireworksRetailList;
       }
     public List getFireworksWholesaleList()
       { 
           if(fireworksWholesaleList == null) 
           {
             fireworksWholesaleList = new ArrayList();
           } 
        return fireworksWholesaleList;
       }
     public void setFireworksWholesaleList(List fireworksWholesaleList)
       { 
       this.fireworksWholesaleList = fireworksWholesaleList;
       }
     public List getHospitalList()
       { 
           if(hospitalList == null) 
           {
             hospitalList = new ArrayList();
           } 
        return hospitalList;
       }
     public void setHospitalList(List hospitalList)
       { 
       this.hospitalList = hospitalList;
       }
     public List getNewInspList()
       { 
           if(newInspList == null) 
           {
             newInspList = new ArrayList();
           } 
        return newInspList;
       }
     public void setNewInspList(List newInspList)
       { 
       this.newInspList = newInspList;
       }
     public List getOtherList()
       { 
           if(otherList == null) 
           {
             otherList = new ArrayList();
           } 
        return otherList;
       }
     public void setOtherList(List otherList)
       { 
       this.otherList = otherList;
       }
     public List getPlanList()
       { 
           if(planList == null) 
           {
             planList = new ArrayList();
           } 
        return planList;
       }
     public void setPlanList(List planList)
       { 
       this.planList = planList;
       }
     public List getSchoolList()
       { 
           if(schoolList == null) 
           {
             schoolList = new ArrayList();
           } 
        return schoolList;
       }
     public void setSchoolList(List schoolList)
       { 
       this.schoolList = schoolList;
       }
     public List getUstList()
       { 
           if(ustList == null) 
           {
             ustList = new ArrayList();
           } 
        return ustList;
       }
     public void setUstList(List ustList)
       { 
       this.ustList = ustList;
       }
       
     public List getAlertList()
       { 
           if(alertList == null) 
           {
             alertList = new ArrayList();
           } 
        return alertList;
       }
     public void setAlertList(List alertList)
       { 
       this.alertList = alertList;
       }
    public List getIndMobileList()
      { 
          if(indMobileList == null) 
          {
            indMobileList = new ArrayList();
          } 
       return indMobileList;
      }
    public void setIndMobileList(List indMobileList)
      { 
      this.indMobileList = indMobileList;
      }
    public int getIndMobileCount()
      { 
       return indMobileCount;
      }
    public void setIndMobileCount(int indMobileCount)
      { 
      this.indMobileCount = indMobileCount;
      }
  }
