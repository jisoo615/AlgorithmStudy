package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P38정확한순위 {
    // 플로이드 워셜로 순위 알아내기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n+1][n+1];
        int INF = 500 * 100000;
        for(int[] tmp : dp) Arrays.fill(tmp, INF);
        for (int i = 1; i < n+1; i++) dp[i][i] = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            dp[A][B] = 1; // A<B
        }
        // 플루이드
        for (int mid = 1; mid < n+1; mid++) {
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid]+dp[mid][j]);
                }
            }
        }

        int result = 0;
        for (int i = 1; i < n + 1; i++) {
            int cnt = 0;
            for (int j = 1; j < n+1; j++) {
                if(dp[i][j]!=INF || dp[j][i]!=INF) cnt++;// 선입선출 아는 노드가 n개 있으면 순위를 알 수 있음
            }
            if(cnt==n) result += 1;
        }

        System.out.println(result);
    }
}
