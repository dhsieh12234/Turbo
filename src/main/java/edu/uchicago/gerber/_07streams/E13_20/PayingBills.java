package edu.uchicago.gerber._07streams.E13_20;

import java.util.*;

public class PayingBills {
    public static List<List<Integer>> ListAllPrices(int n) {
        List<List<Integer>> res = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        int[] bills = {1, 5, 20, 100};

        helper(new ArrayList<>(), 0, n, res, bills, 0);
        return res;
    }

    public static void helper(List<Integer> lst, int total, int n, List<List<Integer>> res, int[] bills, int startIndex) {
        if (total == n) {
            res.add(new ArrayList<>(lst));
            return;
        }

        if (total > n) {
            return;
        }

        for (int i = startIndex; i < bills.length; i++) {
            lst.add(bills[i]);
            helper(lst, total + bills[i], n, res, bills, i);
            lst.remove(lst.size() - 1);
        }
    }
}
