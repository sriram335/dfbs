package hs.to;

import hs.util.HsListMap;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class HsFilter extends HsTypeValue implements Serializable {
    private String index;
    private String label;
    private String orderType;
    private HsListMap filterListMap;

    public HsFilter() {
        index = "1";
    }


    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = (index == null) ? "1" : index;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public HsListMap getFilterListMap() {
        return filterListMap;
    }

    public void setFilterListMap(HsListMap filterListMap) {
        this.filterListMap = filterListMap;
    }

    public String[] getParamValues() {
        String[] paramArray = null;
        if (filterListMap == null) {
            paramArray = new String[0];
        } else {
            List paramList = new ArrayList();


            Iterator i = filterListMap.getList().iterator();

            while (i.hasNext()) {
                HsTypeValue typeValue = (HsTypeValue)i.next();
                if (typeValue.isArray() && typeValue.getValueArray() != null) {
                    String[] array = typeValue.getValueArray();
                    for (int j = 0; j < array.length; j++) {
                        paramList.add(array[j]);
                    }

                } else {
                    paramList.add(typeValue.getValue());
                }
            }
            paramArray = new String[paramList.size()];
            int ii = 0;
            Iterator jj = paramList.iterator();
            while (jj.hasNext()) {
                paramArray[ii] = (String)jj.next();
                ii++;
            }


        }
        return paramArray;
    }


}
