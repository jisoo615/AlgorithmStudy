package ch19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16236 {
    public static int[] dx = {-1, 0, 0, 1};
    public static int[] dy = {0, -1, 1, 0};
    public static PriorityQueue<Fish> pq;
    public static int result = 0, N;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        Fish shark = null;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    shark = new Fish(i, j, 0, 0);
                    map[i][j] = 0;
                }
            }
        }

        pq = new PriorityQueue<>((f1, f2)->{
            if(f1.dist == f2.dist){
                if(f1.x==f2.x){
                    return f1.y - f2.y;
                }
                return f1.x - f2.x;
            }
            return f1.dist - f2.dist;
        });
        int sharkSize = 2;
        int eaten = 0;
        while (true){
            pq.clear();
            bfs(sharkSize, shark);
            Fish eatable = pq.poll();
            if(eatable == null) break;
            eaten += 1;
            map[eatable.x][eatable.y] = 0;
            if(eaten == sharkSize){
                eaten = 0;
                sharkSize++;
            }
            shark = new Fish(eatable.x, eatable.y, 0, 0);
            result += eatable.dist;
        }

        System.out.println(result);
    }

    public static void bfs(int sharkSize, Fish shark){
        // 먹을 수 있는 가장 가까운 곳 찾기(sharksize보다 작은 것 찾기)
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new int[] {shark.x, shark.y, 0});
        visited[shark.x][shark.y] = true;
        while (!q.isEmpty()){
            int[] tmp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];
                if(nx>=N || nx<0 || ny>=N || ny<0 || visited[nx][ny]
                    || map[nx][ny] > sharkSize) continue;
                visited[nx][ny] = true;
                if(map[nx][ny] == sharkSize || map[nx][ny] == 0){
                    q.add(new int[] {nx, ny, tmp[2]+1});
                    continue;
                }
                pq.add(new Fish(nx, ny, map[nx][ny], tmp[2]+1));
            }
        }
        return;
    }

    static class Fish{
        int x, y, num, dist;
        public Fish(int x, int y, int num, int dist){
            this.x=x; this.y=y; this.num=num; this.dist=dist;
        }
    }
}
