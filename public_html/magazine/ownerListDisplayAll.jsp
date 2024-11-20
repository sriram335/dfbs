<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Magazine Permits" />
<c:set var="title" scope="request" value="Owner List" />
<c:set var="level" scope="request" value="1" />


<jsp:include page="/app/common/hsPageHeader.jsp" />
<a   href="/dfbs/magazine/start.do?method=backToStart">
              [back to start]</a>
<a   href="/dfbs/magazine/start.do?method=renewBy">
              [back to search]</a>
<div id="mainBox">



<div id="sideContentFluid">
<c:if test="${ sessionScope.MAGAZINE_USER == null }">  
  <h2>More Filter Options</h2>
             
          <p>
          <html:form action="/ownerListDisplay" method="get" styleId="filterByCity">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCity" />
         search by magazine location city: <html:select property="city" onchange="submitForm('filterByCity')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="OWNER_CITIES_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>    
              
          <p>
          <html:form action="/ownerListDisplay" method="get" styleId="filterByCounty">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCounty" />
        search by magazine location county: <html:select property="county" onchange="submitForm('filterByCounty')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="OWNER_COUNTY_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>   
   </c:if>       
          
          <h2>Contact Us</h2>
          <p>
          You can contact us via email or by phone
          </p>
          <p>
         <A title=mailto:cclouse@dhs.in.gov href="mailto:cclouse@dhs.in.gov"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/>  </A>: <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/></strong>
          </p>
          <p>
          <A title=mailto:mcarroll@dhs.in.gov href="mailto:mcarroll@dhs.in.gov"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/></A> : <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/></strong>
          </p>
             
  <jsp:include page="feeDetails.jsp"/>
   <jsp:include page="shoppingCart.jsp"/>        
   
</div>


<div id="mainContentFluid" align="left">

<h2>Magazine Owner List </h2>

<div class="charNav">
<ul>
<c:if test="${ sessionScope.MAGAZINE_USER == null }"> 
    
<c:forEach var="c" items="${OWNER_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${OWNER_LIST_FILTER.type == 'byLetter' && OWNER_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
  <li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/magazine/ownerListDisplay.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</c:if>
</ul>
</div>


  <c:forEach var="owner" items="${DFBS_OWNER_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <c:out value="${owner.ownerLastName}" />
  </h3>
  <c:if test="${sessionScope.DFBS_SECURITY == null}"> 
  <c:out value="${owner.ownerCity}" />
  </c:if>
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null || sessionScope.MAGAZINE_USER != null }">  
  <p class="listingInfo">
    <c:out value="${owner.ownerAddress1}" /><br />
    <c:if test="${owner.ownerAddress2 != null && owner.ownerAddress2 != ''}">
    <c:out value="${owner.ownerAddress2}" /><br />
    </c:if>
     </p>
    <c:out value="${owner.ownerCity}" />, <c:out value="${owner.ownerState}" /> <c:out value="${owner.ownerZip}" /><br />
     <a   href="/dfbs/magazine/ownerListDisplay.do?method=updateOwner&ownerId=<c:out value="${owner.ownerId}"/>">
                [ update owner]</a></br>
 <%--   <a   href="/dfbs/magazine/contact.do?method=updateContact&ownerId=<c:out value="${owner.ownerId}"/>">
                [update contact]</a></br> --%>
    <a   href="https://oas.dhs.in.gov/dfbs/magazine/permit.do?method=printPermit&key1=<c:out value="${owner.ownerId}"/>&key2=AllOwner&key3=Single">
                [ print all permit(s) for this owner]</a></br>
    </c:if>
     <c:if test="${ sessionScope.DFBS_SECURITY.groupLevelAll =='DBA' || sessionScope.DFBS_SECURITY.groupLevelFire != null}">
      <a   href="/dfbs/magazine/magazineUser.do?method=addNewUser&ownerId=<c:out value="${owner.ownerId}" />">
                 [ add user]</a>
      </c:if>
    <br />
 
  
 <a   href="/dfbs/magazine/permit.do?method=addPermit&ownerKey=<c:out value="${owner.ownerId}"/>">
             [Add new magazine to this owner]</a> 
<a   href="/dfbs/magazine/start.do?method=showAllPermits&ownerId=<c:out value="${owner.ownerId}"/>">
             [Show all magazines for this owner]</a>
          <table cellspacing="0" style="width:100%;" summary="sales" >
                <tr>
                  <th >file(s) uploaded for this owner</th>
                </tr>
                <tbody>
              <c:forEach var="file" items="${owner.fileList}" varStatus="i">
                   <tr class="row<c:out value='${(i.index % 2) + 1}' />">
                    <td>
                    Files Name: <c:out value="${file.fileName}"/>&nbsp; Upload Date: <c:out value="${file.fileDateString}"/> <br/> 
                    <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }"> 
                     <a   href="/dfbs/magazine/permit.do?method=downLoadFile&fileId=<c:out value="${file.fileId}"/>&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
                           [download this file]</a>
                     </c:if>
                        </td>
                      <td>
              </tr>
              </c:forEach>
                </tbody>
              </table> 

