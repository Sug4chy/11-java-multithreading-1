package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static final AtomicInteger x = new AtomicInteger(0);
    static final AtomicInteger count = new AtomicInteger(0);

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (x.get() < 999999) {
                int i  = x.incrementAndGet();
                if ((i % 10) + (i / 10) % 10 + (i / 100) % 10 == (i / 1000)
                        % 10 + (i / 10000) % 10 + (i / 100000) % 10) {
                    System.out.println(x);
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