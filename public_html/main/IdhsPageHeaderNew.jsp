<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>



	<HEAD>
		<link rel="stylesheet" href="/dfbs/css/styles.css" type="text/css">
	</HEAD>
<style type="text/css"><!--.RootClass { color:#FFFFFF; padding:2px 14px 4px ; background-image:url(/dfbs/images/btn_blue.gif); position: relative;  left: -5px;  top: 9px;  background-repeat: no-repeat;  font-family: Arial, Helvetica, sans-serif;  font-size: 10px;  font-weight: normal; }.RootHover { padding:2px 14px 4px ; cursor:pointer; cursor:Hand; background-image:url(/dfbs/images/btn_blue_ro.gif); position: relative;  left: -5px;  top: 9px;  background-repeat: no-repeat;  font-family: Arial, Helvetica, sans-serif;  font-size: 10px;  font-weight: normal;  color: #FFFFFF; }.SubClass { background:#D4EBFB; border-color:#336699; color:#000000; border-style:Solid; border-width:1px; padding:0 0 0 5px ; position: relative;  left: -100px;  top: 29px;  background-repeat: no-repeat;  font-family: Arial, Helvetica, sans-serif;  font-size: 10px; }.SubHover { background:#E6E6E6; border-color:#ADD8E6; border-style:Solid; border-width:1px; padding:0 0 0 5px ; cursor:pointer; cursor:Hand; position: relative;  left: -100px;  top: 29px;  background-repeat: no-repeat;  font-family: Arial, Helvetica, sans-serif;  font-size: 10px;  color: #000000; }.Navigation1UltraWebMenu1Hover { background:DarkBlue; color:#FFFFFF; }.Navigation1UltraWebMenu1Disabled { cursor:Wait; }--></style>

<table id="Table1" cellspacing="0" cellpadding="0" border="0" style="border-width:0px;width:100%;border-collapse:collapse;">
	<tr align="Left" valign="Top">
		<td background="/dfbs/images/back_top-stripe.gif" style="height:85px;"><table id="Table2" cellspacing="0" cellpadding="0" border="0" style="border-width:0px;height:85px;width:750px;border-collapse:collapse;">
				<tr align="Left" valign="Top">
				<td colspan="3" style="height:63px;"><img id="IndianaMain" src="/dfbs/images/hdr_indaina_main.gif" alt="" border="0" style="height:63px;width:525px;" /></td>
			</tr>
			<c:if test="${sessionScope.DFBS_SECURITY != null && sessionScope.DFBS_SECURITY.userId != null && sessionScope.DFBS_SECURITY.userId != 'PILLINGWORTH' && sessionScope.DFBS_SECURITY.userId != 'PROBISON'}"> 
			<tr>
				<td valign="Top" style="height:22px;width:180px;"><img id="HeaderPiece2" src="/dfbs/images/hdr-piece2.gif" alt="" border="0" style="height:22px;width:155px;" /></td><td valign="Top" style="height:22px;width:236px;"><img id="TitleDor" src="/dfbs/images/title_dhs.gif" alt="" border="0" style="height:22px;width:236px;" /></td><td class="txt-topnav" align="Center" valign="Middle" style="height:22px;width:379px;"><table id="Table3" class="txt-topnav" cellspacing="0" cellpadding="0" border="0" style="border-width:0px;width:250px;border-collapse:collapse;">
					<td>&nbsp;</td
						><td style="width:5px;"></td>
						<td style="width:5px;"></td>
						<td style="width:5px;"><img id="ToolButtonEndLeft1" src="/dfbs/images/toolbtn_end_l.gif" alt="" border="0" style="height:15px;width:5px;" /></td>
						<td nowrap="nowrap" align="Center" valign="Middle" background="/dfbs/images/toolbtn_background.gif" style="width:80px;"><a id="ContactUsButton" href="/dfbs/main/main.do?method=backToMain" style="color:White;"><span id="ContactUsText"><b>DFBS&nbsp;Home</b></span></a></td>
						<td style="width:5px;"><img id="ToolButtonEndRight1" src="/dfbs/images/toolbtn_end_r.gif" alt="" border="0" style="height:15px;width:5px;" /></td>
						<td style="width:5px;"><img id="ToolButtonEndLeft1" src="/dfbs/images/toolbtn_end_l.gif" alt="" border="0" style="height:15px;width:5px;" /></td>
						<td align="Center" valign="Middle" background="/dfbs/images/toolbtn_background.gif" style="width:50px;"><a id="FaqButton" href="/dfbs/main/main.do?method=logOut" style="color:White;"><span id="FAQSText"><b>Log&nbsp;Out</b></span></a></td>
						<td style="width:5px;"><img id="ToolButtonEndRight1" src="/dfbs/images/toolbtn_end_r.gif" alt="" border="0" style="height:15px;width:5px;" /></td>
						<td style="width:5px;"><img id="ToolButtonEndLeft2" src="/dfbs/images/toolbtn_end_l.gif" alt="" border="0" style="height:15px;width:5px;" /></td>
						<td align="Center" valign="Middle" background="/dfbs/images/toolbtn_background.gif" style="width:50px;"><a id="FaqButton" href="/dfbs/main/main.do?method=changePassword" style="color:White;"><span id="FAQSText"><b>Change&nbsp;Password</b></span></a></td>
						<td style="width:5px;"><img id="ToolButtonEndRight2" src="/dfbs/images/toolbtn_end_r.gif" alt="" border="0" style="height:15px;width:5px;" /></td>
					</tr>
			</c:if>		
				</table></td>
			</tr>
		</table></td>
	</tr>    
</table>
	<!--Header file end------------------------------------------------------------------------------------------------->

 <div id="pageBox">

<%
main.data.DfbsDataMap dmap = (main.data.DfbsDataMap)
application.getAttribute(hs.util.HsConstant.DFBS_DATA_MAP_KEY);
main.data.FireReportsDAO fDAO = (main.data.FireReportsDAO) dmap.getHsDAO(dmap.FIRE_REPORTS);
hs.data.HsDatabaseConnection dc = fDAO.getHsDatabaseConnection();
if(dc.getDatabase() != null && dc.getDatabase().equals("semt")) {
%>

<p class="error" style="font-size:x-large;font-weight:bold;padding: 20px;">
Running on TEST Database...
</p>

<% } %> 
  
