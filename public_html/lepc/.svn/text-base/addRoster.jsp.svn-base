<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Indiana LEPC's"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
<div id="mainBox">
<a   href="/dfbs/lepc/lepc.do?method=viewLepcYear&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
                 [back to LEPC]</a>
  <a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a>  
  <c:if test="${sessionScope.LEPC_DOC_ROSTER.rosterId == 0 }">
        <a  href="/dfbs/lepc/roster.do?method=copyRoster&lepcId=<c:out value='${sessionScope.LEPC_SELECTED.lepcId}'/>&rosterId=<c:out value='${LEPC_DOC_ROSTER.rosterId}'/>&county=<c:out value='${LEPC_COUNTY_SELECTED}'/>">
                  Copy Roster</a><br/> 
  </c:if>
  <h2>Add / Update LEPC Roster</h2>
  
<p class="error">
Please update the existing roster representatives copied from last year (given below), before adding new representatives.
</p>
<p class="messageBox" size="200" align="left"><u><b> ROSTER</b></u></br>
>>	DO fill ALL 12 roster representative categories -<font color="red"><b> THIS IS THE MINIMUM MEMBERSHIP REQUIREMENT</b></font></br>
>>	DO ensure that ALL roster members have completed the mandatory ethics training</br>
>>	DO include a minimum of 1 member and maximum of 9 members per category</br>
>>	DO NOT submit a roster with ANY category unfilled</br>
>>	DO NOT include a member in more than 1 category </br>
>> ONLY FEDERAL/STATE/LOCAL GOVERNMENT EMPLOYEES CAN HAVE PROXIES
</p>
<div id="leftContentFluid"> 
<table cellspacing="0"  summary="sales"   frame="box">
      <tbody class="rowHeader">  
