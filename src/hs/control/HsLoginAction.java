package hs.control;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public abstract class HsLoginAction extends ControlAction {
    public abstract boolean validateUser(HttpServletRequest request);

    public ActionForward executeControl(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                                                             ServletException {
        try {
            ServletContext context =
                this.getServlet().getServletConfig().getServletContext();
            
            HttpSession session = request.getSession();
            if (validateUser(request)) {
                String uri = (String)session.getAttribute("HS_TARGET_URI");
                return (uri == null) ? mapping.findForward("index") :
                       new ActionForward(uri, true);
            } else {
                request.setAttribute("HS_ERROR", "");
                return mapping.findForward("loginForm");
            }
        } catch (Exception e) {
            saveError(request, e);
            return mapping.findForward("error");
        }

    }

}
