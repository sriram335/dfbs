<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Magazine Permits" />
<c:set var="title" scope="request" value="Inspections List" />
<c:set var="level" scope="request" value="1" />


<jsp:include page="/app/common/hsPageHeader.jsp" />
<a   href="/dfbs/magazine/start.do?method=renewByIdNumber&idNumber=<c:out value="${PERMIT_SELECTED.magIdNumber}"/>">
              [back to permit]</a>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
  <a   href="/dfbs/magazine/inspection.do?method=newInspection&idNumber=<c:out value="${PERMIT_SELECTED.magIdNumber}"/>">
             [Add a new inspection]</a>
</c:if>

<table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th >Inspection Details</th>
  </tr>
  <tbody>
   <c:forEach var="inspection" items="${sessionScope.PERMIT_INSPECTIONS.inspections}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
     
        Inspection Date:  <c:out value="${inspection.inspDateString}"/>&nbsp; <br/> 
        Inspector:   <c:out value="${inspection.inspectorName}"/></span><br/></br>
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_magazine_letter.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">[view inspection ]</a>
      <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">      
       <a   href="/dfbs/magazine/inspection.do?method=updateInspection&inspectionId=<c:out value="${inspection.inspId}"/>">
             [Edit this inspection]</a>
      </c:if>
        <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire == 'USER' && inspection.editFlag < 7}">      
       <a   href="/dfbs/magazine/inspection.do?method=updateInspection&inspectionId=<c:out value="${inspection.inspId}"/>">
             [Edit this inspection]</a>
      </c:if>    
          </td>
         
      </tr>
    </c:forEach>
  </tbody>
</table>
 
</div>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
