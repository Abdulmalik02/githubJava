public class RecursiveCatalanNumbers {

    static int count = 20;
    static long[] resultArr = new long[20]; // It is used to prevent calculating values more than once

    public static void main(String[] args) {
        System.out.println(totalTree(count));
    }

    public static long totalTree(int n) {
        if (n < 2) // It is stop condition for recursion
            return 1;
        else {
            long leftLeaf = 0;
            long rightLeaf = 0;
            long sum = 0;
            for (int i = 1; i <= n; i++) {
                if (resultArr[i - 1] == 0) {
                    resultArr[i - 1] = totalTree(i - 1);
                }
                leftLeaf = resultArr[i - 1];
                if (resultArr[n - i] == 0) {
                    resultArr[n - i] = totalTree(n - i);
                }
                rightLeaf = resultArr[n - i];
                sum = sum + (leftLeaf * rightLeaf);
            }
            return sum;
        }
    }
}