package hs.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ProtectedForwardAction extends ProtectedAction {

    public ActionForward executeProtectedControl(ActionMapping mapping,
                                                 ActionForm form,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) throws IOException,
                                                                                      ServletException {

        return new ActionForward(mapping.getParameter());
    }
}
