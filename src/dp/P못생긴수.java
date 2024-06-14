package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P못생긴수 {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[1001];
        dp[1] = dp[2] = dp[3] = dp[5] = 1;
        dp[7] = -1;

        int cnt = 0;
        for (int i = 1; i <= 1000; i++) {
            dp[i] = recur(i);
            if(dp[i]==1) cnt++;
            if(cnt==n){
                System.out.println(i);
                break;
            }
        }
    }
    static public int recur(int x){
        if(dp[x]==1) return 1;

        if(x%2==0) return recur(x/2);
        else if(x%3==0) return recur(x/3);
        else if(x%5==0) return recur(x/5);

        return -1;
    }
}
