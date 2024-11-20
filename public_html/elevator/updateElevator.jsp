<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Elevator Permits" />
<c:set var="title" scope="request" value="Update Elevator Information" />

<jsp:include page="/app/common/hsPageHeader.jsp"/>
<a   href="/dfbs/elevator/owner.do?method=view&ownerId=<c:out value="${sessionScope.OWNER_SELECTED.ownerId}"/>">
             [back to owner]</a> 
<div id="sideContentFluid">
 <jsp:include page="updateElevatorSidebar.jsp"/>
 </div>
 <div id="leftContentFluid">
 <h2>Update Elevator Information</h2> 
 <html:form action="/elevator" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUpdateElevator"/> 
         <html:hidden property="ownerId"/> 
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
      <th scope="row">state number :</th>
      <td>
       <c:out value="${sessionScope.ELEVATOR_SELECTED.stateNumber}"/>
       </td>
      </tr>
    <tr> 
  <tr>
      <th scope="row">device capacity :</th>
      <td>
       <html:text property="deviceCapacity" size="8" maxlength="8"/>
       </td>
      </tr>
    <tr>
      <th scope="row">contact speed :</th>
      <td>
       <html:text property="contactSpeed" size="8" maxlength="8"/>
       </td>
      </tr>
     <tr>
      <th scope="row">number of openings :</th>
      <td>
       <html:text property="numberOpenings" size="10" maxlength="10"/>
       </td>
      </tr>
      <tr>
      <th scope="row">total travel :</th>
      <td>
       <html:text property="totalTravel" size="10" maxlength="10"/>
       </td>
      </tr>
      <tr>
      <th scope="row">platform size :</th>
      <td>
       <html:text property="platformSize" size="10" maxlength="20"/>
       </td>
      </tr>
      <tr>
      <th scope="row">contract number :</th>
      <td>
       <html:text property="contractNumber" size="10" maxlength="15"/>
       </td>
      </tr>
      <tr>
      <th scope="row">type of control :</th>
      <td>
       <html:text property="typeControl" size="10" maxlength="10"/>
       </td>
      </tr>
       <tr>
      <th scope="row">adopted code :</th>
      <td>
       <html:text property="adoptedCode" size="10" maxlength="10"/>
       </td>
      </tr>
       <tr>
      <th scope="row">year installed :</th>
      <td>
       <html:text property="yearInstalled" size="10" maxlength="10"/>(mm/dd/yyyy)
       </td>
      </tr>
       <tr>
      <th scope="row">occupation code :</th>
      <td>
      <html:select property="occCode"  onchange="setSelectValue('SELECT_ABBREVIATION','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_OCC_CODES" property="value" labelProperty="label"/>
        </html:select>
       </td>
      </tr>
       <tr>
      <th scope="row">number of floors :</th>
      <td>
       <html:text property="floors" size="30" maxlength="30"/>
       </td>
      </tr>
         <tr>
          <th scope="row"  class="required"  > * inspection  contracting company:</th>
          <td>
          <html:select property="contractorId"  onchange="setSelectValue('SELECT_ABBREVIATION','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_CON_COMPANY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
         </td>
        </tr>
         <tr>
          <th scope="row">user name :</th>
          <td>
           <html:text property="locUserName" size="50" maxlength="65"/>
           </td>
          </tr>
          <tr>
          <th scope="row">user address1 :</th>
          <td>
           <html:text property="locAddress1" size="30" maxlength="30"/>
           </td>
          </tr>   
          <tr>
          <th scope="row">user address2 :</th>
          <td>
           <html:text property="locAddress2" size="30" maxlength="30"/>
           </td>
          </tr>   
          <tr>
          <th scope="row">user city :</th>
          <td>
           <html:text property="locCity" size="30" maxlength="30"/>
           </td>
          </tr>  
          <tr>
          <th scope="row">user state :</th>
          <td>
           <html:text property="locState" size="2" maxlength="2"/>
           </td>
          </tr>  
          <tr>
          <th scope="row">user zip :</th>
          <td>
           <html:text property="locZip" size="5" maxlength="5"/>
           </td>
          </tr>  
          <tr>
          <th scope="row">user zip2 :</th>
          <td>
           <html:text property="locZip2" size="4" maxlength="4"/>(mm/dd/yyyy)
           </td>
          </tr>  
    <tr>
      <th scope="row"   class="required"     >  user county:</th>
      <td>
          <html:select property="locCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_CODE')" >
          <html:option value="">Please Select</html:option>
          <html:options collection="DFBS_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${ELEVATOR_ERROR.locCounty == 'ERROR'}">
              <br/><span class="error">* please enter county  </span> 
            </c:if>
       </td>
    </tr>
 
    <tr>
        <th scope="row" class="required" >* phone:</th>
        <td>
          <html:text property="locUserPhone" size="10" maxlength="10"/>
            <c:if test="${PERMIT_ERROR.displayPhone == 'ERROR'}">
              <br/><span class="error">* please enter phone  </span> 
            </c:if>
         </td>
    </tr>
    
     <tr>
        <th scope="row" >email:</th>
        <td>
           <html:text property="locUserEmail" size="30" maxlength="50"/>
        </td>
    </tr>
   <tr>
      <th scope="row"    >  elevator status:</th>
      <td>
          <html:select  property="elevStatus">
          <html:option value="">Please Select</html:option>
          <html:option value="A">Active</html:option>
          <html:option value="I">Inactive</html:option>
         </html:select>
        </td>
    </tr>
     
   
  </tbody>
        </table>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelElev != null}"> 
        <p class="message">
          <html:submit value="Save " styleClass="button"/>
        </p>
        </c:if>
   </html:form>   
  
</div>

