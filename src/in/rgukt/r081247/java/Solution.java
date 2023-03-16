package in.rgukt.r081247.java;

import java.io.IOException;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Solution {
    public static void main(String[] args) {
        Set<String> s = new HashSet<>();
        s.add(null);
        s.add(null);
        System.out.println(s);
        s.remove(null);
        s.remove(null);
        System.out.println(s);
        Lock lock = new ReentrantLock();
        Condition c = lock.newCondition();
        c.signalAll();


    }
}