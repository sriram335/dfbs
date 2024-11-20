<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
      <c:if test="${sessionScope.CIGARETTE_COMPANY.receiptId == '0' && sessionScope.CIGARETTE_COMPANY.companyName == null}"> 
      <a   href="/dfbs/cigarette/cigarette.do?method=addCompany">
              [ Select Manufacturer Company ]</a>       
      </c:if>
      <c:if test="${sessionScope.CIGARETTE_COMPANY.receiptId == '0' && sessionScope.CIGARETTE_COMPANY.companyName != null }">         
      <a   href="/dfbs/cigarette/cigarette.do?method=editCompany">
              [ Edit Manufacturer Company Information ] </a></br>
      
      </c:if>
     
  
  <div class="styledBox">
    <table cellspacing="0" class="noBorder" >
      <tbody class="rowHeader">
        <tr>
         <th scope="row"  style="font-size:small;font-weight:bold;"></th>
         <td>
           <b> Company: <c:out value="${sessionScope.CIGARETTE_COMPANY.companyName}"/></b>
          </td>
        </tr>
         
        <tr>
          <th scope="row"></th>
          <td>
            <STRONG>Address:</STRONG></br><c:out value="${sessionScope.CIGARETTE_COMPANY.companyAddress1}"/>
            <br/>
            <c:if test="${sessionScope.CIGARETTE_COMPANY.companyAddress2 != null && sessionScope.CIGARETTE_COMPANY.companyAddress2 != ''}">
              <c:out value="${sessionScope.CIGARETTE_COMPANY.companyAddress2}"/>
              <br/>
            </c:if>
            <c:out value="${sessionScope.CIGARETTE_COMPANY.companyCity}"/>
            <c:out value="${sessionScope.CIGARETTE_COMPANY.companyState}"/>
            <c:out value="${sessionScope.CIGARETTE_COMPANY.companyZip}"/>
            </td>
        </tr>
        
         
      </tbody>
    </table>
    </div>
  
 