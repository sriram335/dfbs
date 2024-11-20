<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
StringBuffer homeUrl = new java.lang.StringBuffer("<a href='");
    homeUrl.append(application.getInitParameter("app_server"));
    homeUrl.append(request.getContextPath()).append("/variance/start.do'>");
    homeUrl.append("Back to IDHS Variance Online Application</a>");
%>

<p>
Thank you for your payment!
</p>
<p>
<%= homeUrl.toString() %>
</p>
