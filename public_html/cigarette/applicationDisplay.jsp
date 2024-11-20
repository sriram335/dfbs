<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
 </br>    
  <div class="styledBox">
    <table cellspacing="0" class="noBorder" >
      <tbody class="rowHeader">
         <tr>
          <th scope="row" ></th>
          <td>
            Contact:<c:out value="${sessionScope.CIGARETTE_APPLICATION.contact}"/></br>
            Title:<c:out value="${sessionScope.CIGARETTE_APPLICATION.title}"/>
          </td>
        </tr>
      </tbody>
    </table>
    </div>
  
 
