package ems.control.action;
import ems.control.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;
import otherUsers.to.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import hs.control.*;
import main.data.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;

public class EmsWaiverAction  extends ControlAction
{
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        ServletContext context =  this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        EmsWaiverForm waiverForm = (EmsWaiverForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsWaiverDAO wDAO = (EmsWaiverDAO) dmapNew.getHsDAO(DfbsDataMap2.WAIVER);
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
      if (method.equals("waiverList"))
        {   
           int levelId = Integer.parseInt(request.getParameter("levelId"));
           setList(request,wDAO,waiverForm,levelId);
           return mapping.findForward("waiverList");
        } 
        if (method.equals("waiverDetail"))
        {  
           int waiverId = Integer.parseInt(request.getParameter("waiverId"));
           setView(request,wDAO,waiverForm,waiverId);
           return mapping.findForward("waiverDetail");
        } 
        if (method.equals("addNewWaiver"))
        {  
           return mapping.findForward("waiverDetail");
        }         
        else if(method.equals("saveWaiver")) 
         
       { 
          EmsWaiver waiver =  waiverForm.getEmsWaiver();
          EmsWaiverForm errorForm = new EmsWaiverForm();
           if (validateWaiver(waiver,errorForm))
          { if (waiver.getWaiverId()==0)
             {
             EmsLevel level = (EmsLevel)session.getAttribute("LEVEL");
             int levelId = level.getLevelId();
             wDAO.insertWaiver(waiver,levelId);
              setList(request,wDAO,waiverForm,levelId);
             return mapping.findForward("waiverList");
             }
             else
             {
             wDAO.updateWaiver(waiver);
             int providerId = Integer.parseInt(request.getParameter("providerId"));
             setList(request,wDAO,waiverForm,providerId);
             return mapping.findForward("waiverList");
             }
          }
          else
          { request.setAttribute("WAIVER_ERROR",errorForm);
           return mapping.findForward("waiverDetail");
          }
        }
          
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
  
  private static void setList(HttpServletRequest request,EmsWaiverDAO ewDAO,EmsWaiverForm waiverForm,int levelId) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     
        list = ewDAO.selectWaiverList(EmsSQL.SELECT_WAIVER_LIST,levelId);
        request.setAttribute("WAIVER_LIST",new HsListWrapper(list));
     
  }
  
  protected static boolean validateWaiver(EmsWaiver waiver ,EmsWaiverForm errorForm) throws Exception
  {
    boolean noError = true;
    
    if(waiver.getWaiverCode() == null || waiver.getWaiverCode().trim().equals("")  )
    {
    errorForm.setWaiverCode("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setWaiverCode("");
       
    }
    
    return noError;
  }
   protected static EmsWaiver setView(HttpServletRequest request,
    EmsWaiverDAO rDAO,
      EmsWaiverForm waiverForm, int id) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    EmsWaiver waiver = rDAO.selectWaiver(id);
    
    session.setAttribute("WAIVER",waiver);
    
    waiverForm.setProperties(waiver);
     return waiver; 
   
  }
}