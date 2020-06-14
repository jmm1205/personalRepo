package eval;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;

public class SerialList extends java.lang.Object implements java.io.Serializable{


    public class Node extends java.lang.Object implements java.io.Serializable{
        public Comparable<?> key;
        public Object val;
        public SerialList.Node next;

        public Node(Comparable k, Object v){
            this.key = k;
            this.val = v;
            this.next = null;
        }
    }
    public transient int size;
    public transient SerialList.Node head;
    public transient SerialList.Node tail;

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

    private void addLast(Comparable key, Object val){
        if(this.head == null){ //empty list
            add(key, val);
        }
        SerialList.Node temp, p;
        temp = new SerialList.Node(key, val);
        p = this.tail;
        p.next = temp;
        this.tail = temp;
        this.size++;
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
        if(this.size == 0) throw new NoSuchElementException();

        SerialList.Node temp = this.head;
        if(key.compareTo(temp.key) == 0){
            this.head = temp.next;
            this.size--;
            return temp.val;
        }
        while(temp.next != null){
            temp = temp.next;
        }
        if(temp == null || temp.next == null)
            throw new NoSuchElementException();
        SerialList.Node holder = temp.next;
        SerialList.Node next = temp.next.next;
        temp.next = next;
        this.size--;
        return holder.val;
    }

    public Object remove(int index){
        if(index < 0 || index > this.size || this.size == 0) throw new IndexOutOfBoundsException();

        SerialList.Node temp = this.head;
        if(index == 0){
            this.head = temp.next;
            this.size--;
            return temp.val;
        }
        for(int i = 0; temp != null && i < index - 1; i++){
            temp = temp.next;
        }
        if(temp == null || temp.next == null)
            throw new IndexOutOfBoundsException();
        SerialList.Node holder = temp.next;
        SerialList.Node next = temp.next.next;
        temp.next = next;
        this.size--;
        return holder.val;
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        out.writeInt(this.size);
        for(SerialList.Node cur = this.head; cur != null; cur = cur.next){
//            SerialList.Node temp = new SerialList.Node(cur.key, cur.val);
//            System.out.println("Writing to file: " + temp.key + " " + temp.val);
//            out.writeObject(temp);
            out.writeObject(cur.key);
            out.writeObject(cur.val);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        assert this.size == 0 && this.head == null && this.tail == null;
        int n = in.readInt();
        for(int i = 0; i < n - 1; i++){
//            SerialList.Node temp = (SerialList.Node)in.readObject();
//            System.out.println("Reading from file: " + temp.key + " " + temp.val);
//            addLast(temp.key, temp.val);
            Comparable key = (Comparable)in.readObject();
            Object val = in.readObject();
            addLast(key, val);
        }
    }



    public int size(){ return this.size; }

    public void sort(){

        return;
    }

    public String toString(){
        SerialList.Node cur = this.head;
        for(int i = 0; i < this.size; i++){
            cur = cur.next;
            System.out.print("(" + cur.key + ", " + cur.val + ") -> ");
            if(i % 5 == 0) System.out.println();
        }
        return " ";
    }


}
