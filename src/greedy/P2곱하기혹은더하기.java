package greedy;

import java.util.Scanner;

public class P2곱하기혹은더하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int answer = s.charAt(0)-48;

        for (int i = 1; i < s.length(); i++) {
            int num = s.charAt(i) - 48;

            if(answer==0) answer += num;
            else if(num==0) answer += num;
            else answer *= num;
        }

        System.out.println(answer);
    }
}
