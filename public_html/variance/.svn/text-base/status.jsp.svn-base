<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<div class="messageBox">
  <div >
   <p class="messageBox"> <b><u>Status</u></b></br>
   <c:if test="${NEW_VARIANCE_STATUS =='START'}">
    Application Process-In Progress</br> 
     </c:if>
     <c:if test="${NEW_VARIANCE_STATUS =='START_OWNER'}">
    Application Process-In Progress</br> 
    Applicant / Owner Information-In Progress.</br> 
    </c:if>
     <c:if test="${NEW_VARIANCE_STATUS =='END_OWNER'}">
    Application Process-In Progress</br> 
    Applicant / Owner Information-Completed.</br> 
    </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='START_SUBMITTER'}">
    Application Process-In Progress</br> 
    Applicant / Owner Information-Completed.</br> 
    Person Submitting Information-In Progress.</br>  
    </c:if>
      <c:if test="${NEW_VARIANCE_STATUS =='END_SUBMITTER'}">
    Application Process-In Progress</br> 
    Applicant / Owner Information-Completed.</br> 
    Person Submitting Information-Completed.</br>  
    </c:if>
       <c:if test="${NEW_VARIANCE_STATUS =='START_DESIGNER'}">
    Application Process-In Progress</br> 
    Applicant / Owner Information-Completed.</br> 
    Person Submitting Information-Completed.</br>  
    Design Professional Information-In Progress.</br>  
    </c:if>
     <c:if test="${NEW_VARIANCE_STATUS =='END_DESIGNER'}">
    Application Process-In Progress</br> 
    Applicant / Owner Information-Completed.</br> 
    Person Submitting Information-Completed.</br>  
     Design Professional Information-Completed.</br>  
    </c:if>
    <c:if test="${NEW_VARIANCE_STATUS =='START_PROJECT'}">
    Application Process-In Progress</br> 
    Applicant / Owner Information-Completed.</br> 
    Person Submitting Information-Completed.</br>  
    Design Professional Information-Completed.</br>  
    Project Information-In Progress.</br>  
    </c:if>
    <c:if test="${NEW_VARIANCE_STATUS =='END_PROJECT'}">
    Application Process-In Progress</br> 
    Applicant / Owner Information-Completed.</br> 
    Person Submitting Information-Completed.</br>  
    Project Information-Completed.</br>  
    </c:if>
     <c:if test="${NEW_VARIANCE_STATUS =='START_CODE'}">
    Application Process-In Progress</br> 
    Applicant / Owner Information-Completed.</br> 
    Person Submitting Information-Completed.</br>  
    Design Professional Information-Completed.</br>  
    Variance Information-In Progress.</br>  
    </c:if>
    <c:if test="${NEW_VARIANCE_STATUS =='END_CODE'}">
    Application Process-In Progress</br> 
    Applicant / Owner Information-Completed.</br> 
    Person Submitting Information-Completed.</br>  
    Variance Information-Completed.</br>  
    </c:if>
    </p>
     Please note that a transaction fee will be added during checkout for credit card payment.
      ( Total fee = Fee in the shopping cart +$1 portal fee will be added for one online payment. On top of this Total fee  a 2% credit card processing fee will be added (charged by the credit card company)  
      <br/>  Total fee = Fee in the shopping cart +$1 portal fee for echeck payments.)<br/>
   <b> <u class="error">Application Fee:<c:out value="${VARIANCE_APPLICATION_SELECTED.amount}"/>0</u></b>
    <table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th >file(s) uploaded</th>
  </tr>
  <tbody>

<c:forEach var="file" items="${sessionScope.VARIANCE_APPLICATION_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
      Name <c:out value="${file.fileName}"/>&nbsp; Date:<c:out value="${file.fileDateString}"/><br/> 
          </td>
        <td>
</tr>

</c:forEach>

  </tbody>
</table> 
</div>
  
</div>
