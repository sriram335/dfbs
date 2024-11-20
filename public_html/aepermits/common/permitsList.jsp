<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
 <c:forEach var="permit" items="${sessionScope.DFBS_OWNER.permits}" varStatus="i">
<table cellspacing="0" style="width: 100%;" summary="sales">
  <tr>
    <th>permit #</th>
    <th>address</th>
    <th>status</th>
  </tr>
  <tbody>
   
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
         <b> <c:out value="${permit.permitNumber}"/>&nbsp;</span></b>
         Inspector Assigned:<c:out value="${permit.inspectorName}"/>&nbsp;</br>
          <a   href="/dfbs/aepermits/permit.do?method=viewPermit&permitNumber=<c:out value="${permit.permitNumber}"/>">
             [view  application ]</a> 
          
          <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
           <a   href="/dfbs/aepermits/main.do?method=viewFees&permitNumber=<c:out value="${permit.permitNumber}"/>">
             [view fee details ]</a> 
              <a   href="/dfbs/aepermits/permit.do?method=updatePermit&permitNumber=<c:out value="${permit.permitNumber}"/>">
             [update permit ]</a> 
             <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${permit.permitNumber}"/>">
               [ Inspections List for this permit ]</a>
        </c:if>
        </td>
        <td>
          <c:out value="${permit.actualLocation}"/>
          <br/>
          <c:out value="${permit.street1}"/>
          <br/>
          <c:if test="${permit.street2 != null && permit.street2 != ''}">
            <c:out value="${permit.street2}"/>
            <br/>
          </c:if>
          <c:out value="${permit.city}"/>,
          <c:out value="${permit.state}"/>
          <c:out value="${permit.zip}"/>.&nbsp;<c:out value="${permit.county}"/> County
        </td>
         <td>
          <c:choose>
            <c:when test="${permit.status == 'EXPIRED'  }">
             <img class="iconFalse" src="/dfbs/img/clearbits/close.gif" alt="expired"/>
              <span class="error" style="font-weight:bold;font-size:medium;"><c:out value="${permit.status}" /> </span>
             <html:form action="/application" method="post">
              <input type="hidden" name="method" id="METHOD_KEY" value="startApplication"/>
              <input type="hidden" name="permitNumber"  value="${permit.permitNumber}"/>
                    <input type="submit" value="Renew" class="button"/></br></br>
            </html:form>
             </c:when>
            <c:when test="${permit.status == 'PENDING'}">
              <img class="iconNotice" src="/dfbs/img/clearbits/alert.gif" alt="expired"/>
              <span class="notice" style="font-weight:bold;font-size:medium;">
              pending<br />
              (<c:out value="${permit.applicationDateString}" />)
              </span>
              <html:form action="/special" method="post">
              <input type="hidden" name="key"  value="${permit.permitNumber}"/>
              <input type="hidden" name="ownerId"  value="${permit.ownerId}"/>
              <input type="hidden" name="method" id="METHOD_KEY" value="addSpecialFormDirect"/>
                    <input type="submit" value="Apply for Special" class="button"/></br></br>
              </html:form>
            </c:when>
            <c:when test="${permit.status == 'VALID'}">
              <img class="iconTrue" src="/dfbs/img/clearbits/check.gif" alt="valid"/>
              <span class="message" style="font-weight:bold;font-size:medium;">valid </span>
               <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_entertainment_permit_new.rdf&p_id=<c:out value="${permit.permitNumber}"/>">[ view permit ]</a>
             <html:form action="/special" method="post">
              <input type="hidden" name="key"  value="${permit.permitNumber}"/>
               <input type="hidden" name="ownerId"  value="${permit.ownerId}"/>
              <input type="hidden" name="method" id="METHOD_KEY" value="addSpecialFormDirect"/>
                    <input type="submit" value="Apply for Special" class="button"/></br></br>
              </html:form>
               <html:form action="/application" method="post">
              <input type="hidden" name="permitNumber"  value="${permit.permitNumber}"/>
              <input type="hidden" name="method" id="METHOD_KEY" value="startApplication"/>
                    <input type="submit" value="Renew" class="button"/></br></br>
              </html:form>
              </c:when>
              <c:when test="${ permit.status == 'EXPIRES SOON'}">
              <img class="iconTrue" src="/dfbs/img/clearbits/check.gif" alt="valid"/>
              <span class="error" style="font-weight:bold;font-size:medium;"><c:out value="${permit.status}" /> </span>
               <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_entertainment_permit_new.rdf&p_id=<c:out value="${permit.permitNumber}"/>">[ view permit ]</a>
              <html:form action="/application" method="post">
              <input type="hidden" name="permitNumber"  value="${permit.permitNumber}"/>
              <input type="hidden" name="method" id="METHOD_KEY" value="startApplication"/>
                    <input type="submit" value="Renew" class="button"/></br></br>
              </html:form>
              </c:when>
            <c:otherwise>
              <img class="iconFalse" src="/dfbs/img/clearbits/check.gif" alt="valid"/>
            </c:otherwise>
          </c:choose>
          <c:if test="${sessionScope.DFBS_SECURITY !=null}">
           <a href="/dfbs/aepermits/main.do?method=IDHSgoToUpload&idNumber=<c:out value="${permit.permitNumber}"/>&idType=AEPermit">
                    [upload AE Annual facility floor plan]</a> </br>
          </c:if>
         
        </td>
      </tr>
      
   
    <c:choose>
    <c:when test="${permit.specialsCount > 0 }">
    <table cellspacing="0" style="width: 100%;" summary="sales">
    <tr><th>
    <p class="error" style="font-size:medium;font-weight:bold;padding: 12px;">
    Special Endorsements for this Annual Permit
    </p>
     </th></tr>
    </table>
    <table cellspacing="0" style="width: 100%;" summary="sales">
    <tr>
    <th>Event Name</th>
    <th>Event Date</th>
    <th>Event Time</th>
    <th>Maximum Occupancy</th>
    <th>Issue Date</th>
    <th>Expiration Date</th>
    <th>Status</th>
    </tr>
    <c:forEach var="special" items="${permit.specials}" varStatus="j">
    
    <tbody>
     <tr class="row<c:out value='${(j.index % 2) + 1}' />">
        <td>
          <c:out value="${special.eventName}"/>&nbsp;
        </td>
         <td>
          <c:out value="${special.eventDateString}"/>&nbsp;
        </td>
         <td>
          <c:out value="${special.eventTime}"/>&nbsp;
        </td>
         <td>
          <c:out value="${special.maximumOccupancy}"/>&nbsp;
          </td>
        <td>
           
          <c:out value="${special.issueDateString}"/>&nbsp;
        </td>  
        <td> 
          <c:out value="${special.expirationDateString}"/>&nbsp;
          
        </td>
        <td> 
           <c:if test="${special.issueDate == NULL}">
              <img class="iconNotice" src="/dfbs/img/clearbits/alert.gif" alt="expired"/>
            <span class="notice" style="font-weight:bold;font-size:medium;">
              pending<br />
             </span>
            </c:if>
             <c:if test="${special.issueDate != NULL}">
              <img class="iconTrue" src="/dfbs/img/clearbits/check.gif" alt="valid"/>
              <span class="message" style="font-weight:bold;font-size:medium;">valid </span>
               <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_entertainment_permit_new.rdf&p_id=<c:out value="${permit.permitNumber}"/>&p_special_date=<c:out value="${special.issueDateString}"/>">[ permit ]</a>
            </c:if>
             <a   href="/dfbs/aepermits/special.do?method=viewSpecial&specialId=<c:out value="${special.specialId}"/>">
             [view this special ]</a> 
             <c:if test="${sessionScope.DFBS_SECURITY !=null}">
              <a href="/dfbs/aepermits/main.do?method=IDHSgoToUpload&idNumber=<c:out value="${special.specialId}"/>&idType=AESpecial">
                    [upload AE Special floor plan]</a> </br>
               <a   href="/dfbs/aepermits/special.do?method=updateSpecial&specialId=<c:out value="${special.specialId}"/>">
             [update this special ]</a> 
              </c:if>
        </td>
      </tr> 
       </tbody>
        </c:forEach>
        
        </table>
       </c:when>
       </c:choose>
   
  </tbody>
</table>
 </c:forEach>
