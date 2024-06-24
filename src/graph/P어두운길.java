package graph;

import java.awt.image.ImagingOpException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P어두운길 {
    // (최소신장트리) 크루스칼알고리즘으로 차례로 최소간선을 선택 + 서클되지 않도록 union-find 사용
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parents = new int[N];
        for (int i = 0; i < N; i++) parents[i] = i;

        int total = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>((n1, n2)->{return n1.cost-n2.cost;});
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Node(x, y, cost));
            total += cost;
        }

        int min = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int a = find(node.x);
            int b = find(node.y);
            if(a!=b){
                union(a, b);
                min += node.cost;
            }
        }
        System.out.println(total-min);
    }
    static class Node{
        int x, y, cost;
        public Node(int x, int y, int cost){
            this.x=x; this.y=y; this.cost=cost;
        }
    }
    static public int find(int x){
        if(parents[x]!=x){
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }
    static public void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b) parents[b] = a;
        else parents[a] = b;
    }
}
