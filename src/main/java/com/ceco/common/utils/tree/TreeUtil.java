package com.ceco.common.utils.tree;

import java.util.List;

/**
 * @author zy
 * @Date 2020-02-02 11:19
 */

public interface TreeUtil<E> {
    public String getTreeId();
    public String getTreeParentId();
    public void setTreeChildList(List<E> treeChildList);
}

