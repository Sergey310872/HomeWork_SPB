package HomeWork_7.Task_7_1;

import java.util.Date;

public class Main {
//    static ThreadPong threadPong;

    public static void main(String[] args) {
        System.out.println("Игра начинается:");

        long timeStart = new Date().getTime();
        long timeFinish = timeStart + 10000;

        new ThreadPong("Пинг", timeFinish).start();
        new ThreadPong("       Понг", timeFinish).start();
    }
}

class ThreadPong extends Thread {
    static String currentDeal = "       Понг";//new String();
    private String deal;
    private long timeEnd;

    ThreadPong(String d, long t) {
        this.deal = d;
        this.timeEnd = t;
    }

    @Override
    public void run() {
        try {
            while (timeEnd > new Date().getTime()) {
                if ((!currentDeal.equals(deal))) {
                    System.out.println(deal);
                    currentDeal = deal;
                    this.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
