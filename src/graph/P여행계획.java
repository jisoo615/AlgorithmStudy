package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P여행계획 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N+1][N+1];
        for (int i = 0; i < N; i++) {// 양방향 그래프
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                graph[i+1][j+1] = Integer.parseInt(st.nextToken());
            }
        }

        // 여행경로
        st = new StringTokenizer(br.readLine(), " ");
        int[] route = new int[M];
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }
        String answer = "";
        for (int i = 0; i < M-1; i++) {
            int start = route[i];
            int end = route[i+1];
            answer = "NO";
            if(graph[start][end] == 1){
                answer = "YES"; break;
            }
            if(answer.equals("NO")) break;
        }

        System.out.println(answer);
    }

}