<table cellspacing="0" style="width:100%;" summary="sales">
  <tr>
    <th >permit number</th>
    <th>Address </th>
  </tr>
  <tbody>
    <c:forEach var="magazine" items="${owner.permits}" varStatus="i" >
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
     
    <%--    Permit Number:  <c:out value="${magazine.magPermitNumber}"/>&nbsp; <br/>  --%>
        Identification Number:   <c:out value="${magazine.magIdNumber}"/></span><br/>
        Type: <c:out value="${magazine.magMagType}"/>;Expiration Date: <c:out value="${magazine.magExpDateString}"/><br/>
  
       <c:if test="${ magazine.magIssueDate == null && magazine.feeDue >=0}"> 
       Under process as of  <c:out value="${magazine.magSignedDateString}"/>,to be inspected.
      </c:if>
      <c:if test="${magazine.magIssueDate != null && magazine.magExpDays >=  - 90 }"> 
        <p class="error"> Expires Soon </p>
      <a   href="/dfbs/magazine/ownerListDisplay.do?method=renewDirect&idNumber=<c:out value="${magazine.magIdNumber}"/>&ownerId=<c:out value="${owner.ownerId}"/>">
             [ Renew this magazine ]</a> 
      </c:if>
       <c:if test="${magazine.magIssueDate != null && magazine.magExpDays >= 0 }"> 
      <p class="error"> Expired </p>
      <a   href="/dfbs/magazine/ownerListDisplay.do?method=renewDirect&idNumber=<c:out value="${magazine.magIdNumber}"/>&ownerId=<c:out value="${owner.ownerId}"/>">
             [ Renew this magazine ]</a> 
      </c:if>
     <c:if test="${ sessionScope.DFBS_SECURITY.groupLevelAll =='DBA' || sessionScope.DFBS_SECURITY.groupLevelFire != null}">
      <a   href="/dfbs/magazine/permit.do?method=editInformation&permitKey=<c:out value="${magazine.magIdNumber}"/>&ownerKey=<c:out value="${owner.ownerId}"/>">
                 [ edit permit info]</a>
      </c:if>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null || sessionScope.MAGAZINE_USER != null}">   
      <a   href="/dfbs/magazine/permit.do?method=updatePermit&permitKey=<c:out value="${magazine.magIdNumber}"/>&ownerKey=<c:out value="${owner.ownerId}"/>">
                [ update permit]</a>
          <a   href="/dfbs/magazine/inspection.do?method=inspectionList&permitNumber=<c:out value="${magazine.magIdNumber}"/>">
             [ Inspections List]</a>
            
      </c:if>
      </td>
      
      <td>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">
          <c:out value="${magazine.magAddress1}"/>
          <br/>
          <c:if test="${magazine.magAddress2 != null && magazine.magAddress2 != ''}">
            <c:out value="${magazine.magAddress2}"/>
            <br/>
          </c:if>
          <c:out value="${magazine.magCity}"/>,
          <c:out value="${magazine.magState}"/>
          <c:out value="${magazine.magZip}"/>&nbsp;</br>
           <c:out value="${magazine.magCounty}"/> County;  </br>
          <c:if test="${magazine.magIssueDate == null}"> 
            <a   href="/dfbs/magazine/permit.do?method=approvePermit&key1=<c:out value="${magazine.magIdNumber}"/>">
             [Approve this permit]</a></br>
              <a   href="/dfbs/magazine/permit.do?method=approveAllPermits&key1=<c:out value="${owner.ownerId}"/>&appCounty=<c:out value="${magazine.magCounty}"/>&inspFacId=<c:out value="${magazine.magIdNumber}"/>">
             [Approve all permits for this owner and this county]</a>  
        </c:if>
       <c:if test="${magazine.magIssueDate !=null    && magazine.magExpDays <=0  }"> 
        <a   href="https://oas.dhs.in.gov/dfbs/magazine/permit.do?method=printPermit&key1=<c:out value="${magazine.magIdNumber}" />&key2=Permit&key3=Single">
                [ view permit]</a></br>
      
         </c:if>
          </c:if>
         
          <c:if test="${sessionScope.DFBS_SECURITY == null}">
          <c:out value="${magazine.magCounty}"/> County;  <br/>
         </c:if>
           <c:if test="${magazine.magIssueDate !=null    && magazine.magExpDays <=0 && sessionScope.MAGAZINE_USER != null && sessionScope.DFBS_SECURITY.groupLevelAll == null}"> 
        <a   href="https://oas.dhs.in.gov/dfbs/magazine/permit.do?method=printPermit&key1=<c:out value="${magazine.magIdNumber}" />&key2=Permit&key3=Single">
                [ view permit]</a></br>
             <c:if test="${ magazine.magIssueDate == null && magazine.feeDue >=0}"> 
             Pending Approval.
            </c:if>
      
         </c:if>
        </td>
      </tr>
     </c:forEach>
  </tbody>
</table>
 
  </div>
  </c:forEach>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
</div>