<html:form action="/roster" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="saveRoster"/>
   <html:hidden property="lepcId"/> 
    <html:hidden property="rosterId"/> 
     <html:hidden property="rosterStatus"/> 
     <html:hidden property="rosterDate"/>
      <html:hidden property="rosterAddBy"/>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > LEPC Address1:</th>
      <td>
       <html:text property="rosterAddress1" size="50" maxlength="50"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > LEPC Address2:</th>
      <td>
       <html:text property="rosterAddress2" size="50" maxlength="50"/>
       </td>     
    </tr>
   
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > LEPC City:</th>
      <td>
       <html:text property="rosterCity" size="30" maxlength="30"/>,IN
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > LEPC Zip:</th>
      <td>
       <html:text property="rosterZip" size="5" maxlength="5"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Email:</th>
      <td>
       <html:text property="rosterEmail" size="50" maxlength="50"/>
       </td>     
    </tr>
  <tr>
      <th scope="row"  style= "width:50%" class="required"  > Chairman:</th>
      <td>
      <c:forEach var="person" items="${sessionScope.ROSTER_REP_LIST_ADMIN}" varStatus="i">
      <c:if test="${person.personType =='Chairman' }">
        Name:<c:out value="${person.personFirstName}"/>&nbsp;<c:out value="${person.personMi}"/>&nbsp;<c:out value="${person.personLastName}"/><br/> 
        Roster Approved Date: <c:out value="${person.lepcDateString}"/></br>
         Email:<c:out value="${person.personEmail}"/> <br/>
        <c:if test="${(OTHER_USER != null || sessionScope.DFBS_SECURITY !=null)}">
            <a  href="/dfbs/lepc/representative.do?method=updateRepresentative&personId=<c:out value='${person.personId}'/>">
                  Edit</a><br/>
                      <span class="error">    To Delete click edit and change person status to "Proposed for Removal" and save the representative.</span><br/>
          <%--  <a  href="/dfbs/lepc/representative.do?method=deleteRepresentative&personId=<c:out value='${person.personId}'/>">
                  delete</a><br/>--%>
            </c:if>
         </c:if> 
       </c:forEach>
       <c:if test="${LEPC_DOC_ROSTER.rosterId >0}">  
      <a  href="/dfbs/lepc/representative.do?method=addRepresentative&rosterId=<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterId}'/>&personType=Chairman">
                  Add Chairman</a><br/>  <br/> 
      </c:if>
       </td>     
    </tr>
      <tr>
      <th scope="row"  style= "width:50%" class="required"  > Vice-Chairman:</th>
      <td>
      <c:forEach var="person" items="${sessionScope.ROSTER_REP_LIST_ADMIN}" varStatus="i">
      <c:if test="${person.personType =='Vice - Chairman' }">
        Name:<c:out value="${person.personFirstName}"/>&nbsp;<c:out value="${person.personMi}"/>&nbsp;<c:out value="${person.personLastName}"/><br/> 
        Roster Approved Date: <c:out value="${person.lepcDateString}"/></br>
         Email:<c:out value="${person.personEmail}"/> <br/>
        <c:if test="${(OTHER_USER != null || sessionScope.DFBS_SECURITY !=null)}">
            <a  href="/dfbs/lepc/representative.do?method=updateRepresentative&personId=<c:out value='${person.personId}'/>">
                  Edit</a><br/>
                      <span class="error">    To Delete click edit and change person status to "Proposed for Removal" and save the representative.</span><br/>
          <%--  <a  href="/dfbs/lepc/representative.do?method=deleteRepresentative&personId=<c:out value='${person.personId}'/>">
                  delete</a><br/>--%>
            </c:if>
         </c:if> 
       </c:forEach>
       <c:if test="${LEPC_DOC_ROSTER.rosterId >0}">  
      <a  href="/dfbs/lepc/representative.do?method=addRepresentative&rosterId=<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterId}'/>&personType=Vice - Chairman">
                  Add Vice - Chairman</a><br/>  <br/> 
      </c:if>
       </td>     
    </tr>
    
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Information Coordinator:</th>
      <td>
      <c:forEach var="person" items="${sessionScope.ROSTER_REP_LIST_ADMIN}" varStatus="i">
      <c:if test="${person.personType =='Information Coordinator' }">
       Name:<c:out value="${person.personFirstName}"/>&nbsp;<c:out value="${person.personMi}"/>&nbsp;<c:out value="${person.personLastName}"/><br/> 
        Roster Approved Date: <c:out value="${person.lepcDateString}"/></br>
         Email:<c:out value="${person.personEmail}"/> <br/>
        <c:if test="${(OTHER_USER != null || sessionScope.DFBS_SECURITY !=null)}">
            <a  href="/dfbs/lepc/representative.do?method=updateRepresentative&personId=<c:out value='${person.personId}'/>">
                  Edit</a><br/>
              <span class="error">    To Delete click edit and change person status to "Proposed for Removal" and save the representative.</span><br/>
        <%--    <a  href="/dfbs/lepc/representative.do?method=deleteRepresentative&personId=<c:out value='${person.personId}'/>">
                  delete</a><br/> --%>
            </c:if>
          </c:if>
       </c:forEach>
       <c:if test="${LEPC_DOC_ROSTER.rosterId >0}">
       <a  href="/dfbs/lepc/representative.do?method=addRepresentative&rosterId=<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterId}'/>&personType=Information Coordinator">
                  Add Information Coordinator</a><br/>  <br/> 
        </c:if>
       </td>     
    </tr> 
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Planning Coordinator:</th>
      <td>
      <c:forEach var="person" items="${sessionScope.ROSTER_REP_LIST_ADMIN}" varStatus="i">
      <c:if test="${person.personType =='Planning Coordinator' }">
        Name:<c:out value="${person.personFirstName}"/>&nbsp;<c:out value="${person.personMi}"/>&nbsp;<c:out value="${person.personLastName}"/><br/> 
        Roster Approved Date: <c:out value="${person.lepcDateString}"/></br>
         Email:<c:out value="${person.personEmail}"/> <br/>
       
           <c:if test="${(OTHER_USER != null || sessionScope.DFBS_SECURITY !=null)}">
            <a  href="/dfbs/lepc/representative.do?method=updateRepresentative&personId=<c:out value='${person.personId}'/>">
                  Edit</a><br/>
                <span class="error">    To Delete click edit and change person status to "Proposed for Removal" and save the representative.</span><br/>
         <%--   <a  href="/dfbs/lepc/representative.do?method=deleteRepresentative&personId=<c:out value='${person.personId}'/>">
                  delete</a><br/> --%>
            </c:if>
          </c:if>
       </c:forEach>
       <c:if test="${LEPC_DOC_ROSTER.rosterId >0}">
        <a  href="/dfbs/lepc/representative.do?method=addRepresentative&rosterId=<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterId}'/>&personType=Planning Coordinator">
                  Add  Planning Coordinator</a><br/>  <br/> 
        </c:if>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Community Emergency Coordinator:</th>
      <td>
       <c:forEach var="person" items="${sessionScope.ROSTER_REP_LIST_ADMIN}" varStatus="i">
      <c:if test="${person.personType =='Community Emergency Coordinator' }">
        Name:<c:out value="${person.personFirstName}"/>&nbsp;<c:out value="${person.personMi}"/>&nbsp;<c:out value="${person.personLastName}"/><br/> 
        Roster Approved Date: <c:out value="${person.lepcDateString}"/></br>
        Email:<c:out value="${person.personEmail}"/> <br/>
        
           <c:if test="${(OTHER_USER != null || sessionScope.DFBS_SECURITY !=null)}">
           <a  href="/dfbs/lepc/representative.do?method=updateRepresentative&personId=<c:out value='${person.personId}'/>">
                  Edit</a><br/>
                      <span class="error">    To Delete click edit and change person status to "Proposed for Removal" and save the representative.</span><br/>
     <%--   <a  href="/dfbs/lepc/representative.do?method=deleteRepresentative&personId=<c:out value='${person.personId}'/>">
                  delete</a><br/> --%>
         </c:if>
          </c:if>
       </c:forEach>
       <c:if test="${LEPC_DOC_ROSTER.rosterId >0}">
       <a  href="/dfbs/lepc/representative.do?method=addRepresentative&rosterId=<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterId}'/>&personType=Community Emergency Coordinator">
                  Add  Community Emergency Coordinator</a><br/>  <br/> 
        </c:if>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Secretary:</th>
      <td>
      <c:forEach var="person" items="${sessionScope.ROSTER_REP_LIST_ADMIN}" varStatus="i">
      <c:if test="${person.personType =='Secretary' }">
        Name:<c:out value="${person.personFirstName}"/>&nbsp;<c:out value="${person.personMi}"/>&nbsp;<c:out value="${person.personLastName}"/><br/> 
        Roster Approved Date: <c:out value="${person.lepcDateString}"/></br>
         Email:<c:out value="${person.personEmail}"/> <br/>
        <c:if test="${(OTHER_USER != null || sessionScope.DFBS_SECURITY !=null)}">
            <a  href="/dfbs/lepc/representative.do?method=updateRepresentative&personId=<c:out value='${person.personId}'/>">
                  Edit</a><br/>
                      <span class="error">    To Delete click edit and change person status to "Proposed for Removal" and save the representative.</span><br/>
          <%--  <a  href="/dfbs/lepc/representative.do?method=deleteRepresentative&personId=<c:out value='${person.personId}'/>">
                  delete</a><br/>--%>
            </c:if>
         </c:if> 
       </c:forEach>
       <c:if test="${LEPC_DOC_ROSTER.rosterId >0}">  
      <a  href="/dfbs/lepc/representative.do?method=addRepresentative&rosterId=<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterId}'/>&personType=Secretary">
                  Add Secretary</a><br/>  <br/> 
      </c:if>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Treasurer:</th>
      <td>
      <c:forEach var="person" items="${sessionScope.ROSTER_REP_LIST_ADMIN}" varStatus="i">
      <c:if test="${person.personType =='Treasurer' }">
        Name:<c:out value="${person.personFirstName}"/>&nbsp;<c:out value="${person.personMi}"/>&nbsp;<c:out value="${person.personLastName}"/><br/> 
        Roster Approved Date: <c:out value="${person.lepcDateString}"/></br>
         Email:<c:out value="${person.personEmail}"/> <br/>
        <c:if test="${(OTHER_USER != null || sessionScope.DFBS_SECURITY !=null)}">
            <a  href="/dfbs/lepc/representative.do?method=updateRepresentative&personId=<c:out value='${person.personId}'/>">
                  Edit</a><br/>
                      <span class="error">    To Delete click edit and change person status to "Proposed for Removal" and save the representative.</span><br/>
          <%--  <a  href="/dfbs/lepc/representative.do?method=deleteRepresentative&personId=<c:out value='${person.personId}'/>">
                  delete</a><br/>--%>
            </c:if>
         </c:if> 
       </c:forEach>
       <c:if test="${LEPC_DOC_ROSTER.rosterId >0}">  
      <a  href="/dfbs/lepc/representative.do?method=addRepresentative&rosterId=<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterId}'/>&personType=Treasurer">
                  Add Treasurer</a><br/>  <br/> 
      </c:if>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Document Location (Tier II):</th>
      <td>
       <html:textarea property="rosterLocation" cols="70"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Telephone 24- hour Emergency:</th>
      <td>
       <html:text property="rosterPhoneEmer" size="10" maxlength="10"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Telephone Administrative Number:</th>
      <td>
       <html:text property="rosterPhoneAdmin" size="10" maxlength="10"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Telephone Alternate:</th>
      <td>
       <html:text property="rosterPhoneAlt" size="10" maxlength="10"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Fax:</th>
      <td>
       <html:text property="rosterFax" size="10" maxlength="10"/>
       </td>     
    </tr> 
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Status:</th>
      <td>
       <c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterStatus}'/>
       </td>     
    </tr>
