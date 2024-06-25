package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// 행성터널
public class P2887 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        parents = new int[N];
        Planet[] X = new Planet[N];// 세 지점xyz 중 제일 차이가 적은 값이 최소거리로 채택됨, 중복은 union-find로 거르기
        Planet[] Y = new Planet[N];
        Planet[] Z = new Planet[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            parents[i] = i;
            X[i] = new Planet(i, Integer.parseInt(st.nextToken()));
            Y[i] = new Planet(i, Integer.parseInt(st.nextToken()));
            Z[i] = new Planet(i, Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(X);
        Arrays.sort(Y);
        Arrays.sort(Z);
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < N-1; i++) {
            edgeList.add(new Edge(X[i].vertax, X[i+1].vertax, Math.abs(X[i].coord - X[i+1].coord)));
            edgeList.add(new Edge(Y[i].vertax, Y[i+1].vertax, Math.abs(Y[i].coord - Y[i+1].coord)));
            edgeList.add(new Edge(Z[i].vertax, Z[i+1].vertax, Math.abs(Z[i].coord - Z[i+1].coord)));
        }

        Collections.sort(edgeList);
        int sum = 0;
        int cnt = 0;
        for(Edge egde : edgeList){
            if(cnt == N-1) break;// 간선 다찾으면 종료
            if(find(egde.v1) != find(egde.v2)){
                union(egde.v1, egde.v2);
                sum += egde.cost;
                cnt++;
            }
        }

        System.out.println(sum);
    }
    static class Edge implements Comparable<Edge> {
        int v1, v2, cost;
        public Edge(int v1, int v2, int cost) {
            this.v1 = v1; this.v2 = v2; this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    static class Planet implements Comparable<Planet> {
        int vertax, coord;
        public Planet(int vertax, int dist){
            this.vertax = vertax; this.coord = dist;
        }
        @Override
        public int compareTo(Planet o) {
            return this.coord - o.coord;
        }
    }
    static public int find(int x){
        if(parents[x] == x){
            return x;
        }
        return parents[x] = find(parents[x]);
    }
    static public void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a < b) parents[b] = a;
        else parents[a] = b;
    }
}
