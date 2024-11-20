package hs.control.taglib;

import hs.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ControlTag implements Tag
{
  private PageContext pageContext;
  private Tag parentTag;

  public void setPageContext(PageContext pageContext)
  {
    this.pageContext = pageContext;
  }
  public void setParent(Tag parentTag)
  {
    this.parentTag = parentTag;
    
  }
  public Tag getParent()
  {
    return this.parentTag;
  }
  public int doStartTag() throws JspException 
  {
    return SKIP_BODY;
  }
  public int doEndTag() throws JspException
  {
    ServletRequest request = pageContext.getRequest();
    String key = (String) request.getAttribute(HsConstant.CONTROL_KEY);
    if (key == null) {
      return SKIP_PAGE;
    } 
    else 
    {
      return EVAL_PAGE;  
    }
  }
  public void release()
  {
    
  }
}