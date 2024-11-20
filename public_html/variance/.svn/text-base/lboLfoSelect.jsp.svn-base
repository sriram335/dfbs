<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">
<div id="sideContentFluid">
<jsp:include page="status.jsp"/>

</div>
<div id="leftContentFluid">

 <h1><b><u>Variance Application Process</u></b></h1></br> </br> 

 <h3><b><u>Add LBO and LFO Details to the Application</u></b></h3></br> </br> 
 
 <%--<span class="error"> <b><u> Important Note:</u></b></br> If you do not have the correct LFO email, leave it blank. Complete the application.
 Find the correct LFO email, come back to the web site and update the LFO email information.Please note that till you complete
 the LFO information and we get a acknowlegement from the LFO about this variance, IDHS will not start the review process of this application</span>
--%>
<table cellspacing="0" class="noBorder" summary="OWNER FORM" >
     <tbody class="rowHeader">
     <html:form action="/start" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="saveLboLfo"/>
  <%--   <html:hidden property="varId"/> 
     <html:hidden property="varCodeId"/>  --%>
   
     
     <tr>
      <th scope="row"  class="required" > * LBO Email  </th>
      <td >Based on the information you have provided the project LBO will be:</br>
           <html:text property="varAppLboEmail" size="50" maxlength="80"/></br>
           
      </td>
    </tr>
	<tr>
      <th scope="row"  class="required" > * LBO Contact Number  </th>
      <td ><html:text property="varAppLboPhone" size="10" maxlength="10"/></br>
      </td>
    </tr>
       <c:if test="${VARIANCE_APP_LFO_LBO_ERROR == 'Y'}">
              <span class="error"> Emails and Contact Number can not be left null </span>
       </c:if>
    <tr>
      <th scope="row"  class="required" > *  LFO Email  </th>
      <td >
          <html:text property="varAppLfoEmail" size="50" maxlength="80"/></br>
         <b> If the e-mail address of the local fire official is in the list below, based on the location of your project, 
          please copy that e-mail address to the text box above.  If that address is not in the list, please obtain the correct e-mail address
          from the appropriate local fire official and insert it in the space above.</b></br></br>
          <c:forEach var="emailContact" items="${sessionScope.VARIANCE_LFO_EMAIL_LIST}" varStatus="i">
               <b> Name : <c:out value="${emailContact.resultCity}"/></b></br>Email:<c:out value="${emailContact.resultAddress1}"/>,Phone:<c:out value="${emailContact.resultAddress2}"/></br>
             </c:forEach>
      </td>
    </tr>
	<tr>
      <th scope="row"  class="required" > * LFO Contact Number  </th>
      <td ><html:text property="varAppLfoPhone" size="10" maxlength="10"/></br>
      </td>
    </tr>
    <tr>
      <th scope="row"   > </th>
      <td >
      <html:submit value="Next" styleClass="button" />
      </td>
      </tr>
     </html:form>
      
 </tbody>
</table>



</div>

<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 