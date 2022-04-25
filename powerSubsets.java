package math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IntegerSubsets {
 private static int getBit(int num, int bit) {
        int temp = (1 << bit);
        temp = temp & num;
        if (temp == 0) {
            return 0;
        }
        return 1;
    }

    private static void getAllSubsets(List<Integer> v,
                                      List<HashSet<Integer>> sets) {

        int subsets_count = (int) (Math.pow((double) 2, (double) v.size()));
        for (int i = 0; i < subsets_count; ++i) {
            HashSet<Integer> set = new HashSet<Integer>();
            for (int j = 0; j < v.size(); ++j) {
                if (getBit(i, j) == 1) {
                    int x = v.get(j);
                    set.add(x);
                }
            }
            sets.add(set);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{8, 13, 3, 22, 17, 39, 87, 45, 36};
        List<Integer> v = new ArrayList<>();
        for (Integer i : arr) {
            v.add(i);
        }

        List<HashSet<Integer>> subsets = new ArrayList<>();
        getAllSubsets(v, subsets);

        for (int i = 0; i < subsets.size(); ++i) {
            System.out.print("{");
            for (Integer it : subsets.get(i)) {
                System.out.print(it + ", ");
            }
            System.out.println("}");
        }
    }
}