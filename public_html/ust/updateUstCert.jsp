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
<c:if test="${sessionScope.DFBS_SECURITY != null  }">
       <c:forEach var="file" items="${sessionScope.UST_OLD_APPLICANT.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
             File date:<c:out value="${file.fileDateString}"/>&nbsp; </br>
              <a   href="https://oas.dhs.in.gov/dfbs/aepermits/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [View]</a></br></br>
            </td>
      </tr>
     </c:forEach>
      </c:if>
  <c:forEach var="ust" items="${UST_LIST_NEW.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/ust.do?method=appRenewal" paramId="ustId" paramName="ust" paramProperty="ustId">
    <c:out value="${ust.personLastName}" />
    </html:link> 
     </h3>
      <c:if test="${sessionScope.DFBS_SECURITY != null  }">
       <a   href="/dfbs/ust/ustCert.do?method=updateUstCert&ustId=<c:out value="${ust.ustId}"/>">
             [update Certification ]</a> 
     </c:if>
  </div>
  </c:forEach>

  </div>
<div id="leftContentFluid" align="left">
  <div class="styledBox">
 <html:form action="/ustCert" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUpdateCert"/> 
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
       <html:text property="certNumber" size="15" maxlength="15"/>
        </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%" class="required">Certification Issue Date:</th>
      <td>
        <html:text property="issueDate" size="10" maxlength="10"/> mm/dd/yyyy
        </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%" class="required">Certification Exp. Date:</th>
      <td>
        <html:text property="expDate" size="10" maxlength="10"/> mm/dd/yyyy
       </td>
    </tr>
     <tr>
      <th scope="row" style="width:30%" class="required">Certification type:</th>
      <td>
       <html:text property="certType" size="30" maxlength="30"/>
       </td>
    </tr>
     <tr>
      <th scope="row" style="width:30%" class="required">Status:</th>
      <td>
       <html:select property="status" styleId="SELECT_STATUS" onchange="setSelectValue('SELECT_DESCRIPTION','STATE_ABBREVIATION')" >
          <html:option value="0">Please Select</html:option>
         <html:options collection="UST_CERT_STATUS_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        </td>
    </tr>
     <tr>
      <th scope="row" style="width:30%" class="required"> Certification for Installation / Retrofitting:</th>
      <td>
      <c:if test="${sessionScope.UST_CERT.install != 'I' && sessionScope.UST_CERT.install != 'on' }">
       <c:out value="${sessionScope.UST_CERT.install}"/>
     <input type="checkbox"  class="switch" name="install"  value="I"/>
    </c:if>
     <c:if test="${sessionScope.UST_CERT.install == 'I' || sessionScope.UST_CERT.install == 'on'}">
     <input type="checkbox"  class="switch"  name="install" checked="checked"  value="I"/>
     </c:if> 
       </td>
    </tr>
   
    
    <tr>
      <th scope="row" style="width:30%" class="required"> Certification for Cathodic Protection:</th>
      <td>
      <c:if test="${sessionScope.UST_CERT.cathode !='C' && sessionScope.UST_CERT.cathode != 'on'}">
     <input type="checkbox"  class="switch" name="cathode" value="C"/>
     </c:if>
     <c:if test="${sessionScope.UST_CERT.cathode == 'C' || sessionScope.UST_CERT.cathode == 'on' }">
     <input type="checkbox"  class="switch" checked="checked" name="cathode"  value="C"/>
     </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row" style="width:30%" class="required"> Certification for Testing:</th>
      <td>
      <c:if test="${sessionScope.UST_CERT.testing !='T' && sessionScope.UST_CERT.testing != 'on'}">
     <input type="checkbox"  class="switch"  name="testing" value='T'/>
     </c:if>
     <c:if test="${sessionScope.UST_CERT.testing =='T' || sessionScope.UST_CERT.testing == 'on'}">
     <input type="checkbox"  class="switch" name="testing" checked="checked"  value='T'  />
     </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%" class="required"> Certification for Decommissioning:</th>
      <td>
      <c:if test="${sessionScope.UST_CERT.deccom !='D' && sessionScope.UST_CERT.deccom != 'on'}">
     <input type="checkbox"  class="switch" name="deccom" value="D" />
     </c:if>
     <c:if test="${sessionScope.UST_CERT.deccom =='D' || sessionScope.UST_CERT.deccom == 'on'}">
     <input type="checkbox"  class="switch" name="deccom" checked="checked"  value="D" />
     </c:if></br>
        <html:radio property="decomStatus" value="1"  />Removal
        <html:radio property="decomStatus" value="2"  />Closure
       </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%" class="required"> Copy of Certification Submitted:</th>
      <td>
        <html:radio property="evidenceType" value="1"  />ICC Certification
        <html:radio property="evidenceType" value="2"  />Independent Testing Agency
         <html:radio property="evidenceType" value="3"  />Reciprocity with State 
          <html:select property="licState" styleId="SELECT_INITIAL" onchange="setSelectValue('SELECT_INITIAL','STATE_INTITIAL')">
          <html:option value="0">Please Select</html:option>
          <html:options collection="UST_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       </td>
    </tr>
    <tr>
        <th scope="row" ></th>
        <td>
        
          <p>
          
          <html:submit value=" update " styleClass="button"/></br>
        </p>
        </td>
    </tr>
    
  </tbody>
        </table>
       
    </html:form>  
     <a   href="/dfbs/ust/ust.do?method=sendCertification&ustId=<c:out value="${sessionScope.UST_OLD_APPLICANT.ustId}"/>&userEmail=<c:out value="${sessionScope.UST_OLD_APPLICANT.personEmail}"/>">
             [ Email Certification ]</a> 
</div>
 