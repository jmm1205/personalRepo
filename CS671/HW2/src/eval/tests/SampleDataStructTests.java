package eval.tests;

import eval.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class SampleDataStructTests {
    Random r;
    SerialList sl;
    SerialBST sb;
    SerialPrioQueue spq;
    ObjectOutputStream out;
    ObjectInputStream in;
    ByteArrayOutputStream bos;
    ByteArrayInputStream bis;

    @Before
    public void BEFORE() {
        try{
            r = new Random();
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
        }
        catch(Exception ex){
            System.err.println("Trouble setting up the tests. This is bad.");
            System.err.println(ex);
        }
    }

    @After
    public void AFTER() {
        try{
            out.close();
            bos.close();
        }
        catch(Exception ex){ }
    }
//
//    @Test(timeout=1000) //SerialList add/get
//    public void sample1 () {
//        sl = new SerialList();
//        for(int i=0; i<5; i++){
//            sl.add(5-i, "test"+i);
//        }
//        for(int i=0; i<5; i++){
//            assertEquals(sl.get(i), "test"+(4-i)); //test by index
//            assertEquals(sl.get(new Integer(5-i)), "test"+i); //test by key
//        }
//        try{
//            sl.get(7); //getting item out of range fails
//            throw new AssertionError("Expected IndexOutOfBoundsException.");
//        }
//        catch(IndexOutOfBoundsException ex){}
//        try{
//            sl.get(new Integer(6)); //getting non-existent item fails
//            throw new AssertionError("Expected NoSuchElementException.");
//        }
//        catch(NoSuchElementException ex){ return; }
//    }

//    @Test(timeout=1000) //SerialList remove
//    public void sample2 () {
//        sl = new SerialList();
//        for(int i=0; i<5; i++){
//            sl.add(5-i, "test"+i);
//        }
//        sl.remove(2); //remove by index
//        assertEquals(sl.get(2), "test"+1);
//        try{
//            sl.remove(4);
//            throw new AssertionError("Expected IndexOutOfBoundsException.");
//        } //removing item out of range fails
//        catch(IndexOutOfBoundsException ex){ }
//        sl.remove(new Integer(1)); //remove by key
//        assertEquals(sl.get(0), "test"+3);
//        try{
//            sl.remove(new Integer(1));  //removing removed item fails
//            throw new AssertionError("Expected NoSuchElementException.");
//        }
//        catch(NoSuchElementException ex){ }
//    }
//
//    @Test(timeout=1000) //SerialList isEmpty and size
//    public void sample3 () {
//        sl = new SerialList();
//        assertTrue(sl.isEmpty());
//        sl.add(0, "test");
//        assertFalse(sl.isEmpty());
//    }

//    @SuppressWarnings("unchecked")
//    @Test(timeout=2000) //SerialList sort
//    public void sample4 () {
//        sl = new SerialList();
//        for(Integer x: new Integer[] {5,2,5,7,3,8,1}){
//            sl.add(x, x);
//        }
//        int origSize = sl.size();
//        sl.sort();
//        assertEquals(sl.size(), origSize);
//        SerialList.Node curr = sl.head;
//        SerialList.Node next = sl.head.next;
//        while(next!=null){
//            assertTrue(curr.key.compareTo(next.key) <= 0);
//            assertEquals(curr.key, curr.val);
//            curr = curr.next;
//            next = next.next;
//        }
//    }
//
//    @Test(timeout=3000) //SerialList efficient serialization
//    public void sample5 () {
//        SerialList sl1 = new SerialList();
//        SerialList sl2 = new SerialList();
//        for(int i=0; i<1000; i++){
//            for(Long x: new Long[] {1L,2L,3L,0L,4L,5L}){
//                sl1.add(x, x);
//                sl2.add(r.nextLong(), x);
//            }
//        }
//
//        int copyLength=0;
//        int uniqueLength=0;
//        try{
//            out.writeObject(sl1);
//            copyLength = bos.size();
//            out.writeObject(sl2);
//            uniqueLength = bos.size()-copyLength;
//        }
//        catch(Exception ex){
//            System.err.println(ex.toString());
//            assertTrue(false);
//        }
//
//        assertTrue(copyLength < uniqueLength);
//
//        try{
//            bis = new ByteArrayInputStream(bos.toByteArray());
//            in = new ObjectInputStream(bis);
//            SerialList inList = (SerialList)(in.readObject());
//            assertEquals(inList.size(), 6000);
//            assertEquals(inList.get(0), 5L);
//        }
//        catch(Exception ex){
//            System.err.println(ex.toString());
//            assertTrue(false);
//        }
//    }
//
    private int nChildren(SerialBST.Node n){
        if(n==null)
            return 0;
        return nChildren(n.left) + nChildren(n.right) + 1;
    }

    @Test(timeout=2000) //SerialBST creation
    public void sample6 () {
        sb = new SerialBST();

        Integer[] input = new Integer[]{3, 12, 67, 5, 0, 10, 99, 18, 1};
        for(Integer i: input) {
            sb.add(i, i);
        }
        sb.inOrder();
        assertEquals(sb.root.key, input[0]);
        assertEquals(sb.root.val, input[0]);
        assertEquals(sb.root.right.key, input[1]);
        assertEquals(sb.root.right.val, input[1]);
        assertEquals(sb.root.left.key, input[4]);
        assertEquals(sb.root.left.val, input[4]);
    }

    @Test(timeout=2000) //SerialBST size
    public void sample7 () {
        sb = new SerialBST();

        Integer[] input = new Integer[]{3,12,67,5,0,10,99,18,1};
        for(Integer i: input)
            sb.add(i,i);

        assertEquals(sb.size, input.length);

        assertEquals(nChildren(sb.root), input.length);
    }

    @Test(timeout=2000) //SerialBST inOrder
    @SuppressWarnings("unchecked")
    public void sample8 () {
        sb = new SerialBST();

        Integer[] input = new Integer[]{3,12,67,5,0,10,99,18,1};
        for(Integer i: input)
            sb.add(i,i);

        Object[] result = sb.inOrder();
        assertEquals(result.length, input.length);
        ArrayList<Integer> sortedInput = new ArrayList(Arrays.asList(input));
        Collections.sort(sortedInput);
        for(int i=0; i<result.length; i++){
            assertEquals(sortedInput.get(i), result[i]);
        }
    }

    @Test(timeout=2000) //SerialBST postOrder
    @SuppressWarnings("unchecked")
    public void sample9 () {
        sb = new SerialBST();

        Integer[] input = new Integer[]{3,12,67,5,0,10,99,18,1};
        for(Integer i: input)
            sb.add(i,i);

        Object[] result = sb.postOrder();
        assertEquals(result.length, input.length);
        Integer[] postOrderInput = new Integer[]{1,0,10,5,18,99,67,12,3};
        for(int i=0; i<result.length; i++){
            assertEquals(postOrderInput[i], result[i]);
        }
    }

    @Test(timeout=2000) //SerialBST preOrder
    @SuppressWarnings("unchecked")
    public void sample10 () {
        sb = new SerialBST();

        Integer[] input = new Integer[]{3,12,67,5,0,10,99,18,1};
        for(Integer i: input)
            sb.add(i,i);

        Object[] result = sb.preOrder();
        assertEquals(result.length, input.length);
        Integer[] preOrderInput = new Integer[]{3,0,1,12,5,10,67,18,99};
        for(int i=0; i<result.length; i++){
            assertEquals(preOrderInput[i], result[i]);
        }
    }

    @Test(timeout=2000) //SerialBST find and remove
    public void sample11 () {
        sb = new SerialBST();

        Integer[] input = new Integer[]{3,12,67,5,0,10,99,18,1};
        for(Integer i: input)
            sb.add(i,i);

        sb.find(67);
        try{
            sb.find(100);
            throw new AssertionError("Expected NoSuchElementException.");
        }
        catch(NoSuchElementException ex){
        }

//        sb.printTree(sb.root);
//        sb.remove(67);
//        System.out.println("\n\n\n");
//        sb.printTree(sb.root);
//        sb.remove(3);
//        System.out.println("\n\n\n");
//        sb.printTree(sb.root);
        assertEquals(sb.remove(67), 67);
//        try{
//            sb.find(67);
//            System.out.println("found: "+sb.find(67));
//            throw new AssertionError("Expected NoSuchElementException.");
//        }
//        catch(NoSuchElementException ex){
//        }
    }
//
//    @Test(timeout=2000) //SerialBST balance
//    public void sample12 () {
//        sb = new SerialBST();
//
//        Integer[] input = new Integer[]{3,12,67,5,0,10,99,18,1};
//        for(Integer i: input)
//            sb.add(i,i);
//
//        sb.balance();
//
//        assertEquals(sb.size, input.length);
//
//        assertEquals(nChildren(sb.root.left), nChildren(sb.root.right));
//    }
//
//    @Test(timeout=3000) //SerialBST efficient serialization
//    public void sample13 () {
//        SerialBST sb1 = new SerialBST();
//        SerialBST sb2 = new SerialBST();
//        for(int i=0; i<1000; i++){
//            for(Long x: new Long[] {1L,2L,3L,0L,4L,5L}){
//                sb1.add(x, x);
//                sb2.add(r.nextLong(), x);
//            }
//        }
//
//        int copyLength=0;
//        int uniqueLength = 0;
//        try{
//            out.writeObject(sb1);
//            copyLength = bos.size();
//            out.writeObject(sb2);
//            uniqueLength = bos.size()-copyLength;
//        }
//        catch(Exception ex){
//            System.err.println(ex.toString());
//            assertTrue(false);
//        }
//
//        assertTrue(copyLength < uniqueLength);
//
//        try{
//            bis = new ByteArrayInputStream(bos.toByteArray());
//            in = new ObjectInputStream(bis);
//            SerialBST inBST = (SerialBST)(in.readObject());
//            assertEquals(inBST.size(), 6000);
//            assertEquals(inBST.root.val, 1L);
//        }
//        catch(Exception ex){
//            System.err.println(ex.toString());
//            assertTrue(false);
//        }
//    }

//    @Test(timeout=2000) //SerialPrioQueue adding and order
//    @SuppressWarnings("unchecked")
//    public void sample14 () {
//        spq = new SerialPrioQueue();
//
//        Integer[] input = new Integer[]{3,12,67,5,0,10,99,18,1};
//        for(Integer i: input)
//            spq.add(i,i);
//
//
//        assertEquals(spq.size, input.length);
//
//        assertEquals(spq.head.priority, 99);
//        assertEquals(spq.remove(), 99);
//
//        Comparable prevPrio = new Integer(99);
//        while(spq.size>0){
//            Comparable currPrio = spq.head.priority;
//            assertTrue(prevPrio.compareTo(currPrio)>=0);
//            spq.remove();
//            prevPrio = currPrio;
//        }
//    }

//    @Test(timeout=4000) //SerialPrioQueue preOrder
//    @SuppressWarnings("unchecked")
//    public void sample15 () {
//        spq = new SerialPrioQueue();
//
//        Integer[] input =
//                new Integer[]{3,12,67,5,0,10,99,18,1,12,2,22,30,4,11};
//        for(Integer i: input)
//            spq.add(i,i);
//
//        Object[] result = spq.preOrder();
//
//        assertEquals(result.length, input.length);
//
//        /*for(Object o: result)
//          System.out.println(o);*/
//
//        //check heap property
//        Integer rootLeft = (Integer)result[1];
//        int i;
//        for(i=2; i<=result.length/2; i++){
//            assertTrue(rootLeft.compareTo((Integer)result[i])>=0);
//        }
//
//        Integer rootRight = (Integer)result[i];
//        for(int j=i+1; j<result.length; j++){
//            assertTrue(rootRight.compareTo((Integer)result[j])>=0);
//        }
//    }
//
//    @Test(timeout=3000) //SerialPrioQueue efficient serialization
//    @SuppressWarnings("unchecked")
//    public void sample16 () {
//        SerialPrioQueue spq1 = new SerialPrioQueue();
//        SerialPrioQueue spq2 = new SerialPrioQueue();
//        for(int i=0; i<1000; i++){
//            for(Long x: new Long[] {1L,2L,3L,0L,4L,5L}){
//                spq1.add(x, x);
//                spq2.add(r.nextLong(), x);
//            }
//        }
//
//        int copyLength=0;
//        int uniqueLength = 0;
//        try{
//            out.writeObject(spq1);
//            copyLength = bos.size();
//            out.writeObject(spq2);
//            uniqueLength = bos.size()-copyLength;
//        }
//        catch(Exception ex){
//            System.err.println(ex.toString());
//            assertTrue(false);
//        }
//
//        assertTrue(copyLength < uniqueLength);
//
//        SerialPrioQueue inPrioQueue = null;
//        try{
//            bis = new ByteArrayInputStream(bos.toByteArray());
//            in = new ObjectInputStream(bis);
//            inPrioQueue = (SerialPrioQueue)(in.readObject());
//            assertEquals(inPrioQueue.size(), 6000);
//            assertEquals(inPrioQueue.head.val, 5L);
//        }
//        catch(Exception ex){
//            System.err.println(ex.toString());
//            assertTrue(false);
//        }
//
//        Comparable prevPrio = (Long)inPrioQueue.remove();
//        while(inPrioQueue.size>0){
//            Comparable currPrio = inPrioQueue.head.priority;
//            assertTrue(prevPrio.compareTo(currPrio)>=0);
//            inPrioQueue.remove();
//            prevPrio = currPrio;
//        }
//    }
}