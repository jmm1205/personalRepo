package eval;

import java.util.NoSuchElementException;
import java.util.Stack;

public class SerialBST extends java.lang.Object implements java.io.Serializable {

    public static class Node extends java.lang.Object implements java.io.Serializable{
        public Node parent;
        public Node left;
        public Node right;
        public Comparable<?> key;
        public Object val;

        public Node(Comparable k, Object v){
            this.key = k;
            this.val = v;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    public SerialBST.Node root;
    public int size;

    public SerialBST(){
        this.size = 0;
        this.root = null;
    }

    public void add(Comparable key, Object val){
        SerialBST.Node newNode = new SerialBST.Node(key, val);
        SerialBST.Node cur, p;

        if(this.root == null){
            this.root = newNode;
            this.size++;
            return;
        }else{
            cur = this.root;
        }

        while(true){
            p = cur;

            if(key.compareTo(cur.key) < 0){
                cur = cur.left;
                if(cur == null){
                    p.left = newNode;
                    newNode.parent = p;
                    this.size++;
                    return;
                }
            }    else{
                cur = cur.right;
                if(cur == null){
                    p.right = newNode;
                    newNode.parent = p;
                    this.size++;
                    return;
                }
            }
        }
    }

    public void balance(){
        if(this.root == null) return; //empty tree is balanced tree
        return;
    }

    public Object find(Comparable key) {
        if(this.root == null) return null; //empty tree
        SerialBST.Node cur = this.root;
        while(cur != null){
            if(key.compareTo(cur.key) < 0)
                cur = cur.left;
            else if(key.compareTo(cur.key) > 0)
                cur = cur.right;
            else
                break;
        }
        if(cur == null) throw new NoSuchElementException();
        //System.out.println(cur.key + " DEBUG CODE");
        return cur;
    }


    public Object[] inOrder(){
        Object[] list = new Object[this.size];
        SerialBST.Node cur, pre;
        int index = 0;
        if(this.root == null) return null; //empty tree

        cur = this.root;
        while(cur != null){
            if (cur.left == null){
                //System.out.println(cur.key + " ");
                list[index] = cur.val;
                index++;
                cur = cur.right;
            }
            else{
                pre = cur.left;
                while(pre.right != null && pre.right != cur)
                    pre = pre.right;
                if(pre.right == null){
                    pre.right = cur;
                    cur = cur.left;
                }
                else{
                    pre.right = null;
                    //System.out.println(cur.key + " ");
                    list[index] = cur.val;
                    index++;
                    cur = cur.right;
                }
            }
        }

        return list;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public Object[] postOrder(){
        if(this.root == null) return null;
        Object[] list = new Object[this.size];
        int index = 0;
        Stack<SerialBST.Node> nodes = new Stack<>();
        nodes.push(this.root);
        SerialBST.Node p = null;

        while(!nodes.isEmpty()){
            SerialBST.Node cur = nodes.peek();
            if(p == null || p.left == cur || p.right == cur){
                if(cur.left != null)
                    nodes.push(cur.left);
                else if(cur.right != null)
                    nodes.push(cur.right);
                else{
                    nodes.pop();
                    list[index] = cur.val;
                    index++;
                }
            }
            else if(cur.left == p){
                if(cur.right != null)
                    nodes.push(cur.right);
                else{
                    nodes.pop();
                    list[index] = cur.val;
                    index++;
                }
            }
            else if(cur.right == p){
                nodes.pop();
                list[index] = cur.val;
                index++;
            }
            p = cur;
        }
        return list;
    }

    /**
     * Travers the bst in a preOrder fashion, adds each nodes value to a list as it goes
     * @return
     */
    public Object[] preOrder(){
        Object[] list = new Object[this.size];
        int index = 0;

        Stack<SerialBST.Node> nodes = new Stack<>();
        nodes.push(this.root);
        SerialBST.Node cur = nodes.peek();
        while(!nodes.isEmpty()){
            if(cur != null){
                list[index] = cur.val;
                index++;
                if(cur.right != null)
                    nodes.push(cur.right);
                cur = cur.left;
            }
            else
                cur = nodes.pop();
        }
        return list;
    }

    /**
     * Removes first occurence of a node found with argument 'key'
     * @param key
     * @return
     */
    public Object remove(Comparable key){
        SerialBST.Node cur = this.root;
        SerialBST.Node target = null, p = null;
        SerialBST.Node holder;
        if(cur == null){
            throw new NoSuchElementException();
        }
        while(true){
            if(key.compareTo(cur.key) == 0)
                target = cur;
            if(key.compareTo(cur.key) < 0){
                if(cur.left == null){
                    break;
                }
                p = cur;
                cur = cur.left;
            }
            else{
                if(cur.right == null){
                    break;
                }
                p = cur;
                cur = cur.right;
            }
        }
        if(target == null) {
            throw new NoSuchElementException();
        }
        else{
            holder = copyNode(target);
            if(p == null){
                this.root = null;
            }
            else{
                target.key = cur.key;
                target.val = cur.val;
                if(p.left == cur)
                    p.left = cur.right;
                else{
                    p.right = cur.left;
                }
            }
        }
        return holder.key;
    }

    /**
     * Utility method for copying a node
     * @param n argument node to be copied to assigned variable
     * @return a reference to new node with same values/pointers as 'n'
     */
    private SerialBST.Node copyNode(SerialBST.Node n){
        SerialBST.Node newNode = new SerialBST.Node(null, null);
        newNode.key = n.key;
        newNode.val = n.val;
        newNode.parent = n.parent;

        return newNode;
    }

    /**
     * Method used for debugging, prints the tree horizontally
     * Is not intended for use with large trees as it is a recursive method
     * @param root
     * @param space
     */
    private void printTreeUtil(SerialBST.Node root, int space){
        if(root == null) return;

        space += 10;
        printTreeUtil(root.right, space);

        System.out.print("\n");
        for(int i = 10; i < space; i++){
            System.out.print(" ");
        }
        System.out.print(root.key + "\n");
        printTreeUtil(root.left, space);
    }

    /**
     * Method used for debugging, calls a recursive method that prints the tree horizontally
     * Is not intended for use with large trees as it is a recursive method
     * @param root
     */
    public void printTree(SerialBST.Node root){
        printTreeUtil(this.root, 0);
    }

    public int size(){
        return this.size;
    }

    public String toString(){
        return null;
    }
}
