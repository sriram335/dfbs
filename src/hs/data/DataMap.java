package hs.data;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;


public class DataMap {

    private HsDataSource dataSource = null;
    private Map map = null;
    
        
    public DataMap(HsDataSource ds) {
        map = new HashMap();
        dataSource = ds;
    }

    public static void initialize(DataMap obj, HsDataSource dsParam) throws Exception {
        try {
               
        
        
        Class thisClass = obj.getClass();
        Field[] fields = thisClass.getFields();

        for (int i = 0; i < fields.length; i++) {
            HsDAO dao =
                (HsDAO)Class.forName((String)fields[i].get(null)).newInstance();
            dao.setPool(dsParam);
            obj.putHsDAO(dao);
        }
        
        } catch (Exception e) {
            
            new Exception("ERROR in initialize " + e.getMessage()
            );
            
        }
    }

    public HsDAO getHsDAO(String c) throws Exception {
        HsDAO d = (HsDAO)map.get(c);

        if (d == null) {
            throw new Exception("Data Access Object Not Found!");
        }
        return d;
    }

    public void putHsDAO(HsDAO dao) {
        map.put(dao.getClass().getName(), dao);
    }
    
    public HsDataSource getDataSource() {
        
        return dataSource;
        
    }
    
    
}
