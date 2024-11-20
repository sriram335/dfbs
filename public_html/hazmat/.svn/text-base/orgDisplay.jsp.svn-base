<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
      <c:if test="${sessionScope.HAZMAT_ORG.receiptId == 0 && sessionScope.HAZMAT_ORG.orgName == null}"> 
      <a   href="/dfbs/hazmat/hazmat.do?method=addOrg">
              [ Add organization information ]</a>    
       </c:if>
      <c:if test="${sessionScope.HAZMAT_ORG.orgName != null && sessionScope.HAZMAT_ORG.receiptId == 0 }">         
      <a   href="/dfbs/hazmat/hazmat.do?method=editOrg">
              [ Edit organization information ] </a></br>
      </c:if>
     
     
  
  <div class="styledBox">
    <table cellspacing="0" class="noBorder" >
      <tbody class="rowHeader">
        <tr>
         <th scope="row" colspan="2" style="font-size:large;font-weight:bold;">
            Organization: <c:out value="${sessionScope.HAZMAT_ORG.orgName}"/>
          </th>
        </tr>
         
        <tr>
          <th scope="row"></th>
          <td>
            <c:out value="${sessionScope.HAZMAT_ORG.orgAddress1}"/>
            <br/>
            <c:if test="${sessionScope.HAZMAT_ORG.orgAddress2 != null && sessionScope.HAZMAT_ORG.orgAddress2 != ''}">
              <c:out value="${sessionScope.HAZMAT_ORG.orgAddress2}"/>
              <br/>
            </c:if>
            <c:out value="${sessionScope.HAZMAT_ORG.orgCity}"/>
            <c:out value="${sessionScope.HAZMAT_ORG.orgState}"/>
            <c:out value="${sessionScope.HAZMAT_ORG.orgZip}"/>
            </td>
        </tr>
        
         
      </tbody>
    </table>
    </div>
  
 