 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ page contentType="application/pdf"%>


<%--

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.io.*" %>
<%@ page import="oracle.jdbc.*" %> 
<%@ page import="hs.to.*" %>
<%@ page import="fireworks.hs.to.*" %>
<%@ page import="hs.util.*" %>
<%@ page import="org.apache.struts.util.*" %>

<%@ page import="hs.data.*" %>
<%@ page import="hs.data.system.*" %>
<%@ page import="fireworks.hs.data.*" %>
<%@ page import="hs.report.pdf.*" %>
<%@ page import="fireworks.hs.report.*" %>

 





<%
DfbsDataMap dmap2 = (DfbsDataMap) application.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
DfbsFireworksPermitDAO pDAO = (DfbsFireworksPermitDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_PERMIT);

String permitNumber = request.getParameter("permitNumber");
          List list = new ArrayList();
          if(permitNumber == null) {
            String url = request.getRemoteAddr();
            if(url != null && url.substring(0,8).equals("10.90.27")) {
              list = pDAO.selectPermitToPrint(false);
              ByteArrayOutputStream baos = new ByteArrayOutputStream();
          HsFireworksWholesalerCompliancePdf pdf = 
            new HsFireworksWholesalerCompliancePdf(
              list,baos,application.getRealPath("/WEB-INF/img/dhs_logo.jpg"),
                application.getRealPath("/WEB-INF/img/dhs_logo2.jpg"),
                  application.getRealPath("/WEB-INF/img/director.gif"));           
          request.setAttribute("HS_PDF",pdf);

          request.getRequestDispatcher("/pdf.do").include(request,response);
             
            } 
            else 
            {
             request.setAttribute("HS_ERROR","UNAUTHORIZED_ACCESS");
             response.sendRedirect("/dfbs/fireworks/error.do");  
            }
           
          
            
          } 
          else 
          {
            list = pDAO.selectPermitToPrint(false,permitNumber);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
          HsFireworksWholesalerCompliancePdf pdf = 
            new HsFireworksWholesalerCompliancePdf(
              list,baos,application.getRealPath("/WEB-INF/img/dhs_logo.jpg"),
                application.getRealPath("/WEB-INF/img/dhs_logo2.jpg"),
                  application.getRealPath("/WEB-INF/img/director.gif"));           
          request.setAttribute("HS_PDF",pdf);

          request.getRequestDispatcher("/pdf.do").include(request,response);
           
          }
          
           
          

%> --%>

