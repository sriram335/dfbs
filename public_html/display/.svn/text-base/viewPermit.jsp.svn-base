<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>View Fire Display Information</h2> 
 <div id="sideContentFluid">
 <b><u>Files Uploaded:</u></b></br>
<c:forEach var="file" items="${sessionScope.PERMIT_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
       <a   href="/dfbs/display/display.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a></br>
        </c:if>
          </td>
        <td>
</tr>
</c:forEach>
</div>
<div id="leftContentFluid">
  <div class="styledBox">
 <html:form action="/display" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUpdate"/> 
     <html:hidden property="displayIdNumber"/> 
        <html:hidden property="displayOwnerId"/> 
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
      <th scope="row" style="width : 50%">permit number:</th>
      <td style="font-size:medium;font-weight:bold">
        <c:out value="${PERMIT_SELECTED.displayIdNumber}"/></br>
        <html:hidden property="displayIdNumber"/>
      </td>
    </tr>
  <tr>
      <th scope="row">display issue date :</th>
      <td>
       <html:text property="displayIssueDate" size="10" maxlength="10"/>(mm/dd/yyyy)
         
       </td>
      </tr>
  
  <tr>
      <th scope="row">display permit valid from :</th>
      <td>
       <html:text property="displayDateFrom" size="10" maxlength="10"/>(mm/dd/yyyy)
         
       </td>
      </tr>
    <tr>
      <th scope="row">display permit valid to:</th>
      <td>
        <html:text property="displayDateTo" size="10" maxlength="10"/>(mm/dd/yyyy)
         
       </td>
      </tr>
       <tr>
      <th scope="row">display time:</th>
      <td>
        <html:text property="displayTime" size="10" maxlength="10"/>
         
       </td>
      </tr>
       <tr>
      <th scope="row">display dates:</th>
      <td>
        <jsp:include page="displayDatesIntmap.jsp"/> 
         
       </td>
      </tr>
    <tr>
      <th scope="row">address1 :</th>
      <td>
         <c:out value="${PERMIT_SELECTED.displayAddress1}"/>
        <html:hidden property="displayAddress1"/> 
      </td>
    </tr>
     <tr>
      <th scope="row">address2 :</th>
      <td>
         <c:out value="${PERMIT_SELECTED.displayAddress2}"/>
        <html:hidden property="displayAddress2"/> 
      </td>
    </tr>
     <tr>
      <th scope="row">city :</th>
      <td>
         <c:out value="${PERMIT_SELECTED.displayCity}"/>
        <html:hidden property="displayCity"/> 
      </td>
    </tr>
    <tr>
      <th scope="row" >state:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.displayState}"/>
        <html:hidden property="displayState"/> 
       </td>
    </tr>
    <tr>
      <th scope="row" >zip:</th>
      <td>
       <c:out value="${PERMIT_SELECTED.displayZip}"/>
       <html:hidden property="displayZip"/> 
      </td>
    </tr>
    <tr>
      <th scope="row"   class="required"     >  * county:</th>
      <td>
          <html:select property="displayCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_CODE')" >
          <html:option value="">Please Select</html:option>
          <html:options collection="DISPLAY_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${PERMIT_ERROR.displayCounty == 'ERROR'}">
              <br/><span class="error">* please enter county  </span> 
            </c:if>
       </td>
    </tr>
 
    <tr>
        <th scope="row" class="required" >* phone:</th>
        <td>
          <html:text property="displayPhone" size="10" maxlength="10"/>
            <c:if test="${PERMIT_ERROR.displayPhone == 'ERROR'}">
              <br/><span class="error">* please enter phone  </span> 
            </c:if>
         </td>
    </tr>
    <tr>
        <th scope="row" >fax:</th>
        <td>
           <html:text property="displayFax" size="10" maxlength="10"/>
        </td>
    </tr>
     <tr>
        <th scope="row" >email:</th>
        <td>
           <html:text property="displayEmail" size="30" maxlength="50"/>
        </td>
    </tr>
    
    
       <tr>
      <th scope="row"    >   fire department:</th>
      <td>
          <html:select property="displayFd" styleId="SELECT_FD" onchange="setSelectValue('SELECT_FD','DEPARTMENT_ID')">
          <html:option value="">Please Select</html:option>
          <html:options collection="FIRE_DEPT_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       
       </td>
    </tr>
     <tr>
        <th scope="row" >email:</th>
        <td>
           <html:text property="displayFdEmail" size="30" maxlength="50"/>
        </td>
    </tr>
   
     <tr>
        <th scope="row" >comments:(max 255 characters)</th>
        <td>
            <html:textarea property="displayComments" />
        </td>
    </tr>
     
  </tbody>
        </table>
       
       </html:form>   
   
</div>
 </div>
