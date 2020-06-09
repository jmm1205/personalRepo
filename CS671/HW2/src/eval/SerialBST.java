package eval;

import java.util.NoSuchElementException;
import java.util.Stack;

public class SerialBST extends java.lang.Object implements java.io.Serializable {

    public static class Node extends java.lang.Object{
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

        private void remove(){
            return;
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

    public Object remove(Comparable key){
        SerialBST.Node cur = (SerialBST.Node)find(key);
        SerialBST.Node p = null;

        //is leaf node
        //has two children
        //has one child
        return null;
    }

    public int size(){
        return this.size;
    }

    public String toString(){
        return null;
    }
}
