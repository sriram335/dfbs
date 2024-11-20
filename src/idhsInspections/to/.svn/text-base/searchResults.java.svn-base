package idhsInspections.to;
import hs.to.*;
import java.io.Serializable;
import java.util.*;
import java.text.*;


public class searchResults implements Serializable{
    private String resultId;
    private String resultAddress1;
    private String resultAddress2;
    private String resultCity;
    private String resultCounty;
    private String resultZip;
    private String resultType;
    private String searchSequence;
    private Date permitIssueDate;
    private Date permitExpDate;
    private String facilityName;
    private List resultFileList;
    
    public searchResults() {
    }
    public List getResultFileList()
      {
        if(resultFileList == null) 
        {
          resultFileList = new ArrayList();
        } 
        return resultFileList;
      }

      public void setResultFileList(List resultFileList)
      {
        this.resultFileList = resultFileList;
      }
    public void setFacilityName(String facilityName)
      { 
      this.facilityName = facilityName;
      }
    public String getFacilityName()
      { 
       return facilityName;
      }
    public String getResultZip()
      { 
       return resultZip;
      }
    public void setResultZip(String resultZip)
      { 
      this.resultZip = resultZip;
      }
    public String getResultAddress1()
      { 
       return resultAddress1;
      }
    public void setResultAddress1(String resultAddress1)
      { 
      this.resultAddress1 = resultAddress1;
      }
    public String getResultAddress2()
      { 
       return resultAddress2;
      }
    public void setResultAddress2(String resultAddress2)
      { 
      this.resultAddress2 = resultAddress2;
      }
    public String getResultCity()
      { 
       return resultCity;
      }
    public void setResultCity(String resultCity)
      { 
      this.resultCity = resultCity;
      }
    public String getResultCounty()
      { 
       return resultCounty;
      }
    public void setResultCounty(String resultCounty)
      { 
      this.resultCounty = resultCounty;
      }
    public String getResultId()
      { 
       return resultId;
      }
    public void setResultId(String resultId)
      { 
      this.resultId = resultId;
      }
    public String getResultType()
      { 
       return resultType;
      }
    public void setResultType(String resultType)
      { 
      this.resultType = resultType;
      }
    public String getSearchSequence()
      { 
       return searchSequence;
      }
    public void setSearchSequence(String searchSequence)
      { 
      this.searchSequence = searchSequence;
      }
    public Date getPermitExpDate()
      { 
       return permitExpDate;
      }
    public void setPermitExpDate(Date permitExpDate)
      { 
      this.permitExpDate = permitExpDate;
      }
    public Date getPermitIssueDate()
      { 
       return permitIssueDate;
      }
    public void setPermitIssueDate(Date permitIssueDate)
      { 
      this.permitIssueDate = permitIssueDate;
      }
    public void setPermitIssueDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    permitIssueDate = formatter.parse(dateParam);
    } catch (Exception e)
    {permitIssueDate = null; }
    }
    public String getPermitIssueDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(permitIssueDate == null)
    { return ""; }
    else { return formatter.format(permitIssueDate); }
    }
    public void setPermitExpDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    permitExpDate = formatter.parse(dateParam);
    } catch (Exception e)
    {permitExpDate = null; }
    }
    public String getPermitExpDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(permitExpDate == null)
    { return ""; }
    else { return formatter.format(permitExpDate); }
    }
   
}
