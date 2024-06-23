package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P탑승구 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        for (int i = 1; i < G+1; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < P; i++) {
            int number = Integer.parseInt(br.readLine());// number이하의 gate번호에 들어갈 수 있도록 -> 최대한 큰 gate부터 채우기

            int root = find_parent(number);
            if(root==0) break;// root가 0이면 다 찼다는 의미
            union_parent(root, root-1);
            answer++;
        }

        System.out.println(answer);
    }

    static public int find_parent(int x){
        if(parent[x]!=x){
            parent[x] = find_parent(parent[x]);
        }
        return parent[x];
    }
    static public void union_parent(int a, int b){
        a = find_parent(a);
        b = find_parent(b);
        if(a < b) parent[b] = a;// 더 작은게 루트
        else parent[a] = b;
    }
}
