 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Fees and Fines"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
<jsp:include page="/idhsFeesFines/shoppingCart.jsp"/>
<p class="messageBox">
  Note: You can pay for multiple state permit numbers and/or multiple owners for any selected category. An example for FIREWORKS - After adding a record to the Shopping Cart, search again and you can add other records to the Shopping Cart. When you are finished adding all records you need, proceed to checkout.</A></br>
</p>
</div> 
<c:if test="${FEE_FINES_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.FEE_FINES_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${FEE_FINES_APP_STATUS != 'I'}">
<div id="mainBox">

<div id="leftContentFluid" align="left">
 <p class="messageBox">
Use this application to pay fees due IDHS.  For renewals and permit applications please select the appropriate link from http://www.in.gov/dhs/3552.htm </p>

 <c:if test="${sessionScope.SEARCH_FOR_FINE == null }">
 <b><u>Pay for</u></b></br>
<html:form action="/start" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="search"/> 
 <p class="error">
 Pay your fines here. You need to have Facility Id number provided by the inspector / letter to proceed.
    <html:submit value="Pay Fines" styleClass="button" property="searchFor"/>  </br> </br>
 </p>
   <html:submit value="AE Permits" styleClass="button" property="searchFor"/>  </br> </br>
  <!-- <html:submit value="Amusement Rides" styleClass="button" property="searchFor"/>  </br> </br>-->
   <html:submit value="Blaster Certificate" styleClass="button" property="searchFor"/>  </br> </br>
   <html:submit value="Boiler and Pressure Vessels" styleClass="button" property="searchFor"/>  </br> </br>
 <!-- <html:submit value="Elevators" styleClass="button" property="searchFor"/>  </br> </br>-->
  <html:submit value="Fire Safe Cigarettes" styleClass="button" property="searchFor"/>  </br> </br>
  <html:submit value="Fireworks" styleClass="button" property="searchFor"/>  </br> </br>
  <html:submit value="Hazmat Transport Permit" styleClass="button" property="searchFor"/>  </br> </br>
  <html:submit value="Industrial Building CDR" styleClass="button" property="searchFor"/>  </br> </br>
   <html:submit value="Magazines" styleClass="button" property="searchFor"/>  </br> </br>
  <html:submit value="Plan Review" styleClass="button" property="searchFor"/>  </br> </br> 
  <html:submit value="Purchase Seals" styleClass="button" property="searchFor"/>  </br> </br>
  <html:submit value="Registered Ministries" styleClass="button" property="searchFor"/>  </br> </br>
  <html:submit value="Supervised Fireworks Public Displays" styleClass="button" property="searchFor"/>  </br> </br>
   <html:submit value="UST Certification" styleClass="button" property="searchFor"/>  </br> </br>
  <html:submit value="Variance Application" styleClass="button" property="searchFor"/>  </br> </br>
  
</html:form>
</c:if>
<c:if test="${sessionScope.SEARCH_FOR_FINE != null }">
<p class="messageBox">
<b><u>Steps for paying</u></b>
1.Search for Fee due.</br>
2.Select fees to be paid (check, pay check box).</br>
3.Add all fee selcted in step 2 to cart (click add to cart button).</br>
4.verify the fees and totals in the cart and click proceed to checkout to start payment process.</br>
</p>
<b>
<p class="error"><b>
After searching scroll down to see the dues on the same screen.
</b></p>
<html:form action="/start" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="searchById"/> 
<html:text property="ownerId" size="12" maxlength="12"/></br> 
   <html:submit value="Search by Owner ID" styleClass="button" />  </br> </br> 
</html:form>
<html:form action="/start" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="searchById"/> 
  <c:out value="${sessionScope.SEARCH_FOR_FINE}"/> <html:text property="stateNumber" size="30" maxlength="30"/></br> 
    <html:submit value="Search by State Number" styleClass="button" />  </br> 
</html:form>
<p class="error"><b>
1.Before you start make sure that you have credit card information ready.
2.Make sure you do not exceed your online credit card limit. If you exceed the limit the transaction will fail. To know your limit contact 
your Credit Card issuer.
</b></p>
 
</c:if>
<jsp:include page="showDues.jsp"/> 
</div>
</div>
<div class="clearer">&nbsp;</div>
</div>
</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 