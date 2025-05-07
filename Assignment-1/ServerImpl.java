import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf {

    public ServerImpl() throws RemoteException {
        super();
    }

    public String stringJoin(String str1, String str2) throws RemoteException {
        return str1 + str2;
    }

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }

    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }

    public double divide(int a, int b) throws RemoteException {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero.");
        return (double) a / b;
    }
}

