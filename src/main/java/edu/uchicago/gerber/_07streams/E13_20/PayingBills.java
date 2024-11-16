package edu.uchicago.gerber._07streams.E13_20;

import java.util.*;

public class PayingBills {
    public static List<List<Integer>> ListAllPrices(int n) {
        List<List<Integer>> res = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        int[] bills = {1, 5, 20, 100};

        helper(new ArrayList<>(), 0, n, res, seen, bills);
        return res;
    }

    public static void helper(List<Integer> lst, int total, int n, List<List<Integer>> res, Set<String> seen, int[] bills) {
        if (total == n) {
            // Sort the list to ensure unique combinations regardless of order
            List<Integer> sortedList = new ArrayList<>(lst);
            Collections.sort(sortedList);
            String lstStr = sortedList.toString();

            // Only add the combination if it has not been seen
            if (!seen.contains(lstStr)) {
                seen.add(lstStr);
                res.add(sortedList);  // Add the sorted combination to the result
            }
            return;
        }
        if (total > n) {
            return;
        }

        for (int bill : bills) {
            List<Integer> newLst = new ArrayList<>(lst);
            newLst.add(bill);
            helper(newLst, total + bill, n, res, seen, bills);
        }
    }
}
