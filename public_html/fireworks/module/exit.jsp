 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control />


<p id="exit<c:out value='${param.id}' />">

<c:choose >

<c:when test="${level == 2 && sublevel=='form'}">
    <html:link page="/main.do?method=refresh">cancel</html:link> 
</c:when>


<c:when test="${level == 2}">
    <html:link page="/main.do?method=refresh">back</html:link> 
</c:when>

<c:when test="${level == 3 && sublevel == 'permitEdit'}">
    <html:link page="/main.do?method=view" paramId="ownerId" paramName="DFBS_OWNER" paramProperty="ownerId">cancel</html:link> 
</c:when>

<c:when test="${level == 3 && sublevel == 'confirmCheckout'}">
    <html:link page="/main.do?method=view" paramId="ownerId" paramName="DFBS_OWNER" paramProperty="ownerId">cancel</html:link> 
</c:when>


</c:choose >

</p>

