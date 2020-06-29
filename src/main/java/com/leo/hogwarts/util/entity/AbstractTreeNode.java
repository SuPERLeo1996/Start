package com.leo.hogwarts.util.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AbstractTreeNode
 * @Description
 * @Author Leo
 * @Date 2020/6/29 10:39
 */

public abstract class AbstractTreeNode {
    private String id;
    private String parentId;
    private List<AbstractTreeNode> childrenNodes = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<AbstractTreeNode> getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List<AbstractTreeNode> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }
}
