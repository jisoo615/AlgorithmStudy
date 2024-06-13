package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
입력
2
3 4
1 3 3 2 2 1 4 1 0 6 4 7
4 4
1 3 1 5 2 2 4 1 5 0 2 3 0 6 1 2
출력
19
16
 */
public class P금광 {
    static int[][] dp;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            arr = new int[n][m];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new int[n][m];
            for (int i = 0; i < n; i++) {
                dp[i][0] = arr[i][0];
            }
// dp[i][j] = arr[i][j] + Math.max(dp[i][y+1], dp[i-1][y+1], dp[i+1][y+1]) i,j까지 갔을때 가장 많이 얻을 수 있는 금광값
            for (int j = 1; j < m; j++) {
                for (int i = 0; i < n; i++) {
                    if (i == 0) dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j - 1]) + arr[i][j];
                    else if (i == n - 1) dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - 1]) + arr[i][j];
                    else dp[i][j] = Math.max(Math.max(dp[i][j - 1], dp[i + 1][j - 1]), dp[i - 1][j - 1]) + arr[i][j];
                }
            }
            int answer = 0;
            for (int i = 0; i < n; i++) {
                answer = Math.max(answer, dp[i][m - 1]);
            }
            System.out.println(answer);

        }
    }
}
