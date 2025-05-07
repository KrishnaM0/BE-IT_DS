import java.util.*;

public class Bully {
    int numProcesses;
    boolean[] isAlive;
    int coordinator;

    public Bully(int n) {
        numProcesses = n;
        isAlive = new boolean[n];
        Arrays.fill(isAlive, true);
        coordinator = n; // Highest ID initially
        System.out.println("All processes are UP. Coordinator is P" + coordinator);
    }

    void displayStatus() {
        for (int i = 0; i < numProcesses; i++) {
            System.out.println("P" + (i + 1) + " is " + (isAlive[i] ? "UP" : "DOWN"));
        }
        System.out.println("Current Coordinator: P" + coordinator);
    }

    void bringUp(int pid) {
        if (isAlive[pid - 1]) {
            System.out.println("P" + pid + " is already UP.");
        } else {
            isAlive[pid - 1] = true;
            System.out.println("P" + pid + " is now UP.");
        }
    }

    void bringDown(int pid) {
        if (!isAlive[pid - 1]) {
            System.out.println("P" + pid + " is already DOWN.");
        } else {
            isAlive[pid - 1] = false;
            System.out.println("P" + pid + " is now DOWN.");
        }
    }

    void startElection(int pid) {
        if (!isAlive[pid - 1]) {
            System.out.println("P" + pid + " is DOWN. Cannot start election.");
            return;
        }

        System.out.println("P" + pid + " starts election.");
        int newCoordinator = pid;

        // Send messages to all higher processes
        for (int i = pid; i < numProcesses; i++) {
            System.out.println("P" + newCoordinator + " sends message to P" + (i + 1));
            if (isAlive[i]) {
                newCoordinator = i + 1;
            }
        }

        coordinator = newCoordinator;
        System.out.println("P" + coordinator + " becomes the new Coordinator.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bully bully = null;

        while (true) {
            System.out.println("\nBully Algorithm Menu");
            System.out.println("1. Create Processes");
            System.out.println("2. Display Status");
            System.out.println("3. Bring UP a Process");
            System.out.println("4. Bring DOWN a Process");
            System.out.println("5. Start Election");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter number of processes: ");
                    int n = sc.nextInt();
                    bully = new Bully(n);
                    break;

                case 2:
                    if (bully != null) bully.displayStatus();
                    else System.out.println("Please create processes first.");
                    break;

                case 3:
                    if (bully != null) {
                        System.out.print("Enter process number to bring UP: ");
                        int pid = sc.nextInt();
                        bully.bringUp(pid);
                    } else System.out.println("Please create processes first.");
                    break;

                case 4:
                    if (bully != null) {
                        System.out.print("Enter process number to bring DOWN: ");
                        int pid = sc.nextInt();
                        bully.bringDown(pid);
                    } else System.out.println("Please create processes first.");
                    break;

                case 5:
                    if (bully != null) {
                        System.out.print("Enter process number to start election: ");
                        int pid = sc.nextInt();
                        bully.startElection(pid);
                    } else System.out.println("Please create processes first.");
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

