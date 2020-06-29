package com.leo.hogwarts.util;

import com.google.common.collect.Lists;
import com.leo.hogwarts.util.entity.AbstractTreeNode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @ClassName TreeNodeUtil
 * @Description 树形结构生成
 * @Author Leo
 * @Date 2020/6/29 10:36
 */

public class TreeNodeUtil {

    /**
     * 树形结构构造方法
     * @param allNodes
     * @param <T>
     * @return
     */
    public static <T extends AbstractTreeNode> List<T> treeNodeConstructor(List<T> allNodes) {
        List<T> parentNodes = Lists.newArrayList();
        List<T> classifyList = null;
        if (CollectionUtils.isNotEmpty(parentNodes)) {
            classifyList = allNodes;
            for (T node : classifyList) {
                if (StringUtils.isBlank(node.getParentId())) {
                    parentNodes.add(node);
                }
            }
        }

        List<T> treeNodes = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(parentNodes)) {
            for (T parent : parentNodes) {
                treeNodes.add(recursiveTree(parent, classifyList));
            }
        }

        return treeNodes;
    }

    /**
     * 递归处理子节点数据
     * @param parentNode
     * @param classifyList
     * @param <T>
     * @return
     */
    private static <T extends AbstractTreeNode> T recursiveTree(T parentNode, List<T> classifyList) {
        List<T> childrenNodes = getAllChildrenNodes(parentNode,classifyList);
        if (CollectionUtils.isNotEmpty(childrenNodes)) {
            for (T node : childrenNodes) {
                if (parentNode.getId().equals(node.getParentId())) {
                    parentNode.getChildrenNodes().add(recursiveTree(node, classifyList));
                }
            }
        }
        return parentNode;
    }

    /**
     * 获取所有子节点
     * @param node
     * @param classifyList
     * @param <T>
     * @return
     */
    private static <T extends AbstractTreeNode> List<T> getAllChildrenNodes(T node, List<T> classifyList) {
        List<T> childrenNodes = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(classifyList)) {
            for (T childNode : classifyList) {
                if (childNode.getParentId().equals(node.getId())) {
                    childrenNodes.add(childNode);
                }
            }
        }
        return childrenNodes;
    }

}
