<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="manage account screen"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Manage my account</h2>

  <div id="leftContentFluid">
 <html:form action="/cigUser" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updatePassword"/>
 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   
 <tr>
 <th scope="row"  style= "width:100%"  ></th>
<td>
   
          <html:submit value="Change Password" styleClass="button" />
</td>
</tr> 

  </tbody>
  </table>

 </html:form>  
 
  <html:form action="/cigUser" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="dataExtract"/>
 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
     <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  ></th>
      <td>
      <b><u> Get my company data extract</b></u>
      </td>
     
    </tr>
<tr>
 <th scope="row"  style= "width:100%"  ></th>
<td>
   
          <html:submit value="Email Data Extract" styleClass="button" />
</td>
</tr> 

  </tbody>
  </table>
 
  <p class="message" >You can get your company cigarette information in as txt file attachement which you can import into excel format and submit it for other states.</p>
 </html:form>  
  </div>
  <div class="clearer">&nbsp;</div>
 </div>  
<jsp:include page="/app/common/hsPageFooter.jsp"/>


