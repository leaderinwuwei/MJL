package ds.map;

import java.util.HashMap;
import java.util.Map;

public class LHS{
    public static int findLHS(int[] nums) {
        Map<Integer, Integer> countForNum = new HashMap<>();
        for (int num : nums) {
            countForNum.put(num, countForNum.getOrDefault(num, 0) + 1);
        }
        int longest = 0;
        for (int num : countForNum.keySet()) {
            if (countForNum.containsKey(num + 1)) {
                longest = Math.max(longest, countForNum.get(num + 1) + countForNum.get(num));
            }
        }
        return longest;
    }


    public static void main(String[] args) {
        System.out.println(findLHS(new int[]{1,3,2,2,5,2,3,7}));
    }
}