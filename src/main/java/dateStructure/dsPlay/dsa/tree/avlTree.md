[博客](https://www.cnblogs.com/skywang12345/p/3577479.html)

[另外一篇博客](https://segmentfault.com/a/1190000019101902)

4种平衡状态
LL(左左)，LR(左右)，RR(右右)和RL(右左)
![a](./avlTree.jpg)
其它的失去平衡的AVL树
![a](./avlTree.jpg)
> LL: 根节点左子树的左子树还有非空节点，而根节点的右子树为空节点
> 
> LR： 根节点左子树的右子树还有非空节点，而根节点的右子树为空节点
>
> RL： 根节点右子树的左子树还有非空节点，而根节点的左子树为空节点
>
> RR： 根节点的右子树的右子树还有非空节点，而根节点的左子树为空节点
>
> 以上的原因导致根节点的左右高度差大于 1， 所以不是一个平衡树

## 旋转

> 针对以上的情况对树进行旋转，就可以成为一个平衡的树

#### LL 的旋转
![a](./avlTreeLL.jpg)
相关代码：AVL.leftLeftRotation

#### RR 旋转
![a](./avlTreeRR.jpg)
相关代码：AVL.rightRightRotation

### LR 旋转
> 这种失去平衡的情况，需要选择2次才能再次平衡
> 第一次旋转是围绕"k1"进行的"RR旋转"，第二次是围绕"k3"进行的"LL旋转"。

![a](./avlTreeLR.jpg)

### RL 的旋转
RL是与LR的对称情况！RL恢复平衡的旋转方法如下：
第一次旋转是围绕"k3"进行的"LL旋转"，第二次是围绕"k1"进行的"RR旋转"。

![a](./avlTreeRL.jpg)





