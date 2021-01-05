package dateStructure.chapt07.binarySortTree;

public class Test {
    public static void main(String[] args) {
        Tree test = new Tree();

//        int[] arr = {7, 3, 10, 12, 5, 1, 9};
//        int[] arr = {1,2,3,4,5,6,7,8};

        // 测试右旋转
//        int[] arr = {8,9,6,7,5,4};
        // 测试左旋转
//        int[] arr = {2,1,4,3,5,6};
      // 双旋转
        int[] arr = {8,9,5,4,6,7};

        for (int i : arr) {
            test.add(i);
        }

//        test.inOrder();

//        Node node = test.search(10);
//        System.out.println(node.value);
//
//        test.delete(9);

        test.inOrder();
        System.out.println(test.root.height());
        System.out.println(test.root.value);
    }
}
