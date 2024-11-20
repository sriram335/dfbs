package elevator.control.form;
import elevator.to.*;

import org.apache.struts.action.ActionForm;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
public class elevTestsForm extends ActionForm{
    public elevTestsForm() {
       
    }
  private int testId;
   private String testName;
  private String testValue;
  private String testStatus;
  private int inspectionId;
  
  public int getInspectionId()
    {    return inspectionId;  }
  public void setInspectionId(int inspectionId)
    {   this.inspectionId = inspectionId;  }
  public int getTestId()
    {    return testId;  }
  public void setTestId(int testId)
    {   this.testId = testId;  }
  public String getTestName()
    {    return testName;  }
  public void setTestName(String testName)
    {   this.testName = testName;  }
  public String getTestStatus()
    {    return testStatus;  }
  public void setTestStatus(String testStatus)
    {   this.testStatus = testStatus;  }
  public String getTestValue()
    {    return testValue;  }
  public void setTestValue(String testValue)
    {   this.testValue = testValue;  }
   
    public elevTests  getElevTests()
    {
      elevTests elevTest =new elevTests();
      elevTest.setInspectionId(this.getInspectionId());
      elevTest.setTestId(this.getTestId());
      elevTest.setTestName(this.getTestName());
      elevTest.setTestStatus(this.getTestStatus());
      elevTest.setTestValue(this.getTestValue());
      return elevTest;
  }
  public void setProperties(elevTests elevTest) 
  {
      this.setInspectionId(elevTest.getInspectionId());
      this.setTestId(elevTest.getTestId());
      this.setTestName(elevTest.getTestName());
      this.setTestStatus(elevTest.getTestStatus());
      this.setTestValue(elevTest.getTestValue());
  }
  public void setUpdatedProperties(elevTests updatedTest,elevTests elevTest) throws Exception
   { 
     elevTest.setInspectionId(updatedTest.getInspectionId());
     elevTest.setTestId(updatedTest.getTestId());
     elevTest.setTestName(updatedTest.getTestName());
     elevTest.setTestStatus(updatedTest.getTestStatus());
     elevTest.setTestValue(updatedTest.getTestValue()); 
   }
}
