package in.rgukt.r081247.java.coding.array;

class BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        /*
        int max = Integer.MIN_VALUE;
        for (int i=0;i<prices.length-1;i++) {
            for (int j=i+1;j<prices.length;j++) {
                if ((prices[j] - prices[i]) > max) {
                    max = prices[j] - prices[i];
                }
            }
        }
        if (max > 0)
            return max;
        return 0;
        */

        /*
        int max = Integer.MIN_VALUE;
        int[] sum = new int[prices.length];
        int[] prev = new int[prices.length];

        for (int i=1;i<prices.length;i++) {
            int diff = prices[i] - prices[i-1];
            if (prev[i-1] == 0) {
                sum[i] = diff;
            } else {
                sum[i] = diff + sum[i-1];
            }
            if (sum[i] < 0) {
                prev[i] = 0;
            } else {
                prev[i] = 1;
            }

            if (max < sum[i]) {
                max = sum[i];
            }
        }

        if (max > 0)
            return max;

        return 0;
        */

        /*
        int max = Integer.MIN_VALUE;
        int[] sum = new int[prices.length];

        for (int i=1;i<prices.length;i++) {
            int diff = prices[i] - prices[i-1];
            if (sum[i-1] <= 0) {
                sum[i] = diff;
            } else {
                sum[i] = diff + sum[i-1];
            }

            if (max < sum[i]) {
                max = sum[i];
            }
        }

        if (max > 0)
            return max;

        return 0;
        */

        /*
        int max = Integer.MIN_VALUE;
        int prev = 0;

        for (int i=1;i<prices.length;i++) {
            int diff = prices[i] - prices[i-1];
            if (prev <= 0) {
                prev = diff;
            } else {
                prev= diff + prev;
            }

            if (max < prev) {
                max = prev;
            }
        }

        if (max > 0)
            return max;

        return 0;
        */

        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int i=0;i<prices.length;i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if ((prices[i] - minPrice) > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }
}