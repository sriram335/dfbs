<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
StringBuffer homeUrl = new java.lang.StringBuffer("<a href='");
    homeUrl.append(application.getInitParameter("app_server"));
StringBuffer homeUrl2 = new java.lang.StringBuffer("<a href='");
    homeUrl2.append(application.getInitParameter("app_server"));    
    homeUrl.append(request.getContextPath()).append("/cigarette/cigarette.do?method=cigaretteApp'>");
    homeUrl2.append(request.getContextPath()).append("/cigarette/cigarette.do?method=printApplication'>");
    homeUrl.append("Back to DHS Cigarette Application</a>");
    homeUrl2.append("View and Print Application</a>");
%>
 <p>
Thank you for your payment! Your application process is complete. Click the link below to return to "DHS Cigarette Application".
You need to  print the application and sign and mail the application along with other relavant documentation to the
address listed on the application. Use the links in the box to complete the tasks.
</p>
<p>
<%= homeUrl.toString() %>
</p>
<p>
<%= homeUrl2.toString() %>
</p>


