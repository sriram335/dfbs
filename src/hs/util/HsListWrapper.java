package hs.util;

import java.io.Serializable;

import java.util.List;


public class HsListWrapper implements Serializable {
    private List list;


    public HsListWrapper(List list) {
        this.list = list;
    }

    public List getList() {
        return list;
    }

    public int getSize() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }
}
