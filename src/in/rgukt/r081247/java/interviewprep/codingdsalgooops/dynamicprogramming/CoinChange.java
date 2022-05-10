package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

import java.util.Arrays;

public class CoinChange {
    /**
     * I spent the last 3 days figuring out different ways to solve this problem along with the mathematical proof for the big O
     * I first went through the recursive approach.
     * next 2D DP
     * and lastly this 1D DP solution.
     * though it took a good lot of time it's worth it
     */

    /**
     * Approach   : Recursive
     * Complexity : Time: ; Space:
     */
    class Solution1 {
        public int minTotalCoins;
        public int[] minTotalCoinsCount;
        public int coinChange(int[] coins, int amount) {
            if(amount == 0 || coins.length == 0)
                return 0;

            minTotalCoins = amount + 1;
            Arrays.sort(coins);
            int[] count = new int[coins.length];
            coinChangeDFS(coins, coins.length - 1, 0, amount, count);
            System.out.println("coins: " + Arrays.toString(coins));
            System.out.println("count: " + Arrays.toString(minTotalCoinsCount));
            return minTotalCoins == amount + 1? -1: minTotalCoins;
        }

        public void coinChangeDFS(int[] coins, int index, int totalCoins, int amount, int[] count) {
            if(index < 0)
                return;

            int newCoinCount = amount/coins[index];
            count[index] = newCoinCount;
            int remainingAmount = amount%coins[index];
            if(remainingAmount == 0) {
                if(totalCoins + newCoinCount < minTotalCoins) {
                    minTotalCoins = totalCoins + newCoinCount;
                    minTotalCoinsCount = count.clone();
                }
                return;
            }

            for(int i=newCoinCount;i>=0;i--) {
                count[index] = i;
                remainingAmount = amount - i * coins[index];
                if(totalCoins+i < minTotalCoins)
                    coinChangeDFS(coins, index-1, totalCoins+i, remainingAmount, count);
            }
        }
    }

    /**
     * Approach   : Dynamic Programming 2 Dimensional
     * Complexity : Time: O(n * amount) ; Space: O(n * amount)
     */
    class Solution2 {
        public int coinChange(int[] coins, int amount) {
            int[][] count = new int[coins.length+1][amount+1];
            int[] coinsCount = new int[coins.length];
            for(int i=0;i<count.length;i++) {
                Arrays.fill(count[i], amount + 1);
                if(i != 0)
                    count[i][0] = 0;
            }

            for(int i=1;i<=coins.length;i++) {
                for(int j=1;j<=amount;j++) {
                    if(count[i-1][j] < count[i][j])
                        count[i][j] = count[i-1][j];
                    if(coins[i-1] <= j) {
                        if(count[i][j-coins[i-1]] + 1 < count[i][j])
                            count[i][j] = count[i][j-coins[i-1]] + 1;
                    }
                }
            }

            if(count[coins.length][amount] != amount + 1) {
                int i=coins.length;
                int j=amount;
                while (j != 0) {
                    if(count[i][j] == count[i-1][j]) {
                        i--;
                    } else {
                        coinsCount[i-1]++;
                        j = j - coins[i-1];
                    }
                }
            }

            System.out.println("coins: " + Arrays.toString(coins));
            System.out.println("coinsCount: " + Arrays.toString(coinsCount));

            if(count[coins.length][amount] == amount + 1)
                return -1;
            else
                return count[coins.length][amount];
        }
    }

    /**
     * Approach   : Dynamic Programming 1 Dimensional
     * Complexity : Time: O(n*amount) ; Space: O(amount)
     */
    class Solution3 {
        public int coinChange(int[] coins, int amount) {
            int[] count = new int[amount+1];
            Arrays.fill(count, amount + 1);
            count[0] = 0;
            int[] coinsCount = new int[coins.length];

            for(int i=1;i<=amount;i++) {
                for(int j=0;j<coins.length;j++) {
                    if(coins[j] <= i) {
                        count[i] = Math.min(count[i], count[i-coins[j]]+1);
                    }
                }
            }

            if(count[amount] != amount+1) {
                int i = amount;
                while(i > 0) {
                    for(int j=0;j<coins.length;j++) {
                        if(i-coins[j] >= 0 && count[i] > count[i-coins[j]]) {
                            coinsCount[j]++;
                            i = i-coins[j];
                            break;
                        }
                    }
                }
            }

            System.out.println("coins: " + Arrays.toString(coins));
            System.out.println("coinsCount: " + Arrays.toString(coinsCount));

            if(count[amount] > amount)
                return -1;
            else
                return count[amount];
        }

    }

