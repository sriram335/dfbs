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
<a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a>  
<div id="mainBox">
  <h2>Add LEPC Year</h2>

<div id="leftContentFluid">
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader">  
<html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="saveLepcYear"/>
<tr>
      <th scope="row"  style= "width:50%" class="required"  > Enter Year (YYYY):</th>
      <td>
       <html:text property="lepcYear" size="5" maxlength="5"/>
       <c:if test="${sessionScope.LEPC_YEAR_CHECK == 'Y'}"> 
      <span class="error">This year is already in database, you can not add again</span>
      </c:if>  
       </td>
     
    </tr>
<tr>
<th> </th>
<td>

 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null}"> 
 
  <html:submit value="Add Year" styleClass="button" />
  </c:if>    

</td>
</tr>
</html:form>
</tbody>
</table>
</div>
  <div class="clearer">&nbsp;</div>
 </div> 