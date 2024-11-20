<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Indiana EMS"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>


<div id="mainBox">
  <h2>Data Upload for State of Indiana</h2>
<p class="error"><b><u> Please Select Option 1 or 2</u></b> </p>
<div id="leftContentFluid">

<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader">  
<tr>
<th>Option 1 </th>
<td>
<p class="messageBox" size="100">
I don't know the Provider Certification Number, I want to select provider by name
         <html:form action="/fireHouse" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="goToProvider"/> 
          <input type="submit" value="Proceed to Provider Selection" class="button"/>
         </html:form>        
</p>
</td>
</tr>
<tr>
<th>Option 2 </th>
<td>
<p class="messageBox" size="100">
          
         <html:form action="/fireHouse" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="goToUpload"/> 
          Provider Certification Number:<html:text property="certNumber" size="7" maxlength="7"/> </br>
          <input type="submit" value="Upload Files" class="button"/></br>
           <c:if test="${sessionScope.PROVIDER_CERT_ERROR == 'ERROR'}"> 
            <span class="error">ERROR !. Invalid Certification Number , Enter correct Number or use Option 1</span>
           </c:if>   
         </html:form>        
</p>
</td>
</tr>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelHipaa !=null || sessionScope.DFBS_SECURITY.groupLevelAll !=null}">
<p class="message">
Welcome  <c:out value="${sessionScope.DFBS_SECURITY.userId}"/>, 
</p>
 <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/fireHouse" method="post">
        <input type="hidden" name="method" id="METHOD_KEY"  value="processNewFiles"/>
        <b>Start Date:&nbsp;</b><html:text property="startDate" size="10" maxlength="10"/>(mm/dd/yyyy) </br></br>
        <b>End Date:&nbsp;</b><html:text property="endDate" size="10" maxlength="10"/>(mm/dd/yyyy) </br>
            <p class="messageBox" size="100">
             To process new files</br>
              <input type="submit" value="Process new files" class="button"/></br>
            </p>
            </html:form>
             
        </td>
    </tr>  
</c:if>
</tbody>
</table>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelHipaa != null}">  
  
<table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th >Files List</th>
  </tr>
  <tbody>
<c:forEach var="file" items="${requestScope.FIRE_HOUSE_FILE_LIST.list}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <html:form action="/fireHouse" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFile"/> 
    <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
    <input type="hidden" name="fileName"  value="${file.fileName}"/> 
    <input type="hidden" name="fileType"   value="${file.fileType}"/> 
       <c:out value="${file.fileName}"/>&nbsp;</br>For Provider Certification Number: <c:out value="${file.fileConnector}"/> &nbsp;<br/> 
       File date:<c:out value="${file.fileDateString}"/>&nbsp;,File sent by:<c:out value="${file.fileLoadedBy}"/>;&nbsp;</br>File Status:<c:out value="${file.filePlanType}"/></br>
         <input type="submit" value="View File" class="button"/>
       </html:form>&nbsp;
         <html:form action="/fireHouse" method="post" >
         <input type="hidden" name="method" id="METHOD_KEY" value="approveFile"/> 
        <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
        <input type="submit" value="File Processed" class="button"/>
        </html:form>&nbsp;
        <html:form action="/fireHouse" method="post" >
         <input type="hidden" name="method" id="METHOD_KEY" value="deleteFile"/> 
        <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
        <input type="submit" value="Delete" class="button"/>
        </html:form>
         <html:form action="/fireHouse" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="sendErrorEmail"/> 
          <input type="hidden" name="certNumber"   value ="${file.fileConnector}"/> 
          <input type="hidden" name="userEmail"  value="${file.fileLoadedBy}"/> 
          <input type="hidden" name="fileName"   value="${file.fileName}"/> 
      
         <html:textarea property="comments" rows="3" cols="70"/>
       <input type="submit" value="Email Error" class="button"/>
              </html:form>
          </td>
        </tr>
</c:forEach>
  </tbody>
</table> 
 </c:if>
</div> 
  <div class="clearer">&nbsp;</div>
 </div> 