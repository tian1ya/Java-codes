#### 写递归的秘诀
    关键是明确函数的 【定义】是什么，然后相信这个定义，利用这个定义去推导最终结果，绝不要跳入到递归的细节中去
比如计算一个二叉树总共有多少个结点
```java
int count(TreeNode root) {
    if (root == null) return 0;
    return 1 + count(root.left) + count(root.right);
        }
```

二叉树的问题难点在于，如何把题目的要求细化成每个节点需要做的事情