 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>


<hs:control/>

<c:set var="module" scope="request" value="Fireworks" />
<c:set var="title" scope="request" value="IDHS Fireworks Permit Application" />



<jsp:include page="/app/common/hsPageHeader.jsp" />


<h2>Select Permit Year </h2>

<div id="leftContentFluid">

<p class="error">
 Note: 1.For last year permit and permit details contact cclouse@dhs.in.gov </br>
 2.Important!! Product must be in the facility before an inspection will occur!
 </p>
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="permitYear"/> <b><u>Application for Fireworks Permit for year</u> </b></br></br>
           <html:select property="permitYear" styleId="SELECT_PERMIT_YEAR" onchange="setSelectValue('SELECT_PERMIT_YEAR','PERMIT_YEAR')">
          <html:options collection="PERMIT_YEAR_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${PERMIT_YEAR_ERROR == 'ERROR'}">
          <br/>
          <span class="error">* please specify permit year</span> 
        </c:if> </br></br>
        <input type="submit" value="Start" class="button"/></br></br>
  
</html:form>


</div>

<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" /> 
