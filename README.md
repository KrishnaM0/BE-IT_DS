# 🖥️ Process Election Algorithms - Bully & Ring Algorithm

## 📌 Objective:
To understand and implement **process election algorithms** (Bully and Ring) used in distributed systems to elect a **coordinator** (leader process) among a group of processes.

---

## 🧠 What is a Process Election Algorithm?

In distributed systems, there’s no central control. When the current **coordinator (leader process)** fails (goes DOWN), an election is held to select a new coordinator. These algorithms help in:
- Ensuring reliability
- Maintaining synchronization
- Selecting the highest priority process as coordinator

---

## 🔀 Types Implemented in This Project

### 1. 👊 Bully Algorithm
- Every process has a unique ID.
- A process starts an election if it notices that the coordinator is DOWN.
- It sends messages to all higher-ID processes.
- If none responds, it becomes the new coordinator.
- If a higher process is alive, it takes over the election and “bullies” the rest.

**Time Complexity:** O(n²) in worst case.

---

### 2. 🔁 Ring Algorithm
- All processes are arranged in a logical **ring**.
- Each process can communicate only with its **next neighbor**.
- A process sends election messages in a circular way.
- All alive processes pass the message and add their ID.
- The process with the **highest ID** becomes the new coordinator.

**Time Complexity:** O(n)

---

## 📥 How to Run

1. Compile the Java files:
   ```bash
   javac Bully.java
   javac Ring.java
   ```

2. Run the program:
   ```bash
   java Bully
   java Ring
   ```

3. Follow the menu-driven interface to simulate elections.

---

## 📷 Sample Output

```
Bully Algorithm Menu
1. Create Processes
2. Display Status
3. UP a Process
4. DOWN a Process
5. Start Election
6. Exit

Enter choice: 1
Enter number of processes: 5
All processes are created and are UP. Coordinator is P5

Enter choice: 4
Enter process to DOWN: 5
P5 is now DOWN.

Enter choice: 5
Enter process to start election: 2
P2 starts election.
P2 sends message to P3
P3 sends message to P4
P4 sends message to P5
P2 receives no response from higher processes.
P4 becomes the new Coordinator.
```

---

## 📘 Viva/Practical Questions and Answers

### 🔁 General Questions

**Q1: What is the purpose of an election algorithm?**  
A: To elect a new coordinator when the current one fails in a distributed system.

**Q2: What are the two algorithms you implemented?**  
A: Bully Algorithm and Ring Algorithm.

---

### 👊 Bully Algorithm Viva

**Q3: Why is it called the Bully Algorithm?**  
A: Because the highest-ID process forces (bullies) others to make itself the coordinator.

**Q4: What happens when the coordinator fails?**  
A: Any alive process detects failure and starts the election.

**Q5: What is the main disadvantage?**  
A: It generates a large number of messages (O(n²)).

---

### 🔁 Ring Algorithm Viva

**Q6: How does the Ring Algorithm work?**  
A: A process sends election message around the ring. The highest ID in the ring becomes the coordinator.

**Q7: What if the starting process is not the highest ID?**  
A: The message goes around the ring and the process with the highest ID becomes the new coordinator.

**Q8: How is the ring maintained?**  
A: Logically, each process knows the next one. We simulate this in code using modular arithmetic.

---

### 🔄 Comparison Questions

| Feature             | Bully Algorithm                   | Ring Algorithm                      |
|---------------------|------------------------------------|--------------------------------------|
| Communication       | All-to-all                         | One-to-one (ring)                    |
| Time Complexity     | O(n²)                              | O(n)                                 |
| Simplicity          | Simple but heavy in traffic        | Simple and fewer messages            |
| Coordinator         | Highest ID                         | Highest ID                           |

---

## ❓ More Questions for Practice

**Q9: What happens if two processes start election at the same time?**  
A: Both elections run, and the process with the highest ID wins in both algorithms.

**Q10: Can the Ring Algorithm run if processes are down?**  
A: Yes, it skips dead processes and continues the ring until it gets back to the initiator.

**Q11: Can the Bully Algorithm work if all higher-ID processes are down?**  
A: Yes, the current process becomes the coordinator.

**Q12: What do we do if the current coordinator is back UP?**  
A: Depending on design, it may reinitiate election or stay as a normal process.

**Q13: Which algorithm is better for large systems?**  
A: Ring Algorithm, because it has fewer messages.

**Q14: What do we mean by "coordinator"?**  
A: The coordinator is the leader process responsible for managing distributed operations.

---

## ✅ Conclusion

This project helps you understand the core concepts of process coordination and leader election in distributed systems. By simulating both **Bully** and **Ring** algorithms, we explore different strategies, advantages, and trade-offs in distributed leader election.

---

## 👨‍💻 Developed Using
- Java (Basic OOP & Arrays)
- Menu-driven CLI structure
- Scanner for user input

---

## 📂 Files
- `Bully.java`: Code for Bully Algorithm
- `Ring.java`: Code for Ring Algorithm
- `README.md`: Explanation and Viva Questions

---

## 🙌 Contributions & Usage

Feel free to use this code for:
- Academic practicals
- Distributed system simulations
- Learning and understanding how coordinator election works