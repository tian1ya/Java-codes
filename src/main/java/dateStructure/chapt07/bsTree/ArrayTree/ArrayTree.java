package dateStructure.chapt07.bsTree.ArrayTree;

public class ArrayTree {
    int[] data;

    public ArrayTree(int[] data) {
        this.data = data;
    }

    public void preOrder() {
        if (data == null && data.length < 0) {
            return;
        }
        preOrder(0);
    }

    private void preOrder(int index) {
        System.out.println(data[index]);
        if ((2 * index + 1) >= data.length) return;
        preOrder(2 * index + 1);

        if ((2 * index + 2) >= data.length) return;
        preOrder(2 * index + 2);
    }
}
