package lepc.control.form;
import lepc.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.control.form.*;
import main.to.*;

import org.apache.struts.upload.FormFile;
import org.apache.struts.action.*;
public class lepcExerciseForm extends ActionForm{
    public lepcExerciseForm() {
       
    }
  private int exerciseId;
  private int lepcId;
  private String exerciseType;
  private String reportBy;
  private String reportDate;
  private String reportType;
  private String exerciseStartDate;
  private String exerciseEndDate;
  private String exerciseStartTime;
  private String exerciseEndTime;
  private String exerciseLocation;
  private String exerciseCity;
  private String exerciseCounty;
  private String exerciseScenario;
  private String exerciseIncidentType;
  private String exerciseStatus;
  private String exerciseNumber;
  public String getExerciseEndDate()
  {    return exerciseEndDate;  }
  public void setExerciseEndDate( String  exerciseEndDate)
  {   this.exerciseEndDate = exerciseEndDate;  }
  public String getExerciseStartDate()
  {    return exerciseStartDate;  }
  public void setExerciseStartDate( String  exerciseStartDate)
  {   this.exerciseStartDate = exerciseStartDate;  }
  public String getReportDate()
  {    return reportDate;  }
  public void setReportDate( String  reportDate)
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
  public String getExerciseNumber()
    {    return exerciseNumber;  }
  public void setExerciseNumber(String exerciseNumber)
    {   this.exerciseNumber = exerciseNumber;  }
  public lepcExercise getExercise() throws Exception
   {
     lepcExercise exercise = new lepcExercise();
     exercise.setExerciseCity(this.getExerciseCity());
     exercise.setExerciseCounty(this.getExerciseCounty());
     exercise.setExerciseEndDateString(this.getExerciseEndDate());
     exercise.setExerciseEndTime(this.getExerciseEndTime());
     exercise.setExerciseIncidentType(this.getExerciseIncidentType());
     exercise.setExerciseLocation(this.getExerciseLocation());
     exercise.setExerciseScenario(this.getExerciseScenario());
     exercise.setExerciseStartDateString(this.getExerciseStartDate());
     exercise.setExerciseStartTime(this.getExerciseStartTime());
     exercise.setExerciseStatus(this.getExerciseStatus());
     exercise.setExerciseType(this.getExerciseType());
     exercise.setReportBy(this.getReportBy());
     exercise.setReportDateString(this.getReportDate());
     exercise.setReportType(this.getReportType());
     exercise.setExerciseId(this.getExerciseId());
     exercise.setLepcId(this.getLepcId());
     exercise.setExerciseNumber(this.getExerciseNumber());
  return exercise;
   }
    public void setProperties(lepcExercise exercise) throws Exception
   { 
     this.setExerciseCity(exercise.getExerciseCity());
     this.setExerciseCounty(exercise.getExerciseCounty());
     this.setExerciseEndDate(exercise.getExerciseEndDateString());
     this.setExerciseEndTime(exercise.getExerciseEndTime());
     this.setExerciseIncidentType(exercise.getExerciseIncidentType());
     this.setExerciseLocation(exercise.getExerciseLocation());
     this.setExerciseScenario(exercise.getExerciseScenario());
     this.setExerciseStartDate(exercise.getExerciseStartDateString());
     this.setExerciseStartTime(exercise.getExerciseStartTime());
     this.setExerciseStatus(exercise.getExerciseStatus());
     this.setExerciseType(exercise.getExerciseType());
     this.setReportBy(exercise.getReportBy());
     this.setReportDate(exercise.getReportDateString());
     this.setReportType(exercise.getReportType());
     this.setExerciseId(exercise.getExerciseId());
     this.setLepcId(exercise.getLepcId());
     this.setExerciseNumber(exercise.getExerciseNumber());
   }
}
