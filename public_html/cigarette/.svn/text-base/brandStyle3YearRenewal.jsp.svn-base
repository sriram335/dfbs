<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
<h2> <u>Select Brands/Styles you want to Renew for next 3 years</u></h2>
</br></br>
<p class=messageBox>
Hint: Use Renew all to renew all, if you don't want to renew few styles un-check them. If you are editing the styles, finish all editing before you
using this option.
<html:form action="/application" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="renewAll"/> 
  <input type="submit" value="Renew All" class="button" />  </br> 
</html:form>  
</p>
 <a href="/dfbs/cigarette/brand.do?method=addBrand3YearRenewal">
              [Add new style ]</a> 
<html:form action="/application" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="addBrandsFor3YearRenewal"/>

<div id="mainContentFluid">
         
<table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th >Brand</th>
    <th style="width:30%;">Cigarette Style</th>
    <th >Length</th>
    <th >Circum-</br>ference</th>
    <th >Marking</th>
    <th >Cigarette Flavor</th> 
    <th >Filter</th> 
    <th >Cigarette Package</th> 
    <th >Renew?</th>
  </tr>
  <tbody>
  
   <c:forEach var="brand" items="${sessionScope.CIGARETTE_BRAND_RENEWAL_LIST}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
      <%-- <c:out value="${brand.cigaretteId}"/>&nbsp; <br/> --%>
       <c:out value="${brand.cigaretteBrand}"/>&nbsp; <br/> 
       <a   href="/dfbs/cigarette/brand.do?method=editBrand3YearRenewal&brandId=<c:out value="${brand.cigaretteId}"/>">
              [ Edit ]</a>  
      </td>
        <td>
       <c:out value="${brand.cigaretteStyle}"/>&nbsp; <br/> 
         </td>
         <td>
       <c:out value="${brand.cigaretteLength}"/>&nbsp; <br/> 
         </td>
         <td>
       <c:out value="${brand.cigaretteCircumference}"/>&nbsp; <br/> 
         </td>
        <td>
       <c:out value="${brand.cigaretteMarking}"/>&nbsp; <br/> 
         </td>
         <td>
       <c:out value="${brand.cigaretteFlavor}"/>&nbsp; <c:out value="${brand.cigaretteFlavorOther}"/>&nbsp;<br/> 
         </td>
         <td>
       <c:out value="${brand.cigaretteFilter}"/>&nbsp; <br/> 
         </td>
         <td>
       <c:out value="${brand.cigarettePackage}"/>&nbsp; <br/> 
         </td>
        <td>
        <c:if test="${(sessionScope.CIGARETTE_RENEW_ALL=='Y')}">
         <b>Renew:</b><input type="checkbox" name="<c:out value='${brand.cigaretteId}' />" class="switch" value="Y" checked/>
         </c:if>
         <c:if test="${(sessionScope.CIGARETTE_RENEW_ALL!='Y')}">
         <b>Renew:</b><input type="checkbox" name="<c:out value='${brand.cigaretteId}' />" class="switch" value="Y" />
        </c:if>
       </td>
      </tr>
    </c:forEach>
  </tbody>
</table></br></br>

             <input type="submit" value="Renew Selected Brands" class="button" />  </br> 
            
</html:form>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />