package main.to;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;



public class FireReportsGroup  implements Serializable
{private String reportGroup;
 private List reports;
  public FireReportsGroup()
  {
  }
    public String getReportGroup()
  { 
   return reportGroup;
  }
public void setReportGroup(String reportGroup)
  { 
  this.reportGroup = reportGroup;
  }
   public int getReportsCount () 
  {
    return (reports == null) ? 0 : reports.size();
  }

  public List getReports()
  {
    if(reports == null) 
    {
      reports = new ArrayList();
    } 
    return reports;
  }

  public void setReports(List reports)
  {
    this.reports = reports;
  }
}
