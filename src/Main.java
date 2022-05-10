import in.rgukt.r081247.java.datastructure.list.Dequeue;
import in.rgukt.r081247.java.interviewprep.codingdsalgooops.heap.MergeKSortedLists;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
    011 3
    010 2
    001 1
    000 0
    111 -1
    110 -2
    101 -3
    100 -4
 */

public class Main {
    public static int coinChangeRecursive(int[] coins, int amount, int coinsCount) {
        if(amount == 0)
            return coinsCount;
        int minCoins = Integer.MAX_VALUE;
        for(int coin: coins) {
            if(amount-coin >= 0) {
                int tempCoinsCount = coinChangeRecursive(coins, amount-coin, coinsCount+1);
                if(tempCoinsCount < minCoins)
                    minCoins = tempCoinsCount;
            }
        }
        return minCoins;
    }
    public static int coinChange(int[] coins, int amount) {
        if(amount == 0 || coins.length == 0)
            return 0;
        int minCoins = coinChangeRecursive(coins, amount, 0);
        if(minCoins == Integer.MAX_VALUE)
            return -1;
        return minCoins;
    }


    public static void main(String[] args) {
        int[] coins = new int[]{1, 3, 4, 5};
        int amount = 7;
        int coinCount = coinChange(coins, amount);
        System.out.println("count: " + coinCount);
    }
}
