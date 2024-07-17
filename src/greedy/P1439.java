package greedy;

import java.util.Scanner;
import java.util.StringTokenizer;

public class P1439 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        // 1 부분과 0부분 중 더 적은 만큼을 뒤집으면 됨
        StringTokenizer st0 = new StringTokenizer(S, "0");
        StringTokenizer st1 = new StringTokenizer(S, "1");
        // split()으로 하면 st0 = {"", "", "", "11", "", ""} 이런식으로 빈문자열이 생김
        System.out.println(Math.min( st0.countTokens(), st1.countTokens() ));
    }
}
