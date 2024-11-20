 
<%@ taglib uri="https://oas.dhs.in.gov/hs/hs.tld" prefix="hs"%>
<p>
  &nbsp;
</p>
<p>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
                                                                            prefix="html"%><%@ taglib uri="/WEB-INF/hs.tld"
                                                                                                      prefix="hs"%><%@ taglib uri="http://java.sun.com/jstl/fmt"
                                                                                                                              prefix="fmt"%><%@ taglib uri="/WEB-INF/struts-bean.tld"
                                                                                                                                                       prefix="bean"%><%@ taglib uri="/WEB-INF/struts-logic.tld"
                                                                                                                                                                                 prefix="logic"%><hs:control/>
</p>
<html:xhtml/>
<c:choose>
  <c:when test="${DFBS_PERMIT == null || DFBS_PERMIT.new}">
    <tr colspan=2>
      <td colspan=2><font color="red">For Stand/Sec8a Permits Contact Chris Clouse@317-232-1407</font></td>
    </tr>
    <tr>
      <th scope="row" style="width:30%">Permit number:</th>
      <td style="font-size:medium;font-weight:bold">NEW</td>
    </tr>
    <tr>
       <th scope="row" style="width:30%" class="required">*type:</th>
      <td>
       <%-- <html:radio property="permitType" value="1"  />wholesale
        <br/>
        <html:radio property="permitType" value="2"  />retail stand (section 8a only)--%>
        <br/> 
        <html:radio property="permitType" value="3"  />retail class 1
        <br/>
               <%--    no new tents allowed     <html:radio property="permitType" value="4" styleClass="switch"  onclick="showMiscRow();"/>retail tent --%>
        <br/> 
      </td>
    </tr>
    <tr >
      <th scope="row" class="required">*category(if wholesale):</th>
      <td>
        <html:checkbox property="manufacturer" styleClass="switch"/>manufacturer
        <br/>
        <html:checkbox property="wholesaler" styleClass="switch"/>wholesaler
        <br/>
        <html:checkbox property="importer" styleClass="switch"/>importer
        <br/>
        <html:checkbox property="distributor" styleClass="switch"/>distributor 
        <c:if test="${DFBS_PERMIT_ERROR.categoryId == -1}">
          <br/>
          <span class="error">* please specify category </span>
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*street 1:</th>
      <td>
        <html:text property="street1" size="30" maxlength="30"/>
        <c:if test="${DFBS_PERMIT_ERROR.street1 == 'ERROR'}">
          <br/>
          <span class="error">* please specify street address</span> 
        </c:if>
        <br/>
      </td>
    </tr>
    <tr>
      <th scope="row">street 2:</th>
      <td>
        <html:text property="street2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*city:</th>
      <td>
        <html:text property="city" size="30" maxlength="30"/>
        <c:if test="${DFBS_PERMIT_ERROR.city == 'ERROR'}">
          <br/>
          <span class="error">* please specify city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*state:</th>
      <td>
        <html:select property="state">
          <html:option value="IN">INDIANA</html:option>
          <html:options collection="DFBS_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${DFBS_PERMIT_ERROR.state == 'ERROR'}">
          <br/>
          <span class="error">* please select a state</span> 
        </c:if>
        <c:if test="${DFBS_PERMIT_ERROR.state == 'ERROR2'}">
          <br/>
          <span class="error">* please select &quot;INDIANA&quot; as state</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*zip:</th>
      <td>
        <html:text property="zip" size="5" maxlength="5"/>
        <c:if test="${DFBS_PERMIT_ERROR.zip == 'ERROR'}">
          <br/>
          <span class="error">* please specify zip code</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*county:</th>
      <td>
        <html:select property="countyId" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${DFBS_PERMIT_ERROR.countyId == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if>
      </td>
    </tr>
  </c:when>
  <c:otherwise>
    <tr>
      <th scope="row" >permit number:</th>
      <td style="font-size:medium;font-weight:bold">
        <c:out value="${DFBS_PERMIT.permitNumber}"/>
        <html:hidden property="permitNumber"/>
      </td>
    </tr>
    <tr>
      <th scope="row" style="width:30%">type:</th>
      <td>
        <c:out value="${DFBS_PERMIT.permitTypeString}"/>
        <html:hidden property="permitType"/>
      </td>
    </tr>
    <c:if test="${DFBS_PERMIT.permitType == 1 }">
      <tr>
        <th scope="row">category:</th>
        <td>
          <c:out value="${DFBS_PERMIT.categoryString}"/>
          <html:hidden property="manufacturer"/>
          <html:hidden property="wholesaler"/>
          <html:hidden property="importer"/>
          <html:hidden property="distributor"/>
        </td>
      </tr>
    </c:if>
    <c:choose>
      <c:when test="${DFBS_PERMIT.permitType == 4}">
        <tr>
      <th scope="row" class="required">*street 1:</th>
      <td>
        <html:text property="street1" size="30" maxlength="30"/>
        <c:if test="${DFBS_PERMIT_ERROR.street1 == 'ERROR'}">
          <br/>
          <span class="error">* please specify street address</span> 
        </c:if>
        <br/>
      </td>
    </tr>
    <tr>
      <th scope="row">street 2:</th>
      <td>
        <html:text property="street2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*city:</th>
      <td>
        <html:text property="city" size="30" maxlength="30"/>
        <c:if test="${DFBS_PERMIT_ERROR.city == 'ERROR'}">
          <br/>
          <span class="error">* please specify city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*state:</th>
      <td>
        <html:select property="state">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${DFBS_PERMIT_ERROR.state == 'ERROR'}">
          <br/>
          <span class="error">* please select a state</span> 
        </c:if>
        <c:if test="${DFBS_PERMIT_ERROR.state == 'ERROR2'}">
          <br/>
          <span class="error">* please select &quot;INDIANA&quot; as state</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*zip:</th>
      <td>
        <html:text property="zip" size="5" maxlength="5"/>
        <c:if test="${DFBS_PERMIT_ERROR.zip == 'ERROR'}">
          <br/>
          <span class="error">* please specify zip code</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*county:</th>
      <td>
        <html:select property="countyId" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${DFBS_PERMIT_ERROR.countyId == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if>
      </td>
    </tr>
      </c:when>
      <c:otherwise>
        <tr>
          <th scope="row">street 1:</th>
          <td>
            <c:out value="${DFBS_PERMIT.street1}"/>
            <html:hidden property="street1"/>
          </td>
        </tr>
        <tr>
          <th scope="row">street 2:</th>
          <td>
            <c:out value="${DFBS_PERMIT.street2}"/>
            <html:hidden property="street2"/>
          </td>
        </tr>
        <tr>
          <th scope="row">city:</th>
          <td>
            <c:out value="${DFBS_PERMIT.city}"/>
            <html:hidden property="city"/>
          </td>
        </tr>
        <tr>
          <th scope="row">state:</th>
          <td>
            <c:out value="${DFBS_PERMIT.state}"/>
            <html:hidden property="state"/>
          </td>
        </tr>
        <tr>
          <th scope="row" class="required">*zip:</th>
          <td>
            <html:text property="zip" size="5" maxlength="5"/>
            <c:if test="${DFBS_PERMIT_ERROR.zip == 'ERROR'}">
              <br/>
              <span class="error">* please specify zip code</span> 
            </c:if>
          </td>
        </tr>
        <tr>
          <th scope="row">county:</th>
          <td>
            <c:out value="${DFBS_PERMIT.county}"/>
            <html:hidden property="countyId"/>
          </td>
        </tr>
      </c:otherwise>
    </c:choose>
  </c:otherwise>
</c:choose>
<tr>
  <th colspan="2">&nbsp;</th>
</tr>
<tr>
  <th scope="row" class="required">* registered retail merchant certificate # / TID #:</th>
  <td>
    <html:text property="merchantNumber" size="30" maxlength="30"/></br> If you do not have this info, visit https://secure.in.gov/apps/dor/bt1/
    <c:if test="${DFBS_PERMIT_ERROR.merchantNumber == 'ERROR'}">
      <br/>
      <span class="error">* please specify merchant number</span> 
    </c:if>
  </td>
</tr>
<tr>
  <th scope="row" class="required">*contact:</th>
  <td>
    <html:text property="contactPerson" size="30" maxlength="60"/>
    <c:if test="${DFBS_PERMIT_ERROR.contactPerson == 'ERROR'}">
      <br/>
      <span class="error">* please specify contact person</span> 
    </c:if>
  </td>
</tr>
<tr>
  <th scope="row" class="required">*phone:</th>
  <td>
    <html:text property="phoneNumber" size="10" maxlength="10"/> (type the numbers only)
    <c:if test="${DFBS_PERMIT_ERROR.phoneNumber == 'ERROR'}">
      <br/>
      <span class="error">* please specify phone number</span> 
    </c:if>
  </td>
</tr>
<tr>
  <th scope="row">fax:</th>
  <td>
    <html:text property="faxNumber" size="10" maxlength="10"/>
  </td>
</tr>
<tr>
  <th scope="row">email:</th>
  <td>
    <html:text property="emailAddress" size="30" maxlength="80"/>
  </td>
</tr>

<tr>
<th scope="row" class="required">*date to inspect:</th>
<td>
  <html:text property="inspectDate" size="10" maxlength="10"/>(mm/dd/yyyy) </br>
  ** You would like IDHS inspector(s) to inspect the store / facility on this date.
  <c:if test="${DFBS_PERMIT_ERROR.inspectDate == 'ERROR'}">
    <br/>
    <span class="error">* please specify date greater than today's date to inspect</span> 
  </c:if>
</td>
<tr>
  <th scope="row" class="required">*open date:</th>
  <td>
    <html:text property="openDate" size="10" maxlength="10"/>(mm/dd/yyyy) </br>
    ** You will have fireworks stock in your store for the current year by this date.
    <c:if test="${DFBS_PERMIT_ERROR.openDate == 'ERROR'}">
      <br/>
      <span class="error">* please specify date greater than today's date</span> 
    </c:if>
  </td>
</tr>
<tr>
  <th scope="row" class="required">* hrs of operation:</th>
  <td>
    <html:text property="hoursOfOperation" size="15" maxlength="30"/>
    <c:if test="${DFBS_PERMIT_ERROR.hoursOfOperation == 'ERROR'}">
      <br/>
      <span class="error">* please specify hours of operation</span> 
    </c:if>
  </td>
</tr>
