package hs.report.pdf;

import java.io.*;
import java.util.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import hs.to.*;
import hs.data.*;
import hs.util.*;


public class HsPdfGrid 
{
  protected Image logoImage;
  private PdfPTable gridTable = null;
  private boolean newPage;
 
  
  private int gridNumber;
  
  public static BaseFont bold;
  public static BaseFont normal;
  
  public static Font boldBigger;
  public static Font boldBig;
  public static Font boldBigUnderLine;
  public static Font boldMedium;
  public static Font boldSmall;
  public static Font boldSmaller;
  
  public static Font normalBig;
  public static Font normalMedium;
  public static Font normalSmall;
  public static Font normalSmaller;
  
  private boolean enableBorder;
  
  static 
  {
    try {
      bold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);   
      normal = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); 
      
      
      boldBigger = new Font(bold,16);
      boldBig = new Font(bold,14);
      boldBigUnderLine = new Font(bold,14,Font.UNDERLINE);
      boldMedium = new Font(bold,12);
      boldSmall = new Font(bold,10);
      boldSmaller = new Font(bold,8);
  
      
  
      normalBig = new Font(normal,14);
      normalMedium = new Font(normal,12);
      normalSmall = new Font(normal,10);
      normalSmaller = new Font(normal,8);
      
    } catch (Exception e) {}
    
    
  }
  
  public HsPdfGrid(int gridNumber) throws Exception
  {
    
    this.gridNumber = gridNumber;
    gridTable = new PdfPTable(this.gridNumber);
    gridTable.setWidthPercentage(100);
    gridTable.setSplitLate(false);
    
    enableBorder = true;
    
  }
  
  public void reset() 
  {
    gridTable = new PdfPTable(this.gridNumber);
    gridTable.setWidthPercentage(100);
    gridTable.setSplitLate(false);
  }
   public void reset(int gridNumber) 
  {
    this.gridNumber = gridNumber;
    gridTable = new PdfPTable(this.gridNumber);
    gridTable.setWidthPercentage(100);
    gridTable.setSplitLate(false);
  }
  
  public Image getLogoImage()
  {
    return logoImage;
  }

 
  public void setLogoImage(String imageUrlPath)
  {
    try{
       this.logoImage = Image.getInstance(imageUrlPath);
    } catch (Exception e) 
    {
       this.logoImage = null;
    }
    
  }
  
   public void addHeader(String title) throws Exception
  {
    PdfPCell cell  = new PdfPCell();
    this.enableBorder(cell);
    cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
    cell.setColspan(6);
    
    float[] widths = {12f,88f};
    PdfPTable htable = new PdfPTable(2);
    htable.setWidthPercentage(100f);
    htable.setWidths(widths);
    PdfPCell hcell = new PdfPCell();
    hcell.setBorder(0);
    
    Image img = this.getLogoImage();
    if(img != null) {
      img.scalePercent(15);
      hcell.addElement(new Chunk(img,5,-45));
      htable.addCell(hcell);
    }  else {
      hcell.addElement(new Chunk(""));
      htable.addCell(hcell);
    }
    Paragraph par = new Paragraph("  Indiana Department of Homeland Security (IDHS)",boldBig);
    par.setAlignment(Paragraph.ALIGN_LEFT);
    hcell = new PdfPCell();
    hcell.setBorder(0);
    hcell.addElement(par);
    
    StringBuffer sb = new StringBuffer();
    sb.append("  ").append(title).append("\n\r");
    par = new Paragraph(sb.toString(),boldBig);
    
    par.setAlignment(Paragraph.ALIGN_LEFT);
    hcell.addElement(par);
    htable.addCell(hcell);
    
    cell.addElement(htable);
   
    gridTable.addCell(cell);
  }
  public void add(int gridSpan,String label,String text) throws Exception
  {
    PdfPCell cell = new PdfPCell();
    this.enableBorder(cell);
    cell.setColspan(gridSpan);
    StringBuffer sb = new StringBuffer();
    sb.append(label).append("\n\r");
    Paragraph par = new Paragraph(sb.toString(),normalSmaller);
    sb = new StringBuffer();
    sb.append("   ").append(text).append("\n\r");
    par.add(new Chunk(sb.toString(),boldMedium));
    cell.addElement(par);
    
    gridTable.addCell(cell);
  }
  public void add(int gridSpan,String label,Chunk chunk) throws Exception
  {
    PdfPCell cell = new PdfPCell();
    this.enableBorder(cell);
    cell.setColspan(gridSpan);
    StringBuffer sb = new StringBuffer();
    sb.append(label).append("\n\r");
    Paragraph par = new Paragraph(sb.toString(),normalSmaller);
    sb = new StringBuffer();
    par.add(chunk);
    cell.addElement(par);
    
    gridTable.addCell(cell);
  }
  
  public void add(PdfPCell cell) throws Exception 
  {
    gridTable.addCell(cell);
  }
  
  public void add(String txt) throws Exception 
  {
    PdfPCell cell = new PdfPCell();
    this.enableBorder(cell);
    cell.setColspan(this.gridNumber);
    cell.setPadding(10);
    
    StringBuffer sb = new StringBuffer();
    sb.append(txt).append("\n\r");
    Paragraph par = new Paragraph(sb.toString(),normalSmall);
    
    cell.addElement(par);
    
    gridTable.addCell(cell);
  }
  public void add() throws Exception 
  {
    PdfPCell cell = new PdfPCell();
    this.enableBorder(cell);
    cell.setColspan(this.gridNumber);
    cell.setPadding(0);
    
    StringBuffer sb = new StringBuffer();
    Paragraph par = new Paragraph("\n",normalSmaller);
    
    cell.addElement(par);
    
    gridTable.addCell(cell);
  }
  
  public void add(Paragraph par) throws Exception 
  {
    PdfPCell cell = new PdfPCell();
    this.enableBorder(cell);
    cell.setColspan(this.gridNumber);
    cell.setPadding(10);
    
    cell.addElement(par);
    
    gridTable.addCell(cell);
  }
  
  public void add(int gridSpan,Paragraph par) throws Exception 
  {
    PdfPCell cell = new PdfPCell();
    this.enableBorder(cell);
    cell.setColspan(gridSpan);
    cell.setPadding(10);
    
    cell.addElement(par);
    
    gridTable.addCell(cell);
    
  }
  public void add(PdfPTable table) throws Exception 
  {
    PdfPCell cell = new PdfPCell();
    this.enableBorder(cell);
    cell.setColspan(this.gridNumber);
    cell.addElement(table);
    gridTable.addCell(cell);
  }
  
  
  
  public void add(int gridSpan,String label,PdfPTable table) throws Exception 
  {
    PdfPCell cell = new PdfPCell();
    this.enableBorder(cell);
    cell.setColspan(gridSpan);
    cell.setPadding(10);
    StringBuffer sb = new StringBuffer();
    sb.append(label).append("\n\r");
    Paragraph par = new Paragraph(sb.toString(),normalSmaller);
    cell.addElement(par);
    cell.addElement(table);
    
    gridTable.addCell(cell);
  }
  
  
  
  public PdfPTable getGridTable() 
  {
    return gridTable;
  }
  
  public void enableBorder(PdfPCell cell) 
  {
    if(this.enableBorder) 
    {
      cell.enableBorderSide(PdfPCell.RIGHT);
      cell.enableBorderSide(PdfPCell.LEFT);
      cell.enableBorderSide(PdfPCell.TOP);
      cell.enableBorderSide(PdfPCell.BOTTOM);
    } 
    else 
    {
      cell.disableBorderSide(PdfPCell.RIGHT);
      cell.disableBorderSide(PdfPCell.LEFT);
      cell.disableBorderSide(PdfPCell.TOP);
      cell.disableBorderSide(PdfPCell.BOTTOM);
    }
  }
  

  public boolean isEnableBorder()
  {
    return enableBorder;
  }

  public void setEnableBorder(boolean enableBorder)
  {
    this.enableBorder = enableBorder;
  }

  public boolean isNewPage()
  {
    return newPage;
  }

  public void setNewPage(boolean newPage)
  {
    this.newPage = newPage;
  }



    public int getGridNumber() {
        return gridNumber;
    }
}
