<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Elevator Permits" />
<c:set var="title" scope="request" value="Elevator Online Application" />

<jsp:include page="/app/common/hsPageHeader.jsp"/>

<h2>Start Application</h2>
<div id="sideContentFluid">
    <jsp:include page="shoppingCart.jsp"/>      
       
</div>
<div id="leftContentFluid" align="left">
<html:form action="/owner" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="ownerName"/>
  <p class="messageBox" size="100"> 
  Look up elevator by owner name like: (to look up Eli Lilly enter Lilly in the box, it will return all data matches)</br>
       <html:text property="ownerDBA" size="30" maxlength="50"/></br>
      <input type="submit" value="Search by Owner Name" class="button"/>
      </p>
</html:form>
<html:form action="/owner" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="ownerStreet"/>
  <p class="messageBox" size="100"> 
  Look up elevator by owner steet number (for 302 W WASHINGTON enter 302 in the box)</br>
       <html:text property="ownerDBA" size="30" maxlength="50"/></br>
      <input type="submit" value="Search by Owner Street Number" class="button"/>
      </p>
</html:form>
<html:form action="/owner" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="elevStateNumber"/>
  <p class="messageBox" size="100"> 
  Look up elevator by elevator state number </br>
       <html:text property="ownerDBA" size="30" maxlength="50"/></br>
      <input type="submit" value="Search by Elevator State Number" class="button"/>
      </p>
</html:form>
<html:form action="/owner" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="elevStreetNumber"/>
  <p class="messageBox" size="100"> 
  Look up elevator by elevator street number (for 302 W WASHINGTON enter 302 in the box)</br>
       <html:text property="ownerDBA" size="30" maxlength="50"/></br>
      <input type="submit" value="Search by Elevator Street Number" class="button"/>
      </p>
</html:form>
<html:form action="/owner" method="post">
  <p class="messageBox" size="100"> 
  Look up from owners list by first letter of owner name</br>
      <input type="submit" value="Search All owners" class="button"/>
      </p>
</html:form>
</div>
<div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp" /> 