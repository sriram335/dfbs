<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<html:xhtml/>

<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Approve / Deny Permit</h2>
  
  <div id="leftContentFluid">
   <c:if test="${FIREWORKS_PERMIT_APPROVE == 'Y'}">
    <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="approveSaveCounty"/>
      <html:hidden property="permitNumber"/>
     
      
        <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
          <tr>
        <th scope="row"   class="required"     >  *Approval Comments(Max 750 Characters):</th>
          <td>
            <html:textarea property="permitComments" rows="10" cols="70"/>
                 <c:if test="${FIREWORKS_PERMIT_APPROVE_ERROR == 'ERROR'}">
              <br/><span class="error">* please enter comments  </span> 
            </c:if>
            </td>
      </tr> 
          </tbody>
        </table>
        <p>
          <html:submit value="Approve" styleClass="button"/>
        </p>
    </html:form>
    </c:if>
     <c:if test="${FIREWORKS_PERMIT_APPROVE == 'N'}">
    <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="denySaveCounty"/>
      <html:hidden property="permitNumber"/>
     
      
        <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
          <tr>
        <th scope="row"   class="required"     >  *Denied Comments(Max 750 Characters):</th>
          <td>
            <html:textarea property="permitComments" rows="10" cols="70"/>
                 <c:if test="${FIREWORKS_PERMIT_APPROVE_ERROR == 'ERROR'}">
              <br/><span class="error">* please enter comments  </span> 
            </c:if>
            </td>
      </tr> 
          </tbody>
        </table>
        <p>
          <html:submit value="Denied" styleClass="button"/>
        </p>
    </html:form>
    </c:if>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
