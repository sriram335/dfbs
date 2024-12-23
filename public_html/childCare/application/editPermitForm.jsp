<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Child Care Facility Renewal"/>
<c:set var="title" scope="request" value="Child Care Facility Information"/>
<c:set var="step" scope="request" value="2"/>
<c:set var="renew" scope="request" value="renewed"/>

<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <div id="sideContentFluid">
 <jsp:include page="/childCare/application/shoppingCart.jsp"/>
<c:forEach var="file" items="${sessionScope.PERMIT_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
       <a   href="/dfbs/childCare/childcare.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a></br> 
        </c:if>
          </td>
        <td>
</tr>
</c:forEach>
</div>
<div id="mainBox">
 <h2>Edit Permit</h2>
  
<div id="rightContentFluid">
 <p class="control2">
      <html:link page="/application.do?method=step2">cancel</html:link>
    </p>
</div>
  <div id="leftContentFluid">
    <html:form action="/childcare" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="editPermit"/>
      <html:hidden property="ownerId"/>
      <html:hidden property="applicationKey"/>
       <html:hidden property="receivedDate"/>
       <html:hidden property="active"/>
      <html:hidden property="county" styleId="COUNTY_NAME"/>
      
         <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
            <jsp:include page="permitForm.jsp"/>
            
          </tbody>
        </table>
        <p>
          <html:submit value="Complete Application Process" styleClass="button"/>
        
        </p>
    </html:form>
   

    <c:if test="${requestScope.DFBS_PERMIT.new}">
      <script type="text/javascript">
        setSelectValue('SELECT_COUNTY','COUNTY_NAME');
        showMiscRow();
      </script>
    </c:if> 
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
