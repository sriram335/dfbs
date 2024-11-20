package idhsFines.to;



import hs.to.*;
import main.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class ShoppingCart  implements Serializable
{ private List feeIdList;
  private Map feeIdMap;
  private int feeIdCounter;
  private double amount;
  private int receiptId;
  private String paymentType;
  private String checkoutId;
    public ShoppingCart() {
       
    }
  public String getCheckoutId()
  {
   return checkoutId;
  }

  public void setCheckoutId(String checkoutId)
  {
   this.checkoutId = checkoutId;
  }
  public Map getFeeIdMap()
  {
   if(feeIdMap == null) 
   {
     feeIdMap = new HashMap();
   }
   return feeIdMap;
  }

  public void setFeeIdMap(Map feeIdMap)
  {
   this.feeIdMap = feeIdMap;
  }
  public int getFeeIdMapCount()
  {
   return (this.feeIdMap == null) ? 0 : feeIdMap.size();
  }

  public void addFeeId(feeDetails feeDet) throws Exception
  {
   if(feeDet == null) return;
   
     Map map = this.getFeeIdMap();
     map.put(feeDet.getTransactionId(),feeDet);
    amount = amount + Double.parseDouble(feeDet.getDue());
   feeIdCounter ++;
  }
  public feeDetails getFeeDetails(String key)
  {
   if(key == null || feeIdMap == null ) {
     return new feeDetails();
   }
   feeDetails feeDet = (feeDetails) feeIdMap.get(key);
   return (feeDet == null) ? new feeDetails() : feeDet;
  }
  
  
  public void removeFeeId(String key) throws Exception
  {
   if(key == null || feeIdMap == null ) return;
   feeDetails feeDet = (feeDetails) feeIdMap.get(key);
       amount = amount - Double.parseDouble(feeDet.getDue());
    feeIdCounter --;
   feeIdMap.remove(key);
     }
  
  
  public int getFeeIdCounter()
  {
   return feeIdCounter;
  }

  public void setFeeIdCounter(int feeIdCounter)
  {
   this.feeIdCounter = feeIdCounter;
  }
 
  public double getAmount()
  {
  return amount;
  }
  public void setAmount(double amount)
  {
  this.amount = amount;
  }
 
  public String getPaymentType()
  {
  return paymentType;
  }
  public void setPaymentType(String paymentType)
  {
  this.paymentType = paymentType;
  }
  public int getReceiptId()
  {
  return receiptId;
  }
  public void setReceiptId(int receiptId)
  {
  this.receiptId = receiptId;
  }
  public List getFeeIdList()
  {
   if(feeIdList == null) 
   {
     feeIdList = new ArrayList();
   } 
   return feeIdList;
  }

  public void setFeeIdList(List feeIdList)
  {
   this.feeIdList = feeIdList;
  }
  
  
}
