<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value=" Agreement" />

<jsp:include page="/app/common/hsPageHeader.jsp" />
<div id="mainContentFluid">

</div>
<div id="mainBox">
<div id="sideContentFluid">
<c:if test="${sessionScope.NEW_COURSE  != null }"> 
  <jsp:include page="/ems/newCourseStatus.jsp"/>
  </c:if>
  </div>  
               

      
<div id="mainContentFluid" align="left">


  <div class="listing" align="left">
  <c:if test="${sessionScope.AGREE_TO_FLAG == 'YES'}"> 
 <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="createNewCourse"/>  
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
  <p class="messageBox" size="100">
 By clicking I Agree button you agree to "All of the subject matter listed in the checked boxes will be covered in the approved course as the minimum curriculum as adopted by the Emergency Medical Services Commission. Curriculum additions will be taught as determined by the medical director of the certified training institution."
 </br>
          <html:submit value="I Agree" styleClass="button" />
   </p>
   </c:if>
 </html:form>
</c:if>
<html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="verifyChecks"/>   
   <STRONG><U><FONT color="#ff3333">Must meet or exceed the followin criteria</FONT></U></STRONG></br>
 <%--- for first responder  <c:out value="${sessionScope.COURSE.crssCourseId}"/>--%>
<c:if test="${COURSE.crssCourseId==150012}">
 <c:forEach var="chkbox" items="${COURSE.checkBoxes}" varStatus="i" >
<c:if test="${chkbox.boxName == 'chbox1' && chkbox.boxValue=='off'}"> 
<b> </br><input type="checkbox" name="chbox1"   class="switch"/>	A list of resource materials, references, textbooks, workbooks, etc. that will be used by the students during the course.
Please print, enter actual hours and scan and upload the check list for completing course approval.
<a   href="https://forms.in.gov/Download.aspx?id=9343" target="_blank">
             [Frist Responder Checklist]</a></b></br>
 </c:if>
<c:if test="${chkbox.boxName == 'chbox1' && chkbox.boxValue== 'on'}"> 
<b> </br><input type="checkbox" name="chbox1"   class="switch" checked />	A list of resource materials, references, textbooks, workbooks, etc. that will be used by the students during the course.
Please print, enter actual hours and scan and upload the check list for completing course approval.
<a   href="https://forms.in.gov/Download.aspx?id=9343" target="_blank">
             [Frist Responder Checklist]</a></b></br></c:if>
