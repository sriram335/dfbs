<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<a   href="/dfbs/elevator/owner.do?method=view&ownerId=<c:out value="${sessionScope.OWNER_SELECTED.ownerId}"/>">
             [back to owner]</a> 
<c:if test="${requestScope.ELEV_APP_AGREE !=null  && requestScope.ELEV_APP_AGREE !='Agreed' }">  
<p class="error">
You need to Checkbox "I agree" to proceed
</p>
</c:if>
<h2>Installtion Application</h2> 
<div id="sideContentFluid">
 

</div>

<div id="leftContentFluid">
  <div class="styledBox">

  <html:form action="/application" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="applySaveInstallation"/>       
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     
    <tr>
      <th scope="row" style="width : 50%">state number:</th>
      <td style="font-size:medium;font-weight:bold">
        <c:out value="${sessionScope.ELEVATOR_SELECTED.stateNumber}"/></br>
     Address:   <c:out value="${sessionScope.ELEVATOR_SELECTED.locAddress1}"/></br>
        <c:out value="${sessionScope.ELEVATOR_SELECTED.locAddress2}"/></br>
        <c:out value="${sessionScope.ELEVATOR_SELECTED.locCity}"/></br>
        <c:out value="${sessionScope.ELEVATOR_SELECTED.locState}"/></br>
        <c:out value="${sessionScope.ELEVATOR_SELECTED.locZip}"/></br>
        <html:hidden property="stateNumber"/> 
      </td>
    </tr>
   
  <%--  <p class="messageBox">
      <b><u> AFFIRMATION OF THE OWNER </u></b>
      I, the owner, or authorized officer of the owner, of the building in which regulated lifting device is being installed or altered
      affirm under penalties for perjury that:</br></br>      
      1. This document and all attachments were prepared under my direction or supervision in accordance with system designed to assure
      that qualified personnel properly gather and evaluate the information submitted. Based on my inquiry of the person or persons who
      manage the system, or those persons directly responsible for gathering the information, the information submitted is, to the best of
      my knowledge and belief, tue, accurate, and complete.</br></br>
      2. The regulated lifting device will be installed or altered in accordance with all applicable rules adopted by the commisssion and will
      not be changed from the design specified in the plans and specifications submited with the application and released by the office.</br></br>
      3. The contractor responsible for the installation or alteration of the regulated lifting device was choosen under my direction and to the 
      best of my knowledge and belief, after exercising due diligence, has expertise necessary to install or alter the regulated lifting
      device in accordance with the rules adopted by the commission.</br></br>
      4. I here by grant the authority to and require all individuals employed by either the contractor or the owner to immediately suspend
      the operation of the regulated lifting devide upon discovering a condtion that could result in the unsafe operation of the regulated
      lifting device, and to report the discovery of such a condition to the office.</br></br>
      5. I understand that providing false information constitutes an act of perjury, which is a Class D felony punishable by a prison term and
      a fine up to $10,000.</br></br>
       <input type="checkbox" name="ownerAgree" class="switch"/>I agree
       </p>  --%>
       <tr>
      <th scope="row" class="required"  > * owner/authorized officer:</th>
      <td>
       <html:text property="ownerBy" size="50" maxlength="100"/>
        <c:if test="${requestScope.ELEVATOR_APP_ERROR.ownerBy == 'ERROR'}">
              <br/><span class="error">* please enter owner name  </span> 
            </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row" >owner firm:</th>
      <td>
       <html:text property="ownerFirm" size="50" maxlength="100"/>
       
      </td>
    </tr>
     <tr>
      <th scope="row" >elevator owner phone:</th>
      <td>
       <html:text property="ownerDesignation" size="10" maxlength="10"/>
      </td>
    </tr>
     <tr>
      <th scope="row" class="required" > * elevator owner email:</th>
      <td>
       <html:text property="ownerEmail" size="50" maxlength="100"/>
       <c:if test="${requestScope.ELEVATOR_APP_ERROR.ownerEmail == 'ERROR'}">
              <br/><span class="error">* please enter email  </span> 
            </c:if>
      </td>
    </tr>
    <%--   <p class="messageBox">
      <b><u> AFFIRMATION OF THE CONTRACTOR </u></b>
      I, the contractor, or authorized officer of the contractor, responsible for the installation or alteration of the regulated lifting device hereby  
      affirm under penalties for perjury that:</br></br>      
      1. This document and all attachments were prepared under my direction or supervision in accordance with system designed to assure
      that qualified personnel properly gather and evaluate the information submitted. Based on my inquiry of the person or persons who
      manage the system, or those persons directly responsible for gathering the information, the information submitted is, to the best of
      my knowledge and belief, tue, accurate, and complete.</br></br>
      2. The regulated lifting device will be installed or altered in accordance with all applicable rules adopted by the commisssion and will
      not be changed from the design specified in the plans and specifications submited with the application and released by the office.</br></br>
      3. All individuals installing or altering the regulated lifting device.</br>
      A. have sufficient background, knowledge, skills and training to install or alter, inspect, and maintain regulated lifting device.</br>
      B. have the training and expertise necessary to recognize and report any condition that could result in the unsafe operation of
      the regulated lifting device.</br>
      C. are provided with sufficient on-going training to reasonbly ensure that the individuals are proficient in the standards affecting
      regulated lifting device that have been adopted by the commission, and</br>
      D. possess the requisite authority and are required to immediately suspend operation of the regulated lifting device upon 
      discovering a condition that could result in the unsafe operation of the regulated lifting device, and to report the discovery of
      such a condition to the office</br>
      4. I understand that providing false information constitutes an act of perjury, which is a Class D felony punishable by a prison term and
      a fine up to $10,000.</br></br>
       <input type="checkbox" name="contractorAgree" class="switch"/>I agree
       </p> --%>
      <tr>
      <th scope="row"  class="required" > * applied by :</th>
      <td>
       <html:text property="appliedBy" size="50" maxlength="100"/>
       <c:if test="${requestScope.ELEVATOR_APP_ERROR.appliedBy == 'ERROR'}">
              <br/><span class="error">* please enter applied name  </span> 
            </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row" >applied firm:</th>
      <td>
       <html:text property="appliedFirm" size="50" maxlength="100"/>
      </td>
    </tr>
     <tr>
      <th scope="row" >applied designation:</th>
      <td>
       <html:text property="appliedDesignation" size="50" maxlength="100"/>
      </td>
    </tr>
     <tr>
      <th scope="row" class="required" > * applied email:</th>
      <td>
       <html:text property="appliedEmail" size="50" maxlength="100"/>
       <c:if test="${requestScope.ELEVATOR_APP_ERROR.appliedEmail == 'ERROR'}">
              <br/><span class="error">* please enter email  </span> 
            </c:if>
      </td>
    </tr>
    
     
    
       </tbody>
        </table>
 
  <p>
       <html:submit value="Save " styleClass="button"/>
      </html:form>
     
      </p>
         
   
</div>
 </div>
