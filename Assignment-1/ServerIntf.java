import java.rmi.*;

public interface ServerIntf extends Remote {
    String stringJoin(String str1, String str2) throws RemoteException;
    int add(int a, int b) throws RemoteException;
    int subtract(int a, int b) throws RemoteException;
    int multiply(int a, int b) throws RemoteException;
    double divide(int a, int b) throws RemoteException;
}

