package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1238파티 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()); // 파티 장소

        // 방향 가중치, 그래프 및 역방향 그래프 초기화
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        ArrayList<ArrayList<Edge>> reverseGraph = new ArrayList<>();
        for (int i = 0; i <= N; i++) { // 0~N까지 포함
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph.get(n).add(new Edge(m, t));
            reverseGraph.get(m).add(new Edge(n, t)); // 역방향 그래프
        }

        // X에서 모든 노드로 가는 최단 거리
        int[] distFromX = dijkstra(X, reverseGraph);
        // 모든 노드에서 X로 가는 최단 거리
        int[] distToX = dijkstra(X, graph);

        // 각 노드의 왕복 거리 계산
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (distFromX[i] == Integer.MAX_VALUE || distToX[i] == Integer.MAX_VALUE) continue; // 도달 불가능한 경우
            answer = Math.max(answer, distFromX[i] + distToX[i]);
        }

        System.out.println(answer);
    }

    public static int[] dijkstra(int start, ArrayList<ArrayList<Edge>> graph) {
        int[] distance = new int[graph.size()];
        Arrays.fill(distance, Integer.MAX_VALUE); // 초기값 설정
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
        pq.offer(new Edge(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (current.dist > distance[current.index]) continue; // 이미 방문한 노드 스킵

            for (Edge next : graph.get(current.index)) {
                int newDist = current.dist + next.dist;
                if (newDist < distance[next.index]) {
                    distance[next.index] = newDist;
                    pq.offer(new Edge(next.index, newDist));
                }
            }
        }
        return distance;
    }

    public static class Edge {
        int index, dist;

        public Edge(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }
}
