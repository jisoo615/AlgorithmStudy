package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// dp로 하면 T2가 20나와서 틀림, 다익스트라
public class P39화성탐사 {
    public static void main(String[] args) throws IOException {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int INF = 125*9+1;
        StringBuilder sb = new StringBuilder();

        while (T-->0){
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int[][] distance = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] arr = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(arr[j]);
                }
            }
            // 거리테이블 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    distance[i][j] = INF;
                }
            }
            // 다익스트라
            PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2)->{
                return p1[2] - p2[2];
            });
            pq.add(new int[] {0, 0, map[0][0]});
            while (!pq.isEmpty()){
                int[] point = pq.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = point[0] + dx[i];
                    int ny = point[1] + dy[i];
                    int cost = point[2];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if(distance[nx][ny] < cost + map[nx][ny]) continue;
                    distance[nx][ny] = cost + map[nx][ny];
                    pq.add(new int[] {nx, ny, cost + map[nx][ny]});
                }
            }
            sb.append(distance[N-1][N-1]).append("\n");
        }
        System.out.println(sb);
    }
}
