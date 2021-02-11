package in.rgukt.r081247.java.util.practice;

import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) throws Exception {
        /*
        String state = switch (State.WAITING) {
            case CREATED -> {
                System.out.println("in Created");
                yield "Created";
            }
            case RUNNING -> {
                System.out.println("in Running");
                yield "Running";
            }
            case WAITING -> {
                System.out.println("in Waiting");
                yield "Waiting";
            }
            case COMPLETED -> {
                System.out.println("in Completed");
                yield "Completed";
            }
            default -> {
                System.out.println("in Default");
                yield "Default";
            }
        };

        System.out.println(state);
         */

        String json = """
                {
                  "id": 2,
                  "name": "Vinod Parlapalli"
                }
                """;
        System.out.println(json);
    }

    public static enum State {
        CREATED, RUNNING, WAITING, COMPLETED
    }



}