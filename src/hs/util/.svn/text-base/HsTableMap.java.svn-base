package hs.util;

import java.io.Serializable;

import java.util.List;


/*
 * This is a ListMap of HsTableRow
 */

public class HsTableMap implements Serializable {
    private HsListMap listMap;


    public HsTableMap() {
        listMap = new HsListMap();
    }


    public int getRowsSize() {
        return listMap.getSize();
    }


    public HsListMap getListMap() {
        return listMap;
    }

    public List getRows() {
        return listMap.getList();
    }

    public void setListMap(HsListMap table) {
        this.listMap = table;
    }

    public void put(int rowId, String rowKey, String columnKey, Object obj) {
        HsTableRow row = (HsTableRow)listMap.get(rowKey);
        if (row == null) {
            row = new HsTableRow();
            row.setRowId(rowId);
            row.setKey(rowKey);
            listMap.add(rowKey, row);
        }
        row.put(columnKey, obj);

    }

    public Object get(String rowKey, String columnKey) {
        HsTableRow row = (HsTableRow)listMap.get(rowKey);
        if (row == null) {
            return null;
        }
        return row.get(columnKey);
    }

    public HsTableRow getRow(String rowKey) {
        HsTableRow row = (HsTableRow)listMap.get(rowKey);
        if (row == null) {
            return null;
        }
        return row;
    }

    public Object get(String columnKey) {
        Object obj = null;
        List list = listMap.getList();
        if (list != null && list.size() > 0) {
            HsTableRow row = (HsTableRow)list.get(0);
            if (row == null) {
                obj = null;
            }
            obj = row.get(columnKey);

        }
        return obj;

    }


}
