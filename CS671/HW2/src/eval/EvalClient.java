package eval;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class EvalClient extends java.lang.Object implements java.lang.Runnable{
    public String address;
    public Socket connection;
    public ObjectInputStream in;
    public ObjectOutputStream out;
    public int port;

    public EvalClient(String a, int p){
        port = p;
        address = a;
    }

    public void initialize(){
        return;
    }

    public Object doTask(Class c, Object target, String name, Object[] args){
        return null;
    }

    public boolean checkMethod(String name, Method m, Object[] args){
        return false;
    }

    public void run(){
        return;
    }

    public EvalTask getWork(){
        return null;
    }

    public boolean checkWork(){
        return false;
    }

    public static void main(String[] args){
        return;
    }

}