<tr>
<th> </th>
<td>
<c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' || sessionScope.OTHER_USER.userLoginId != null)}"> 
  <html:submit value="Save Roster" styleClass="button" />
  
</c:if>
</td>
</tr>
</html:form>
</tbody>
</table> <br/>  <br/> 

<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' || sessionScope.OTHER_USER.userLoginId != null}"> 
<c:if test="${sessionScope.LEPC_DOC_ROSTER.rosterId > 0 }">
<a  href="/dfbs/lepc/representative.do?method=addRepresentative&rosterId=<c:out value='${sessionScope.LEPC_DOC_ROSTER.rosterId}'/>">
                  Add Representative</a><br/>  <br/> 
                  
</c:if>
<p class="error"> <c:out value="${sessionScope.ROSTER_REP_CHECK}"/></p>
<b><u>Representatives</u></b><br/>  <br/> 
 <table cellspacing="0" style="width:100%;" summary="sales" >
  
<c:forEach var="person" items="${sessionScope.ROSTER_REP_LIST}" varStatus="i">
 <tr>
    <th >Representative</th>
    <th>Proxy </th>
  </tr>
  <tbody>
  <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
        <b><c:out value="${person.personType}"/></b>
         <c:if test="${person.personDept != null}">
         &nbsp;(<c:out value="${person.personDept}"/>)
         </c:if><br/> 
        Name:<c:out value="${person.personFirstName}"/>&nbsp;<c:out value="${person.personMi}"/>&nbsp;<c:out value="${person.personLastName}"/><br/> 
        Roster Added Date: <c:out value="${person.addDateString}"/></br>
         Email:<c:out value="${person.personEmail}"/> <br/>
        <c:if test="${sessionScope.LEPC_DOC_ROSTER.rosterId > 0 }">
    <%--    <c:if test="${LEPC_DOC_ROSTER.rosterAddBy == OTHER_USER.userLoginId }"> --%>
        <c:if test="${(OTHER_USER != null || sessionScope.DFBS_SECURITY !=null)}">
         <a  href="/dfbs/lepc/representative.do?method=updateRepresentative&personId=<c:out value='${person.personId}'/>">
                  Edit</a><br/>
              <span class="error">    To Delete click edit and change person status to "Proposed for Removal" and save the representative.</span>
       <%--  <html:form action="/representative" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="deleteRepresentative"/> 
          <input type="hidden" name="personId"   value ="${person.personId}"/> 
          <input type="submit" value="Delete Rep" class="button"/>
          </html:form> --%>
         </c:if>
        </c:if>
    
        <a  href="/dfbs/lepc/representative.do?method=proxyQuestion&personId=<c:out value='${person.personId}'/>">
                  Add Representative Proxy</a> <br/> 
        </td>
       <td>
    <c:forEach var="proxy" items="${person.rosterProxies}" varStatus="i">
        Name:<c:out value="${proxy.personFirstName}"/>&nbsp;<c:out value="${proxy.personMi}"/>&nbsp;<c:out value="${proxy.personLastName}"/><br/> 
      <%--  Last Roster Approved Date: <c:out value="${proxy.lepcDateString}"/></br>--%>
         Email:<c:out value="${proxy.personEmail}"/> <br/>
       
        <c:if test="${(OTHER_USER != null || sessionScope.DFBS_SECURITY !=null)}">
         <a  href="/dfbs/lepc/representative.do?method=updateRepresentativeProxy&proxyId=<c:out value='${proxy.personId}'/>">
                  Edit</a><br/>
          <html:form action="/representative" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="deleteProxy"/> 
          <input type="hidden" name="personId"   value ="${proxy.personId}"/> 
          <input type="submit" value="Delete Proxy" class="button"/>
         </html:form>
         </c:if>
      </c:forEach>
      </td>
      </tr>
      </tbody>
</c:forEach>
</table>
</c:if>

</div>
  <div class="clearer">&nbsp;</div>
 </div> 