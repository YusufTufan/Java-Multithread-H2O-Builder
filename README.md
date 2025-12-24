# Concurrent H2O Molecule Builder 💧

![Java](https://img.shields.io/badge/Language-Java-orange)
![Concurrency](https://img.shields.io/badge/Topic-Multithreading-blue)
![Status](https://img.shields.io/badge/Status-Completed-success)

## 📖 Overview

This project provides a robust solution to the classic **Building H₂O** synchronization problem. It simulates the creation of water molecules by coordinating independent Hydrogen and Oxygen threads using advanced Java concurrency mechanisms.

[cite_start]The system ensures that threads (atoms) bond only when the strict chemical rule of **2 Hydrogen + 1 Oxygen** is met, effectively managing race conditions, preventing deadlocks, and avoiding thread starvation[cite: 11, 43, 44].

## 🚀 Key Features

* **Thread Safety:** Guarantees correct molecule formation ($H_2O$) regardless of the thread arrival speed.
* **Deadlock Prevention:** Uses carefully ordered semaphore acquisitions.
* [cite_start]**Efficient Resource Management:** Utilizes `wait()` and `notify()` mechanisms (via Semaphores) to avoid busy-waiting and reduce CPU load[cite: 73].
* [cite_start]**Atomicity:** Ensures the bonding process is atomic using a `CyclicBarrier`[cite: 164].

## 🛠️ Technical Architecture

The core logic is encapsulated within the `H2OBarrier` class, which orchestrates the threads using the following primitives:

1.  [cite_start]**Mutex (Locks):** Protects shared resources (`hCount`, `oCount`) to ensure mutual exclusion during state updates[cite: 158].
2.  **Semaphores:** Manages the queue of waiting atoms. [cite_start]Threads are blocked (`acquire`) until the correct combination (2H, 1O) is present, then released (`release`)[cite: 161, 162].
3.  [cite_start]**CyclicBarrier:** Synchronization point that ensures all 3 threads (2H + 1O) complete the bonding process together before any new threads can proceed[cite: 164].

### Code Structure

* [cite_start]`H2OProblem.java`: The main entry point that initializes the barrier and starts the atom threads[cite: 136].
* [cite_start]`H2OBarrier.java`: The central control monitor containing `hydrogen()` and `oxygen()` logic[cite: 91].
* [cite_start]`HydrogenThread.java`: Represents the Hydrogen atom lifecycle[cite: 117].
* [cite_start]`OxygenThread.java`: Represents the Oxygen atom lifecycle[cite: 98].

## 💻 How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/YOUR_USERNAME/Concurrent-H2O-Simulation.git](https://github.com/YOUR_USERNAME/Concurrent-H2O-Simulation.git)
    cd Concurrent-H2O-Simulation
    ```

2.  **Compile the Java files:**
    ```bash
    javac *.java
    ```

3.  **Run the simulation:**
    ```bash
    java H2OProblem
    ```

## 📊 Sample Output

The simulation generates an output demonstrating the synchronized bonding process:

```text
x:=== Building H2O-Problem- ===:x

H-1 is waiting...
H-2 is waiting...
O-1 is waiting...
--- Molecule Created! ---
--

```text
👥 Contributors
Yusuf Tufan

Halil Arin Degirmenci

Furkan Urkmez

Selcuk Ozkurt