    public static int minTotalCoins;
    public static int[] minTotalCoinsCount;
    public static int coinChange(int[] coins, int amount) {
        if(amount == 0 || coins.length == 0)
            return 0;

        minTotalCoins = amount + 1;
        Arrays.sort(coins);
        int[] count = new int[coins.length];
        coinChangeDFS(coins, coins.length - 1, 0, amount, count);
        System.out.println("coins: " + Arrays.toString(coins));
        System.out.println("count: " + Arrays.toString(minTotalCoinsCount));
        return minTotalCoins == amount + 1? -1: minTotalCoins;
    }

    public static void coinChangeDFS(int[] coins, int index, int totalCoins, int amount, int[] count) {
        if(index < 0)
            return;

        int newCoinCount = amount/coins[index];
        count[index] = newCoinCount;
        int remainingAmount = amount%coins[index];
        if(remainingAmount == 0) {
            if(totalCoins + newCoinCount < minTotalCoins) {
                minTotalCoins = totalCoins + newCoinCount;
                minTotalCoinsCount = count.clone();
            }
            return;
        }

        for(int i=newCoinCount;i>=0;i--) {
            count[index] = i;
            remainingAmount = amount - i * coins[index];
            if(totalCoins+i < minTotalCoins)
                coinChangeDFS(coins, index-1, totalCoins+i, remainingAmount, count);
        }
    }

    public static int coinChangeDynamicProgramming2D(int[] coins, int amount) {
        int[][] count = new int[coins.length+1][amount+1];
        int[] coinsCount = new int[coins.length];
        for(int i=0;i<count.length;i++) {
            Arrays.fill(count[i], amount + 1);
            if(i != 0)
                count[i][0] = 0;
        }

        for(int i=1;i<=coins.length;i++) {
            for(int j=1;j<=amount;j++) {
                if(count[i-1][j] < count[i][j])
                    count[i][j] = count[i-1][j];
                if(coins[i-1] <= j) {
                    if(count[i][j-coins[i-1]] + 1 < count[i][j])
                        count[i][j] = count[i][j-coins[i-1]] + 1;
                }
            }
        }

        if(count[coins.length][amount] != amount + 1) {
            int i=coins.length;
            int j=amount;
            while (j != 0) {
                if(count[i][j] == count[i-1][j]) {
                    i--;
                } else {
                    coinsCount[i-1]++;
                    j = j - coins[i-1];
                }
            }
        }

        System.out.println("coins: " + Arrays.toString(coins));
        System.out.println("coinsCount: " + Arrays.toString(coinsCount));

        if(count[coins.length][amount] == amount + 1)
            return -1;
        else
            return count[coins.length][amount];
    }

    public static int coinChangeDynamicProgramming1D(int[] coins, int amount) {
        int[] count = new int[amount+1];
        int[] coinsCount = new int[coins.length];
        Arrays.fill(count, amount+1);
        count[0] = 0;

        for(int i=1;i<=amount;i++) {
            for(int j=0;j<coins.length;j++) {
                if(coins[j] <= i && (count[i-coins[j]] + 1) < count[i]) {
                    count[i] = count[i-coins[j]] + 1;
                }
            }
        }

        if(count[amount] != amount+1) {
            int i = amount;
            while(i > 0) {
                for(int j=0;j<coins.length;j++) {
                    if(i-coins[j] >= 0 && count[i] > count[i-coins[j]]) {
                        coinsCount[j]++;
                        i = i-coins[j];
                        break;
                    }
                }
            }
        }

        System.out.println("coins: " + Arrays.toString(coins));
        System.out.println("coinsCount: " + Arrays.toString(coinsCount));

        if(count[amount] == amount+1)
            return -1;
        else
            return count[amount];

    }

    public static void main(String[] args) {
        int[] coins = new int[] {186,419,83,408};
        int amount = 6249;

        System.out.println("Total Coins: " + coinChange(coins, amount));
        System.out.println("Total Coins: " + coinChangeDynamicProgramming2D(coins, amount));
        System.out.println("Total Coins: " + coinChangeDynamicProgramming1D(coins, amount));
    }
}
