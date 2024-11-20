package hs.report.pdf;

import java.io.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.BaseFont;
import hs.to.*;
import hs.data.*;
import com.lowagie.text.Image;
import util.logging.DHSLogLevel;
import util.logging.Log;

public abstract class HsPdf extends PdfPageEventHelper
{

  protected Document document = null;
  protected PdfWriter writer = null;
  protected PdfContentByte cb = null;
  protected OutputStream os = null;
  
  protected String logoPath = null;
  protected String watermarkPath = null;
  
  protected PdfTemplate tpl = null;
  
  protected String documentId = null;
  
  protected Image watermarkImage;
  
  protected boolean page;
 
  

  public abstract void processDocument() throws Exception;
  
  public HsPdf(OutputStream os) throws Exception
  {
    this.os = os;
    document = new Document(PageSize.A4);
    writer = PdfWriter.getInstance(document,os);
    page = true;
  }
  public HsPdf(OutputStream os,boolean landscape) throws Exception
  {
    this.os = os;
    if(landscape){
      document = new Document(PageSize.A4.rotate());
    } 
    else 
    {
      document = new Document(PageSize.A4);
    }
    writer = PdfWriter.getInstance(document,os);
  }
  
  
  public OutputStream getOutputStream() 
  {
    return os;
  }
  
 
  public void write()  throws Exception
  {
        
    document.open();
    cb = writer.getDirectContent();
   
    tpl = writer.getDirectContent().createTemplate(100, 100);
    tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));
    
    processDocument();
    document.close();
  }
  public void onEndPage(PdfWriter writer,Document document) 
  {
    
  
    try {
    
      Image watermarkImg = this.getWatermarkImage();
      if(watermarkImg != null) {
        PdfContentByte cbu = writer.getDirectContentUnder();
        cbu.addImage( watermarkImg);
      }
      if(isPage()) {
        BaseFont hb = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);   
        BaseFont hn = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); 
    
        StringBuffer sb = new StringBuffer();
        sb.append(getDocumentId()).append(" (page ");
        sb.append(writer.getPageNumber()).append(" of ");
        float textSize = hb.getWidthPoint(sb.toString(), 12);
        float textBase = document.bottom() - 20;
    
  
    
        cb.beginText();
        float adjust = hn.getWidthPoint("0", 12);
        cb.setFontAndSize(hn, 12);
        cb.setTextMatrix(document.right() - textSize - adjust, textBase);
        cb.showText(sb.toString());
        cb.endText();
    
  
        cb.addTemplate(tpl, document.right() - adjust, textBase);
      }
   }
   catch (Exception e) {
    
   }
  }
  
   public void onCloseDocument(PdfWriter writer, Document document) {
   
       if(isPage()) {
        try {
          BaseFont hn = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); 
      
          StringBuffer sb = new StringBuffer();
        
          tpl.beginText();
          tpl.setFontAndSize(hn, 12);
          tpl.setTextMatrix(0, 0);
          sb.append(writer.getPageNumber() - 1).append(")");
          tpl.showText(sb.toString());
          tpl.endText();
       
       } catch (Exception e) {}
   
    }
    }

  public Image getWatermarkImage()
  {
    return watermarkImage;
  }

 
  public void setWatermarkImage(String imageUrlPath,int x, int y)
  {
    try{
       this.watermarkImage = Image.getInstance(imageUrlPath);
       this.watermarkImage.setAbsolutePosition(x,y);
    
    } catch (Exception e)
    {
      e.printStackTrace();
      Log.log("ACTION_LAYER", DHSLogLevel.INFO, "HazmatPermitAction", "printAllPermits", "1-method.:"+ e.getMessage());

      this.watermarkImage = null;

    }
    
  }

  public String getDocumentId()
  {
    return (documentId == null) ? "" : documentId;
  }

  public void setDocumentId(String documentId)
  {
    this.documentId = documentId;
  }

  public boolean isPage()
  {
    return page;
  }

  public void setPage(boolean page)
  {
    this.page = page;
  }
  
  
  
 
  
 
  
  
}