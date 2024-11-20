<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Fire Display  Permits" />
<c:set var="title" scope="request" value="Permit Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Add /Update Display Date</h2> 
<a   href="/dfbs/display/display.do?method=editpermit&ownerKey=<c:out value="${OWNER_SELECTED.ownerKey}"/>&displayKey=<c:out value="${PERMIT_SELECTED.displayPermitKey}"/>">
              [Cancel]</a>


<div id="leftContentFluid">
  <div class="styledBox">
 <html:form action="/displayDate" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveIntDate"/> 
      <html:hidden property="displayId"/> 
       <html:hidden property="displayDateId"/> 
      <html:hidden property="saveType"/> 
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    
      <tr>
      <th scope="row">display date :</th>
      <td>
       <html:text property="displayDate" size="10" maxlength="10"/>(mm/dd/yyyy)
         
       </td>
      </tr>
    <tr>
      <th scope="row">display time:</th>
      <td>
        <html:text property="displayTime" size="20" maxlength="20"/>
         
       </td>
      </tr>
     
  </tbody>
        </table>
        <p>
          <html:submit value="Save Date " styleClass="button"/>
        
        </p>
    </html:form>   
 
</div>
 

