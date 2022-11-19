package HomeWork_7.Task_7_2;


import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    static String currentItem = new String();
    static ThreadAgg threadAgg;
    static int choice;
    static long timeFinish;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (choice != 1 && choice != 2) {
            System.out.print("Курица - Яйцо\n Введите значение (1 - курица, 2 - яйцо): ");
            choice = in.nextInt();
        }
        System.out.println("Игра начинается:");
        timeFinish = new Date().getTime() + 500;

        Semaphore sem = new Semaphore(1); // 1 разрешение
        ThreadAgg tAgg1 = new ThreadAgg("Курица", sem);
        tAgg1.setPriority(Thread.MAX_PRIORITY);
        tAgg1.start();
        ThreadAgg tAgg2 = new ThreadAgg("       Яйцо", sem);
        tAgg2.setPriority(Thread.MIN_PRIORITY);
        tAgg2.start();

        while ((tAgg1.isAlive()) || (tAgg2.isAlive())) {
        }
        String lastItem = (Main.choice == 1) ? "Курица" : "       Яйцо";
        if (!lastItem.equals(currentItem)) {
            System.out.println(lastItem);
        }
    }
}

class ThreadAgg extends Thread {
    private String item;
    Semaphore sem;

    ThreadAgg(String it, Semaphore sem) {
        this.item = it;
        this.sem = sem;
    }

    @Override
    public void run() {
        while (Main.timeFinish > new Date().getTime()) {
            try {
                sem.acquire();
                if ((!Main.currentItem.equals(item))) {
                    Main.currentItem = item;
                    System.out.println(Main.currentItem);
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            sem.release();
        }
    }
}
