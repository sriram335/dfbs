<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<b><u>Search by ID</u></b>
<html:form action="/search" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="searchById"/> 
 
  <p align="left">
                 Facility ID:<html:text property="idNumber" size="30" maxlength="30"/> </br></br>
          Search For <html:select property="searchFor" styleId="SELECT_INSP" >
          <html:option value="Fire">Fire</html:option>
          <html:option value="Elevators">Elevators</html:option>
          <html:option value="BPV">BPV</html:option>
          <html:option value="CDR">CDR</html:option>
        </html:select>
            <input type="submit" value="Search" class="button"/></br>
        </br></br></br>
</p>
</html:form>

<div class="clearer">&nbsp;</div>
