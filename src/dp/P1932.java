package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 밑에서 올라오면 맨 오른쪽일떄 또 조건 안걸어도됨
        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j <=i ; j++) {
                arr[i][j] += Math.max(arr[i+1][j], arr[i+1][j+1]);
            }
        }
        System.out.println(arr[0][0]);
    }
}
