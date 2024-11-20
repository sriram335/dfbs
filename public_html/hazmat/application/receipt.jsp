<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>

<%
StringBuffer homeUrl = new java.lang.StringBuffer("<a href='");
    homeUrl.append(application.getInitParameter("app_server"));
    homeUrl.append(request.getContextPath()).append("/hazmat/hazmat.do?method=hazmatApp'>");
    homeUrl.append("Back to DHS Hazardous Material Transport Permit</a>");
    StringBuffer homeUrl2 = new java.lang.StringBuffer("<a href='");
    homeUrl2.append(application.getInitParameter("app_server"));
    homeUrl2.append(request.getContextPath()).append("/hazmat/hazmat.do?method=printAllPermits'>");
    homeUrl2.append("View and Print Permit</a>");
%>

<p>
Thank you for your payment! Click the link below to return to main application and
print your permits.
</p>
<p>
<%= homeUrl.toString() %>
</p>
<p>
<%= homeUrl2.toString() %>
</p>