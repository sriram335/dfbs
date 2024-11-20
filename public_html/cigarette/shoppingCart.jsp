<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<p class ="error">
<b><u>NOTE:</u></b> Other states in USA can contact DfbsDatabase@dhs.in.gov to get a cigarette data extract in delimited data format. This can be used as a data import for their local databases.
</p>
   
<table cellspacing="0" style="width: 75%;" summary="sales">
  <tr>
    <th>Shopping Cart </th>
  </tr>
  <tbody>
  <tr>
  <td>
  <h2>Company: <c:out value="${sessionScope.CIGARETTE_COMPANY.companyName}"/></h2></br>
  Total number of brand families in this session:<c:out value="${sessionScope.CIGARETTE_COMPANY.totalBrands}"/></br>
  Fees for this session:<c:out value="${sessionScope.CIGARETTE_COMPANY.amount}"/>0
</td>
</tr>
 
  </tbody>
</table></br>


<c:if test="${sessionScope.DFBS_SECURITY == null}">   
<c:if test="${sessionScope.CIGARETTE_COMPANY.receiptId == 0 &&( sessionScope.CIGARETTE_COMPANY.amount >0 || sessionScope.CIGARETTE_APPLICATION.brandsMapCount >0)}">

 <html:form action="/checkout" method="post">
       <p class="messageBox">
       <b><FONT color="#ff0000">Click this button ONLY when you have entered all Brands and Styles.</FONT></b> 
      Please note that a transaction fee will be added during checkout for credit card payment.
      ( Total fee = Fee in the shopping cart +$1 portal fee will be added for one online payment. On top of this Total fee  a 2% credit card processing fee will be added (charged by the credit card company)  )
      If you do not wish to use credit card, complete the application, print and mail it along with the applicable check.
         <input type="submit" value="proceed to credit card payment" class="button"/></br>
      </p>
</html:form> 

 <html:form action="/checkout" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="noPayment"/>
       <p class="messageBox">
     <b><FONT color="#ff0000">Click this button ONLY when you have entered all Brands and Styles.</FONT></b>   
     If you are  making the payment using a check, make the check payable to Indiana Department of Homeland Security. The check must be drawn on a bank headquartered in the United States.
     <input type="submit" value="Complete the Application" class="button"/></br>
      ("View and Print Application" option will be enabled after you click this button)
     </p>
</html:form>

 </p>
</c:if>
</c:if>  

<c:if test="${sessionScope.CIGARETTE_COMPANY.receiptId != 0 }">
<a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=cigarette_application.rdf&p_application_id=<c:out value="${sessionScope.CIGARETTE_APPLICATION.appId}"/>"  ><b><h3>[View and Print Application]</h3></b></a></br>


  </c:if>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCigarette == 'SUPERVISOR'   }">     
 <html:form action="/checkout" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="check"/>
      <p>
        <input type="submit" value="Received Check" class="button"/>
      </p>
       Check Number:<html:text property="receiptId" size="30" maxlength="30"/>
      
</html:form>

</c:if> 
 
 <h2>Contact Us</h2>
          <p>
          You can contact us via email or by phone
          </p>
          <p>
         <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"  href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/>  </A>: <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/></strong>
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/></A> : <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/></strong>
          </p>         
         
        <!--  If you are experiencing difficulties or technical issues with this application email  <A title=mailto:DfbsDatabase@dhs.in.gov href="mailto:DfbsDatabase@dhs.in.gov">Dev Nimmagadda </A> or call 317-232-1418</br>-->
          <U><STRONG>Mailing Address:</STRONG></U> </br>OFFICE OF THE STATE FIRE MARSHAL(IDHS)</br>
          Attn: Reduced Ignition Propensity Cigarette Program </br> 302 W Washington St #E241</br> Indianapolis IN 46204</br>