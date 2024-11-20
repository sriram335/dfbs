<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
     
   
  
  <div class="styledBox">
    <table cellspacing="0" class="noBorder" >
      <tbody class="rowHeader">
        <tr>
          <th scope="row" colspan="2" style="font-size:large;font-weight:bold;">
            Carrier:<c:out value="${sessionScope.HAZMAT_CARRIER.carrierName}"/>
          </th>
        </tr>
         <tr>
          <th scope="row" ></th>
          <td>
            Contact:<c:out value="${sessionScope.HAZMAT_CARRIER.carrierContact}"/></br>
            Title:<c:out value="${sessionScope.HAZMAT_CARRIER.carrierTitle}"/>
          </td>
        </tr>
        
         
      </tbody>
    </table>
    </div>
  
 
