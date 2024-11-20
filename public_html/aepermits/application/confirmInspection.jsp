<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>untitled</title>
  </head>
  <body>
   <c:if test="${sessionScope.INSPECTION_SAVED_BB == 'True'}">
  Inspection Added to Database. View permit status to make sure that Inspection is added and Permit is ready for print.</br>
       <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_entertainment_list_tobe_inspected.rdf&p_id=<c:out value="${INSPECTION_SAVED.inspFacId}"/>">[view permit status ]</a></br>
  </c:if>
   <c:if test="${sessionScope.INSPECTION_SAVED_BB == 'False'}">
  Inspection Adding Error Contact DfbsDatabase@dhs.in.gov, if data entered is correct.(You can enter compliance inspection only and you need to enter password from black berry.</br>
  </c:if>
  </body>
</html>