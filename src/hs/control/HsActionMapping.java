package hs.control;

import org.apache.struts.action.ActionMapping;


public class HsActionMapping extends ActionMapping {
    private String actionId;

    public HsActionMapping() {
        super();
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String newActionId) {
        actionId = newActionId;
    }


}
