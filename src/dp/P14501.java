package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 퇴사
public class P14501 {
    static int n;
    static int[] t, p;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        t = new int[n+1];
        p = new int[n+1];
        dp = new int[n+2];
        for (int i = 1; i <= n; i++) {
            String[] line = br.readLine().split(" ");
            t[i] = Integer.parseInt(line[0]);
            p[i] = Integer.parseInt(line[1]);
        }
        // dp[i] = i부터 퇴사날까지의 이익
        for (int i = n; i > 0; i--) {
            int nextDay = i + t[i];// i번째 일을 선택했을때 nextDay이후의 상담 선택가능
            if(nextDay > n+1) dp[i] = dp[i+1];// 퇴사날 이후에 끝나면 선택안함(기존것 선택)
            else {
                dp[i] = Math.max(dp[i+1], dp[nextDay]+p[i]);// i를 선택 안한 경우, i를 선택한 경우
            }
        }

        System.out.println(dp[1]);
    }
}

