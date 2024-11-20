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
public class FireworksWholesalerCompliancePdf extends HsPdf 
{
  private static final String TITLE = 
  "Certificate of Compliance for Manufacturer, Wholesaler, " +  
  "Importer or Distributor of Fireworks ";

 
  private java.util.List listOfPermits = null;
  HsPdfGrid grid = null;
  
  private Image signatureImg = null;
  
  public FireworksWholesalerCompliancePdf(java.util.List list,OutputStream os,String logo,String watermarkPath,String signturePath ) throws Exception
  {
    super(os);
   
    listOfPermits = list;
    
    grid = new HsPdfGrid(6);
    grid.setLogoImage(logo);
    this.setWatermarkImage(watermarkPath,85,140);
    
     try{
       signatureImg = Image.getInstance(signturePath);
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
      par = new Paragraph("* No Wholesaler Compliance Permit to Print*",boldBig);
      document.add(par);
      return;
    }
   

    Iterator i = listOfPermits.iterator();
  
    while(i.hasNext()) {
    
      grid.addHeader("Division of Fire and Building Safety");
   
      DfbsFireworksPermit fp = (DfbsFireworksPermit) i.next(); 
  
    
    
    
    
    /* ----- */   
    
    par = new Paragraph();
    par.add(new Chunk(TITLE,boldMedium));
    par.add(new Chunk("(as defined in IC 22-11-14-1)",normalSmall));
    
    grid.add(par);
    
    
    
    grid.add(3,"Certificate Number:",fp.getPermitNumber());
    grid.add(3,"Year:",Integer.toString(fp.getPermitYear()));
        if(fp.getRetailerType() != null) {
         grid.add(3,"Retail Type:",fp.getRetailerType());
         grid.add(3,"","");
        }
    
    
        
    /* ----- BODY BEGIN ----- */
        
     
    
    
    
    par = new Paragraph();
    par.add(new Chunk("Pursuant to IC 22-11-14-5, this ",normalSmall));
    par.add(new Chunk(TITLE,boldSmall));
    par.add(new Chunk("is issued to the applicant listed below:\n\r",normalSmall));
    
    
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
    
    
    grid.add(par);
     /* end owner address */
    
    
    par = new Paragraph();
    par.add(new Chunk("This ",normalSmall));
    par.add(new Chunk(TITLE,boldSmall));
     sb = new StringBuffer();
    sb.append("expires on December 31, ").append(fp.getPermitYear()).append("\n\r");
    par.add(new Chunk(sb.toString(),normalSmall));
        
    par = new Paragraph();
    par.add(new Chunk("This ",normalSmall));
    par.add(new Chunk(TITLE,boldSmall));
    par.add(new Chunk("is not transferable except to a subsequent owner ",normalSmall));
    par.add(new Chunk("or operator of a fireworks business at the above location ",normalSmall));
    par.add(new Chunk("in accordance with the policies and guidelines of the State Fire Marshal.\n\r",normalSmall));
    
  
    
    
  
    par.add(new Chunk("This manufacturer, wholesaler, importer, or distributor to whom ",normalSmall));
    par.add(new Chunk("this certificate of compliance has been issued must provide a certified copy of this ",normalSmall));
     par.add(new Chunk("certificate to each Indiana retailer who purchases fireworks listed in IC 22-11-14-8(A) ",normalSmall));
    par.add(new Chunk("from the holder hereof.\n\r",normalSmall));
 
    
  
   par.add(new Chunk("Such retailer shall post the certified copy in a conspicuous ",normalSmall));
   par.add(new Chunk("place at each location where fireworks described in IC 22-11-14-8(A) are sold or offered for sale.\n\r",normalSmall));
   grid.add(par);
   
   
    
    
    
   /* ----- BODY END ----- */
    
    
    if(signatureImg != null) {
      signatureImg.scalePercent(5);
      grid.add(3,"James L. Greeson, Indiana State Fire Marshal",new Chunk(signatureImg,25,-10));
    }  else {
      grid.add(3,"James L. Greeson, Indiana State Fire Marshal"," ");
    }  
  
  
  grid.add(3,"Date:",fp.getIssueDateString());
    
   
    
        
    /* ----- */
    
    
 
    par = new Paragraph();
    par.add(new Chunk("As the manufacturer, wholesaler, importer or distributor, or its duly ",normalSmall));
    par.add(new Chunk("authorized representative, I hereby certify that this is a true and accurate ",normalSmall));
    par.add(new Chunk("copy of the ",normalSmall));
    par.add(new Chunk(TITLE,boldSmall));
    par.add(new Chunk("issued by the State Fire Marshal to the holder listed above.\n\r",normalSmall));
    grid.add(par);
    
     /* ----- */
    
    grid.add(2,"Signature:","");
    grid.add(2,"Title:","");
    grid.add(2,"Date:","");
    
    
    
 
    //PRINT MAILING ADDRESS
    DfbsOwner owner = fp.getOwner();
    
    
    PdfContentByte cbu = writer.getDirectContentUnder();
    cbu.beginText();
    cbu.setFontAndSize(bold,8f);
    int Y = 65;
    int h = 10;
    Y = Y - h;
    cbu.setTextMatrix(70,Y);    
    cbu.showText(owner.getOwnerName());
    cbu.endText();
    
    
    cbu.beginText();
    cbu.setFontAndSize(bold,8f);
    Y = Y - h;
    cbu.setTextMatrix(70,Y);
    sb = new StringBuffer();
    sb.append(owner.getStreet1());
    cbu.showText(sb.toString());
    cbu.endText();
    
    if(owner.getStreet2() != null && !owner.getStreet2().trim().equals("")) 
    {
      cbu.beginText();
      cbu.setFontAndSize(bold,8f);
      Y = Y - h;
      cbu.setTextMatrix(70,Y);
      sb = new StringBuffer();
      sb.append(owner.getStreet2());
      cbu.showText(sb.toString());
      cbu.endText();
    }
   
    
    cbu.beginText();
    cbu.setFontAndSize(bold,8f);
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


