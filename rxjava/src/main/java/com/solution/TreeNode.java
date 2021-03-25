package com.solution;

/**
 * 文件名: TreeNode
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2021/3/4
 */
public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int value;



    public static int getHeight(TreeNode node){
        if (node == null){
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight,rightHeight);
    }
}
