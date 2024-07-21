package impl;

import java.util.Scanner;

public class P18406 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        String str = Integer.toString(n);
        int mid_index = str.length() / 2;
        int left = 0, right = 0;
        for (int i = 0; i < mid_index; i++) {
            left += str.charAt(i) - 48;
        }
        for (int i = mid_index; i < str.length(); i++) {
            right += str.charAt(i) - 48;
        }

        if(left==right) System.out.println("LUCKY");
        else System.out.println("READY");
    }
}
