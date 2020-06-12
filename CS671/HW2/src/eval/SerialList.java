package eval;

import java.util.NoSuchElementException;

public class SerialList {


    public class Node extends java.lang.Object{
        public Comparable<?> key;
        public Object val;
        public SerialList.Node next;

        public Node(Comparable k, Object v){
            this.key = k;
            this.val = v;
            this.next = null;
        }

//        @Override
//        public String toString(){
//            return this.val + "";
//        }
    }
    public int size;
    public SerialList.Node head;
    public SerialList.Node tail;

    public SerialList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public SerialList(SerialList.Node h, SerialList.Node t, int s){

    }

    public void add(Comparable key,Object val){
        SerialList.Node newNode = new SerialList.Node(key, val);
        newNode.next = this.head;
        this.head = newNode;
        if(this.tail == null)
            this.tail = newNode;
        this.size++;
        return;
    }

    public Object get(Comparable key){
        SerialList.Node cur = this.head;
        if(cur == null) throw new NoSuchElementException();
        while(cur != null){
            if(key.compareTo(cur.key) == 0)
                return cur.val;
            cur = cur.next;
        }
        throw new NoSuchElementException();
    }

    public Object get(int index){
        if(index < 0 || index > this.size) throw new IndexOutOfBoundsException();
        SerialList.Node cur = this.head;
        for(int i = 0; i < index; i++)
            cur = cur.next;
        return cur.val;
    }

    public boolean isEmpty(){ return this.size == 0;}

    public Object remove(Comparable key){
        SerialList.Node cur = this.head;
        SerialList.Node p = cur;
        if(cur == null) throw new NoSuchElementException();
        while(cur != null){
            if(key.compareTo(cur.key) == 0)
                break;
            p = cur;
            cur = cur.next;
        }
        if(key.compareTo(cur.key) == 0){
            if(cur == this.head)
                this.head = cur.next;
            if(cur == this.tail)
                this.tail = p;
            if(p != null){
                p.next = cur.next;
                cur.next = null;
            }
            this.size--;
            return cur.val;
        }
        throw new NoSuchElementException();
    }

    public Object remove(int index){
        if(index < 0 || index > this.size) throw new IndexOutOfBoundsException();
        if(this.head == null) return null;

        SerialList.Node cur, p;
        p = cur = this.head;
        if(index == 0){
            this.head = cur.next;
            this.size--;
            return cur.val;
        }
        for(int i = 0; i < index; i++){
            p = cur;
            cur = cur.next;
        }
        p.next = cur.next;
        if(cur == this.tail)
            this.tail = p;
        this.size--;
        return cur.val;
    }

    public int size(){ return this.size; }

    public void sort(){

        return;
    }

    public String toString(){

        return " ";
    }


}
