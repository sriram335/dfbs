package hs.util;

import java.util.HashMap;
import java.util.Map;


public class HsTableRow {
    private int rowId;
    private String key;
    private Map map;

    public HsTableRow() {
        map = new HashMap();
        key = "";
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int r) {
        rowId = r;
    }

    public void put(String columnKey, Object obj) {
        map.put(columnKey, obj);
    }

    public Object get(String columnKey) {
        return map.get(columnKey);
    }

    public int getSize() {
        return map.size();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map row) {
        this.map = map;
    }


}
