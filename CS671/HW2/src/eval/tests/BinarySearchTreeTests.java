package eval.tests;

import eval.SerialBST;

public class BinarySearchTreeTests {

    SerialBST sb = new SerialBST();

    public void basicInsertTest() {
        Integer[] input = new Integer[]{3, 12, 67, 5, 0, 10, 99, 18, 1};
        for (Integer i : input)
            sb.add(i, i);
    }


    public void main(){
        basicInsertTest();
    }
}
