<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<span class="required" >
<a   href="/dfbs/elevator/servContractor.do?method=backToStart">
              [Clear all and start over]</a></br></br>
              </span>
     
     
 <c:if test="${sessionScope.SERVICE_CONTRACTOR_SELECTED.contFee > 0}">  
<c:if test="${sessionScope.SERVICE_CONTRACTOR_SELECTED.fileCount > 0}">
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll == null }"> 
 <html:form action="/checkoutCont" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="checkout"/>
 <p class="error">
      <input type="checkbox" name="affirmation"  class="switch"  value="Y"/>
     <b>By checking this box , I hereby affirm under penalty of perjury  that all the information provided through this
     online application process is true to the best of my knowledge.You have to agree to this to complete online transaction</b> 
     </p>  
      <p>
      <c:if test="${sessionScope.IDHS_ELEV_CONT_AFFIRMATION=='Y'}"> 
      You must read, agree and check the check box above to check out!      
      </c:if>
        <input type="submit" value="proceed to checkout" class="button"/>
      </p>
      <p class="messageBox">
      Please note the added transaction fee during checkout.  
      </p>
     
</html:form>
</c:if>
</c:if>
</c:if>  
<c:if test="${sessionScope.SERVICE_CONTRACTOR_SELECTED.contFee > 0}">  
<c:if test="${sessionScope.SERVICE_CONTRACTOR_SELECTED.contId > 0}">  
 <p class="msgbox" align="left">
       Please upload the following documents before proceeding to check out.
       Use the upload link provided below to upload the documents.</br>
       1.Proof of continuing education.</br>
       2.Proof of insurance.</br>
       3.Proof of worker's compensation coverage.</br>
       4.Criminal History.</br>
       5.For details on the  above in requirenments please visit 
        <a   href="http://forms.in.gov/Download.aspx?id=7144">
              <span > http://www.in.gov/dhs/2625.htm  </span></a></br> 
</p>

</c:if>
<c:if test="${sessionScope.SERVICE_CONTRACTOR_SELECTED.contId == 0}">  
 <p class="msgbox" align="left">
       Please upload the following documents before proceeding to check out.
       Use the upload link provided below to upload the documents.</br>
       1.Proof of eligibility.</br>
       2.Proof of insurance.</br>
       3.Criminal History.</br>
       4.For details on the  above in requirenments please visit 
        <a   href="http://forms.in.gov/Download.aspx?id=6464">
              <span > http://www.in.gov/dhs/2625.htm  </span></a></br> 
</p>

</c:if>
</c:if>

  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelElev != null }">     
 <html:form action="/checkoutCont" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="check"/>
      
       Check Number:<html:text property="receiptId" size="30" maxlength="30"/>
      <p>
        <input type="submit" value="Received Check / Save " class="button"/>
      </p>
</html:form>
 
</c:if>  

<table cellspacing="0" style="width: 75%;" summary="sales">

  <tr>
    <th>Shopping Cart </th>
  </tr>
  <tbody>
  <tr>
  <td>
 <span class="required" >
 <U><b>In this session</b></U></br>
  Total Owners:<c:out value="${sessionScope.SERVICE_CONTRACTOR_SELECTED.contLastName}"/>,<c:out value="${sessionScope.SERVICE_CONTRACTOR_SELECTED.contFirstName}"/></br> 
  Fees:<c:out value="${sessionScope.SERVICE_CONTRACTOR_SELECTED.contFee}"/>0</span>
</td>
</tr>
<tr >
<td>
<U>Files Uploaded:</U> 
</td>
</tr>
    
       <c:forEach var="file" items="${SERVICE_CONTRACTOR_SELECTED.fileList}" varStatus="i">
              <tr>
                <td>
                     <c:out value="${file.fileName}"/>&nbsp; <br/> 
                </td>
                </tr>
            </c:forEach>
  
                  <a href="/dfbs/elevator/servContractor.do?method=goToUpload&idNumber=<c:out value="${sessionScope.SERVICE_CONTRACTOR_SELECTED.contId}"/>&idType=ElevatorCont">
                    [upload files]</a>
             
      
  </tbody>
</table>




<h2>Contact Us</h2>
          <p>
          You can contact us via email or by phone
          </p>
          <p>
         <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"  href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/>  </A>: <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/></strong>
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/></A> : <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/></strong>
          </p>   </br>   </br>          
        
          
