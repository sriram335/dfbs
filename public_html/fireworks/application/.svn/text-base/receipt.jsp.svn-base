 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<%
StringBuffer homeUrl = new java.lang.StringBuffer("<a href='");
    homeUrl.append(application.getInitParameter("app_server"));
    homeUrl.append(request.getContextPath()).append("/fireworks/main.do'>");
    homeUrl.append("Back to DHS Fireworks</a>");
%>

<p>
Thank you for your payment!
</p>
<p>
<%= homeUrl.toString() %>
</p>
