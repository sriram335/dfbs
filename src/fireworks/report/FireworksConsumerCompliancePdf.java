package fireworks.report;
import hs.report.pdf.*;
import fireworks.data.*;
import fireworks.to.*;
import java.io.*;
import java.util.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import hs.to.*;
import hs.data.*;
import hs.util.*;
public class FireworksConsumerCompliancePdf extends HsPdf 
{
  private static final String TENT_TITLE = 
  "Certificate of Compliance for Retail Sale of Consumer Fireworks ";
  
  private static final String RETAIL_TITLE = 
  "Permit for Fireworks Stand Retail Sales of Fireworks Listed ";


 
 private java.util.List listOfPermits = null;
 HsPdfGrid grid = null;
 
 Image signatureImg = null;
  
  public  FireworksConsumerCompliancePdf(java.util.List list,OutputStream os,String logo,String watermarkPath,String signturePath ) throws Exception
  {
    super(os);
    listOfPermits = list;
    grid = new HsPdfGrid(6);
    grid.setLogoImage(logo);
    this.setWatermarkImage(watermarkPath,85,140);
    
     try{
       signatureImg = Image.getInstance(signturePath) ;
    } catch (Exception e) 
    {
      signatureImg = null;
    }
    
  }
  

  public void processDocument() throws Exception
  {
    writer.setPageEvent(this);
  
    StringBuffer sb = new StringBuffer();
    Paragraph par = new Paragraph();
    
    BaseFont bold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);   
    BaseFont normal = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); 
  
    Font boldBig = new Font(bold,14);
    Font boldMedium = new Font(bold,12);
    Font boldSmall = new Font(bold,10);
  
    Font normalSmall = new Font(normal,10);
    Font normalSmaller = new Font(normal,8);
  
      
    
    
    if(listOfPermits == null || listOfPermits.size() == 0) 
    {
      par = new Paragraph("* No Consumer Compliance Permit to Print * ",boldBig);
      document.add(par);
      return;
    }
    
   
    Iterator i = listOfPermits.iterator();
  
   
    
    while(i.hasNext()) {
    
    grid.addHeader("Division of Fire and Building Safety");
    
    DfbsFireworksPermit fp = (DfbsFireworksPermit) i.next(); 
    
     boolean isRetail = false;
     if(fp.getPermitNumber().charAt(2) == 'R') 
     {
       isRetail = true;
     }
    
    
    par = new Paragraph();
    if(isRetail) {
      par.add(new Chunk(RETAIL_TITLE,boldMedium));
      par.add(new Chunk("(as defined in IC 22-11-14-8a)",normalSmall));
    } else 
    {
      par.add(new Chunk(TENT_TITLE,boldMedium));
      par.add(new Chunk("(as defined in IC 22-11-14-1)",normalSmall));
    }
    grid.add(par);
    
    
    
    grid.add(3,"Certificate Number:",fp.getPermitNumber());
    grid.add(3,"Year:",Integer.toString(fp.getPermitYear()));
      if(fp.getRetailerType() != null) {
       grid.add(3,"Retail Type:",fp.getRetailerType());
       grid.add(3,"","");
    }
    /* ----- BODY BEGIN ----- */
        
     
    par = new Paragraph();
    if(isRetail) {
      par = new Paragraph();
      par.add(new Chunk("Pursuant to IC 22-11-14-7, this ",normalSmall));
      par.add(new Chunk(RETAIL_TITLE,boldSmall));
      par.add(new Chunk("is issued to the applicant listed below:\n\r",normalSmall));
    } else 
    {
      par = new Paragraph();
      par.add(new Chunk("Pursuant to IC 22-11-14-5, this ",normalSmall));
      par.add(new Chunk(TENT_TITLE,boldSmall));
      par.add(new Chunk("is issued to the applicant listed below:\n\r",normalSmall));
    }
    
    
    
    
    /* begin owner address */
   
    sb = new StringBuffer();
    sb.append(fp.getOwnerName()).append("\n");
    par.add(new Chunk(  sb.toString(),boldMedium));
    
    sb = new StringBuffer();
    sb.append(fp.getStreet1()).append("\n");
    if(fp.getStreet2() != null && !fp.getStreet2().trim().equals("")) 
    {
      sb.append(fp.getStreet2()).append("\n");
    }
    sb.append(fp.getCity()).append(", ").append(fp.getState()).append(" ").append(fp.getZip()).append("\n\r");
    par.add(new Chunk(  sb.toString(),boldSmall));
   
    /* end owner address */
    
     if(isRetail) {
      par.add(new Chunk("This ",normalSmall));
      par.add(new Chunk(RETAIL_TITLE,boldSmall));
      sb = new StringBuffer();
      sb.append("expires on December 31, ").append(fp.getPermitYear()).append("\n\r");
      par.add(new Chunk(sb.toString(),normalSmall));
    } else 
    {
      par.add(new Chunk("This ",normalSmall));
      par.add(new Chunk(TENT_TITLE,boldSmall));
      sb = new StringBuffer();
      sb.append("expires on December 31, ").append(fp.getPermitYear()).append("\n\r");
      par.add(new Chunk(sb.toString(),normalSmall));
    
    }
    
    if(isRetail) {
     
     par.add(new Chunk("This permit shall be posted by the retailer at the stand so that ",normalSmall));
     par.add(new Chunk("it is easily seen by the public. Only fireworks listed in IC 22-11-14-8(a) ",normalSmall));
     par.add(new Chunk("may be sold under this permit and the seller shall be at least ",normalSmall));
     par.add(new Chunk("18 years of age. No fireworks shall be sold out of a motor vehicle (as defined in IC-9-13-2-105).\n\r ",normalSmall));
     
     par.add(new Chunk("At each stand, the retailer shall provide a posted certificate of compliance ",normalSmall));
     par.add(new Chunk("from the manufacturer, wholesaler,importer, or distributer from which the fireworks for sale ",normalSmall));
     par.add(new Chunk("at the above location were purchased. ",normalSmall));
     
     
     
    } else 
    {
    
    par.add(new Chunk("This ",normalSmall));
    par.add(new Chunk(TENT_TITLE,boldSmall));
    par.add(new Chunk("is not transferable except to a subsequent owner ",normalSmall));
    par.add(new Chunk("or operator of a fireworks business at the above location ",normalSmall));
    par.add(new Chunk("in accordance with the policies and guidelines of the State Fire Marshal.\n\r",normalSmall));
    
    
   
    par.add(new Chunk("This ",normalSmall));
    par.add(new Chunk(TENT_TITLE,boldSmall));
    par.add(new Chunk("is valid only for a tent or a structure at the address listed above. A separate ",normalSmall));
    par.add(new Chunk(TENT_TITLE,boldSmall));
    par.add(new Chunk("shall be obtained for each tent or structure in which consumer fireworks are sold.\n\r",normalSmall));
    
    }
    grid.add(par);
    
    
   /* ----- BODY END ----- */
    
  
   if(signatureImg != null) {
      signatureImg.scalePercent(5);
      grid.add(3,"James L. Greeson, Indiana State Fire Marshal",new Chunk(signatureImg,25,-10));
    }  else {
      grid.add(3,"James L. Greeson, Indiana State Fire Marshal"," ");
    }  
  
  grid.add(3,"Date:",fp.getIssueDateString());
    
  
   //PRINT MAILING ADDRESS
    DfbsOwner owner = fp.getOwner();
  
   PdfContentByte cbu = writer.getDirectContentUnder();
    cbu.beginText();
    cbu.setFontAndSize(bold,12f);
    int Y = 80;
    int h = 15;
    Y = Y - h;
    cbu.setTextMatrix(70,Y);    
    cbu.showText(owner.getOwnerName());
    cbu.endText();
    
    
    cbu.beginText();
    cbu.setFontAndSize(bold,12f);
    Y = Y - h;
    cbu.setTextMatrix(70,Y);
    sb = new StringBuffer();
    sb.append(owner.getStreet1());
    cbu.showText(sb.toString());
    cbu.endText();
    
    if(owner.getStreet2() != null && !owner.getStreet2().trim().equals("")) 
    {
      cbu.beginText();
      cbu.setFontAndSize(bold,12f);
      Y = Y - h;
      cbu.setTextMatrix(70,Y);
      sb = new StringBuffer();
      sb.append(owner.getStreet2());
      cbu.showText(sb.toString());
      cbu.endText();
    }
    
    
    
    cbu.beginText();
    cbu.setFontAndSize(bold,12f);
    Y = Y - h;
    cbu.setTextMatrix(70,Y);
    sb = new StringBuffer();
    sb.append(owner.getCity()).append(", ").append(owner.getState()).append(" ").append(owner.getZip());
    cbu.showText(sb.toString());
    cbu.endText();
    
    document.add(grid.getGridTable());
    grid.reset();
    
    document.newPage();
    
    
    }
  }
  
 
  
 
  
  
  
}
