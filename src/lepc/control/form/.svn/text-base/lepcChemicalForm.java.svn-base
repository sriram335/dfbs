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
public class lepcChemicalForm extends ActionForm{
    public lepcChemicalForm() {
       
    }
  private int chemicalId;
  private int exerciseId;
  private String isChemEHS;
  private String isChemCercla;
  private String rqForChem;
  private String quantityReleased;
  private String chemName;
  public String getIsChemCercla()
    {    return isChemCercla;  }
  public void setIsChemCercla(String isChemCercla)
    {   this.isChemCercla = isChemCercla;  }
  public String getIsChemEHS()
    {    return isChemEHS;  }
  public void setIsChemEHS(String isChemEHS)
    {   this.isChemEHS = isChemEHS;  }
  public String getQuantityReleased()
    {    return quantityReleased;  }
  public void setQuantityReleased(String quantityReleased)
    {   this.quantityReleased = quantityReleased;  }
  public String getRqForChem()
    {    return rqForChem;  }
  public void setRqForChem(String rqForChem)
    {   this.rqForChem = rqForChem;  }
  public int getChemicalId()
    {    return chemicalId;  }
  public void setChemicalId(int chemicalId)
    {   this.chemicalId = chemicalId;  }
  public int getExerciseId()
    {    return exerciseId;  }
  public void setExerciseId(int exerciseId)
    {   this.exerciseId = exerciseId;  }
  public String getChemName()
    {    return chemName;  }
  public void setChemName(String chemName)
    {   this.chemName = chemName;  }
  public lepcChemical getChemical() throws Exception
   {
     lepcChemical chem = new lepcChemical();
     chem.setIsChemCercla(this.getIsChemCercla());
     chem.setIsChemEHS(this.getIsChemEHS());
     chem.setQuantityReleased(this.getQuantityReleased());
     chem.setRqForChem(this.getRqForChem());
     chem.setChemicalId(this.getChemicalId());
     chem.setExerciseId(this.getExerciseId());
     chem.setChemName(this.getChemName());
  return chem;
   }
    public void setProperties(lepcChemical chem) throws Exception
   { 
     this.setIsChemCercla(chem.getIsChemCercla());
     this.setIsChemEHS(chem.getIsChemEHS());
     this.setQuantityReleased(chem.getQuantityReleased());
     this.setRqForChem(chem.getRqForChem());
     this.setChemicalId(chem.getChemicalId());
     this.setExerciseId(chem.getExerciseId());
     this.setChemName(chem.getChemName());
   }
}
