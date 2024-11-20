 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Fireworks"/>
<c:set var="title" scope="request" value="Fireworks Seller"/>
<c:set var="step" scope="request" value="2"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Edit Permit</h2>
  <div id="rightContentFluid">
    <p class="control2">
      <html:link page="/application.do?method=step2">cancel</html:link>
    </p>
  </div>
  <div id="leftContentFluid">
    <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="editPermit"/>
      <html:hidden property="categoryId"/>
      <html:hidden property="permitNumber"/>
      <html:hidden property="ownerId"/>
      <html:hidden property="applicationKey"/>
      <html:hidden property="noMap"/>
      <html:hidden property="county" styleId="COUNTY_NAME"/>
      <p style="font-size:medium;font-weight:bold;">
        <img class="icon" src="/dfbs/img/clearbits/arrow2_e.gif" alt="arrow"/>add new permit :
      </p>
     
        <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
            <jsp:include page="permitForm.jsp"/>
            <tr>
              <th colspan="2">&nbsp;</th>
            </tr>
          </tbody>
        </table>
        <p>
          <html:submit value="save to your application" styleClass="button"/>
        </p>
    </html:form>
    <c:if test="${requestScope.DFBS_PERMIT.new}">
      <script type="text/javascript">
        setSelectValue('SELECT_COUNTY','COUNTY_NAME');
        showMiscRow();
      </script>
    </c:if>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
