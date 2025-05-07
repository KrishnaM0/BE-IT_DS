import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String serverURL = "rmi://localhost/Server";
            ServerIntf server = (ServerIntf) Naming.lookup(serverURL);

            // String join
            System.out.print("Enter First String: ");
            String str1 = sc.nextLine();
            System.out.print("Enter Second String: ");
            String str2 = sc.nextLine();
            System.out.println("Strings After Joining: " + server.stringJoin(str1, str2));

            // Arithmetic operations
            System.out.print("Enter First Number: ");
            int a = sc.nextInt();
            System.out.print("Enter Second Number: ");
            int b = sc.nextInt();

            System.out.println("Addition: " + server.add(a, b));
            System.out.println("Subtraction: " + server.subtract(a, b));
            System.out.println("Multiplication: " + server.multiply(a, b));
            try {
                System.out.println("Division: " + server.divide(a, b));
            } catch (ArithmeticException e) {
                System.out.println("Division Error: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Exception Occurred At Client! " + e.getMessage());
        }
    }
}

