//Kendall Brown
//6/22/21
//cmsc 256
package cmsc256;



public class BinarySearchTree <E extends Comparable<? super E>>{
    private BinaryNode<E> root;
    private int size;


    public BinarySearchTree(){
        clear();
    }


    private boolean addToParent(BinaryNode<E> parentNode, BinaryNode<E> addNode){
        int compare = addNode.value.compareTo(parentNode.value);
        boolean wasAdded = false;
        if (compare < 0) { // the addNode value is less than the parentNode value
            // if parent has no left node, add new node as left
            if (parentNode.left == null) {
                parentNode.left = addNode;
                wasAdded = true;
            }
            else {
                // otherwise, add to parentNode's left (recursive)
                wasAdded = addToParent(parentNode.left, addNode);
            }
        }
        else if (compare > 0) { // the addNode value is greater than the parentNode value
            // if parent has no right node, add new node as right
            if (parentNode.right == null) {
                parentNode.right = addNode;
                wasAdded = true;
            }
            else {
                // otherwise, add to parentNode's right (recursive)
                wasAdded = addToParent(parentNode.right, addNode);
            }
        }
        return wasAdded;
    }

    public boolean add(E inValue){
        BinaryNode<E> node = new BinaryNode<>(inValue);
        boolean wasAdded = true;

        if(root == null){
            root = node;
        }
        else{
            wasAdded = addToParent(root, node);
        }
        if(wasAdded){
            size++;

        }
        return wasAdded;
    }

    public boolean remove(E valueToRemove) {
        if (root == null) {
            return false;
        }
        if (valueToRemove.compareTo(root.value) == 0) {
            if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                BinaryNode<E> formerRight = root.right;
                root = root.left;
                addToParent(root, formerRight);
            }
            size--;

            return true;
        }
        return removeSubNode(root, valueToRemove);
    }


    private boolean removeSubNode(BinaryNode<E> parent, E removeValue) {
        int compareParent = removeValue.compareTo(parent.value);
        BinaryNode<E> subTree = null;

        if (compareParent > 0) {

            subTree = parent.right;
        }

        else {

            subTree = parent.left;
        }
        if(subTree == null){
            return false;
        }
        if (subTree.value.compareTo(removeValue) == 0) {
            BinaryNode<E> replacement;
            if (subTree.left == null) {
                replacement = subTree.right;
            }
            else if (subTree.right == null) {
                replacement = subTree.left;
            }
            else {
                BinaryNode<E> formerRight = subTree.right;
                replacement = subTree.left;
                addToParent(replacement, formerRight);
            }

            if (compareParent > 0) {
                parent.right = replacement;
            }
            else {
                parent.left = replacement;
            }

            size--;
            return true;
        }
        return removeSubNode(subTree, removeValue);
    }

    public BinaryNode<E> getRoot(){
        return root;
    }
    public int size(){
        return size;
    }
    public void clear(){
        root = null;
        size = 0;
    }

    /** Returns the node containing the largest entry in the tree */
    public BinaryNode<E> findLargest(){
        if(root == null)
            return null;
        BinaryNode<E> largest = root;
        while(largest.right != null)
            largest = largest.right;
        return largest;
    }
    /** Removes and returns the node containing the largest entry in the tree */

    public BinaryNode<E> removeLargest(){
        BinaryNode<E> largest = findLargest();
        if(largest == null)
            return null;
        remove(largest.getValue());
        return largest;
    }
    /** Return the height of this binary tree */
    private int getHeight(BinaryNode<E> subroot) {
        if (subroot.left == null){
            if(subroot.right == null)
                return 0;
            else
                return 1 + getHeight(subroot.right);
        }
        if(subroot.right == null)
            return 1 + getHeight(subroot.left);
        return 1 + Math.max(getHeight(subroot.left), getHeight(subroot.right));
    }

    public int getHeight() {
        if (size == 0)
            return -1;
        if (size == 1)
            return 0;
        return getHeight(root);
    }
        /** Returns true if the tree is a full binary tree */

    public boolean isFullBinaryTree(){
        return size == 2 * getNumberOfLeaves() - 1;

    }
    /** Return the number of leaf nodes */
    public int getNumberOfLeaves(){
        return getNumberOfLeaves(root);

    }
    private int getNumberOfLeaves(BinaryNode<E> subroot){
        if(subroot == null)
            return 0;
        if (subroot.left == null && subroot.right == null)
            return 1;
        return getNumberOfLeaves(subroot.left) + getNumberOfLeaves(subroot.right);
    }



    /** Return the number of internal nodes */
    public int getNumberOfInternalNodes(){

        return size - getNumberOfLeaves();
    }


    public class BinaryNode<E> {
            protected E value;
            protected BinaryNode<E> right;
            protected BinaryNode<E> left;


            //constructor
            public BinaryNode(E valueIn) {
                value = valueIn;
            }

            public E getValue() {
                return value;
            }




        }
    }
