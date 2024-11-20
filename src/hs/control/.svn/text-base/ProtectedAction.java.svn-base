package hs.control;

import hs.to.HsUser;

import hs.util.HsConstant;

import java.io.IOException;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public abstract class ProtectedAction extends ControlAction {
    public ActionForward executeControl(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                                                             ServletException {


        HttpSession session = request.getSession();
        HsUser user = (HsUser)session.getAttribute(HsConstant.USER_KEY);


        if (user == null) {
            StringBuffer uri = new StringBuffer(request.getServletPath());
            Map params = request.getParameterMap();
            if (uri.toString().equals("/")) {
                session.setAttribute("HS_TARGET_URI", null);
            } else {
                if (params != null && params.size() > 0) {
                    uri.append("?");
                    Set names = params.keySet();
                    Iterator i = names.iterator();
                    while (i.hasNext()) {
                        String name = (String)i.next();
                        String[] value = (String[])params.get(name);
                        uri.append(name).append("=").append(value[0]);
                        uri.append("&amp;");
                    }

                }
                session.setAttribute("HS_TARGET_URI", uri.toString());
            }
            return mapping.findForward("loginForm");
        } else {
            if (!user.getUserid().equals("SYSTEM")) {
                try {
                    HsActionMapping map = (HsActionMapping)mapping;
                    if (!user.hasToken(map.getActionId())) {
                        throw new Exception("HS_ERROR_NO_AUTHORIZATION");
                    }
                } catch (Exception e) {
                    request.setAttribute("HS_ERROR", e.getMessage());
                    return mapping.findForward("error");
                }
            }
        }

        session.setAttribute("HS_TARGET_URI", null);
        return executeProtectedControl(mapping, form, request, response);
    }


    public abstract ActionForward executeProtectedControl(ActionMapping mapping,
                                                          ActionForm form,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response) throws IOException,
                                                                                               ServletException;
}
