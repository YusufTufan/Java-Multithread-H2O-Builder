import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CyclicBarrier;

class H2OBarrier {
    private final Lock mutex;
    private int hydrogenCount;
    private int oxygenCount;
    private final Semaphore hydrogenQueue;
    private final Semaphore oxygenQueue;
    private final CyclicBarrier barrier;

    public H2OBarrier() {
        this.mutex = new ReentrantLock();
        this.hydrogenCount = 0;
        this.oxygenCount = 0;
        this.hydrogenQueue = new Semaphore(0);
        this.oxygenQueue = new Semaphore(0);
        this.barrier = new CyclicBarrier(3, () -> {
            System.out.println(">>> Bir H2O molekülü oluşturuldu! <<<\n");
        });
    }

    public void hydrogen() throws InterruptedException {
        mutex.lock();
        hydrogenCount++;

        if (hydrogenCount >= 2 && oxygenCount >= 1) {
            // Bir molekül tamamlanabilir
            hydrogenQueue.release(2);
            hydrogenCount -= 2;

            oxygenQueue.release();
            oxygenCount -= 1;

            mutex.unlock();
        } else {
            mutex.unlock();
        }

        hydrogenQueue.acquire(); // Bu H thread'i bekler
        bond("H");

        try {
            barrier.await(); // 3 atom burada senkron bağlanır
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void oxygen() throws InterruptedException {
        mutex.lock();
        oxygenCount++;

        if (hydrogenCount >= 2 && oxygenCount >= 1) {
            hydrogenQueue.release(2);
            hydrogenCount -= 2;

            oxygenQueue.release();
            oxygenCount -= 1;

            mutex.unlock();
        } else {
            mutex.unlock();
        }

        oxygenQueue.acquire();
        bond("O");

        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void bond(String atom) {
        System.out.println(Thread.currentThread().getName() +
                " (" + atom + ") moleküle katıldı.");
    }
}

class HydrogenThread extends Thread {
    private final H2OBarrier barrier;

    public HydrogenThread(H2OBarrier barrier, int id) {
        super("H-" + id);
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 100));
            barrier.hydrogen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class OxygenThread extends Thread {
    private final H2OBarrier barrier;

    public OxygenThread(H2OBarrier barrier, int id) {
        super("O-" + id);
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 100));
            barrier.oxygen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class H2OProblem {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Building H2O Problem Çözümü ===\n");

        H2OBarrier barrier = new H2OBarrier();

        // 6 Hidrojen – 3 Oksijen → 3 molekül oluşturur
        for (int i = 1; i <= 6; i++)
            new HydrogenThread(barrier, i).start();

        for (int i = 1; i <= 3; i++)
            new OxygenThread(barrier, i).start();

        Thread.sleep(2000);
        System.out.println("\n=== Test tamamlandı ===");
    }
}
