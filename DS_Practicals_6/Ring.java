import java.util.*;

public class Ring {
    int numProcesses;
    boolean[] isAlive;
    int currentCoordinator;

    // Constructor: Initializes the number of processes and sets them as UP
    public Ring(int numProcesses) {
        this.numProcesses = numProcesses;
        isAlive = new boolean[numProcesses];
        Arrays.fill(isAlive, true);
        currentCoordinator = numProcesses; // Assume last process is the coordinator initially
        System.out.println("All processes are created and are UP. Initial Coordinator is P" + currentCoordinator);
    }

    // Display the status of all processes
    void displayStatus() {
        for (int i = 0; i < numProcesses; i++) {
            System.out.println("P" + (i + 1) + " is " + (isAlive[i] ? "UP" : "DOWN"));
        }
        System.out.println("Current Coordinator: P" + currentCoordinator);
    }

    // Bring a process UP
    void bringUpProcess(int pid) {
        if (isAlive[pid - 1]) {
            System.out.println("P" + pid + " is already UP.");
        } else {
            isAlive[pid - 1] = true;
            System.out.println("P" + pid + " is now UP.");
        }
    }

    // Bring a process DOWN
    void bringDownProcess(int pid) {
        if (!isAlive[pid - 1]) {
            System.out.println("P" + pid + " is already DOWN.");
        } else {
            isAlive[pid - 1] = false;
            System.out.println("P" + pid + " is now DOWN.");
        }
    }

    // Start the election process from the given process ID
    void startElection(int pid) {
        if (!isAlive[pid - 1]) {
            System.out.println("P" + pid + " is DOWN. Cannot start election.");
            return;
        }

        System.out.println("P" + pid + " starts election.");

        List<Integer> candidates = new ArrayList<>();
        int current = pid - 1; // Adjusting for 0-based index

        // Loop through the ring and collect candidates
        while (true) {
            current = (current + 1) % numProcesses; // Move clockwise in the ring

            if (isAlive[current]) {
                System.out.println("P" + ((current - 1 + numProcesses) % numProcesses + 1) + " sends message to P" + (current + 1));
                candidates.add(current + 1);
            }

            // Stop once the process has looped back to the starting point
            if (current == (pid - 1)) {
                break;
            }
        }

        // Determine the new coordinator (the process with the highest ID)
        int newCoordinator = Collections.max(candidates);
        currentCoordinator = newCoordinator;
        System.out.println("P" + currentCoordinator + " becomes the new Coordinator.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ring ring = null;
        int choice, numProcesses, pid;

        while (true) {
            System.out.println("\nRing Algorithm Menu");
            System.out.println("1. Create Processes");
            System.out.println("2. Display Status");
            System.out.println("3. Bring UP a Process");
            System.out.println("4. Bring DOWN a Process");
            System.out.println("5. Start Election");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter number of processes: ");
                    numProcesses = sc.nextInt();
                    ring = new Ring(numProcesses);
                    break;
                case 2:
                    if (ring != null) {
                        ring.displayStatus();
                    } else {
                        System.out.println("Please create processes first.");
                    }
                    break;
                case 3:
                    if (ring != null) {
                        System.out.print("Enter process to bring UP: ");
                        pid = sc.nextInt();
                        ring.bringUpProcess(pid);
                    } else {
                        System.out.println("Please create processes first.");
                    }
                    break;
                case 4:
                    if (ring != null) {
                        System.out.print("Enter process to bring DOWN: ");
                        pid = sc.nextInt();
                        ring.bringDownProcess(pid);
                    } else {
                        System.out.println("Please create processes first.");
                    }
                    break;
                case 5:
                    if (ring != null) {
                        System.out.print("Enter process to start election: ");
                        pid = sc.nextInt();
                        ring.startElection(pid);
                    } else {
                        System.out.println("Please create processes first.");
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
