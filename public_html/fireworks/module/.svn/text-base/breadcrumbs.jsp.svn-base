 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control />


<div id="breadCrumbs">

<c:if test="${level >= 2}">
    <html:link page="/main.do?method=refresh">Authorized Fireworks Sellers</html:link> >
</c:if>

<c:if test="${level == 3 && sublevel == 'permitEdit'}">
    <html:link page="/main.do?method=view" paramId="ownerId" paramName="DFBS_OWNER" paramProperty="ownerId"><c:out value="${DFBS_OWNER.ownerName}" /></html:link> >
</c:if>

<c:if test="${level == 3 && sublevel == 'confirmCheckout'}">
    <html:link page="/main.do?method=view" paramId="ownerId" paramName="DFBS_OWNER" paramProperty="ownerId"><c:out value="${DFBS_OWNER.ownerName}" /></html:link> >
</c:if>

<c:out value="${ title }" />

</div>
