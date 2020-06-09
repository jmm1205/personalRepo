package eval;

public class EvalTask extends java.lang.Object implements java.io.Serializable {

    public Object[][] args;
    String[] methods;
    Object target;

    public EvalTask(Object t, String[] m, Object[][] a){
        target = t;
        methods = m;
        args = a;
    }
}
