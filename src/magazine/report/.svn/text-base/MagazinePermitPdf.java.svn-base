package magazine.report;

import hs.report.pdf.*;
import magazine.to.*;
import magazine.data.*;
import java.io.*;
import java.util.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import hs.to.*;
import hs.data.*;
import hs.util.*;


public class MagazinePermitPdf extends HsPdf
{private java.util.List listOfPermits = null;
 HsPdfGrid grid = null;
 
 Image signatureImg = null;
  public MagazinePermitPdf(java.util.List list,OutputStream os,String logo,String watermarkPath,String signturePath ) throws Exception
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
  
      
    
    
    if(listOfPermits == null || listOfPermits.size() == 0 ) 
    {
      par = new Paragraph("* No Permit to Print * ",boldBig);
      document.add(par);
      return;
    }
    
   
    
    
    
     Iterator i = listOfPermits.iterator();
  
   
    
    while(i.hasNext()) {
     grid.addHeader("Fire and Building Services Department ");
    
    PermitsToPrint fp = (PermitsToPrint) i.next(); 
    
     par = new Paragraph();
     par.add(new Chunk("REGULATED EXPLOSIVE MAGAZINE PERMIT",boldMedium));
     grid.add(par);
    
    par = new Paragraph();
    par.add(new Chunk("THIS IS TO CERTIFY THAT ",normalSmall));
    par.add(new Chunk(fp.getOwnerName(),boldSmall));
    par.add(new Chunk(" located at " + fp.getMagTownShip() + " township on " + fp.getMagAddress1() +", in the City of ",normalSmall));
    par.add(new Chunk(fp.getMagCity()+ " in " + fp.getMagCountyName() + " County, State of Indiana, and operated by " + fp.getOwnerName(),normalSmall));
    par.add(new Chunk(" to be used for storage of regulated explosives has been approved for operation.\n\r",normalSmall));
    par.add(new Chunk("Be it known that pursuant to provisions of IC 22-14-4, proper application having been made and on file",normalSmall));
    par.add(new Chunk(" in the State Fire Marshal's office of the Fire and Building Services Department, Indianapolis, Indiana, and ",normalSmall));
     par.add(new Chunk("proper inspection made of the premises in such application described, and finding that said ",normalSmall));
    par.add(new Chunk(" premises comply with the fire safety requirements as prescribed.\n\r",normalSmall));
    par.add(new Chunk("Permission is hereby granted to  " +fp.getOwnerName() + " to store explosives at the above described location ",normalSmall));
   par.add(new Chunk("subject to right of cancellation visited by statute, for period ending " + fp.getMagExpDateStringDay(),normalSmall));
   par.add(new Chunk("th day of " + fp.getMagExpDateStringMonth()+", " +fp.getMagExpDateStringYear()+".\n\r",normalSmall));
   par.add(new Chunk("Witness my hand in the city of Indianapolis, State of Indiana on this ",normalSmall));
   par.add(new Chunk( fp.getMagIssueDateStringDay(),normalSmall));
   par.add(new Chunk("th day of " + fp.getMagIssueDateStringMonth()+", " +fp.getMagIssueDateStringYear()+".\n\r",normalSmall));
    grid.add(par);
    
    grid.add(2,"Permit Number",fp.getMagPermitNumber());
    grid.add(2,"Permit valid from:",fp.getMagIssueDateString());
    grid.add(2,"Permit valid to:",fp.getMagExpDateString()); 
    grid.add(2,"Magazine ID",fp.getMagIdNumber());
    grid.add(2,"County:",fp.getMagCountyName());
    grid.add(2,"Township:",fp.getMagTownShip());
    grid.add(2,"Magazine Type:",fp.getMagType());
    grid.add(2,"Explosive Type:",fp.getMagExpType());
    grid.add(2,"Max Quantity(pounds):",Integer.toString(fp.getMagMinQuantity()));
   
   
    /* ----- BODY BEGIN ----- */
        
     
      
    
     par = new Paragraph();
    
    /* begin owner address */
     par.add(new Chunk("Permit issued to: ",normalSmall));
    sb = new StringBuffer();
    sb.append ("\n");
    sb.append (fp.getOwnerName()).append("\n");
    par.add(new Chunk(  sb.toString(),boldMedium));
    
    sb = new StringBuffer();
    sb.append(fp.getOwnAddress1()).append("\n");
    if(fp.getOwnAddress2() != null && !fp.getOwnAddress2().trim().equals("")) 
    {
      sb.append(fp.getOwnAddress2()).append("\n");
    }
    sb.append(fp.getOwnCity()).append(", ").append(fp.getOwnState()).append(" ").append(fp.getOwnZip()).append("\n\r");
    
    par.add(new Chunk(  sb.toString(),boldSmall));
    /* end owner address */
        
    grid.add(par);
    
    
   /* ----- BODY END ----- */
    
  
   if(signatureImg != null) {
      signatureImg.scalePercent(6);
      grid.add(2,"State Fire Marshal:",new Chunk(signatureImg,15,-10));
    }  else {
      grid.add(2,"State Fire Marshal:"," ");
    }  
  
   grid.add(2,"Date:",fp.getMagIssueDateString());
  /* grid.add(2,"Inspector assigned:" ,"STAN CAPOBIANCO"); */
  
   grid.add(2,"Inspector assigned:" ,fp.getInspector()); 
  
   
    document.add(grid.getGridTable());
    grid.reset();
    
    document.newPage();
    
    }
    
  }
  
 
  
 
  
  
 
}