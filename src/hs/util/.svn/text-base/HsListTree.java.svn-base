package hs.util;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HsListTree implements Serializable {
    private List list;
    private Map map;


    public HsListTree() {
        list = new ArrayList();
        map = new HashMap();
    }

    public int getTreeSize() {
        return (map == null) ? 0 : map.size();
    }

    public int getListSize() {
        return (list == null) ? 0 : list.size();
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void add(HsListTreeNode node) throws Exception {
        if (node.getNodeLevel() == 0)
            throw new Exception("Invalid tree node!");

        if (node.getNodeLevel() == 1) {
            list.add(node);
            map.put(node.getNodeKey(), node);
        } else {
            HsListTreeNode existingNode =
                (HsListTreeNode)map.get(node.getParentNodeKey());
            if (existingNode == null) {
                throw new Exception("Unknown parent tree node!");
            }
            existingNode.addNode(node);
            map.put(node.getNodeKey(), node);
        }
    }

    public HsListTreeNode get(String key) {
        return (HsListTreeNode)this.map.get(key);
    }
    
    public HsListTreeNode get(int i) {
        return (HsListTreeNode) this.list.get(i);
    }
    
}
