package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P11404 {
    public static void main(String[] args) throws IOException {
        int INF = 100000*(100-1)+1; // 비용은 최대 100000 * n100
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String[] arr;

        // map 초기화
        int[][] map = new int[n+1][n+1];
        for(int[] tmp : map) Arrays.fill(tmp, INF);
        for (int i = 1; i < n + 1; i++) map[i][i] = 0;

        for (int i = 0; i < m; i++) {
            arr = br.readLine().split(" ");
            int v1 = Integer.parseInt(arr[0]);
            int v2 = Integer.parseInt(arr[1]);
            int dist = Integer.parseInt(arr[2]);
            map[v1][v2] = Math.min(map[v1][v2], dist);
        }

        // 플루이드워셜 모든 정점끼리의 최소거리
        for (int k = 1; k < n+1; k++) {// 경유
            for (int a = 1; a < n+1; a++) {// 출발
                for (int b = 1; b < n+1; b++) {// 도착
                    map[a][b] = Math.min(map[a][b], map[a][k]+map[k][b]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                int d = map[i][j];
                if(d==INF) sb.append("0 ");
                else sb.append(d+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
