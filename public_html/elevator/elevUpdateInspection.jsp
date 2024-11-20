<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>


<hs:control/>
<c:set var="module" scope="request" value="Elevator Inspections" />
<c:set var="title" scope="request" value="Add New Inspection" />

<html:xhtml/>

  
<jsp:include page="/app/common/hsPageHeader.jsp"/>


<h2>Update Inspection</h2> 
<a   href="/dfbs/elevator/inspection.do?method=inspectionList">
              [back to inspection list]</a>
              <a   href="/dfbs/elevator/elevTests.do?method=addTestsList&inspectionId=<c:out value="${ELEVATOR_INSPECTION_SELECTED.inspectionId}"/>">
              [tests list]</a>
  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=elev_report_of_tests.rdf&p_insp_id=<c:out value="${inspection.inspectionId}"/>">[View Inspection Report] </a> 

 <html:form action="/inspection" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUpdateInspection"/> 
      <html:hidden property="inspectionId"/> 
      <html:hidden property="stateNumber"/> 
      <html:hidden property="comments"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
     <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
          <th scope="row" class="required"   > * inspection Date:(mm/dd/yyyy)</th>
          <td>
          <html:text property="inspectionDate" size="10" maxlength="10"/> 
            </td>
    </tr>
    <tr>
          <th scope="row"    >  inspection time:</th>
          <td>
          <html:text property="inspTime" size="10" maxlength="10"/> 
            </td>
    </tr>
    <tr>
          <th scope="row"    >  last 5 year test Date:(mm/dd/yyyy)</th>
          <td>
          <html:text property="last5YearTest" size="10" maxlength="10"/> 
            </td>
    </tr>
     <tr>
          <th scope="row"   >  next 5 year test Date:(mm/dd/yyyy)</th>
          <td>
          <html:text property="next5YearTest" size="10" maxlength="10"/> 
            </td>
    </tr>
     <tr>
          <th scope="row"   >  last annual year test Date:(mm/dd/yyyy)</th>
          <td>
          <html:text property="lastAnnualTest" size="10" maxlength="10"/> 
            </td>
    </tr>
     <tr>
          <th scope="row"   >  next annual year test Date:(mm/dd/yyyy)</th>
          <td>
          <html:text property="nextAnnualTest" size="10" maxlength="10"/> 
            </td>
    </tr>
    <tr>
          <th scope="row"    > sprinkler status:</th>
          <td>
          <html:select property="sprinklers"  onchange="setSelectValue('SELECT_YN','YES')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_YN_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
       <tr>
          <th scope="row"   >  seal number</th>
          <td>
          <html:text property="sealNumber" size="12" maxlength="12"/> 
            </td>
       </tr>
        <tr>
          <th scope="row"   >  seal number</th>
          <td>
          <html:text property="sealNumber" size="12" maxlength="12"/> 
            </td>
       </tr>
        <tr>
          <th scope="row"   >  run by bottom</th>
          <td>
          <html:text property="runToBottom" size="8" maxlength="8"/> 
            </td>
       </tr>
        <tr>
          <th scope="row"   >  run by top</th>
          <td>
          <html:text property="runToTop" size="8" maxlength="8"/> 
            </td>
       </tr>
       <tr>
          <th scope="row"   >  refuge bottom</th>
          <td>
          <html:text property="refugeBottom" size="8" maxlength="8"/> 
            </td>
       </tr>
        <tr>
          <th scope="row"   >  refuge top</th>
          <td>
          <html:text property="refugeTop" size="8" maxlength="8"/> 
            </td>
       </tr>
        <tr>
          <th scope="row"   >  empty down</th>
          <td>
          <html:text property="emptyBottom" size="8" maxlength="8"/> 
            </td>
       </tr>
        <tr>
          <th scope="row"   >  empty up</th>
          <td>
          <html:text property="emptyUp" size="8" maxlength="8"/> 
            </td>
       </tr>
       <tr>
          <th scope="row"   >  full down</th>
          <td>
          <html:text property="fulldown" size="8" maxlength="8"/> 
            </td>
       </tr>
        <tr>
          <th scope="row"   >  full up</th>
          <td>
          <html:text property="fullUp" size="8" maxlength="8"/> 
            </td>
       </tr>
       <tr>
          <th scope="row"   >  governor trip speed</th>
          <td>
          <html:text property="govTripSpeed" size="8" maxlength="8"/> 
            </td>
       </tr>
       <tr>
          <th scope="row"   >  over speed switch</th>
          <td>
          <html:text property="overSpeedSwitch" size="8" maxlength="8"/> 
            </td>
       </tr>
        <tr>
          <th scope="row"   >  slide test</th>
          <td>
          <html:text property="slide" size="8" maxlength="8"/> 
            </td>
       </tr>
       <tr>
          <th scope="row"   >  break test</th>
          <td> <html:text property="breakTest" size="8" maxlength="8"/>   </td>
       </tr>
       <tr>
          <th scope="row"   >  flex hose Date:(mm/dd/yyyy)</th>
          <td>
          <html:text property="flexHoseDate" size="10" maxlength="10"/> 
            </td>
        </tr>
        <tr>
          <th scope="row"   >  working pressure</th>
          <td> <html:text property="workingPressure" size="8" maxlength="8"/>   </td>
       </tr>
       <tr>
          <th scope="row"   >  relief pressure</th>
          <td> <html:text property="reliefPressure" size="8" maxlength="8"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  nearest striking point</th>
          <td> <html:text property="nearStrkingPoint" size="8" maxlength="8"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  fire fight service date:(mm/dd/yyyy)</th>
          <td>
          <html:text property="fireFightService" size="10" maxlength="10"/> 
            </td>
        </tr>
         <tr>
          <th scope="row"   >  location telephone</th>
          <td> <html:text property="telephone" size="10" maxlength="10"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  location district</th>
          <td> <html:text property="telephone" size="10" maxlength="10"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  unresticted car motion</th>
          <td>  <html:select property="carMotion"  onchange="setSelectValue('SELECT_YN','YES')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_YN_OPTIONS" property="value" labelProperty="label"/>
        </html:select></td>
       </tr>
        <tr>
          <th scope="row"   >  ascening car</th>
          <td>  <html:select property="ascendingCar"  onchange="setSelectValue('SELECT_YN','YES')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_YN_OPTIONS" property="value" labelProperty="label"/>
        </html:select></td>
       </tr>
       <tr>
          <th scope="row"   >  type buffer</th>
          <td> <html:text property="typeBuffer" size="30" maxlength="30"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  buffer stroke</th>
          <td> <html:text property="bufferStroke" size="30" maxlength="30"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  material rails</th>
          <td> <html:text property="materialRails" size="30" maxlength="30"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  type governor</th>
          <td> <html:text property="typeGovernor" size="30" maxlength="30"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  safety device type</th>
          <td> <html:text property="safetyDeviceType" size="30" maxlength="30"/>   </td>
       </tr>
       <tr>
          <th scope="row"   >  stand by power</th>
          <td> <html:text property="standbyPower" size="30" maxlength="30"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  pit</th>
          <td> <html:text property="pit" size="30" maxlength="30"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  machine room</th>
          <td> <html:text property="machineRoom" size="30" maxlength="30"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  hoistway</th>
          <td> <html:text property="hositway" size="30" maxlength="30"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  shut off volve</th>
          <td> <html:text property="shutoffVolve" size="30" maxlength="30"/>   </td>
       </tr>
        <tr>
          <th scope="row"   >  cylinder check</th>
          <td> <html:text property="cylinderCheck" size="30" maxlength="30"/>   </td>
       </tr>
       <tr>
      <th scope="row"        >  remarks:</th>
          <td>
            <html:textarea property="remarks" rows="10" cols="70"/>
             </td>
      </tr> 
      <tr>
          <th scope="row"   >  receiver of report</th>
          <td> <html:text property="receiverReport" size="30" maxlength="100"/>   </td>
       </tr>
       <tr>
          <th scope="row"   > submittor of report</th>
          <td> <html:text property="submittor" size="30" maxlength="30"/>   </td>
       </tr>
        <tr>
          <th scope="row"   > district</th>
          <td> <html:text property="submittor" size="15" maxlength="15"/>   </td>
       </tr>
       <tr>
          <th scope="row"  class="required"  > * inspection  status:</th>
          <td>
          <html:select property="inspectionStatus"  onchange="setSelectValue('SELECT_ABBREVIATION','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_INSP_STATUS_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
         <c:if test="${ELEV_INSPECTION_ERROR.inspType == 'ERROR'}">
              <br/><span class="error">* please inspection status  </span> 
          </c:if>  
            </td>
           </tr>
           <tr>
          <th scope="row"  class="required"  > * inspection  type:</th>
          <td>
          <html:select property="inspectionType"  onchange="setSelectValue('SELECT_ABBREVIATION','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_INSP_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
         <c:if test="${ELEV_INSPECTION_ERROR.inspType == 'ERROR'}">
              <br/><span class="error">* please inspection status  </span> 
          </c:if>  
            </td>
           </tr>
           <tr>
          <th scope="row"  class="required"  > * inspection  category:</th>
          <td>
          <html:select property="inspCategory"  onchange="setSelectValue('SELECT_ABBREVIATION','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_INSP_CAT_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
         <c:if test="${ELEV_INSPECTION_ERROR.inspType == 'ERROR'}">
              <br/><span class="error">* please inspection status  </span> 
          </c:if>  
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
          <th scope="row"  class="required"  > * inspection  contractor:</th>
          <td>
          <html:select property="sevContractorId"  onchange="setSelectValue('SELECT_ABBREVIATION','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_CONTRACTOR_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
         </td>
           </tr>
                <tr>
          <th scope="row"  class="required"  > * inspection  mechanic:</th>
          <td>
          <html:select property="serMechanicId"  onchange="setSelectValue('SELECT_ABBREVIATION','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ELEV_MECHANIC_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
         </td>
           </tr>
             <tr>
      <th scope="row"   class="required"     >  * inspector:</th>
      <td>
          <html:select property="inspectorId" styleId="SELECT_INSPECTOR_ID" onchange="setSelectValue('SELECT_INSPECTOR_ID','OPTION_VALUE')">
          <html:option value="">Please Select</html:option>
          <html:options collection="INSPECTORS_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        </td>
      </tr> 
      <tr>
          <tr>
          <th scope="row"   > </th>
          <td>
           <html:submit value=" Save " styleClass="button"/>
          </td>
           </tr>
           </tbody>
           </table>
           </html:form>