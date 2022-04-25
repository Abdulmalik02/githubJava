package com.TimBuchalka;
public class PerfectNumber {
    public static boolean isPerfectNumber (int number) {
        int count =0;

        if(number > 0) {
            for (int i = 1; i < number; i++) {
                if (number % i == 0) {
                    count = count + i;
                }
            }
        }

        if(count > 1) {
            System.out.println(count);
        }

        return false;
    }
}