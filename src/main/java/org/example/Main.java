package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static final AtomicInteger x = new AtomicInteger(0);
    static final AtomicInteger count = new AtomicInteger(0);

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (x.intValue() < 999999) {
                int intValue = x.incrementAndGet();
                if ((intValue % 10) + (intValue / 10) % 10 + (intValue / 100) % 10 == (intValue / 1000)
                        % 10 + (intValue / 10000) % 10 + (intValue / 100000) % 10) {
                    System.out.println(intValue);
                    count.incrementAndGet();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}