package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 병사 배치하기 LIS 가장긴증가하는부분수열
public class P18353 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N]; // dp[i] = i 번째를 포함하는 가장 긴 내림차순 길이
        Arrays.fill(dp, 1);
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        for (int i = 1; i < N; i++) {// dp[0] 은 무조건 1이니까 dp[1]부터 시작
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[i]) dp[i] = Math.max(dp[j]+1, dp[i]);
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(N-max);
    }
}
