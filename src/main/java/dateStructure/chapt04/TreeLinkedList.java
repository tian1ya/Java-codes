package dateStructure.chapt04;

public class TreeLinkedList implements Tree {

    private Object element;
    private TreeLinkedList parent;
    private TreeLinkedList firstChild;
    private TreeLinkedList nextSibling;

    public TreeLinkedList(Object obj) {
        this(obj, null, null, null);
    }

    public TreeLinkedList() {
        this(null, null, null, null);
    }

    public TreeLinkedList(Object element, TreeLinkedList parent, TreeLinkedList firstChild, TreeLinkedList nextSibling) {
        this.element = element;
        this.parent = parent;
        this.firstChild = firstChild;
        this.nextSibling = nextSibling;
    }

    @Override
    public Object getElem() {
        return element;
    }

    @Override
    public Object setElem(Object o) {
        this.element = o;
        return element;
    }

    @Override
    public TreeLinkedList getParent() {
        return parent;
    }

    @Override
    public TreeLinkedList getFirstChild() {
        return firstChild;
    }

    @Override
    public TreeLinkedList getNextSibling() {
        return nextSibling;
    }

    @Override
    /*
        返回当前节点后代元素的数目，既以当前节点为根的子树的规模，统计(子)树的规模
        一棵树的规模，等于根节点下所有子树规模之和再加一，也等于根节点的后代总数。
        先通过 firstChild 引用找出根节点的长子，并沿着 nextSibling 引用顺 次找到其余的孩子，递归地统计出各子树的规模。最后，只要将所有子树的规模累加起来，再计入 根节点本身，就得到了整棵树的规模。
     */
    public int getSize() {
        int size = 1;
        TreeLinkedList subTree = firstChild;
        while (subTree != null) {
            size += subTree.getSize();
            subTree = subTree.getNextSibling();
        }
        return size;
    }

    @Override
    /*
        v为根的子树高度
        首先通过 firstChild 引用找出根节点的长子，并沿着 nextSibling 引用顺次找到其余的孩子，递归地计算出各子树的高度。
        最后，只要找出所有子树的最大高度，再 计入根节点本身，就得到了根节点的高度(即树高)。
     */
    public int getHeight() {
        int height = -1;
        TreeLinkedList subTree = firstChild;
        while (subTree != null) {
            // 所有孩子中最大的高度
            height = Math.max(height, subTree.getHeight());
            subTree = subTree.getNextSibling();
        }
        return height + 1;
    }

    @Override
    /*
        从 v 的父亲开始，沿着 parent 指针不断上移，直到深度为 0 的树 根。在这个过程中所遇到的每个节点，
        都是 v 的真祖先;反之，在这一过程中，v 的每一真祖先迟 早都会被找到。因此，根据总共上移的层数，就可以得到 v 在整棵树中的深度。
     */
    public int getDepth() {
        // 当前节点的深度
        int depth = 1;
        TreeLinkedList p = parent;
        while (p != null) {
            depth++;
            p = p.getParent();
        }
        return depth;
    }

    /*
      遍历树
        前序遍历：
            对任一(子)树的前序遍历，将首先访问其根节点，然后再递归地对其下的各棵子树进行前序遍历。对于同一根节点下的各棵子树，遍历的次序通常是任意的;但若换成有序树，
            则可以按照兄 弟间相应的次序对它们实施遍历

        后序遍历：
            首先递归地对根节点下的各棵子树进行后序遍历，最后才访问根节点。由后序遍历生成的节点序列，称作后序遍历序列。

        层次遍历
            各节点被访问的次序取决于它们各自的深度，其策略可以总结 为“深度小的节点优先访问”。对于同一深度的节点，访问的次序可以是随机的，通常取决于它们的 存储次序，
            即首先访问由firstChild指定的长子，然后根据nextSibling确定后续节点的次序。

     */

    public void PreOrderTraversal(TreeLinkedList node) {
        TreeLinkedList v;
        if (node != null) {
            System.out.println(node.getElem());
            for (v = node.getFirstChild(); v != null; v.getNextSibling()) {
                // 依次递归操作就能将一个节点的所有 FirstChild 遍历，然后回到 NextSibling，然后遍历NextSibling的所有FirstChild，然后在是下一个NextSibling
                PreOrderTraversal(v);
            }
        }
    }

    public void postOrderTraversal(TreeLinkedList node) {
        TreeLinkedList v;
        if (node != null) {
            for (v = node.getFirstChild(); v != null; v.getNextSibling()) {
                PreOrderTraversal(v);
            }
            System.out.println(node.getElem());
        }
    }
}
