<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<table cellspacing="0" style="width:80%;" summary="sales">
  <tr>
    <th ><b> Brand Family(s):</b></th>
  </tr>
  <tbody>
   
    <c:forEach var="mapItem" items="${sessionScope.UNIQUE_BRANDS}" varStatus="j">
              <c:set scope="request" var="brand" value="${mapItem.value}"/>
      <tr class="row<c:out value='${(j.index % 2) + 1}' />">
        <td>
          <FONT color="#ff3333"><b>Cigarette Brand Family Name:
              <c:out value="${brand.cigaretteBrand}"/>
            </b>
          </FONT></br>
           <c:if test="${sessionScope.CIGARETTE_COMPANY.receiptId == 0}">
           <a href="/dfbs/cigarette/brand.do?method=addSameBrand&brandName=<c:out value="${brand.cigaretteBrand}"/>">
           [Add same brand family different cigarette style]</a> </br>
           </c:if>
         
<table cellspacing="0" style="width:100%;" summary="sales">
  <tr>
    <th >cigarette details</th>
  </tr>
  <tbody>
   
    <c:forEach var="mapItem" items="${sessionScope.CIGARETTE_APPLICATION.brandsMap}" varStatus="i">
              <c:set scope="request" var="cigarette" value="${mapItem.value}"/>
     <c:if test="${brand.cigaretteBrand == cigarette.cigaretteBrand}">
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
         <b> style:</b><c:out value="${cigarette.cigaretteStyle}"/>&nbsp; 
         <b> marking:</b><c:out value="${cigarette.cigaretteMarking}"/>&nbsp;  
         <b> l/c (in mm):</b><c:out value="${cigarette.cigaretteLength}"/>/<c:out value="${cigarette.cigaretteCircumference}"/>&nbsp;   
         <c:if test="${sessionScope.CIGARETTE_COMPANY.receiptId == '0'}">  
           <html:link page="/brand.do?method=editBrand" paramId="key" paramName="mapItem" paramProperty="key">
                 <span >[edit cigarette]</span>                 
                 </html:link>
           </c:if>  </br>      
         <b> flavor:
         <c:if test="${brand.cigaretteFlavorOther != null}">
          </b><c:out value="${cigarette.cigaretteFlavorOther}"/>&nbsp;
         </c:if>
         <c:if test="${brand.cigaretteFlavorOther == ''}">
         </b><c:out value="${cigarette.cigaretteFlavor}"/>&nbsp;
         </c:if>
         <b> filter:</b><c:out value="${cigarette.cigaretteFilter}"/>&nbsp; 
         <b> package:</b><c:out value="${cigarette.cigarettePackage}"/>&nbsp;
        <c:if test="${sessionScope.CIGARETTE_COMPANY.receiptId == '0'}">       
         <html:link page="/brand.do?method=removeBrand" paramId="key" paramName="mapItem" paramProperty="key">
                 <span >[remove cigarette]</span>
         </html:link>    
         
         </c:if> 
        </td>
      </tr>
    </c:if>
    </c:forEach>
   
  </tbody>
</table>
  </td>
      </tr>
  
    </c:forEach>
   
  </tbody>
</table>
