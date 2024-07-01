package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P40숨바꼭질 {
    public static void main(String[] args) throws IOException {
        int INF = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        boolean[][] graph = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++) {
            str = br.readLine().split(" ");
            int v = Integer.parseInt(str[0]);
            int u = Integer.parseInt(str[1]);
            graph[v][u] = true;
            graph[u][v] = true;
        }
        int[] distance = new int[N+1];
        for (int i = 2; i < N + 1; i++) {
            distance[i] = INF;
        }

        // 1에서 모든 노드까지의 거리 구하기 : 다익스트라
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2)->{
            return n1[1] - n2[1];
        });
        pq.add(new int[] {1, 0});
        while(!pq.isEmpty()){
            int[] p = pq.poll();
            for (int i = 1; i < N+1; i++) {
                if( !graph[p[0]][i] ) continue;
                if(distance[i] > p[1]+1){
                    pq.add(new int[] {i, p[1]+1});
                    distance[i] = p[1]+1;
                }
            }
        }

        int cnt=0, maxNode = 0;
        for (int i = 2; i < N+1; i++) {
            if(distance[i]==INF) continue;
            if(distance[i] > distance[maxNode]){// 갱신
                maxNode = i;
                cnt = 1;
            }
            else if(distance[i] == distance[maxNode]) cnt++;
        }
        System.out.println(maxNode+" "+distance[maxNode]+" "+cnt);
    }

}
