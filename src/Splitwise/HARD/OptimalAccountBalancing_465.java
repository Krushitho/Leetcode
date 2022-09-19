package Splitwise.HARD;

import java.util.HashMap;
import java.util.Map;

/**
 *  You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.
 *
 * Return the minimum number of transactions required to settle the debt.
 *
 *
 *
 * Example 1:
 *
 * Input: transactions = [[0,1,10],[2,0,5]]
 * Output: 2
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 *
 * Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * Output: 1
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 *
 * solution:
 * Hash Table + Backtracking
 *
 * Try all possible cases of solving debt and find out which one yields small no of txns.
 *
 * O(N!) -> TC
 * O(M+N)
 *
 */
public class OptimalAccountBalancing_465 {
    public static void main(String[] args) {
        int[][] transactions = {{0,1,10},{2,0,5}};
        System.out.println(minTransfers(transactions));
    }
    public static int minTransfers(int[][] transactions) {
       int[] balances = gteBalances(transactions);
        //Enumerate all possible combinations to settle the debt
       return settleBalances(0,balances);
    }

    private static int settleBalances(int curID, int[] balances) {
        // Skip settled debt
        while(curID < balances.length && balances[curID] == 0) {
            curID++;
        }
        if(curID == balances.length) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i=curID+1;i<balances.length;i++) {
            // Only settle debt when the debts[i] has different sign with debts[index]
            if(balances[i] * balances[curID] < 0) {
                balances[i] += balances[curID];
                min = Math.min(min, settleBalances(curID+1, balances));
                balances[i] -= balances[curID];
            }
        }

        return min;
    }

    private static int[] gteBalances(int[][] transactions) {
        Map<Integer,Integer> balances = new HashMap<>();
        for(int[] transaction:transactions) {
            int giver =  transaction[0];
            int taker =  transaction[1];
            int amount =  transaction[2];

            balances.put(giver, balances.getOrDefault(giver, 0)+amount);
            balances.put(taker, balances.getOrDefault(taker, 0)-amount);
        }

        int[] arr = new int[balances.size()];
        int index = 0;

        for(int val:balances.values()) {
            arr[index++] = val;
        }

        return arr;
    }
}
