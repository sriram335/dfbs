<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

  <div class="styledBox">
    <table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
        <tr>
          <th scope="row" colspan="2" style="font-size:large;font-weight:bold;">
            <c:out value="${sessionScope.DFBS_OWNER.ownerName}"/>
            
          </th>
        </tr>
        <tr>
          <th scope="row">address:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER.street1}"/>
            <br/>
            <c:if test="${sessionScope.DFBS_OWNER.street2 != null && sessionScope.DFBS_OWNER.street2 != ''}">
              <c:out value="${sessionScope.DFBS_OWNER.street2}"/>
              <br/>
            </c:if>
            <c:out value="${sessionScope.DFBS_OWNER.city}"/>,
            <c:out value="${sessionScope.DFBS_OWNER.state}"/>
            <c:out value="${sessionScope.DFBS_OWNER.zip}"/></br>
            <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
              <a   href="/dfbs/aepermits/main.do?method=updateOwner&ownerId=<c:out value="${sessionScope.DFBS_OWNER.ownerId}"/>">
             [update owner ]</a> 
             </c:if>
              
          </td>
        </tr>
        <tr>
          <th scope="row">phone:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER.phoneNumber}"/>
          </td>
        </tr>
        <tr>
          <th scope="row">fax:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER.faxNumber}"/>
          </td>
        </tr>
        <tr>
          <th scope="row">contact:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER.firstName}"/> <c:out value="${sessionScope.DFBS_OWNER.lastName}"/>
          </td>
        </tr>
         <tr>
          <th scope="row">contact phone:</th>
          <td>
            <c:out value="${sessionScope.DFBS_OWNER.contactPhoneNumber}"/>
          </td>
        </tr>
        
      </tbody>
    </table>
    </div>
  
 