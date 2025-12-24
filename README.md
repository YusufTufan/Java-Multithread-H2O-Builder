# Concurrent H2O Molecule Builder 💧

![Java](https://img.shields.io/badge/Language-Java-orange)
![Concurrency](https://img.shields.io/badge/Topic-Multithreading-blue)
![Status](https://img.shields.io/badge/Status-Completed-success)

## 📖 Overview

This project provides a robust solution to the classic **Building H₂O** synchronization problem. It simulates the creation of water molecules by coordinating independent Hydrogen and Oxygen threads using advanced Java concurrency mechanisms.

The system ensures that threads (atoms) bond only when the strict chemical rule of **2 Hydrogen + 1 Oxygen** is met, effectively managing race conditions, preventing deadlocks, and avoiding thread starvation

## 🚀 Key Features

* **Thread Safety:** Guarantees correct molecule formation ($H_2O$) regardless of the thread arrival speed.
* **Deadlock Prevention:** Uses carefully ordered semaphore acquisitions.
* **Efficient Resource Management:** Utilizes `wait()` and `notify()` mechanisms (via Semaphores) to avoid busy-waiting and reduce CPU load.
* **Atomicity:** Ensures the bonding process is atomic using a `CyclicBarrier`.

## 🛠️ Technical Architecture

The core logic is encapsulated within the `H2OBarrier` class, which orchestrates the threads using the following primitives:

1. **Mutex (Locks):** Protects shared resources (`hCount`, `oCount`) to ensure mutual exclusion during state updates.
2. **Semaphores:** Manages the queue of waiting atoms.Threads are blocked (`acquire`) until the correct combination (2H, 1O) is present, then released (`release`).
3. **CyclicBarrier:** Synchronization point that ensures all 3 threads (2H + 1O) complete the bonding process together before any new threads can proceed.

### Code Structure

* `H2OProblem.java`: The main entry point that initializes the barrier and starts the atom threads.
* `H2OBarrier.java`: The central control monitor containing `hydrogen()` and `oxygen()` logic.
* `HydrogenThread.java`: Represents the Hydrogen atom lifecycle.
* `OxygenThread.java`: Represents the Oxygen atom lifecycle.

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

```


## 👥 Contributors

| Name | Role |
|Data|------|
| **Yusuf Tufan** | Developer & Reporter |
| **Halil Arin Degirmenci** | Developer & Reporter |
| **Furkan Urkmez** | Developer & Reporter |
| **Selcuk Ozkurt** | Developer & Reporter |
