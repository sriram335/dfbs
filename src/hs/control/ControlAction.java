package hs.control;

import hs.util.HsConstant;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * <p>
 * Control Action is the base class for all HS Actions that sets control in the
 * request to protect jsp files.
 * </p>
 * @author Dennis Leonardo
 * @version 1.0
 */
public abstract class ControlAction extends Action {
    /**
     * Implementation of execute method inherited from Action. Sets
     * HsConstant.CONTROL_KEY in request attribute.
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                                                      ServletException {
        request.setAttribute(HsConstant.CONTROL_KEY, "OK");
        return executeControl(mapping, form, request, response);
    }

    /**
     *
     *
     */
    public abstract ActionForward executeControl(ActionMapping mapping,
                                                 ActionForm form,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) throws IOException,
                                                                                      ServletException;


    /**
     *
     *
     */
    public static void saveError(HttpServletRequest request, Exception e) {
        Map map = (Map)request.getAttribute("HS_ERROR_MAP");
        if (map == null) {
            map = new HashMap();
        }
        map.put("EXCEPTION_MESSAGE", e.getMessage());
        request.setAttribute("HS_ERROR_MAP", map);
    }

    public static void saveError(HttpServletRequest request, String id) {
        Map map = (Map)request.getAttribute("HS_ERROR_MAP");
        if (map == null) {
            map = new HashMap();
        }
        map.put("ERROR_ID", id);
        request.setAttribute("HS_ERROR_MAP", map);
    }

    public static void saveError(HttpServletRequest request, String id,
                                 String message) {
        Map map = (Map)request.getAttribute("HS_ERROR_MAP");
        if (map == null) {
            map = new HashMap();
        }
        map.put("ERROR_ID", id);
        map.put("ERROR_MESSAGE", message);
        request.setAttribute("HS_ERROR_MAP", map);
    }

    public static boolean validate(HttpServletRequest request,
                                   HttpServletResponse response,
                                   String validateUrl) {
        Map errorMap = new HashMap();
        errorMap.put("NO_ERROR", "TRUE");
        boolean noError = true;
        try {
            request.setAttribute("HS_ERROR_MAP", errorMap);
            RequestDispatcher rp = request.getRequestDispatcher(validateUrl);
            rp.include(request, response);
            if (!errorMap.get("NO_ERROR").equals("TRUE")) {
                noError = false;
            }
        } catch (Exception e) {
            errorMap.put("NO_ERROR", "FALSE");
            noError = false;
        }
        request.setAttribute("HS_ERROR_MAP", errorMap);
        return noError;

    }

}
