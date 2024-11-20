package hazmat.report;


import hs.report.pdf.*;
import java.io.*;
import java.util.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import hs.to.*;
import hs.data.*;
import hazmat.to.*;
import hazmat.data.*;
import hs.util.*;

public class  HazmatPermitPdf extends HsPdf
{




    private java.util.List listOfPermits = null;
    HsPdfGrid grid = null;

    Image signatureImg = null;

    public  HazmatPermitPdf(java.util.List list,OutputStream os,String logo,String watermarkPath,String signturePath, String stateSealPath ) throws Exception
    {
        super(os);
        listOfPermits = list;
        grid = new HsPdfGrid(6);
        grid.setLogoImage(logo);
        //grid.setStateSealImage(stateSealPath);
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
            par = new Paragraph("* No Permit to Print * ",boldBig);
            document.add(par);
            return;
        }


        Iterator i = listOfPermits.iterator();



        while(i.hasNext()) {

            grid.addHeader("Hazmat Division ");

            PermitsToPrint fp = (PermitsToPrint) i.next();




            par = new Paragraph();
            if (fp.getShipRadLevel().equals("LOW")||fp.getShipRadLevel().equals("HIGH"))
            {
                par.add(new Chunk(fp.getShipRadLevel() +" LEVEL RADIOACTIVE WASTE TRANSPORT PERMIT",boldMedium));
            }
            else {
                par.add(new Chunk(fp.getShipRadLevel() +" RADIOACTIVE TRANSPORT PERMIT",boldMedium));
            }
            grid.add(par);

            grid.add(3,"Permit Number:",fp.getShipPermitNumber());
            grid.add(3,"Carrier Name:",fp.getCarrierName());
            grid.add(3,"Permit valid from:",fp.getShipIssueDateString());
            grid.add(3,"Permit valid to:",fp.getShipExpDateString());
            grid.add(3,"Shipment Origin:",fp.getShipOrigin());
            grid.add(3,"Shipment Destination:",fp.getShipDestination());
            grid.add(3,"Shipment Type:",fp.getShipmentType());
            grid.add(3,"Number of Casks:",Integer.toString(fp.getNumberOfCasks()));
            /* ----- BODY BEGIN ----- */




            par = new Paragraph();

            /* begin owner address */
            par.add(new Chunk("Permit issued to: ",normalSmall));
            sb = new StringBuffer();
            sb.append ("\n");
            sb.append (fp.getOrgName()).append("\n");
            par.add(new Chunk(  sb.toString(),boldMedium));

            sb = new StringBuffer();
            sb.append(fp.getOrgAddress1()).append("\n");
            if(fp.getOrgAddress2() != null && !fp.getOrgAddress2().trim().equals(""))
            {
                sb.append(fp.getOrgAddress2()).append("\n");
            }
            sb.append(fp.getOrgCity()).append(", ").append(fp.getOrgState()).append(" ").append(fp.getOrgZip()).append("\n\r");
            par.add(new Chunk(  sb.toString(),boldSmall));

            /* end owner address */




            grid.add(par);


            /* ----- BODY END ----- */


            if(signatureImg != null) {
                signatureImg.scalePercent(8);
                grid.add(3,"State Fire Marshal:",new Chunk(signatureImg,90,-10));
            }  else {
                grid.add(3,"State Fire Marshal:"," ");
            }

            grid.add(3,"Date:",fp.getShipIssueDateString());

            par = new Paragraph();

            /* begin owner address */
            par.add(new Chunk("To verify authenticity of this permit use https://oas.dhs.in.gov/dfbs/hazmat/shipment.do?method=verify ",normalSmall));
            grid.add(par);
            //PRINT MAILING ADDRESS


            PdfContentByte cbu = writer.getDirectContentUnder();
            cbu.beginText();
            cbu.setFontAndSize(bold,12f);
            int Y = 80;
            int h = 15;
            Y = Y - h;
            cbu.setTextMatrix(70,Y);
            cbu.showText(fp.getOrgName());
            cbu.endText();


            cbu.beginText();
            cbu.setFontAndSize(bold,12f);
            Y = Y - h;
            cbu.setTextMatrix(70,Y);
            sb = new StringBuffer();
            sb.append(fp.getOrgAddress1());
            cbu.showText(sb.toString());
            cbu.endText();

            if(fp.getOrgAddress2() != null && !fp.getOrgAddress2().trim().equals(""))
            {
                cbu.beginText();
                cbu.setFontAndSize(bold,12f);
                Y = Y - h;
                cbu.setTextMatrix(70,Y);
                sb = new StringBuffer();
                sb.append(fp.getOrgAddress2());
                cbu.showText(sb.toString());
                cbu.endText();
            }



            cbu.beginText();
            cbu.setFontAndSize(bold,12f);
            Y = Y - h;
            cbu.setTextMatrix(70,Y);
            sb = new StringBuffer();
            sb.append(fp.getOrgCity()).append(", ").append(fp.getOrgState()).append(" ").append(fp.getOrgZip());
            cbu.showText(sb.toString());
            cbu.endText();

            document.add(grid.getGridTable());
            grid.reset();

            document.newPage();


        }
    }







}
