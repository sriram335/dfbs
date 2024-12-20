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
<h2> Add / Update UST Applicant Information</h2> 
<a   href="/dfbs/ust/ust.do">
              [back to start]</a></br>
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
<div id="leftContentFluid" align="left">
  <div class="styledBox">
 <html:form action="/ust" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUst"/> 
      <html:hidden property="ustId"/> 

<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   <tr>
      <th scope="row" style="width:30%" class="required">* Address type:</th>
      <td>
        <html:radio property="addressFlag" value="1"  />Home
        <html:radio property="addressFlag" value="2"  />Work
        <c:if test="${APPLICATION_ERROR.addressFlag == 'ERROR'}">
              <br/><span class="error">* please enter address type  </span> 
       </c:if>
       </td>
    </tr>
    <tr>
    <th scope="row"  class="required"> * company name:</th>
          <td>
           <html:text property="companyName" size="30" maxlength="30"/>
              <c:if test="${APPLICATION_ERROR.companyName == 'ERROR'}">
              <br/><span class="error">* please enter address type  </span> 
             </c:if>  
            </td>
          </th>
    </tr>
    <tr>
    <th scope="row"  class="required"    > * name:</th>
          <td>
          <c:if test="${sessionScope.UST_OLD_APPLICANT !=null}">  
          <c:out value="${sessionScope.UST_OLD_APPLICANT.personLastName}"/>
          <html:hidden property="personLastName"/>
          </c:if>
           <c:if test="${sessionScope.UST_OLD_APPLICANT ==null}"> 
           <html:text property="personLastName" size="30" maxlength="30"/>
                 <c:if test="${APPLICATION_ERROR.personLastName == 'ERROR'}">
              <br/><span class="error">* please enter last name  </span> 
            </c:if>
            </c:if>
            </td>
          </th>
    </tr>
   <%-- <tr>
    <th scope="row"  class="required"    > * person first name:</th>
          <td>
           <html:text property="personFirstName" size="30" maxlength="30"/>
                 <c:if test="${APPLICATION_ERROR.personFirstName == 'ERROR'}">
              <br/><span class="error">* please enter first name  </span> 
            </c:if>
            </td>
          </th>
    </tr> 
    <tr>
    <th scope="row"    >  person mi:</th>
          <td>
           <html:text property="personMi" size="1" maxlength="1"/>
            </td>
          </th>
    </tr>--%>
    <tr>
          <th scope="row"   class="required"   > * address1:</th>
          <td>
           <html:text property="personAddress1" size="30" maxlength="30"/>
             <c:if test="${APPLICATION_ERROR.personAddress1 == 'ERROR'}">
              <br/><span class="error">* please enter address1  </span> 
            </c:if> 
            </td>
          </th>
        </tr>
     <tr>
          <th scope="row"     >  address2:</th>
          <td>
           <html:text property="personAddress2" size="30" maxlength="30"/>
             
            </td>
          </th>
        </tr>
        
      <tr>
          <th scope="row"  class="required"    > * city:</th>
          <td>
           <html:text property="personCity" size="30" maxlength="30"/>
                 <c:if test="${APPLICATION_ERROR.personCity == 'ERROR'}">
              <br/><span class="error">* please enter city  </span> 
            </c:if>
            </td>
          </th>
        </tr>
        
   <tr>
      <th scope="row"        >   state:</th>
      <td>
          <html:select property="personState" styleId="SELECT_INITIAL" onchange="setSelectValue('SELECT_INITIAL','STATE_INTITIAL')">
          <html:option value="IN">INDIANA</html:option>
          <html:options collection="UST_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${APPLICATION_ERROR.personState == 'ERROR'}">
              <br/><span class="error">* please enter state  </span> 
            </c:if>
       </td>
    </tr> 
        <tr>
          <th scope="row"  class="required"    > * zip:</th>
          <td>
           <html:text property="personZip" size="5" maxlength="5"/>
                 <c:if test="${APPLICATION_ERROR.personZip == 'ERROR'}">
              <br/><span class="error">* please enter zip  </span> 
            </c:if>
            </td>
          </th>
        </tr>
      
    <tr>
        <th scope="row"  class="required" >phone office:</th>
        <td>
          <html:text property="personPhoneOffice" size="10" maxlength="10"/>
              <c:if test="${APPLICATION_ERROR.personPhoneOffice == 'ERROR'}">
              <br/><span class="error">* please enter phone  </span> 
            </c:if>
         </td>
    </tr>
    <tr>
        <th scope="row"   >phone home:</th>
        <td>
          <html:text property="personPhoneHome" size="10" maxlength="10"/>
             
         </td>
    </tr>
    <tr>
        <th scope="row" >fax:</th>
        <td>
           <html:text property="personFax" size="10" maxlength="10"/>
        </td>
    </tr>
     <tr>
        <th scope="row" class="required" >email:</th>
        <td>
           <html:text property="personEmail" size="30" maxlength="50"/>
             <c:if test="${APPLICATION_ERROR.personEmail == 'ERROR'}">
              <br/><span class="error">* please email  </span> 
            </c:if>
        </td>
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
          <html:submit value=" Next " styleClass="button"/></br>
        </p>
        </td>
    </tr>
  </tbody>
        </table>
        
    </html:form>   
  
</div>
 
