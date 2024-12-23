<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Owner Application" />


<jsp:include page="/app/common/hsPageHeader.jsp" />
 <div id="sideContentFluid">
  <jsp:include page="shoppingCart.jsp"/>
</div>
<b><u>Owner Information:</u></b>
  <div class="styledBox" align="left">
    <table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
        <tr>
          <th scope="row"  style="font-size:large;font-weight:bold;">NAME:</th>
           <td style="font-size:large;font-weight:bold;">
            <c:out value="${sessionScope.OWNER_SELECTED.ownerDBA}"/>
          </td>
        </tr>
        <tr>
          <th scope="row">address:</th>
          <td>
            <c:out value="${sessionScope.OWNER_SELECTED.ownerAddress1}"/>
            <br/>
            <c:if test="${sessionScope.OWNER_SELECTED.ownerAddress2 != null && sessionScope.OWNER_SELECTED.ownerAddress2 != ''}">
              <c:out value="${sessionScope.OWNER_SELECTED.ownerAddress2}"/>
              <br/>
            </c:if>
            <c:out value="${sessionScope.OWNER_SELECTED.ownerCity}"/>,
            <c:out value="${sessionScope.OWNER_SELECTED.ownerState}"/>
            <c:out value="${sessionScope.OWNER_SELECTED.ownerZip}"/>
          </td>
        </tr>
        <tr>
          <th scope="row">phone:</th>
          <td>
            <c:out value="${sessionScope.OWNER_SELECTED.ownerPhone}"/>
          </td>
        </tr>
        <tr>
          <th scope="row">fax:</th>
          <td>
            <c:out value="${sessionScope.OWNER_SELECTED.ownerFax}"/>
          </td>
        </tr>
         <tr>
          <th </th>
          <td>
           <html:form action="/owner" method="post">
          <input type="hidden" name="method" id="METHOD_KEY" value="newInstallation"/>
                  <p> <input type="submit" value="Apply for New Installation Permit" class="button"/></p>
          </html:form> 
          <a   href="/dfbs/elevator/elevator.do?method=feesByOwnerId&ownerId=<c:out value="${sessionScope.OWNER_SELECTED.ownerId}"/>">
             [pay fees for this owner]</a>   
         </td>
          </tr>
      </tbody>
    </table></br>
    <p class="error">
   <b><u>Important Note:</u></b> A. If the last annual test(older than one year) and last 5 year test dates(older than 5 years) are not current , permits will not me issued. Please verify the status of elevator before calling IDHS.
  </br> B. If you have outstanding fees also the permit(s) will not be issued.</br>
  C.If you click the dates in Blue, it will open the corresponding reports for printing.
    </p>
   <b><u> Elevators for Owner:</u></b></br>
    <c:forEach var="elevator" items="${OWNER_SELECTED.elevators}" varStatus="i" >
    State Number :<a   href="/dfbs/elevator/elevator.do?method=updateElevator&stateNumber=<c:out value="${elevator.stateNumber}"/>"><b><c:out value="${elevator.stateNumber}"/></b></a> ; Address:<c:out value="${elevator.locUserName}"/>, <c:out value="${elevator.locAddress1}"/>,<c:out value="${elevator.locCity}"/>,<c:out value="${elevator.locZip}"/></br>
    <b><u>Elevator Status:</u></b> Last Invoice Date:  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=elev_invoice.rdf&p_re_owner=<c:out value="${elevator.ownerId}"/>&p_print_date=<c:out value="${elevator.invoiceDateString}"/>"><c:out value="${elevator.invoiceDateString}"/></a> ;
    Last Annual Permit Date: <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=elev_oper_certificate_new.rdf&p_state=<c:out value="${elevator.stateNumber}"/>&p_ann_date=<c:out value="${elevator.permitDateString}"/>"><c:out value="${elevator.permitDateString}"/> </a> ;
    Last Alteration/Installation Permit Date: <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=elev_oper_inst_alt.rdf&p_state=<c:out value="${elevator.stateNumber}"/>&p_in_date=<c:out value="${elevator.yearInstallDateString}"/>"><c:out value="${elevator.yearInstallDateString}"/></a>; 
    Last Temporary Permit Date: <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=elev_temp_permit.rdf&p_state=<c:out value="${elevator.stateNumber}"/>&p_temp_date=<c:out value="${elevator.yearTempDateString}"/>"><c:out value="${elevator.yearTempDateString}"/> </a> ;
    Last Annual Test Date: <c:out value="${elevator.year1TestDateString}"/> ;
    Last 5 Year Test Date: <c:out value="${elevator.year5TestDateString}"/>;
    Last Paid Date: <c:out value="${elevator.yearPaidDateString}"/>;
    <html:form action="/elevator" method="post">
     <input type="hidden" name="method" id="METHOD_KEY" value="elevatorAction"/>
     <html:hidden property="elevStateNumber" value="${elevator.stateNumber}"/>
       <html:submit value="Apply for Alteration Permit" styleClass="button" property="elevAction"/>
       <c:if test="${sessionScope.DFBS_SECURITY != null}"> 
       <html:submit value="Submit /Print inspection" styleClass="button" property="elevAction"/>
       </c:if>
      </html:form>
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=elev_invoice.rdf&p_re_owner=<c:out value="${elevator.ownerId}"/>&p_print_date=<c:out value="${elevator.invoiceDateString}"/>">[ print last invoice for this elevator ]</a>
             
      <a   href="/dfbs/elevator/elevator.do?method=feesByStateNumber&stateNumber=<c:out value="${elevator.stateNumber}"/>">
             [pay fees for this elevator]</a>  </br></br>
    <%--  <html:form action="/elevator" method="post">
     <input type="hidden" name="method" id="METHOD_KEY" value="feesByStateNumber"/>
     <html:hidden property="stateNumber" value="${elevator.stateNumber}"/>
     <html:submit value="Pay Invoice" styleClass="button" />
     </html:form></br> --%>
     </c:forEach>
     
    </div>
  
 