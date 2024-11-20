<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<div id="sideContentFluid">
     <a   href="/dfbs/code/main.do?method=startOverForm&manufacturerNameId=<c:out value="${sessionScope.MANUFACTURER.manufacturerNameId}"/>">
              [ Cancel and Start Over New ]</a>               
  </div>
  <div class="styledBox">
    <table cellspacing="0" class="noBorder" summary="MANUFACTURER DATA">
      <tbody class="rowHeader">
        <tr>
          <th scope="row" colspan="2" style="font-size:large;font-weight:bold;">
            <c:out value="${sessionScope.MANUFACTURER.manufacturerName}"/>
          </th>
        </tr>
        <tr>
          <th scope="row">address:</th>
          <td>
            <c:out value="${sessionScope.MANUFACTURER.manufacturerAddress1}"/>
            <br/>
            <c:if test="${sessionScope.MANUFACTURER.manufacturerAddress2 != null && sessionScope.MANUFACTURER.manufacturerAddress2 != ''}">
              <c:out value="${sessionScope.MANUFACTURER.manufacturerAddress2}"/>
              <br/>
            </c:if>
            <c:out value="${sessionScope.MANUFACTURER.manufacturerCity}"/>,
            <c:out value="${sessionScope.MANUFACTURER.manufacturerState}"/>
            <c:out value="${sessionScope.MANUFACTURER.manufacturerZip}"/>
            <c:out value="${sessionScope.MANUFACTURER.manufacturerStatus}"/>
          </td>
        </tr>
        <tr>
          <th scope="row">Web Page URL:</th>
          <td>
            <c:out value="${sessionScope.MANUFACTURER.manufacturerWebPage}"/></br>
             <a   href="/dfbs/code/main.do?method=editManufacturer&manufacturerNameId=<c:out value="${sessionScope.MANUFACTURER.manufacturerNameId}"/>">
              [ Edit web page information ]</a>
               
          </td>
        </tr>
         <tr>
         <th scope="row">Releases:</th>
          <td>
             <a   href="/dfbs/code/main.do?method=viewRelease">
              [ View Design Release's ]</a><br/>
               <a   href="/dfbs/code/newRelease.do?method=newRelease&manufacturerNameId=<c:out value="${sessionScope.MANUFACTURER.manufacturerNameId}"/>">
              [ Apply for New Design Release ]</a>
          </td>
        </tr>  
        <tr>
         <th scope="row">Contact(s):</th>
          <td>
             <a   href="/dfbs/code/editPerson.do?method=newPerson&codePersonType=M&facilityId=<c:out value="${sessionScope.MANUFACTURER.manufacturerNameId}"/>">
              [ Add new contact ]</a>
          </td>
        </tr>     
      </tbody>
    </table>
    </div>
  
 