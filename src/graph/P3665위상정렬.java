package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P3665위상정렬 {
    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] graph = new int[N+1][N+1];
            int[] indegree = new int[N+1];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] oldRank = new int[N];
            for (int j = 0; j < N; j++) {
                oldRank[j] = Integer.parseInt(st.nextToken());
            }
            // 모든 그래프 간선을 생성 : 1위는 2,3,4,5위보다 위 / 2위는 3,4,5위보다 위 / 3위는 4,5위보다 위 / ...
            for (int j = 0; j < N-1; j++) {
                for (int k = j+1; k < N; k++) {
                    int from = oldRank[j];
                    int to = oldRank[k];
                    graph[from][to] = 1;
                    indegree[to] += 1;
                }
            }

            // 바뀐 간선 정보로 변경시키기
            int m = Integer.parseInt(br.readLine());
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(graph[a][b]==1){
                    graph[b][a] = 1;
                    graph[a][b] = 0;
                    indegree[b] -= 1;
                    indegree[a] += 1;
                }else {
                    graph[b][a] = 0;
                    graph[a][b] = 1;
                    indegree[a] -= 1;
                    indegree[b] += 1;
                }
            }

            // 순서 구하기
            boolean certain = true;
            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            Queue<Integer> q = new LinkedList<>();
            for (int j = 1; j < N+1; j++) {
                if(indegree[j] == 0) q.add(j);
            }
            while(!q.isEmpty()){
                if(q.size()>1){
                    certain = false;
                    break;
                }

                int v = q.poll();
                sb.append(v+" ");
                cnt++;

                for (int j = 1; j < N+1; j++) {
                    if(graph[v][j]==1){
                        indegree[j] -= 1;
                        if(indegree[j]==0) q.add(j);
                    }
                }
            }

            if(!certain) result.append("?\n");// 순서가 불명확할때 ex) 공동순위
            else if(cnt!=N) result.append("IMPOSSIBLE\n");// 순위를 정할 수 없는 경우 ex) cycle
            else result.append(sb+"\n");
        }
        System.out.println(result);
    }
}
