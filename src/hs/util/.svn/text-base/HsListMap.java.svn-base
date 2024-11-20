package hs.util;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HsListMap implements Serializable {
    private List list;
    private Map map;


    public HsListMap() {
        list = new ArrayList();
        map = new HashMap();
    }

    public int getSize() {
        return (list == null) ? 0 : list.size();
    }

    public List getList() {
        return list;
    }

  
    public Map getMap() {
       return map;
    }


    public void add(String key, Object obj) {
        this.list.add(obj);
        this.map.put(key, obj);
    }

    public Object get(String key) {
        return this.map.get(key);
    }
}
