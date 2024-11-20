<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<html:xhtml/>
<c:set var="module" scope="request" value="Child Care Facility Renewal"/>
<c:set var="title" scope="request" value="Child Care Facility Information"/>
<c:set var="step" scope="request" value="2"/>
<c:set var="renew" scope="request" value="renewed"/>

<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <div id="sideContentFluid">
 <jsp:include page="/childCare/application/shoppingCart.jsp"/>

</div>

<div id="mainContentFluid" align="left">

<h2>Child Care Facilities</h2>

 <html:link page="/start.do">[delete and start over]</html:link>

<hr/>
<p class="messageBox" size="100" align="left">
<b><u>Renewal Process:</b></u> :</br>
1.Verify facility information.</br>
2.Verify Owner information.</br>
3.Verify Contact(s) information</br>
idhs/dfbs.Upload room list files.<a   href="/dfbs/childCare/childcare.do?method=downLoadFile&fileName=Child Care Room List Sample.pdf&fileId=1131&fileType=MASTER" target="_blank">
             [sample room list] </a></br>
5.Add the facility to Shopping Cart.</br>
6.Make the payment using Credit card.</br>
This completes the process. You can apply for renewal / pay inspection fees for multiple facilities in one session. Use  Renew / Apply for more permits button.
</p>

    <h3 style="margin-bottom:5px;">
        Permit Number:  <c:out value="${sessionScope.PERMIT_SELECTED.permitNumber}"/>&nbsp; </br>
        Inspector Assigned:<c:out value="${sessionScope.PERMIT_SELECTED.inspectorName}"/>&nbsp;</br> </h3>
        
          <c:out value="${sessionScope.PERMIT_SELECTED.address1}"/>
          <br/>
          <c:if test="${sessionScope.PERMIT_SELECTED.address2 != null && sessionScope.PERMIT_SELECTED.address2 != ''}">
            <c:out value="${sessionScope.PERMIT_SELECTED.address2}"/>
            <br/>
          </c:if>
          <c:out value="${sessionScope.PERMIT_SELECTED.city}"/>,
          <c:out value="${sessionScope.PERMIT_SELECTED.state}"/>
          <c:out value="${sessionScope.PERMIT_SELECTED.zip}"/>&nbsp;</br>
          <c:out value="${sessionScope.PERMIT_SELECTED.county}"/> County;</br> 
     <c:if test="${sessionScope.PERMIT_SELECTED.renewStatus != 'Y'}">
    <a   href="/dfbs/childCare/childcare.do?method=renewPermitForm&ownerKey=<c:out value="${sessionScope.PERMIT_SELECTED.ownerId}"/>&permitKey=<c:out value="${sessionScope.PERMIT_SELECTED.permitNumber}"/>">
              <span >[verify facility information]</span> </a>  
      </c:if>         
     <c:if test="${sessionScope.PERMIT_SELECTED.renewStatus == 'Y'}">
              <img class="iconTrue" src="/dfbs/img/clearbits/check.gif" />
              <span class="message" style="font-weight:bold;font-size:medium;">Verified </span>
      </c:if></br> 
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
  Uploaded Files: <c:forEach var="file" items="${sessionScope.PERMIT_SELECTED.fileList}" varStatus="i">
     <a   href="/dfbs/childCare/childcare.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             <c:out value="${file.fileName}"/> </a>   
                  </c:forEach>
</c:if>
    <table cellspacing="0" style="width:75%;" summary="sales">
      <tbody >
        <tr>
          <th  > <b><u> Owner</b></u>  </th>
          <td>
           <b> <c:out value="${sessionScope.OWNER_RENEW.ownerLastName}"/></b><br/>
            <c:out value="${sessionScope.OWNER_RENEW.ownerAddress1}"/>
            <br/>
            <c:if test="${sessionScope.OWNER_RENEW.ownerAddress2 != null && sessionScope.OWNER_RENEWR.ownerAddress2 != ''}">
              <c:out value="${sessionScope.OWNER_RENEW.ownerAddress2}"/>
              <br/>
            </c:if>
            <c:out value="${sessionScope.OWNER_RENEW.ownerCity}"/>,
            <c:out value="${sessionScope.OWNER_RENEW.ownerState}"/>
            <c:out value="${sessionScope.OWNER_RENEW.ownerZip}"/><br>
             <c:if test="${sessionScope.OWNER_RENEW.renewStatus != 'Y'}">
            <a   href="/dfbs/childCare/application.do?method=renewOwner&ownerKey=<c:out value="${sessionScope.OWNER_RENEW.ownerId}"/>">
              <span >[verify owner information]</span> </a> 
               </c:if>
             <c:if test="${sessionScope.OWNER_RENEW.renewStatus == 'Y'}">
              <img class="iconTrue" src="/dfbs/img/clearbits/check.gif" />
              <span class="message" style="font-weight:bold;font-size:medium;">Verified </span>
             </c:if>
          </td>
        </tr>
       
        <c:forEach var="mapItem" items="${sessionScope.OWNER_RENEW.contactsMap}" varStatus="i">
              <c:set scope="request" var="contact" value="${mapItem.value}"/>
         <tr>
         <th > <b><u> Contacts</b></u></th>
          <td>
             Name: <b> <c:out value="${contact.personFirstName}"/>  <c:out value="${contact.personLastName}"/></b><br/>
             Phone:<c:out value="${contact.personPhone}"/> <br/>
            Fax:   <c:out value="${contact.personFax}"/><br/>
            Email: <c:out value="${contact.personEmail}"/></br>
            <c:if test="${contact.renewStatus != 'Y'}">
             <a   href="/dfbs/childCare/contact.do?method=renewContact&contactKey=<c:out value="${contact.personId}"/>">
              <span >[verify contact information]</span> </a>   
              </c:if>
             <c:if test="${contact.renewStatus == 'Y'}">
              <img class="iconTrue" src="/dfbs/img/clearbits/check.gif" />
              <span class="message" style="font-weight:bold;font-size:medium;">Verified </span>
             </c:if>
             </td>
        </tr>   
        </c:forEach>
        
       
        
        
      </tbody>
    </table> </br></br>
 <c:if test="${sessionScope.UPLOAD_FLAG == 'Y'}">
    <html:form action="/childcare" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="goToUpload"/>
         <input type="submit" value="Upload File(s)/Room list)" class="button"/>
</html:form> </br></br>
</c:if>
 <c:if test="${sessionScope.PERMIT_SELECTED.uploadStatus == 'Y'}">
    <html:form action="/childcare" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="addToCart"/>
         <input type="submit" value="Add facility to Shopping Cart" class="button"/>
</html:form>
</c:if>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }"> 
    <html:form action="/childcare" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="addToCart"/>
         <input type="submit" value="Add facility to Shopping Cart" class="button"/>
</html:form>
</c:if>
</div>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />

