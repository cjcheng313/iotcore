package com.ccj.common.utils.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zy
 * @Date 2020-02-02 11:19
 */
public class TreeParser{

    /**
     * 解析树形数据
     * @param topId
     * @param entityList
     * @return
     */
    public static <E extends TreeUtil<E>> List<E> getTreeList(String topId, List<E> entityList) {
        List<E> resultList=new ArrayList<>();

        //获取顶层元素集合
        String parentId;
        for (E entity : entityList) {
            parentId=entity.getTreeParentId();
            if(parentId==null||topId.equals(parentId)){
                resultList.add(entity);
            }
        }

        //获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setTreeChildList(getSubList(entity.getTreeId(),entityList));
        }

        return resultList;
    }

    /**
     * 获取子数据集合
     */
    public   static  <E extends TreeUtil<E>>  List<E> getSubList(String id, List<E> entityList) {
        List<E> childList=new ArrayList<>();
        String parentId;

        //子集的直接子对象
        for (E entity : entityList) {
            parentId=entity.getTreeParentId();
            if(id.equals(parentId)){
                childList.add(entity);
            }
        }

        //子集的间接子对象
        for (E entity : childList) {
            entity.setTreeChildList(getSubList(entity.getTreeId(), entityList));
        }

        //递归退出条件
        if(childList.size()==0){
            return null;
        }

        return childList;
    }

}

