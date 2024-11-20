<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>



<hs:control/>

<c:set var="module" scope="request" value="AE Permits" />
<c:set var="title" scope="request" value="AE Permits Application" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<c:if test="${AE_PERMITS_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.AE_PERMITS_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${AE_PERMITS_APP_STATUS != 'I'}">
<div align="left" >
<h2>Start Application</h2>

<c:if test="${sessionScope.DFBS_SECURITY !=null}">
<p class="message">
Welcome  <c:out value="${sessionScope.DFBS_SECURITY.userId}"/>, 
</p>
</c:if>
 <a   href="/dfbs/aepermits/main.do?method=downLoadFile&fileName=How to add a Inspection.pdf&fileId=288&fileType=MASTER" target="_blank">
             [Frequently Asked Questions for AE permit]</a></br>

<div >
 

<c:if test="${ sessionScope.SEARCH_AE == null}"> 
<p class="messageBox" >
   <u> <b>IMPORTANT NOTE(s):  </br></b> </u>  1.a)You need to upload
     an electronic copy of the entertainment facility floor plan to apply / renew permit online.
     So keep the floor plan file ready for upload during the application process.
     Only files with these extensions are accepted  BMP,DOC,DWF,GIF,HTM,JPEG, JPG,PDF,PPT,RTF,TIF,TIFF,TXT.</br>
     b) If you are applying for permit under fee exempt category, upload 501c document. 
     If you fail to upload the document, your permit process may be delayed</br>
     2.For each additional floor plan of the facility you need to pay an additional $99.00 fee.
     Keep the credit card ready when you start the application process to pay for the fee as applicable. </br>
     3. For Special Permit application, Please apply at least 2 weeks before the event date to facilitate the IDHS inspector's inspection.</br> </br>
</p>
<p class="messageBox" size="200"  >
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!</br>
IDHS is trying to eliminate the duplicate data in the database. To achieve this objective, we request the users
first search the database for their permit based on the location address, if you do not locate your permit then
apply for a new permit. If you find a pemit for your location address , please go ahead and apply for its renewal.
If you find some information which you are unable to change online, go ahead complete the renewal process. After your renewal is 
complete, email the changes you could not make online  to: <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/> at <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>
     or <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/> at <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>
</br> !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!       </p>

<html:form action="/start" method="post">
<p class="messageBox" >
Facility refers to the physical location of the building which needs the AE permit. For Street number if location is  302 W washington st
enter 302, under street name enter washington ignore street prefix like N,S,W and suffix like st,ave,ct etc. The search will bring up
results based on pattern search.</br></br>
Facility Street Number:<html:text id="streetNumber" property="streetNumber" size="10" maxlength="10"/></br>
Facility Street Name:<html:text id="streetName" property="streetName" size="20" maxlength="20"/></br>
Facility City:<html:text id="city" property="city" size="30" maxlength="30"/></br></br>
<c:if test="${requestScope.SEARCH_NULL == 'ERROR'}">
<p class="error"> Need atleast one parameter for search</p>
</c:if>
  <input type="hidden" name="method" id="METHOD_KEY" value="search"/>
        <input type="submit" value="Search" class="button"/></br></br>
</html:form> 
 <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="renewByPermitNumber"/>
            <p>
           search by Permit Number:
               <html:text property="permitNumber" size="12" maxlength="12"/>
            </br>
              <input type="submit" value="Select the permit by permit number" class="button"/>
            </p>
          
          <c:if test="${START_FORM_ERROR.permitNumber =='ERROR'}">
          <span class="error">* please enter permit number</span>
        </c:if> </br></br></br></br>
      </html:form>
</c:if>
<c:if test="${sessionScope.SEARCH_AE == 'SEARCH'}"> 
<p class="error" >
if you do not find a match, go to the bottom of list and click <b> I did not find a match </b> button.</br></br>
</p>
        <c:forEach var="aePermits" items="${AE_SEARCH_LIST.list}" varStatus="a"  >
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          AE ID Number:<c:out value="${aePermits.resultId}"/> 
        </h3>
          <p class="listingInfo">
          Location Address:           <br/>
           <c:out value="${aePermits.facilityName}" /><br/>
            <c:out value="${aePermits.resultAddress1}" />,
            <c:out value="${aePermits.resultAddress2}" /></br>
            <c:out value="${aePermits.resultCity}" />,<c:out value="${aePermits.resultZip}" />,<c:out value="${aePermits.resultCounty}" /> County
            </br>
          </p>
          <a   href="/dfbs/aepermits/main.do?method=viewOwner&permitNumber=<c:out value="${aePermits.resultId}"/>"> Renew</a>&nbsp;&nbsp;
            </div>
           
  </c:forEach>
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="No Match"/>
           <input type="submit" value="I did not find a match" class="button"/>
    
</html:form>
 </c:if>

<c:if test="${sessionScope.SEARCH_AE == 'No Match' ||sessionScope.DFBS_SECURITY != null}">  
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="renewByOwner"/>
  <p class="messageBox" size="100">
       If you have a previous AE permit permit </br>
       from State of Indiana and would like to renew / view it </br> 
      <b> click below</b> </p>
        <input type="submit" value="Renew & View" class="button"/>
      
</html:form>
<html:form action="/application" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="startApplication"/>
     <p class="messageBox" size="100">
       If you never had a AE permit permit from State of Indiana </br>
       and would like to add a new AE permit facility with new owner name and address </br> 
      <b> click below</b> </p>
      <p>
        <input type="submit" value="New owner & New permit" class="button"/>
      </p>
    
</html:form>

<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="renewByOwner"/>
      <p class="messageBox" size="100">
       If you have a previous AE permit permit from State of Indiana </br>
       and would like to add a new AE permit facility with same owner name and address </br> 
      <b> click below</b> </p>
      <p>
        <input type="submit" value="Existing Owner & New Permit" class="button"/>
      </p>
       
</html:form>

</c:if>
</div>
</div>
<div class="clearer">&nbsp;</div>
</c:if>
<%--

<b><u>Under maintenance, please come back later.</b></u> --%>
<jsp:include page="/app/common/hsPageFooter.jsp" />
