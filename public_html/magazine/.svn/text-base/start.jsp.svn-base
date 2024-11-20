<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Magazine Permits" />
<c:set var="title" scope="request" value="Magazine Permits Application" />



<jsp:include page="/app/common/hsPageHeader.jsp" />
<c:if test="${MAG_PERMITS_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.MAG_PERMITS_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${MAG_PERMITS_APP_STATUS != 'I' || sessionScope.DFBS_SECURITY != null}">

<h2>Start Application</h2>


<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
       <a   href="/dfbs/magazine/permit.do?method=downLoadFile&fileName=How to add a Inspection.doc&fileId=284&fileType=MASTER" target="_blank">
             [Help file on how to add a inspection]</a></br>
        </c:if>
 <div id="sideContentFluid">
  <jsp:include page="shoppingCart.jsp"/>
</div>
<div id="leftContentFluid" align="left">
<p class="error" size="100">
<b><u>IMPORTANT NOTE:</b></u> : This application can be used to apply for new magazine permits. Or you can use it to renew a existing 
magazine permit (for this option you will need the magazine Identification Number that starts with MA ).</br>
<b><u>New Business process rules</b></u>
1.Magazine owners have to keep the same MA number assigned during its life cycle. Even if you move the magazine you will retain the same number for
all future renewals. 2. Magazine owners are advised to paint this MA number inside the Magazine in a visible way for IDHS inspectors to inspect.
3. After a new magazine application or a renewal is done for the first time online, magazine owners will be provided with a user name and password
for the system. Magazine owners have to use this user name and password for all subsequent new / renewal applications of magazines.
4. With user name and password use, all the magazine owners can a.) update magazine location address b.) print their own approved permits
c.)update owner / contact information. 
If you have questions or concerns contact <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/> or <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/>.
</p></br>
<c:if test="${sessionScope.DFBS_SECURITY !=null}">
<p class="message">
Welcome  <c:out value="${sessionScope.DFBS_SECURITY.userId}"/>, 
</p>
<html:form action="/magazineUser" method="post">
<input type="hidden" name="method" id="METHOD_KEY" value="userList"/>
   <p class="messageBox" size="100">
   To Manage magaizne users</br>
         <input type="submit" value="Manage Users" class="button"/>
    </p>  
</html:form>
 <tr>
      <th scope="row"  class="required"    > </th></br>
      <td>
      <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY"  value="lookupNewPermits"/>
            <p class="messageBox" size="100">
             To process new permits</br>
              <input type="submit" value="Process new permits" class="button"/></br>
            </p>
            </html:form>
        </td>
    </tr>  
</c:if>
<b> Select a Option below</b></br></br>
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="renewBy"/>
   <p class="messageBox" size="100">
   To Renew or view information</br>
         <input type="submit" value="Renew & View" class="button"/>
    </p>  
</html:form>
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="newPermitNewOwner"/>
     <p class="messageBox" size="100">
        To add a new magazine permit with new owner name and address </br> 
        <input type="submit" value="New owner & New permit" class="button"/>
      </p>
</html:form>
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="newPermitOldOwner"/>
      <p class="messageBox" size="100">
       If you would like to add a new magazine permit with same owner name and address </br> 
       <input type="submit" value="Existing Owner & New Permit" class="button"/>
      </p>
       
</html:form>
<html:form action="/magazineUser" method="post">
      <p class="messageBox" size="100">
       I have a IDHS assigned user account, I would like to access my account and apply for new magazine or renew existing magazines </br> 
       <input type="submit" value="Login to Magazine user account" class="button"/>
      </p>
       
</html:form>
</div>
<%--</c:if>
<c:if test="${sessionScope.DFBS_SECURITY ==null}">
<p class="messageBox" size="100">
<b>
This application is experiencing technical difficulties, Check back later </b>
</p> 
 </c:if>  --%>

<div class="clearer">&nbsp;</div>

</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp" />
