package main.control;
import main.data.*;
import java.util.*;

import javax.servlet.*;
import hs.util.*;
import hs.to.*;
import hs.data.*;

public class DfbsContextListener implements javax.servlet.ServletContextListener
{
    //
    // Constructors'
    
    
    //
    public DfbsContextListener() { }

    //
    // Methods
 
 
    
 
  
   public void contextInitialized(ServletContextEvent sce) 
    {
      try
      {
        
        HsDataSource ds2 = new HsDataSource("jdbc/dhst");
        HsDataSource dsNew = new HsDataSource("jdbc/dhst");
         DfbsDataMap map2 = new DfbsDataMap(ds2);
         sce.getServletContext().setAttribute(HsConstant.DFBS_DATA_MAP_KEY,map2);
        DfbsDataMap2 map3 = new DfbsDataMap2(dsNew);
         sce.getServletContext().setAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW,map3);
        
      } 
      catch (Exception e) 
      {
        System.out.println("Context Listener Error: " + e.getMessage());
      }
    } 
    public void contextDestroyed(ServletContextEvent sce) 
    {
      
    }
}