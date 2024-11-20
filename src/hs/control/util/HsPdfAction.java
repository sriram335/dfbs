package hs.control.util;

import hs.control.ProtectedAction;



import org.apache.struts.action.*;
import org.apache.struts.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import hs.control.*;
import hs.to.*;
import hs.report.pdf.*;
import hs.data.*;



import hs.data.system.*;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.*;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.Image;


public class HsPdfAction extends ControlAction 
{
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
          makePdf(request, response);
          return null;
      }
       catch (Exception e) 
      {
         System.out.println(e.getMessage());
         saveError(request,e);
         return mapping.findForward("error");
      }
      
  }
	public void makePdf(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException,Exception {
		try {
      HsPdf pdf = (HsPdf) request.getAttribute("HS_PDF");
      
      if(pdf == null) throw new Exception("No pdf file to write!");
    
      pdf.write();
    
      ByteArrayOutputStream baos = (ByteArrayOutputStream) pdf.getOutputStream();
      
			// setting some response headers
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// setting the content type
			response.setContentType("application/pdf");
			// the contentlength is needed for MSIE!!!
			response.setContentLength(baos.size());
			// write ByteArrayOutputStream to the ServletOutputStream
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();

		} catch (Exception e2) {
                        throw new Exception("Error in " + getClass().getName() + "\n" + e2);
		}
	}
}