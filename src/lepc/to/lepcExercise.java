package lepc.to;
import java.io.Serializable;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class lepcExercise implements Serializable{
    public lepcExercise() {
       
    }
    private int exerciseId;
    private int lepcId;
    private String exerciseType;
    private String reportBy;
    private Date reportDate;
    private String reportType;
    private Date exerciseStartDate;
    private Date exerciseEndDate;
    private String exerciseStartTime;
    private String exerciseEndTime;
    private String exerciseLocation;
    private String exerciseCity;
    private String exerciseCounty;
    private String exerciseScenario;
    private String exerciseIncidentType;
    private String exerciseStatus;
    private String exerciseNumber;
    
  public Date getExerciseEndDate()
    {    return exerciseEndDate;  }
  public void setExerciseEndDate(Date exerciseEndDate)
    {   this.exerciseEndDate = exerciseEndDate;  }
  public Date getExerciseStartDate()
    {    return exerciseStartDate;  }
  public void setExerciseStartDate(Date exerciseStartDate)
    {   this.exerciseStartDate = exerciseStartDate;  }
  public Date getReportDate()
    {    return reportDate;  }
  public void setReportDate(Date reportDate)
    {   this.reportDate = reportDate;  }
  public String getExerciseType()
    {    return exerciseType;  }
  public void setExerciseType(String exerciseType)
    {   this.exerciseType = exerciseType;  }
  public String getReportBy()
    {    return reportBy;  }
  public void setReportBy(String reportBy)
    {   this.reportBy = reportBy;  }
  public String getReportType()
    {    return reportType;  }
  public void setReportType(String reportType)
    {   this.reportType = reportType;  }
  public int getExerciseId()
    {    return exerciseId;  }
  public void setExerciseId(int exerciseId)
    {   this.exerciseId = exerciseId;  }
  public int getLepcId()
    {    return lepcId;  }
  public void setLepcId(int lepcId)
    {   this.lepcId = lepcId;  }
  public String getExerciseCity()
    {    return exerciseCity;  }
  public void setExerciseCity(String exerciseCity)
    {   this.exerciseCity = exerciseCity;  }
  public String getExerciseCounty()
    {    return exerciseCounty;  }
  public void setExerciseCounty(String exerciseCounty)
    {   this.exerciseCounty = exerciseCounty;  }
  public String getExerciseEndTime()
    {    return exerciseEndTime;  }
  public void setExerciseEndTime(String exerciseEndTime)
    {   this.exerciseEndTime = exerciseEndTime;  }
  public String getExerciseIncidentType()
    {    return exerciseIncidentType;  }
  public void setExerciseIncidentType(String exerciseIncidentType)
    {   this.exerciseIncidentType = exerciseIncidentType;  }
  public String getExerciseLocation()
    {    return exerciseLocation;  }
  public void setExerciseLocation(String exerciseLocation)
    {   this.exerciseLocation = exerciseLocation;  }
  public String getExerciseScenario()
    {    return exerciseScenario;  }
  public void setExerciseScenario(String exerciseScenario)
    {   this.exerciseScenario = exerciseScenario;  }
  public String getExerciseStartTime()
    {    return exerciseStartTime;  }
  public void setExerciseStartTime(String exerciseStartTime)
    {   this.exerciseStartTime = exerciseStartTime;  }
  public String getExerciseStatus()
    {    return exerciseStatus;  }
  public void setExerciseStatus(String exerciseStatus)
    {   this.exerciseStatus = exerciseStatus;  }
  public void setExerciseEndDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  exerciseEndDate = formatter.parse(dateParam);
  } catch (Exception e)
  {exerciseEndDate = null; }
  }
  public String getExerciseEndDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(exerciseEndDate == null)
  { return ""; }
  else { return formatter.format(exerciseEndDate); }
  }
  public void setExerciseStartDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  exerciseStartDate = formatter.parse(dateParam);
  } catch (Exception e)
  {exerciseStartDate = null; }
  }
  public String getExerciseStartDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(exerciseStartDate == null)
  { return ""; }
  else { return formatter.format(exerciseStartDate); }
  }
  public void setReportDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  reportDate = formatter.parse(dateParam);
  } catch (Exception e)
  {reportDate = null; }
  }
  public String getReportDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(reportDate == null)
  { return ""; }
  else { return formatter.format(reportDate); }
  }
  public String getExerciseNumber()
    {    return exerciseNumber;  }
  public void setExerciseNumber(String exerciseNumber)
    {   this.exerciseNumber = exerciseNumber;  }
}
