package eval;

public class SerialList {


    public class Node extends java.lang.Object{
        public SerialList.Node parent;
        public SerialList.Node left;
        public SerialList.Node right;
        public Comparable<?> key;
        public Object val;

        public Node(Comparable k, Object v){
            this.key = k;
            this.val = v;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public void remove(){
            return;
        }

    }

    public Node root;
    public int size;
    public void add(Comparable key, Object val){
        if(root.right == root) //empty tree
        {
            SerialList.Node newNode = new SerialList.Node(key, val);
            root.right = newNode;
            newNode.parent = root;
            newNode.right = root;
        }

        return;
    }

    public void balance(){
        return;
    }

    public Object find(Comparable key){
        return null;
    }

    public Object[] inOrder(){
        return null;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public Object[] postOrder(){
        return null;
    }

    public Object[] preOrder(){
        return null;
    }

    public Object remove(Comparable key){
        return null;
    }

    public int size(){
        return this.size;
    }

    public Object get(int i){
        return null;
    }

    public String toString(){
        return null;
    }
}
