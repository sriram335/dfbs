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
          <a   href="/dfbs/amuseRide/owner.do?method=editOwner&key=<c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerKey}"/>">
             [Edit Owner]</a>   
         </td>
          </tr>
      </tbody>
    </table></br>
  <b><u> <a   href="/dfbs/amuseRide/ride.do?method=addNewRide">
             [Add New Amusement Ride]</a>  </br> 
   <a   href="/dfbs/amuseRide/owner.do?method=view&ownerId=<c:out value="${AMUSE_OWNER_SELECTED.ownerId}"/>">
             [view rides list]</a></br> 
    Insurances for Owner:</u></b></br>
    <a   href="/dfbs/amuseRide/insurance.do?method=addNewInsurance"><b>[Add New Insurance]</b></a> </br>
   
   <%-- <c:forEach var="mapItem" items="${sessionScope.AMUSE_OWNER_SELECTED.insMap}" varStatus="i">
              <c:set scope="request" var="ins" value="${mapItem.value}"/>
    Policy Number :<a   href="/dfbs/amuseRide/insurance.do?method=updateInsurance&insAppKey=<c:out value="${ins.insuranceId}"/>&ownerKey=<c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerKey}"/>"><b><c:out value="${ins.policyNumber}"/></b></a> </br>
    Insurance Company: <c:out value="${ins.insName}"/> ;
    Permit Effective Date:<c:out value="${ins.effDateString}"/> ;
    Permit Expiration Date: <c:out value="${ins.expDateString}"/> ;
    <a   href="/dfbs/amuseRide/insurance.do?method=updateInsurance&insAppKey=<c:out value="${ins.insuranceId}"/>&ownerKey=<c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerKey}"/>"><b>[Edit]</b></a> </br>
       </c:forEach> --%>
  <html:form action="/insurance" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="noInsChange"/>
      <p>
        <input type="submit" value="No Changes to Insurance Information " class="button"/>
      </p>
</html:form>
  <c:forEach var="ins" items="${sessionScope.AMUSE_OWNER_SELECTED.insList}" varStatus="i" >
  
  <div class="listing">
  Policy Number :<a   href="/dfbs/amuseRide/insurance.do?method=updateInsurance&insAppKey=<c:out value="${ins.insuranceId}"/>&ownerKey=<c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerKey}"/>"><b><c:out value="${ins.policyNumber}"/></b></a> </br>
    Insurance Company: <c:out value="${ins.insName}"/> ;
    Permit Effective Date:<c:out value="${ins.effDateString}"/> ;
    Permit Expiration Date: <c:out value="${ins.expDateString}"/> ;
    <a   href="/dfbs/amuseRide/insurance.do?method=updateInsurance&insAppKey=<c:out value="${ins.insuranceId}"/>&ownerKey=<c:out value="${sessionScope.AMUSE_OWNER_SELECTED.ownerKey}"/>"><b>[Edit]</b></a> </br>
         
  </div>
  
  </c:forEach>
     
    </div>
  
 