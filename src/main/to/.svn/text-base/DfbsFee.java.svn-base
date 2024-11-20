package main.to;
import hs.to.*;

public class DfbsFee 
{
  private int startIndex;
  private int endIndex;
  private String description;
  private double amount;
  private double otherAmount;
    
  public DfbsFee()
  {
  }
  
  public double getFee(int quantity) 
  {
      if(quantity < 0 ) return 0;
      
      if((quantity >= startIndex) && (quantity <= endIndex)) 
      {
        return amount;
      } 
      else if((quantity > startIndex) && (endIndex == -1)) 
      {
        return amount + ((quantity - startIndex) * otherAmount );
      }
      return 0;
  }

  public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public int getEndIndex()
  {
    return endIndex;
  }

  public void setEndIndex(int endIndex)
  {
    this.endIndex = endIndex;
  }

  public double getOtherAmount()
  {
    return otherAmount;
  }

  public void setOtherAmount(double otherAmount)
  {
    this.otherAmount = otherAmount;
  }

  public int getStartIndex()
  {
    return startIndex;
  }

  public void setStartIndex(int startIndex)
  {
    this.startIndex = startIndex;
  }
  
 
  
  
}