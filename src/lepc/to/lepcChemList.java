package lepc.to;

import java.io.Serializable;

public class lepcChemList implements Serializable{
    private String chemicalName;
    private String chemName;
    private String chemCas;
    private String chemEhsRq;
    private String chemCerlaRq;
    public lepcChemList() {
       
    }
  public String getChemCas()
    {    return chemCas;  }
  public void setChemCas(String chemCas)
    {   this.chemCas = chemCas;  }
  public String getChemCerlaRq()
    {    return chemCerlaRq;  }
  public void setChemCerlaRq(String chemCerlaRq)
    {   this.chemCerlaRq = chemCerlaRq;  }
  public String getChemEhsRq()
    {    return chemEhsRq;  }
  public void setChemEhsRq(String chemEhsRq)
    {   this.chemEhsRq = chemEhsRq;  }
  public String getChemName()
    {    return chemName;  }
  public void setChemName(String chemName)
    {   this.chemName = chemName;  }
  public String getChemicalName()
    {    return chemicalName;  }
  public void setChemicalName(String chemicalName)
    {   this.chemicalName = chemicalName;  }
}
