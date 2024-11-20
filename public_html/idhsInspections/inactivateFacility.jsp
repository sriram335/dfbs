<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>

<div id="leftContentFluid"> 
  <h2 style="font-weight:bold;">Inactivate Facility  </h2>
 
 
<html:form action="/search" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveInactivateFacility"/>
 
   <tr>
  <td width="50">
  <div class="listing">
   <c:out value="${sessionScope.INACTIVATE_FACILITY.inspFacId}" /><br/>
   Reason for Inactivity<input type="text"   name="<c:out value='${sessionScope.INACTIVATE_FACILITY.inspFacId}' />" size="100" maxlength="200"/>
   
  </div>
 </td>
 </tr>
   <p>
   
          <html:submit value="Save" styleClass="button" />
   </p>
  </html:form>
  </div>
 