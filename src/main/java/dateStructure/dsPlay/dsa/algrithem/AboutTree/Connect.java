package dateStructure.dsPlay.dsa.algrithem.AboutTree;

/*
    116
    完美二叉树：二叉树呈三角形行状
 */
public class Connect {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static Node connect(Node root) {
        if (root==null) return root;
        connect(root.left, root.right);
        return root;
    }

    private static void connect(Node left, Node right) {
        if (left == null || right==null) return;
        left.next = right;

        connect(left.left, left.right);
        connect(right.left, right.right);
        connect(left.right, right.left);
    }

    public static void main(String[] args) {
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node2 = new Node(2, node4, node5, null);

        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node3 = new Node(3, node6, node7, null);

        Node node1 = new Node(1, node2, node3, null);

        Node node = connect(node1);
        System.out.println("a");
    }
}
