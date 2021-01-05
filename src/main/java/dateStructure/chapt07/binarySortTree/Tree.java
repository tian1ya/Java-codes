package dateStructure.chapt07.binarySortTree;

public class Tree {
    Node root;

    public void add(int value) {
        if (root == null)
            root = new Node(value);
        else
            root.add(new Node(value));
    }

    public void inOrder() {
        if (root != null) root.inOrder();
    }

    public Node search(int value) {
        return root != null ? root.search(value) : new Node();
    }

    public Node delete(int value) {
        if (root.value == value) {
            Node tmp = root;
            Node minNode = root.findMinNode(root);
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
            return tmp;
        }
        if (root != null) {
            Node parent = searchParent(root, value);
            Node node = search(value);
            return root.delete(parent, node);
        } else
            throw new RuntimeException("node value is: " + value + " is not exist");

    }

    private Node searchParent(Node root, int value) {
        if (root.value == value)
            return null;
        if ((root.left != null && root.left.value == value) || (root.right != null && root.right.value == value))
            return root;
        else if (value < root.value)
            return searchParent(root.left, value);
        else
            return searchParent(root.right, value);


    }
}
