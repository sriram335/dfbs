 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml />

<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
        <tr>
          <th scope="row" colspan="2" style="font-size:large;font-weight:bold;text-align:left;" >
            <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.ownerName}"/>
            <c:if test="${DFBS_APPLICATION_ERROR.ownerName == 'ERROR'}">
              <br/><span class="error">* please specify company name  (step 1)</span> 
            </c:if>
          </th>
        </tr>
        <tr>
          <th scope="row">address:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.street1}"/>
            <br/>
            <c:if test="${sessionScope.DFBS_OWNER_APPLICATION.street2 != null && sessionScope.DFBS_OWNER_APPLICATION.street2 != ''}">
              <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.street2}"/>
              <br/>
            </c:if>
            <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.city}"/>,
            <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.state}"/>
            <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.zip}"/>
            <c:if test="${DFBS_APPLICATION_ERROR.street1 == 'ERROR'}">
              <br/><span class="error">* please specify street address (step 1)</span> 
            </c:if>
            <c:if test="${DFBS_APPLICATION_ERROR.city == 'ERROR'}">
              <br/><span class="error">* please specify city  (step 1)</span> 
            </c:if>
            <c:if test="${DFBS_APPLICATION_ERROR.stateId == -1}">
                <br/>
                <span class="error">* please specify a state  (step 1)</span> 
              </c:if>
              <c:if test="${DFBS_APPLICATION_ERROR.zip == 'ERROR'}">
                <br/>
                <span class="error">* please specify zip code  (step 1)</span> 
              </c:if>
          </td>
        </tr>
        <%--
        <tr>
          <th scope="row">phone:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.phoneNumber}"/>
            <c:if test="${DFBS_APPLICATION_ERROR.phoneNumber == 'ERROR'}">
                <br/>
                <span class="error">* please specify a phone number  (step 1)</span> 
              </c:if>
          </td>
        </tr>
        <tr>
          <th scope="row">fax:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.faxNumber}"/>
          </td>
        </tr>
        <tr>
          <th scope="row">email:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.ownerEmail}"/>
          </td>
        </tr>
        <tr><td colspan="2">&nbsp;</td></tr>
        <tr>
          <th scope="row">contact:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.firstName}"/> <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.lastName}"/>
            <c:if test="${DFBS_APPLICATION_ERROR.firstName == 'ERROR'}">
                <br/>
                <span class="error">* please specify firstname of contact  (step 1)</span> 
              </c:if>
              <c:if test="${DFBS_APPLICATION_ERROR.lastName == 'ERROR'}">
                <br/>
                <span class="error">* please specify lastname of contact  (step 1)</span> 
              </c:if>
          </td>
        </tr>
        <tr>
          <th scope="row">contact phone:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.contactPhoneNumber}"/> 
            <c:if test="${DFBS_APPLICATION_ERROR.contactPhoneNumber == 'ERROR'}">
                <br/>
                <span class="error">* please specify contact phone number  (step 1)</span> 
              </c:if>
          </td>
        </tr>
       --%>
        
        
      </tbody>
    </table>