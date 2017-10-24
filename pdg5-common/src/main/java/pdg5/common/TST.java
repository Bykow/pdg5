package pdg5.common;

/**
 * Class representing a Ternary Search Tree, used to stock a diconnary
 */
public class TST {
    /**
     * Private inner class Node, represents a Node (a char) in the data structure
     */
    private class Node {
        public Node left;       // subtree smaller keys
        public Node right;      // subtree greater keys
        public Node center;     // subtree with next key
        public char c;
        public boolean isWord;  // is the current char an end of word
        public int nodeHeight;  // height of subtree

        Node(char c) {
            this.c = c;
            this.isWord = false;
            this.nodeHeight = 0;
            this.left = null;
            this.right = null;
            this.center = null;
        }
    }

    /**
     * Root of the tree
     */
    private Node root;

    /**
     * Put a Node in the tree with a rebalancing
     * @param x the node to put
     * @param key the key (string)
     * @param d index of the char in the word (for recursive purposes)
     * @return Node for recursive calls
     */
    private Node put(Node x, String key, int d) {
        char c = key.charAt(d);

        if (x == null) {
            x = new Node(c);
        }

        if (c < x.c) {
            x.left = put(x.left, key, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, d);
        } else if (d != key.length() - 1) {
            x.center = put(x.center, key, d + 1);
        } else {
            x.isWord = true;
        }

        return restoreBalance(x);
    }

    /**
     * Gets the Node of a key
     * @param x Node to get
     * @param key the key (string)
     * @param d index of the char in the word (for recursive purposes)
     * @return Node for recursive calls
     */
    private boolean get(Node x, String key, int d) {

        if (x == null) {
            return false;
        }

        char c = key.charAt(d);

        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else {
            if (x.isWord && d == key.length() - 1) {
                return true;
            } else if (d == key.length() - 1) {
                return false;
            } else {
                return get(x.center, key, d + 1);
            }
        }
    }

    /**
     * Helper updates the height of a subtree with the height of the childs
     * @param x Node to update
     */
    private void updateNodeHeight(Node x) {
        x.nodeHeight = Integer.max(height(x.right), height(x.left)) + 1;
    }

    /**
     * Rotation on the right of the subtree
     * @param x Node to rotate around
     * @return Node for recursive calls
     */
    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;

        updateNodeHeight(x);
        updateNodeHeight(y);
        return y;
    }

    /**
     * Rotation on the left of the subtree
     * @param x Node to rotate around
     * @return Node for recursive calls
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;

        updateNodeHeight(x);
        updateNodeHeight(y);
        return y;
    }

    /**
     * Return the balance of a tree
     * @param x Node to rotate around
     * @return int the delta between balances
     */
    private int balance(Node x) {
        if (x == null) {
            return 0;
        }

        return height(x.left) - height(x.right);
    }

    /**
     * Restores the balance of the tree
     * @param x Node to rotate around
     * @return Node for recursive calls
     */
    private Node restoreBalance(Node x) {

        if (balance(x) < -1) {// left < right-1
            if (balance(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        } else if (balance(x) > 1) { // left > right+1
            if (balance(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        } else {
            updateNodeHeight(x);
        }
        return x;
    }

    // Renvoie la hauteur du noeux x

    /**
     * height of the current node
     * @param x Node to get height from
     * @return int height
     */
    private int height(Node x) {
        if (x == null) {
            return -1;
        }

        return x.nodeHeight;
    }

    /**
     * Default constructor
     */
    public TST() {
        this.root = null;
    }

    /**
     * Puts a word in the tree
     * @param key word
     */
    public void put(String key) {
        root = put(root, key, 0);
    }

    /**
     * Asks if a word is in the tree
     * @param key string word
     * @return true is the word is in the tree
     */
    public boolean contains(String key) {
       return get(root, key, 0);
    }
}
