<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Inspections Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
<c:if test="${RESULTS_FLAG == 'DAYCARE'}">
  <b><u>Child Care(s):</u></b></br>
   <c:forEach var="dayCare" items="${DAYCARE_SEARCH_LIST.list}" varStatus="d"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          <c:out value="${dayCare.resultType}" /> ID:<c:out value="${dayCare.resultId}"/>
        </h3>
          <p class="listingInfo">
          Location Address: <br/>
           <c:out value="${dayCare.facilityName}" /><br/>
            <c:out value="${dayCare.resultAddress1}" />,
            <c:out value="${dayCare.resultAddress2}" /></br>
            <c:out value="${dayCare.resultCity}" />,<c:out value="${dayCare.resultZip}" />,<c:out value="${dayCare.resultCounty}" />
            </br>
          </p>
      <a   href="/dfbs/idhsInspections/search.do?method=searchByIdFssa&idNumber=<c:out value="${dayCare.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
     </div>
 
  </c:forEach>
</c:if>
</div> 
<div id="mainBox" align="left">
<%--<a   href="/dfbs/idhsInspections/search.do?method=daycareFssa">
 Child Care(s)[<c:out value="${sessionScope.CURRENT_SEARCH.daycareCount}"/>] </a>&nbsp; --%>
<a   href="/dfbs/idhsInspections/idhsInspection.do?method=searchFssa"> 
       Search Start Over </a>&nbsp;       
 
<div id="leftContentFluid">

<b><u>Search by ID</u></b>
<html:form action="/search" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="searchByIdFssa"/> 
 
  <p align="left">
                 Facility ID:<html:text property="idNumber" size="30" maxlength="30"/> </br>
                 <c:if test="${OTHER_USER !=null }"> 
                  <input type="submit" value="Search" class="button"/>
                  </c:if>
        </br>        
</p>
</html:form>

<b><u>Search by </u></b></br> needs Street Number and (City or County))
<html:form action="/search" method="post" >
            <input type="hidden" name="method" id="METHOD_KEY" value="searchPatternFssa"/> 
   <p align="left">
                Facility Name&nbsp;:<html:text property="facilityName" size="30" maxlength="30"/> </br>
             <FONT color="#009933">     (To narrow down search for Cheese Cake Factory use Cheese and city or county combination)</br></FONT>
                Street Number:<html:text property="streetNumber" size="6" maxlength="6"/> </br>
                Street Name&nbsp;&nbsp;&nbsp;:<html:text property="streetName" size="20" maxlength="20"/> </br>
             <FONT color="#009933">   (To narrow down search for west washington st use wash)</br></FONT>
                Facility City&nbsp;&nbsp;&nbsp;&nbsp;:<html:text property="city" size="30" maxlength="30"/></br>
                Facility County:<html:select property="county" >
                <html:option value="">-----</html:option>
                <html:options 
                  collection="DFBS_COUNTY_OPTIONS" 
                  property="value" 
                  labelProperty="label" />
                </html:select>  
                Facility Zip:<html:text property="zip" size="5" maxlength="5"/></br>
                  Show Records&nbsp;:<html:select property="searchActive" >
                <html:option value="Active">Active</html:option>
                <html:option value="All">All</html:option>
                </html:select>   </br> 
               <html:text property="inspStartDate" size="10" maxlength="10"/> (mm/dd/yyyy)<a href="javascript:NewCal('inspStartDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a>
               <html:text property="inspEndDate" size="10" maxlength="10"/> (mm/dd/yyyy)<a href="javascript:NewCal('inspEndDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a></br>

            <c:if test="${OTHER_USER !=null }"> 
            <input type="submit" value="Search" class="button"/></br>
            </c:if>
            <c:if test="${START_FORM_ERROR.streetNumber == 0 && START_FORM_ERROR.streetName == null}">
               <span class="error">
               * please enter street number and street name for search</span> 
              </c:if></br>
</p>
</html:form>

</div>
<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 