<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Add New Elevator Information</h2> 

</div>

<div id="leftContentFluid">
  <div class="styledBox">
 <html:form action="/elevator" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveNewElevator"/> 
    
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
    <th ></th>
    <td>
    *=mandatory fields
    </td>
    </tr>
    <tr>
      <th scope="row"   class="required"     > * device type:</th>
      <td>
          <html:select property="deviceId" onchange="setSelectValue('SELECT_DEVICE','DEVICE_DESCRIPTION')" >
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_DEV_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${requestScope.ELEVATOR_ERROR.deviceId == 99999}">
              <br/><span class="error">* please enter device type  </span> 
            </c:if>
       </td>
    </tr>
  <tr>
      <th scope="row"  class="required" >* device capacity :</th>
      <td>
       <html:text property="deviceCapacity" size="8" maxlength="8"/>
       <c:if test="${requestScope.ELEVATOR_ERROR.deviceCapacity == 'ERROR'}">
              <br/><span class="error">* please enter device capacity  </span> 
            </c:if>
       </td>
      </tr>
    <tr>
      <th scope="row"  class="required" > * contact speed :</th>
      <td>
       <html:text property="contactSpeed" size="8" maxlength="8"/>
       <c:if test="${requestScope.ELEVATOR_ERROR.contactSpeed == 'ERROR'}">
              <br/><span class="error">* please enter contact speed  </span> 
            </c:if>
       </td>
      </tr>
     <tr>
      <th scope="row"  class="required" > * number of openings :</th>
      <td>
       <html:text property="numberOpenings" size="10" maxlength="10"/>
       <c:if test="${requestScope.ELEVATOR_ERROR.numberOpenings == 'ERROR'}">
              <br/><span class="error">* please enter number of openings  </span> 
            </c:if>
       </td>
      </tr>
      <tr>
      <th scope="row"  class="required" >* total travel :</th>
      <td>
       <html:text property="totalTravel" size="10" maxlength="10"/>
       <c:if test="${requestScope.ELEVATOR_ERROR.totalTravel == 'ERROR'}">
              <br/><span class="error">* please enter total travel  </span> 
            </c:if>
       </td>
      </tr>
      <tr>
      <th scope="row"  class="required" > * platform size(sft):</th>
            <td>
       <html:text property="platformSize" size="10" maxlength="20"/>
       <c:if test="${requestScope.ELEVATOR_ERROR.platformSize == 'ERROR'}">
              <br/><span class="error">* please enter platform size  </span> 
            </c:if>
       </td>
      </tr>
   <tr>
     <th scope="row"  class="required" >* contract number :</th>
      <td>
       <html:text property="contractNumber" size="10" maxlength="15"/>
       <c:if test="${requestScope.ELEVATOR_ERROR.contractNumber == 'ERROR'}">
              <br/><span class="error">* please enter contract number  </span> 
            </c:if>
       </td>
      </tr> 
      <tr>
      <th scope="row"  class="required" > * type of control :</th>
      <td>
       <html:text property="typeControl" size="10" maxlength="10"/>
       <c:if test="${requestScope.ELEVATOR_ERROR.typeControl == 'ERROR'}">
              <br/><span class="error">* please enter type control  </span> 
            </c:if>
       </td>
      </tr>
       <tr>
      <th scope="row">code installed to :</th>
      <td>
       <html:text property="adoptedCode" size="10" maxlength="10"/>
       </td>
      </tr>
     <%--  <tr>
      <th scope="row">application date :</th>
      <td>
       <html:text property="yearInstalled" size="10" maxlength="10"/>(mm/dd/yyyy)
       </td>
      </tr> --%>
       <tr>
      <th scope="row">occupation type :</th>
      <td>
        <html:select property="occCode"  onchange="setSelectValue('SELECT_ABBREVIATION','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_OCC_CODES" property="value" labelProperty="label"/>
        </html:select>
       </td>
      </tr>
       <tr>
      <th scope="row"  class="required" > * number of floors :</th>
      <td>
       <html:text property="floors" size="30" maxlength="30"/>
       <c:if test="${requestScope.ELEVATOR_ERROR.floors == 'ERROR'}">
              <br/><span class="error">* please enter number of floors  </span> 
            </c:if>
       </td>
      </tr>
         <tr>
          <th scope="row"  class="required"  > * inspection  contracting company:</th>
          <td >
          <html:select property="contractorId"  onchange="setSelectValue('SELECT_ABBREVIATION','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_CON_COMPANY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${requestScope.ELEVATOR_ERROR.contractorId == 99999}">
              <br/><span class="error">* please enter contractor  </span> 
            </c:if>
         </td>
        </tr>
         <tr>
          <th scope="row"  class="required" > * user name :</th>
          <td>
           <html:text property="locUserName" size="50" maxlength="65"/>
           <c:if test="${requestScope.ELEVATOR_ERROR.locUserName == 'ERROR'}">
              <br/><span class="error">* please enter user name  </span> 
            </c:if>
           </td>
          </tr>
          <tr>
          <th scope="row"  class="required" > * user address1 :</th>
          <td>
           <html:text property="locAddress1" size="30" maxlength="30"/>
           <c:if test="${requestScope.ELEVATOR_ERROR.locAddress1 == 'ERROR'}">
              <br/><span class="error">* please enter address1  </span> 
            </c:if>
           </td>
          </tr>   
          <tr>
          <th scope="row"   >  user address2 :</th>
          <td>
           <html:text property="locAddress2" size="30" maxlength="30"/>
              </td>
          </tr>   
          <tr>
          <th scope="row"  class="required" > * user city :</th>
          <td>
           <html:text property="locCity" size="30" maxlength="30"/>
           <c:if test="${requestScope.ELEVATOR_ERROR.locCity == 'ERROR'}">
              <br/><span class="error">* please enter user city  </span> 
            </c:if>
           </td>
          </tr>  
          <tr>
          <th scope="row"  class="required" > * user state :</th>
          <td>
           <html:text property="locState" size="2" maxlength="2" value="IN" />
           <c:if test="${requestScope.ELEVATOR_ERROR.locState == 'ERROR'}">
              <br/><span class="error">* please enter user state  </span> 
            </c:if>
           </td>
          </tr>  
          <tr>
          <th scope="row"  class="required" > * user zip :</th>
          <td>
           <html:text property="locZip" size="5" maxlength="5"/>
           <c:if test="${requestScope.ELEVATOR_ERROR.locZip == 'ERROR'}">
              <br/><span class="error">* please enter user zip code  </span> 
            </c:if>
           </td>
          </tr>  
          <tr>
          <th scope="row">user zip2 :</th>
          <td>
           <html:text property="locZip2" size="4" maxlength="4"/>
           </td>
          </tr>  
    <tr>
      <th scope="row"   class="required"     > * user county:</th>
      <td>
          <html:select property="locCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_CODE')" >
          <html:option value="">Please Select</html:option>
          <html:options collection="DFBS_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${requestScope.ELEVATOR_ERROR.locCounty == 'ERROR'}">
              <br/><span class="error">* please enter county  </span> 
            </c:if>
       </td>
    </tr>
 
    <tr>
        <th scope="row" class="required" >* phone:</th>
        <td>
          <html:text property="locUserPhone" size="10" maxlength="10"/>
            <c:if test="${requestScope.ELEVATOR_ERROR.locUserPhone == 'ERROR'}">
              <br/><span class="error">* please enter phone  </span> 
            </c:if>
         </td>
    </tr>
    
     <tr>
        <th scope="row" class="required" > * email:</th>
        <td>
           <html:text property="locUserEmail" size="30" maxlength="50"/>
           <c:if test="${requestScope.ELEVATOR_ERROR.locUserEmail == 'ERROR'}">
              <br/><span class="error">* please enter user email  </span> 
            </c:if>
        </td>
    </tr>
   
     
   
  </tbody>
        </table>
        <p class="message">
          <html:submit value="Save " styleClass="button"/>
        
        </p>
   </html:form>   
   
</div>
 </div>
