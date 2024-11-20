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
public class EmsLevelAction extends ControlAction
{
   public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        EmsLevelForm levelForm = (EmsLevelForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsLevelDAO lDAO = (EmsLevelDAO) dmapNew.getHsDAO(DfbsDataMap2.LEVEL);
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
      if (method.equals("levelList"))
        {   
           int providerId = Integer.parseInt(request.getParameter("providerId"));
           setList(request,lDAO,levelForm,providerId);
           return mapping.findForward("levelList");
        } 
       if (method.equals("levelDetail"))
        {  
           int levelId = Integer.parseInt(request.getParameter("levelId"));
           setView(request,lDAO,levelForm,levelId);
           return mapping.findForward("levelDetail");
        } 
        if (method.equals("addNewLevel"))
        {  
           return mapping.findForward("levelDetail");
        }         
                
        else if(method.equals("saveLevel")) 
         
       { 
          EmsLevel level =  levelForm.getEmsLevel();
          EmsLevelForm errorForm = new EmsLevelForm();
           if (validateLevel(level,errorForm))
          { if (level.getLevelId()==0)
             {
             
             EmsProvider provider = (EmsProvider)session.getAttribute("PROVIDER");
             int providerId = provider.getProviderId();
             
             lDAO.insertLevel(level,providerId);
              setList(request,lDAO,levelForm,providerId);
             return mapping.findForward("levelList");
             }
             else
             {
             lDAO.updateLevel(level);
             int providerId = Integer.parseInt(request.getParameter("providerId"));
             setList(request,lDAO,levelForm,providerId);
             return mapping.findForward("levelList");
             }
          }
          else
          { request.setAttribute("LEVEL_ERROR",errorForm);
           return mapping.findForward("levelDetail");
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
  
  private static void setList(HttpServletRequest request,EmsLevelDAO elDAO,EmsLevelForm levelForm,int providerId) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     
        list = elDAO.selectLevelList(EmsSQL.SELECT_LEVEL_LIST,providerId);
        request.setAttribute("LEVEL_LIST",new HsListWrapper(list));
     
  }
  
  protected static boolean validateLevel(EmsLevel level ,EmsLevelForm errorForm) throws Exception
  {
    boolean noError = true;
    
    if(level.getLevelName() == null || level.getLevelName().trim().equals("")  )
    {
    errorForm.setLevelName("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setLevelName("");
       
    }
   return noError;
  }
    protected static EmsLevel setView(HttpServletRequest request,
    EmsLevelDAO lDAO,
      EmsLevelForm levelForm, int id) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    EmsLevel level = lDAO.selectLevel(id);
    
    session.setAttribute("LEVEL",level);
    levelForm.setProperties(level);
    
    return level; 
   
  }
}