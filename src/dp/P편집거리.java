package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 문자열 유사도
public class P편집거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();

        int[][] dp = new int[B.length()+1][A.length()+1]; // dp[i][j] = 문자열 A의 j번쨰와 B의 i번째의 최소 거리
        for (int i = 0; i < A.length()+1; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < B.length()+1; i++) {
            dp[i][0] = i;
        }
        // 초기값 - 아무것도 없음과의 거리
        int cost = 0;
        for (int i = 1; i < B.length()+1; i++) {
            for (int j = 1; j < A.length()+1; j++) {
                cost = 0;
                if(A.charAt(j-1) != B.charAt(i-1)) cost = 1;
                dp[i][j] = Math.min( Math.min( dp[i][j-1]+1, dp[i-1][j]+1 ), dp[i-1][j-1]+cost );// 왼쪽:add, 위쪽:delete, 왼쪽위(대각선):replace
            }
        }

        System.out.println(dp[B.length()][A.length()]);
    }
}