<c:if test="${chkbox.boxName == 'chbox2' && chkbox.boxValue=='off'}"> 
</br><u>A course syllabus, which must include the:</u>
</br><input type="checkbox" name="chbox2"   class="switch"/>	1. Preparatory (min 5.5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox2' && chkbox.boxValue=='on'}"> 
</br><u>A course syllabus, which must include the:</u>
</br><input type="checkbox" name="chbox2"   class="switch" checked/>	1. Preparatory (min 5.5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox3' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox3"   class="switch"/>	2. Anatomy and Physiology (min 2 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox3' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox3"   class="switch" checked/>	2. Anatomy and Physiology (min 2 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox4' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox4"   class="switch"/>	3. Medical Terminology (min .5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox4' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox4"   class="switch" checked/>	3. Medical Terminology (min .5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox5' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox5"   class="switch"/>	4. Pathophysiology (min .5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox5' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox5"   class="switch" checked/>	4. Pathophysiology (min .5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox6' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox6"   class="switch"/>	5. Lifespan Development (min 1 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox6' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox6"   class="switch" checked/>	5. Lifespan Development (min 1 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox7' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox7"   class="switch"/>	6. Public Health (min .5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox7' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox7"   class="switch" checked/>	6. Public Health (min .5 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox8' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox8"   class="switch"/>	7. Pharmacology (min .5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox8' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox8"   class="switch" checked/>	7. Pharmacology (min .5 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox9' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox9"   class="switch"/>	8. Airway / Respiratory / Ventilation (min 2.5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox9' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox9"   class="switch" checked/>	8. Airway / Respiratory / Ventilation (min 2.5 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox10' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox10"   class="switch"/>	9. Assessment (min 2.5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox10' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox10"   class="switch" checked/>	9. Assessment (min 2.5 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox11' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox11"   class="switch"/>	10. Medicine (min 8 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox11' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox11"   class="switch" checked/>	10. Medicine (min 8 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox12' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox12"   class="switch"/>	11. Shock and Resuscitation (min 4 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox12' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox12"   class="switch" checked/>	11. Shock and Resuscitation (min 4 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox13' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox13"   class="switch"/>	12. Trauma (min 7.5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox13' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox13"   class="switch" checked/>	12. Trauma (min 7.5 hours required)
 </c:if>
  <c:if test="${chkbox.boxName == 'chbox14' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox14"   class="switch"/>	13. Special Patient populations (min 6.5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox14' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox14"   class="switch" checked/>	13. Special Patient populations (min 6.5 hours required)
 </c:if>
  <c:if test="${chkbox.boxName == 'chbox15' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox15"   class="switch"/>	14. EMS Operations (min 3 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox15' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox15"   class="switch" checked/>	14. EMS Operations (min 3 hours required)
 </c:if>
 </c:forEach>

 </c:if>
 <%--- for first responder --%>
 <%--- for basic --%>
 <c:if test="${COURSE.crssCourseId==150015}">
 <c:forEach var="chkbox" items="${COURSE.checkBoxes}" varStatus="i" >
<c:if test="${chkbox.boxName == 'chbox1' && chkbox.boxValue=='off'}"> 
<b> </br><input type="checkbox" name="chbox1"   class="switch"/>	A list of resource materials, references, textbooks, workbooks, etc. that will be used by the students during the course.
Please print, enter actual hours and scan and upload the check list for completing course approval.
<a   href="https://forms.in.gov/Download.aspx?id=9387" target="_blank">
             [Basic EMT Checklist]</a></b></br></c:if>
<c:if test="${chkbox.boxName == 'chbox1' && chkbox.boxValue== 'on'}"> 
<b> </br><input type="checkbox" name="chbox1"   class="switch" checked />	A list of resource materials, references, textbooks, workbooks, etc. that will be used by the students during the course.
Please print, enter actual hours and scan and upload the check list for completing course approval.
<a   href="https://forms.in.gov/Download.aspx?id=9387" target="_blank">
             [Basic EMT Checklist]</a></b></br></c:if>
<c:if test="${chkbox.boxName == 'chbox2' && chkbox.boxValue=='off'}"> 
</br><u>A course syllabus, which must include the:</u>
</br><input type="checkbox" name="chbox2"   class="switch"/>	1.Preparatory (min 11 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox2' && chkbox.boxValue=='on'}"> 
</br><u>A course syllabus, which must include the:</u>
</br><input type="checkbox" name="chbox2"   class="switch" checked/>	1.Preparatory (min 11 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox3' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox3"   class="switch"/>	2. Anatomy and Physiology (min 5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox3' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox3"   class="switch" checked/>	2. Anatomy and Physiology (min 5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox4' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox4"   class="switch"/>	3. Medical Terminology (min 1 hour required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox4' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox4"   class="switch" checked/>	3. Medical Terminology (min 1 hour required)
 </c:if>

<c:if test="${chkbox.boxName == 'chbox5' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox5"   class="switch"/>	4. Pathophysiology (min 5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox5' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox5"   class="switch" checked/>	4. Pathophysiology (min 5 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox6' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox6"   class="switch"/>	5. Lifespan Development (min 1.5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox6' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox6"   class="switch" checked/>	5. Lifespan Development (min 1.5 hours required)
 </c:if>
  <c:if test="${chkbox.boxName == 'chbox7' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox7"   class="switch"/>	6. Public Health (min 0.5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox7' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox7"   class="switch" checked/>	6. Public Health (min 0.5 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox8' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox8"   class="switch"/>	7. Pharmacology (min 3 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox8' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox8"   class="switch" checked/>	7. Pharmacology (min 3 hours required) 
 </c:if>
<c:if test="${chkbox.boxName == 'chbox9' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox9"   class="switch"/>	8. Airway / Respiratory / Ventilation (min 5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox9' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox9"   class="switch" checked/>	8. Airway / Respiratory / Ventilation (min 5 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox10' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox10"   class="switch"/>	9. Assessment (min 5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox10' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox10"   class="switch" checked/>	9. Assessment (min 5 hours required)
 </c:if>
  <c:if test="${chkbox.boxName == 'chbox11' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox11"   class="switch"/>	10. Medicine (min 33 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox11' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox11"   class="switch" checked/>	10. Medicine (min 33 hours required)
 </c:if>
   <c:if test="${chkbox.boxName == 'chbox12' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox12"   class="switch"/>	11. Shock and Resuscitation (min 4 hours required) 
 </c:if>
<c:if test="${chkbox.boxName == 'chbox12' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox12"   class="switch" checked/>	11. Shock and Resuscitation (min 4 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox13' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox13"   class="switch"/>	12. Trauma (min 25 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox13' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox13"   class="switch" checked/>	12. Trauma (min 25 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox14' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox14"   class="switch"/>	13. Special Patient populations (min 19 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox14' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox14"   class="switch" checked/>	13. Special Patient populations (min 19 hours required)
 </c:if> 
 <c:if test="${chkbox.boxName == 'chbox15' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox15"   class="switch"/>	14. EMS Operations (min 20 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox15' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox15"   class="switch" checked/>	14. EMS Operations (min 20 hours required)
 </c:if> 
 </c:forEach>

 </c:if>
 
<%--- for basic --%>
<%--- for advanced --%>
 <c:if test="${COURSE.crssCourseId==150019}">
 <c:forEach var="chkbox" items="${COURSE.checkBoxes}" varStatus="i" >
<c:if test="${chkbox.boxName == 'chbox1' && chkbox.boxValue=='off'}"> 
<b> </br><input type="checkbox" name="chbox1"   class="switch"/>	A list of resource materials, references, textbooks, workbooks, etc. that will be used by the students during the course.
Please print, enter actual hours and scan and upload the check list for completing course approval.
<a   href="https://forms.in.gov/Download.aspx?id=10129" target="_blank">
             [Advanced EMT Checklist]</a></b></br></c:if>
<c:if test="${chkbox.boxName == 'chbox1' && chkbox.boxValue== 'on'}"> 
<b> </br><input type="checkbox" name="chbox1"   class="switch" checked />	A list of resource materials, references, textbooks, workbooks, etc. that will be used by the students during the course.
Please print, enter actual hours and scan and upload the check list for completing course approval.
<a   href="https://forms.in.gov/Download.aspx?id=10129" target="_blank">
             [Advanced EMT Checklist]</a></b></br></c:if>
<c:if test="${chkbox.boxName == 'chbox2' && chkbox.boxValue=='off'}"> 
</br><u>A course syllabus, which must include the:</u>
</br><input type="checkbox" name="chbox2"   class="switch"/>	1.Preparatory (min 10 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox2' && chkbox.boxValue=='on'}"> 
</br><u>A course syllabus, which must include the:</u>
</br><input type="checkbox" name="chbox2"   class="switch" checked/>	1.Preparatory (min 10 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox3' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox3"   class="switch"/>	2. Anatomy and Physiology (min 8 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox3' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox3"   class="switch" checked/>	2. Anatomy and Physiology (min 8 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox4' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox4"   class="switch"/>	3. Medical Terminology (min 2 hour required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox4' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox4"   class="switch" checked/>	3. Medical Terminology (min 2 hour required)
 </c:if>

<c:if test="${chkbox.boxName == 'chbox5' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox5"   class="switch"/>	4. Pathophysiology (min 8 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox5' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox5"   class="switch" checked/>	4. Pathophysiology (min 8 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox6' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox6"   class="switch"/>	5. Lifespan Development (min 0.5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox6' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox6"   class="switch" checked/>	5. Lifespan Development (min 0.5 hours required)
 </c:if>
  <c:if test="${chkbox.boxName == 'chbox7' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox7"   class="switch"/>	6. Public Health (min 0.5 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox7' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox7"   class="switch" checked/>	6. Public Health (min 0.5 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox8' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox8"   class="switch"/>	7. Pharmacology (min 30 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox8' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox8"   class="switch" checked/>	7. Pharmacology (min 30 hours required) 
 </c:if>
<c:if test="${chkbox.boxName == 'chbox9' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox9"   class="switch"/>	8. Airway / Respiratory / Ventilation (min 4 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox9' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox9"   class="switch" checked/>	8. Airway / Respiratory / Ventilation (min 4 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox10' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox10"   class="switch"/>	9. Assessment (min 6 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox10' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox10"   class="switch" checked/>	9. Assessment (min 6 hours required)
 </c:if>
  <c:if test="${chkbox.boxName == 'chbox11' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox11"   class="switch"/>	10. Medicine (min 30 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox11' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox11"   class="switch" checked/>	10. Medicine (min 30 hours required)
 </c:if>
   <c:if test="${chkbox.boxName == 'chbox12' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox12"   class="switch"/>	11. Shock and Resuscitation (min 4 hours required) 
 </c:if>
<c:if test="${chkbox.boxName == 'chbox12' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox12"   class="switch" checked/>	11. Shock and Resuscitation (min 4 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox13' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox13"   class="switch"/>	12. Trauma (min 24 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox13' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox13"   class="switch" checked/>	12. Trauma (min 24 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox14' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox14"   class="switch"/>	13. Special Patient populations (min 21 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox14' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox14"   class="switch" checked/>	13. Special Patient populations (min 21 hours required)
 </c:if> 
 <c:if test="${chkbox.boxName == 'chbox15' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox15"   class="switch"/>	14. EMS Operations (min 12 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox15' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox15"   class="switch" checked/>	14. EMS Operations (min 12 hours required)
 </c:if>  
 </c:forEach>

 </c:if>
<%--- for advanced --%>
<%--- for paramedic --%>
<c:if test="${COURSE.crssCourseId==150013}">
 <c:forEach var="chkbox" items="${COURSE.checkBoxes}" varStatus="i" >
<c:if test="${chkbox.boxName == 'chbox1' && chkbox.boxValue=='off'}"> 
<b> </br><input type="checkbox" name="chbox1"   class="switch"/>	A list of resource materials, references, textbooks, workbooks, etc. that will be used by the students during the course.
Please print, enter actual hours and scan and upload the check list for completing course approval.
<a   href="https://forms.in.gov/Download.aspx?id=10131" target="_blank">
             [Paramedic Checklist]</a></b></br></c:if>
<c:if test="${chkbox.boxName == 'chbox1' && chkbox.boxValue== 'on'}"> 
<b> </br><input type="checkbox" name="chbox1"   class="switch" checked />	A list of resource materials, references, textbooks, workbooks, etc. that will be used by the students during the course.
Please print, enter actual hours and scan and upload the check list for completing course approval.
<a   href="https://forms.in.gov/Download.aspx?id=10131" target="_blank">
             [Paramedic Checklist]</a></b></br></c:if>
<c:if test="${chkbox.boxName == 'chbox2' && chkbox.boxValue=='off'}"> 
</br><u>A course syllabus, which must include the:</u>
</br><input type="checkbox" name="chbox2"   class="switch"/>	1.Preparatory (min 8 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox2' && chkbox.boxValue=='on'}"> 
</br><u>A course syllabus, which must include the:</u>
</br><input type="checkbox" name="chbox2"   class="switch" checked/>	1.Preparatory (min 8 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox3' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox3"   class="switch"/>	2. Anatomy and Physiology (min 30 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox3' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox3"   class="switch" checked/>	2. Anatomy and Physiology (min 30 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox4' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox4"   class="switch"/>	3. Medical Terminology (min 4 hour required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox4' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox4"   class="switch" checked/>	3. Medical Terminology (min 4 hour required)
 </c:if>

<c:if test="${chkbox.boxName == 'chbox5' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox5"   class="switch"/>	4. Pathophysiology (min 12 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox5' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox5"   class="switch" checked/>	4. Pathophysiology (min 12 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox6' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox6"   class="switch"/>	5. Lifespan Development (min 4 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox6' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox6"   class="switch" checked/>	5. Lifespan Development (min 4 hours required)
 </c:if>
  <c:if test="${chkbox.boxName == 'chbox7' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox7"   class="switch"/>	6. Public Health (min 2 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox7' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox7"   class="switch" checked/>	6. Public Health (min 2 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox8' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox8"   class="switch"/>	7. Pharmacology (min 40 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox8' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox8"   class="switch" checked/>	7. Pharmacology (min 40 hours required) 
 </c:if>
<c:if test="${chkbox.boxName == 'chbox9' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox9"   class="switch"/>	8. Airway / Respiratory / Ventilation (min 12 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox9' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox9"   class="switch" checked/>	8. Airway / Respiratory / Ventilation (min 12 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox10' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox10"   class="switch"/>	9. Assessment (min 14 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox10' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox10"   class="switch" checked/>	9. Assessment (min 14 hours required)
 </c:if>
  <c:if test="${chkbox.boxName == 'chbox11' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox11"   class="switch"/>	10. Medicine (min 134 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox11' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox11"   class="switch" checked/>	10. Medicine (min 134 hours required)
 </c:if>
   <c:if test="${chkbox.boxName == 'chbox12' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox12"   class="switch"/>	11. Shock and Resuscitation (min 18 hours required) 
 </c:if>
<c:if test="${chkbox.boxName == 'chbox12' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox12"   class="switch" checked/>	11. Shock and Resuscitation (min 18 hours required)
 </c:if>
 <c:if test="${chkbox.boxName == 'chbox13' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox13"   class="switch"/>	12. Trauma (min 80 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox13' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox13"   class="switch" checked/>	12. Trauma (min 80 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox14' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox14"   class="switch"/>	13. Special Patient populations (min 68 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox14' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox14"   class="switch" checked/>	13. Special Patient populations (min 68 hours required)
 </c:if> 
 <c:if test="${chkbox.boxName == 'chbox15' && chkbox.boxValue=='off'}"> 
</br><input type="checkbox" name="chbox15"   class="switch"/>	14. EMS Operations (min 26 hours required)
 </c:if>
<c:if test="${chkbox.boxName == 'chbox15' && chkbox.boxValue=='on'}"> 
</br><input type="checkbox" name="chbox15"   class="switch" checked/>	14. EMS Operations (min 26 hours required)
 </c:if>  

 
 </c:forEach>

 </c:if>
<%--- for paramedic --%>
</body>
<c:if test="${sessionScope.AGREE_TO_FLAG == 'NO'}">
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
    </br>  <STRONG><FONT color="#ff3333">Must read and agree to all conditions by checking all the check boxes</FONT></STRONG></br>
 <p>
   
          <html:submit value="next" styleClass="button" />
   </p>
   </c:if>
</c:if>

 </html:form>
 

</div>
</div>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />

