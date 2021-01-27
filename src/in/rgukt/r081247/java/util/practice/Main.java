package in.rgukt.r081247.java.util.practice;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        ResourceA ra = new ResourceA();
        ResourceB rb = new ResourceB();
        ra.setRb(rb);
        rb.setRa(ra);

        Thread ta = new Thread(() -> {
            ra.getResourceA();
        }, "Thread A");
        Thread tb = new Thread(() -> {
            rb.getResourceB();
        }, "Thread B");

        ta.start();
        try {
            Thread.sleep(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        tb.start();

        ta.join();
        tb.join();

    }

    public static class ResourceA {
        private ResourceB rb;

        public ResourceB getRb() {
            return rb;
        }

        public void setRb(ResourceB rb) {
            this.rb = rb;
        }

        public synchronized void getResourceA() {
            System.out.println("In ResourceA.getResourceA() from " + Thread.currentThread().getName());
            /*
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
            */

            System.out.println("Calling ResourceB.getResourceA() from " + Thread.currentThread().getName());
            rb.getResourceA();
        }

        public synchronized  void getResourceB() {
            System.out.println("In ResourceA.getResourceB() from " + Thread.currentThread().getName());
        }
    }

    public static class ResourceB {
        private ResourceA ra;

        public ResourceA getRa() {
            return ra;
        }

        public void setRa(ResourceA ra) {
            this.ra = ra;
        }

        public synchronized void getResourceB() {
            System.out.println("In ResourceB.getResourceB() from " + Thread.currentThread().getName());
            /*
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
            */

            System.out.println("Calling ResourceA.getResourceB() from " + Thread.currentThread().getName());
            ra.getResourceB();
        }

        public synchronized  void getResourceA() {
            System.out.println("In ResourceB.getResourceA() from " + Thread.currentThread().getName());
        }
    }
}