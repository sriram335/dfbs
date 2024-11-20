<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Owner Application" />


<jsp:include page="/app/common/hsPageHeader.jsp" />
 <div id="sideContentFluid">
  <jsp:include page="shoppingCart.jsp"/>
</div>
<b><u>Owner Information:</u></b>
  <div class="styledBox" align="left">
    <table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
        <tr>
          <th scope="row"  style="font-size:large;font-weight:bold;">NAME:</th>
           <td style="font-size:large;font-weight:bold;">
            <c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerDBA}"/>
          </td>
        </tr>
        <tr>
          <th scope="row">address:</th>
          <td>
            <c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerAddress1}"/>
            <br/>
            <c:if test="${sessionScope.AMUSE_OWNER_SELECTED.ownerAddress2 != null && sessionScope.AMUSE_OWNER_SELECTED.ownerAddress2 != ''}">
              <c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerAddress2}"/>
              <br/>
            </c:if>
            <c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerCity}"/>,
            <c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerState}"/>
            <c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerZip}"/>
          </td>
        </tr>
        <tr>
          <th scope="row">phone:</th>
          <td>
            <c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerPhone}"/>
          </td>
        </tr>
        <tr>
          <th scope="row">fax:</th>
          <td>
            <c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerFax}"/>
          </td>
        </tr>
         <tr>
          <th </th>
          <td>
          <a   href="/dfbs/amuseRide/owner.do?method=feesByOwnerId&ownerId=<c:out value="${owner.ownerId}"/>">
             [pay fees for this owner]</a>  
          <a   href="/dfbs/amuseRide/ride.do?method=addNewRide">
             [add new amusement ride]</a>   
         </td>
          </tr>
      </tbody>
    </table></br>
<c:forEach var="file" items="${sessionScope.AMUSE_OWNER_SELECTED.fileList}" varStatus="i">
<table cellspacing="0" style="width:25%" summary="sales">
<tr>
    <th>Files </th>
  </tr>
  <tbody>
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelElev != null}">  
       <a   href="/dfbs/elevator/elevator.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a></br>
        </c:if>
          </td>
        <td>
</tr>
</tbody>
</table>
</c:forEach>
    <p class="error">
   <b><u>Important Note:</u></b> If you have outstanding fees,the permit(s) will not be issued.</br>
    </p>
   <b><u> Amusement Rides for Owner:</u></b></br>
    <c:forEach var="ride" items="${AMUSE_OWNER_SELECTED.rides}" varStatus="i" >
    Serial Number :<a   href="/dfbs/amuseRide/ride.do?method=updateRide&serialNumber=<c:out value="${ride.serialNumber}"/>"><b><c:out value="${ride.serialNumber}"/></b></a> </br>
    <b><u>Amusement Ride Permit Status:</u></b> Permit Number: <c:out value="${ride.permitNumber}"/> ;
    Permit Application Date:<c:out value="${ride.appDateString}"/> ;
    Permit Expiration Date: <c:out value="${ride.expDateString}"/> ;
     <html:form action="/ride" method="post">
     <input type="hidden" name="method" id="METHOD_KEY" value="rideAction"/>
     <html:hidden property="serialNumber" value="${ride.serialNumber}"/>
       <html:submit value="Apply for Renewal" styleClass="button" property="rideAction"/>
       <c:if test="${sessionScope.DFBS_SECURITY != null}"> 
       <html:submit value="Submit /Print inspection" styleClass="button" property="rideAction"/>
       </c:if>
      </html:form></br>
       </c:forEach>
     
    </div>
  
 