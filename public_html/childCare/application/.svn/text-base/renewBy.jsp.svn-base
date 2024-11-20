<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Child Care Permits" />
<c:set var="title" scope="request" value="Renewal of Permit by" />

<jsp:include page="/app/common/hsPageHeader.jsp" />
<h2>Select Child Care Facility</h2>
<a   href="/dfbs/aepermits/start.do?method=backToStart">
              [back to start]</a>
 <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="renewByOwner"/>
            <p>
              <input type="submit" value="Select the Child Care Facility by owner name" class="button"/></br></br></br>
            </p>
            </html:form>
        </td>
    </tr> 
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">   
   <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY"  value="lookupNew"/>
            <p>
              <input type="submit" value="View New Online Applications" class="button"/></br></br></br>
            </p>
            </html:form>
        </td>
    </tr> 
  </c:if>
   <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
    <html:form action="/start" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="renewByStreetNumber"/>Child Care Facility Location Street Address: 
      <SPAN style="background-color:rgb(192,192,192); background-color:rgb(51,51,255);">
        <html:text property="streetNumber" size="10" maxlength="10"/>
      </SPAN></br>
      <input type="submit" value="Select the Child Care Facility by street address" class="button"/></br>*Note: for 302 West Washington ST, enter 302 in the box, Rural Road 400 enter Rural
         <c:if test="${START_FORM_ERROR.streetNumber == 0}">
         <span class="error">
         * please enter street number</span> 
        </c:if></br></br></br>
    </html:form>
    </td>
    </tr> 
     <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="renewByPermitNumber"/>
            <p>
            Child Care Facility Id:
            <SPAN style="background-color:rgb(192,192,192); background-color:rgb(51,51,255);">
              <html:text property="permitNumber" size="12" maxlength="12"/>
            </SPAN>
            </br>
              <input type="submit" value="Select the Daycare by identification number" class="button"/>
            </p>
          
          <c:if test="${START_FORM_ERROR.permitNumber =='ERROR'}">
          <span class="error">* please enter identification number</span>
        </c:if> </br></br></br></br>
      </html:form>
    </td>
    </tr> 
    
  
      </tbody>
   </table>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
