<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>

<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Advance Search page"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">
  <h2>Advanced search</h2>

   <a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> 
   
  <div id="leftContentFluid">

 <html:form action="/search" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="courseList"/> 
      
     
       
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > select county1:</th>
      <td>
       <html:select property="county1" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">Please Select</html:option>
          <html:options collection="SEARCH_COUNTY_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > select county2:</th>
      <td>
       <html:select property="county2" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">Please Select</html:option>
          <html:options collection="SEARCH_COUNTY_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > select county3:</th>
      <td>
       <html:select property="county3" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">Please Select</html:option>
          <html:options collection="SEARCH_COUNTY_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > select county4:</th>
      <td>
       <html:select property="county4" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">Please Select</html:option>
          <html:options collection="SEARCH_COUNTY_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        </td>
    </tr>
      <tr>
      <th scope="row"  style= "width:50%"   > start date:</th>
      <td>
       <html:text property="startDate" size="10" maxlength="10"/>mm/dd/yyyy
        
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > end date:</th>
      <td>
       <html:text property="endDate" size="10" maxlength="10"/>mm/dd/yyyy
        
      </td>
    </tr>
 <tr>    
<td>
<p>
   
          <html:submit value="Course Search" styleClass="button" />
 </p>
 </td>
 </tr>
  <tr>    
<td>
<p>
   
          <html:submit value="Person Search" styleClass="button" /></br>(needs feedback)
 </p>
 </td>
 </tr>
  <tr>    
<td>
<p>
   
          <html:submit value="provider Search" styleClass="button" /></br>(needs feedback)
 </p>
 </td>
 </tr>
  </tr>
     
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>


