<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Fire Display  Permits" />
<c:set var="title" scope="request" value="Permit Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Add / Update UST Applicant Information</h2> 
<a   href="/dfbs/ust/ust.do">
              [back to start]</a>

<div id="sideContentFluid">
  <jsp:include page="shoppingCart.jsp"/>
  </div>
<div id="leftContentFluid" align="left">
  <div class="styledBox">
 <html:form action="/ustCert" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="addToCart"/> 
      <html:hidden property="ustId"/> 
       <html:hidden property="mailDate"/> 

<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   <tr>
      <th scope="row" style="width:30%" class="required">Certification Number:</th>
      <td>
       <c:out value="${sessionScope.UST_CERT.certNumber}"/>
        <html:hidden property="certNumber"/> 
       </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%" class="required">Certification Issue Date:</th>
      <td>
       <c:out value="${sessionScope.UST_CERT.issueDateString}"/>
        <html:hidden property="issueDate"/> 
       </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%" class="required">Certification Exp. Date:</th>
      <td>
       <c:out value="${sessionScope.UST_CERT.expDateString}"/>
        <html:hidden property="expDate"/> 
       </td>
    </tr>
     <tr>
      <th scope="row" style="width:30%" class="required">Certification type:</th>
      <td>
       <c:out value="${sessionScope.UST_CERT.certType}"/>
        <html:hidden property="certType"/> 
       </td>
    </tr>
     <tr>
      <th scope="row" style="width:30%" class="required">Status:</th>
      <td>
       <c:out value="${sessionScope.UST_CERT.status}"/>
        <html:hidden property="status"/> 
       </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%" class="required"> Certification for Installation / Retrofitting:</th>
      <td>
      <c:if test="${sessionScope.UST_CERT.install !='I'}">
     <input type="checkbox"  class="switch" disabled="disabled"/>
     </c:if>
     <c:if test="${sessionScope.UST_CERT.install == 'I'}">
     <input type="checkbox"  class="switch" disabled="disabled" checked="checked"/>
     </c:if>
        <html:hidden property="install"/> 
       </td>
    </tr>
   
    
    <tr>
      <th scope="row" style="width:30%" class="required"> Certification for Cathodic Protection:</th>
      <td>
      <c:if test="${sessionScope.UST_CERT.cathode !='C'}">
     <input type="checkbox"  class="switch" disabled="disabled"/>
     </c:if>
     <c:if test="${sessionScope.UST_CERT.cathode == 'C'}">
     <input type="checkbox"  class="switch" checked="checked" disabled="disabled"/>
     </c:if>
        <html:hidden property="cathode"/> 
       </td>
    </tr>
     <tr>
      <th scope="row" style="width:30%" class="required"> Certification for Testing:</th>
      <td>
      <c:if test="${sessionScope.UST_CERT.testing !='T'}">
     <input type="checkbox"  class="switch" disabled="disabled"/>
     </c:if>
     <c:if test="${sessionScope.UST_CERT.testing == 'T'}">
     <input type="checkbox"  class="switch" checked="checked" disabled="disabled"/>
     </c:if>
        <html:hidden property="testing"/> 
       </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%" class="required"> Certification for Decommissioning:</th>
      <td>
      <c:if test="${sessionScope.UST_CERT.deccom !='D'}">
     <input type="checkbox"  class="switch" disabled="disabled"/>
     </c:if>
     <c:if test="${sessionScope.UST_CERT.deccom == 'D'}">
     <input type="checkbox"  class="switch" checked="checked" disabled="disabled"/>
     </c:if></br>
        <html:radio property="decomStatus" value="1"  disabled="true"/>Removal
        <html:radio property="decomStatus" value="2"  disabled="true"/>Closure
        <html:hidden property="deccom"/> 
       </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%" class="required"> Copy of Certification Submitted:</th>
      <td>
        <html:radio property="evidenceType" value="1"  disabled="true"/>ICC Certification
        <html:radio property="evidenceType" value="2"  disabled="true"/>Independent Testing Agency
         <html:radio property="evidenceType" value="3"  disabled="true"/>Reciprocity with State 
          <html:select property="licState" styleId="SELECT_INITIAL" onchange="setSelectValue('SELECT_INITIAL','STATE_INTITIAL')"
                       disabled="true">
          <html:option value="0">Please Select</html:option>
          <html:options collection="UST_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <html:hidden property="deccom"/> 
       </td>
    </tr>
    <tr>
        <th scope="row" ></th>
        <td>
        <p class="message" align="center">
        This information is for view only, if you need any of this information to be changed, Enter the details in the comments box, upload supporting
        document(s) along with payment.
        </p>
          <p>
          
          <html:submit value=" Renew " styleClass="button"/></br>
        </p>
        </td>
    </tr>
    
  </tbody>
        </table>
       
    </html:form>  
    <%--
  <html:form action="/ustCert" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveComments"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
        <th scope="row" >comments:</th>
        <td>
           <html:textarea property="comments" rows="10" cols="70"/>
             
        </td>
    </tr>
     <tr>
        <th scope="row" ></th>
        <td>
          <p>
          <html:submit value=" Submit comments " styleClass="button"/></br>
        </p>
        </td>
    </tr>
  </tbody>
        </table>
        
    </html:form>  --%> 
</div>
 
